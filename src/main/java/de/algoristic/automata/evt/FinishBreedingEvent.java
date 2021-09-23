package de.algoristic.automata.evt;

import de.algoristic.automata.core.Generation;

public class FinishBreedingEvent extends AbstractAutomatonEvent {

  private Generation parentalGeneration;
  private Generation filialGeneration;

  public FinishBreedingEvent(Generation parentalGeneration, Generation filialGeneration) {
    super();
    this.parentalGeneration = parentalGeneration;
    this.filialGeneration = filialGeneration;
  }

  public Generation getParentalGeneration() {
    return parentalGeneration;
  }

  public Generation getFilialGeneration() {
    return filialGeneration;
  }
}
