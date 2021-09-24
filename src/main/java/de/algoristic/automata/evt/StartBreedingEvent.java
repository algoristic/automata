package de.algoristic.automata.evt;

import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evolution.Rule;

public class StartBreedingEvent extends AbstractAutomatonEvent {

  private Generation parentalGeneration;

  public StartBreedingEvent(Rule rule, Generation parentalGeneration) {
    super(rule);
    this.parentalGeneration = parentalGeneration;
  }

  public Generation getParentalGeneration() {
    return parentalGeneration;
  }
}
