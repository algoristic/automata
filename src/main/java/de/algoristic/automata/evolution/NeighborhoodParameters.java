package de.algoristic.automata.evolution;

import de.algoristic.automata.core.Generation;

public class NeighborhoodParameters {

  private Rule rule;
  private Generation generation;
  private int currentCellIndex;

  public NeighborhoodParameters(Rule rule, Generation generation, int currentCellIndex) {
    this.rule = rule;
    this.generation = generation;
    this.currentCellIndex = currentCellIndex;
  }

  public Rule getRule() {
    return rule;
  }

  public Generation getGeneration() {
    return generation;
  }

  public int getCurrentCellIndex() {
    return currentCellIndex;
  }
}
