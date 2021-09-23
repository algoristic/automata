package de.algoristic.automata.core;

import java.util.List;
import de.algoristic.automata.evolution.Pattern;

public class Neighborhood implements Pattern {

  private final List<Cell> cells;

  public Neighborhood(List<Cell> cells) {
    this.cells = cells;
  }

  @Override
  public List<Cell> getCells() {
    return cells;
  }

  @Override
  public int size() {
    return cells.size();
  }

  @Override
  public String toString() {
    return cells.toString();
  }
}
