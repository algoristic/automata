package de.algoristic.automata.evt;

import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evolution.Rule;

public class FinishBreedingEvent extends AbstractAutomatonEvent {

  private final int step;
  private final Generation parentalGeneration;
  private final Generation filialGeneration;

  public FinishBreedingEvent(int step, Rule rule, Generation parentalGeneration, Generation filialGeneration) {
    super(rule);
    this.step = step;
    this.parentalGeneration = parentalGeneration;
    this.filialGeneration = filialGeneration;
  }

  public int getStep() {
    return step;
  }

  public Generation getParentalGeneration() {
    return parentalGeneration;
  }

  public Generation getFilialGeneration() {
    return filialGeneration;
  }
}
