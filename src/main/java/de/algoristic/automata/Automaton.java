package de.algoristic.automata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evolution.ElementaryRule;
import de.algoristic.automata.evolution.GameOfLifeRule;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.Rules;
import de.algoristic.automata.evolution.Transition;
import de.algoristic.automata.evt.AutomatonEventListener;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.evt.RegisteredEvents;
import de.algoristic.automata.evt.StartAutomationEvent;
import de.algoristic.automata.evt.StartBreedingEvent;
import de.algoristic.automata.io.Seed;

public class Automaton {

  private final Rule rule;
  private final int runtime;

  private final List<Generation> generations = new ArrayList<>();
  private final RegisteredEvents events = new RegisteredEvents();

  private Automaton(final Generation initialGeneration, Rule rule, int runtime) {
    this.rule = rule;
    this.runtime = runtime;
    this.generations.add(initialGeneration);
  }

  public String run() {
    events.throwStartAutomationEvent(new StartAutomationEvent(rule));
    for (int i = 0; i < runtime; i++) {
      int lastGeneration = (generations.size() - 1);
      Generation parentalGeneration = generations.get(lastGeneration);
      events.throwStartBreedingEvent(new StartBreedingEvent(rule, parentalGeneration));
      Transition transition = new Transition(parentalGeneration, rule);
      Generation filialGeneration = transition.produceFilialGeneration();
      events.throwFinishBreedingEvent(new FinishBreedingEvent(i, rule, parentalGeneration, filialGeneration));
      generations.add(filialGeneration);
    }
    events.throwFinishAutomationEvent(new FinishAutomationEvent(this, generations));
    int lastGeneration = (generations.size() - 1);
    Generation result = generations.get(lastGeneration);
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
    protected Generation generation;
    protected int runtime = 0;
    
    protected Builder(Rule rule) {
      this.rule = rule;
    }
    
    public static WolframsUniverseBuilder wolframsUniverse(int decimalRule) {
      Rule rule = ElementaryRule.getInstance(decimalRule);
      return new WolframsUniverseBuilder(rule);
    }

    public static WolframsUniverseBuilder wolframsUniverse(ElementaryRule rule) {
      return new WolframsUniverseBuilder(rule);
    }

    public static GameOfLifeBuilder gameOfLife() {
      return gameOfLifeBuilder(Rules.CONWAYS_LIFE);
    }

    public static GameOfLifeBuilder gameOfLifeBuilder(Rules rule) {
      return new GameOfLifeBuilder(rule.get());
    }

    public static GameOfLifeBuilder gameOfLife(String ruleString) {
      Rule rule = GameOfLifeRule.getInstance(ruleString);
      return new GameOfLifeBuilder(rule);
    }

    public static GameOfLifeBuilder gameOfLife(GameOfLifeRule rule) {
      return new GameOfLifeBuilder(rule);
    }

    public Builder withRuntime(int runtime) {
      this.runtime = runtime;
      return this;
    }

    public Automaton build() {
      return new Automaton(generation, rule, runtime);
    }
  }

  public static class WolframsUniverseBuilder extends Builder {

    protected WolframsUniverseBuilder(Rule rule) {
      super(rule);
    }

    public Builder chaotic(int range) {
      Random random = new Random();
      StringBuffer buffer = new StringBuffer();
      IntStream.range(0, range).forEach(_i -> {
        if(random.nextBoolean()) {
          buffer.append('1');
        } else {
          buffer.append('0');
        }
      });
      String seed = buffer.toString();
      generation = Generation.getGeneration(seed);
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
      generation = Generation.getGeneration(seed);
      return this;
    }
  }

  public static class GameOfLifeBuilder extends Builder {

    protected GameOfLifeBuilder(Rule rule) {
      super(rule);
    }

    public GameOfLifeBuilder withUnlimitedSpace(boolean unlimitedSpace) {
      ((GameOfLifeRule) rule).setSpaceUnlimited(unlimitedSpace);
      return this;
    }

    public Builder withSeed(Seed seed) {
      String content = seed.getContent();
      int verticalSpace = seed.getVerticalDimension();
      generation = Generation.getGeneration(content, verticalSpace);
      return this;
    }      
  }
}
