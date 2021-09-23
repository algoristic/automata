package de.algoristic.automata.evt;

import de.algoristic.automata.core.Generation;

public class StartBreedingEvent extends AbstractAutomatonEvent {

  private Generation parentalGeneration;

  public StartBreedingEvent(Generation parentalGeneration) {
    super();
    this.parentalGeneration = parentalGeneration;
  }

  public Generation getParentalGeneration() {
    return parentalGeneration;
  }
}
