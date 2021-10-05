package de.algoristic.automata;

import java.util.List;
import java.util.stream.IntStream;
import de.algoristic.automata.core.BinaryCellSupplier;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.TurmitesCellSupplier;
import de.algoristic.automata.core.WireworldCellSupplier;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.Transition;
import de.algoristic.automata.evolution.gameoflife.GameOfLife;
import de.algoristic.automata.evolution.gameoflife.Rules;
import de.algoristic.automata.evolution.turmites.Turmites;
import de.algoristic.automata.evolution.turmites.TurmitesRuleMetadata;
import de.algoristic.automata.evolution.wireworld.Wireworld;
import de.algoristic.automata.evolution.wolframsuniverse.ElementaryCellularAutomaton;
import de.algoristic.automata.evt.AutomatonEventListener;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.evt.RegisteredEvents;
import de.algoristic.automata.evt.StartAutomationEvent;
import de.algoristic.automata.evt.StartBreedingEvent;
import de.algoristic.automata.io.RandomSeed;
import de.algoristic.automata.io.Seed;
import de.algoristic.automata.io.StringSeed;

public class Automaton {

  private final Rule rule;
  private final int runtime;

  private final Memory memory;
  private final RegisteredEvents events = new RegisteredEvents();

  private Automaton(final Generation initialGeneration, Rule rule, int runtime, boolean fullMemory) {
    this.rule = rule;
    this.runtime = runtime;
    this.memory = fullMemory ? new FullMemory(initialGeneration) : new PartialMemory(initialGeneration);
  }

  public String run() {
    events.throwStartAutomationEvent(new StartAutomationEvent(rule));
    for (int i = 0; i < runtime; i++) {
      Generation parentalGeneration = memory.getLastGeneration();
      events.throwStartBreedingEvent(new StartBreedingEvent(rule, parentalGeneration));
      Transition transition = new Transition(parentalGeneration, rule);
      Generation filialGeneration = transition.produceFilialGeneration();
      events.throwFinishBreedingEvent(new FinishBreedingEvent(i, rule, parentalGeneration, filialGeneration));
      memory.add(filialGeneration);
    }
    List<Generation> generations = memory.getSavedGenerations();
    events.throwFinishAutomationEvent(new FinishAutomationEvent(this, generations));
    Generation result = memory.getLastGeneration();
    return result.toBinaryString();
  }

  public Rule getRule() {
    return rule;
  }

  public void registerStartAutomationListener(AutomatonEventListener<StartAutomationEvent> listener) {
    events.registerStartAutomationListener(listener);
  }

  public void registerFinishAutomationListener(AutomatonEventListener<FinishAutomationEvent> listener) {
    events.registerFinishAutomationListener(listener);
  }

  public void registerStartBreedingListener(AutomatonEventListener<StartBreedingEvent> listener) {
    events.registerStartBreedingListener(listener);
  }

  public void registerFinishBreedingListener(AutomatonEventListener<FinishBreedingEvent> listener) {
    events.registerFinishBreedingListener(listener);
  }

  public abstract static class Builder {
    
    protected final Rule rule;
    protected final boolean fullMemory;
    protected Generation generation;
    protected int runtime = 0;
    
    protected Builder(Rule rule, boolean fullMemory) {
      this.rule = rule;
      this.fullMemory = fullMemory;
    }
    
    public static WolframsUniverseBuilder wolframsUniverse(int decimalRule) {
      Rule rule = ElementaryCellularAutomaton.getInstance(decimalRule);
      return new WolframsUniverseBuilder(rule);
    }

    public static WolframsUniverseBuilder wolframsUniverse(ElementaryCellularAutomaton rule) {
      return new WolframsUniverseBuilder(rule);
    }

    public static GameOfLifeBuilder gameOfLife() {
      return gameOfLifeBuilder(Rules.CONWAYS_LIFE);
    }

    public static GameOfLifeBuilder gameOfLifeBuilder(Rules rule) {
      return new GameOfLifeBuilder(rule.get());
    }

    public static GameOfLifeBuilder gameOfLife(String ruleString) {
      Rule rule = GameOfLife.getInstance(ruleString);
      return new GameOfLifeBuilder(rule);
    }

    public static GameOfLifeBuilder gameOfLife(GameOfLife rule) {
      return new GameOfLifeBuilder(rule);
    }

    public static WireworldBuilder wireworld() {
      return new WireworldBuilder();
    }

    public static TurmitesBuilder turmites(Turmites turmites) {
      return new TurmitesBuilder(turmites);
    }

    public static TurmitesBuilder turmites(String ruleString) {
      Turmites turmites = Turmites.getInstance(ruleString);
      return new TurmitesBuilder(turmites);
    }

    public Builder withRuntime(int runtime) {
      this.runtime = runtime;
      return this;
    }

    public Automaton build() {
      return new Automaton(generation, rule, runtime, fullMemory);
    }
  }

  public static class WolframsUniverseBuilder extends Builder {

    protected WolframsUniverseBuilder(Rule rule) {
      super(rule, true);
    }

    public Builder chaotic(int range) {
      Seed seed = new RandomSeed(range, 1);
      generation = Generation.getGeneration(seed, new BinaryCellSupplier());
      return this;
    }

    public Builder simple(int range) {
      StringBuffer buffer = new StringBuffer();
      IntStream.range(0, range).forEach(i -> {
        if(i == (range / 2)) {
          buffer.append('1');
        } else {
          buffer.append('0');
        }
      });
      String seed = buffer.toString();
      generation = Generation.getGeneration(new StringSeed(seed, 1), new BinaryCellSupplier());
      return this;
    }
  }

  public static class GameOfLifeBuilder extends Builder {

    protected GameOfLifeBuilder(Rule rule) {
      super(rule, false);
    }

    public GameOfLifeBuilder withUnlimitedSpace(boolean unlimitedSpace) {
      ((GameOfLife) rule).setSpaceUnlimited(unlimitedSpace);
      return this;
    }

    public Builder withSeed(Seed seed) {
      generation = Generation.getGeneration(seed, new BinaryCellSupplier());
      return this;
    }      
  }

  public static class WireworldBuilder extends Builder {

    protected WireworldBuilder() {
      super(new Wireworld(), false);
    }

    public WireworldBuilder withUnlimitedSpace(boolean unlimitedSpace) {
      ((Wireworld) rule).setSpaceUnlimited(unlimitedSpace);
      return this;
    }

    public Builder withSeed(Seed seed) {
      generation = Generation.getGeneration(seed, new WireworldCellSupplier());
      return this;
    }
  }

  public static class TurmitesBuilder extends Builder {

    private final TurmitesRuleMetadata metadata;

    protected TurmitesBuilder(Turmites turmites) {
      super(turmites, false);
      metadata = turmites.getMetadata();
    }

    public TurmitesBuilder withUnlimitedSpace(boolean unlimitedSpace) {
      ((Turmites) rule).setSpaceUnlimited(unlimitedSpace);
      return this;
    }

    public Builder withSeed(Seed seed) {
      generation = Generation.getGeneration(seed, new TurmitesCellSupplier(metadata));
      return this;
    }
  }
}
