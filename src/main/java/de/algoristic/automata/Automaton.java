package de.algoristic.automata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.Transition;
import de.algoristic.automata.evt.AutomatonEventListener;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.evt.RegisteredEvents;
import de.algoristic.automata.evt.StartAutomationEvent;
import de.algoristic.automata.evt.StartBreedingEvent;

public class Automaton {

  private Rule rule;
  private int runtime;

  private List<Generation> generations = new ArrayList<>();
  private RegisteredEvents events = new RegisteredEvents();

  private Automaton(final Generation initialGeneration, Rule rule, int runtime) {
    this.rule = rule;
    this.runtime = runtime;
    this.generations.add(initialGeneration);
  }

  public String run() {
    events.throwStartAutomationEvent(new StartAutomationEvent());
    for (int i = 0; i < runtime; i++) {
      int lastGeneration = (generations.size() - 1);
      Generation parentalGeneration = generations.get(lastGeneration);
      events.throwStartBreedingEvent(new StartBreedingEvent(parentalGeneration));
      Transition transition = new Transition(parentalGeneration, rule);
      Generation filialGeneration = transition.produceFilialGeneration();
      events.throwFinishBreedingEvent(new FinishBreedingEvent(parentalGeneration, filialGeneration));
      generations.add(filialGeneration);
    }
    events.throwFinishAutomationEvent(new FinishAutomationEvent(this, generations));
    int lastGeneration = (generations.size() - 1);
    Generation result = generations.get(lastGeneration);
    return result.toBinaryString();
  }

  public int getRule() {
    return rule.getDecimalRule();
  }

  public void setRule(int decimalRule) {
    this.rule = Rule.getInstance(decimalRule);
  }

  public void setRuntime(int runtime) {
    this.runtime = runtime;
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

  public static class Builder {
    private final Generation initialGeneration;
    private Rule rule = Rule.getInstance(0);
    private int runtime = 0;

    public Builder(final String seed) {
      this.initialGeneration = Generation.getGeneration(seed);
    }

    public static Builder chaotic(int range) {
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
      return new Builder(seed);
    }

    public static Builder simple(int range) {
      StringBuffer buffer = new StringBuffer();
      IntStream.range(0, range).forEach(i -> {
        if(i == (range / 2)) {
          buffer.append('1');
        } else {
          buffer.append('0');
        }
      });
      String seed = buffer.toString();
      return new Builder(seed);
    }

    public Builder withRule(final int rule) {
      this.rule = Rule.getInstance(rule);
      return this;
    }

    public Builder withRuntime(final int runtime) {
      this.runtime = runtime;
      return this;
    }

    public Automaton build() {
      return new Automaton(initialGeneration, rule, runtime);
    }
  }
}
