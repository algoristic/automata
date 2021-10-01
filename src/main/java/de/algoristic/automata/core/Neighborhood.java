package de.algoristic.automata.core;

import java.util.Iterator;
import java.util.List;
import de.algoristic.automata.evolution.wolframsuniverse.Pattern;

public class Neighborhood implements Pattern, Iterable<Cell> {

  private final Cell cell;
  protected final List<Cell> neightbors;

  public Neighborhood(Cell cell, List<Cell> neighbors) {
    this.cell = cell;
    this.neightbors = neighbors;
  }

  public Cell getCell() {
    return cell;
  }

  public List<Cell> getNeighbors() {
    return getCells();
  }

  @Override
  public List<Cell> getCells() {
    return neightbors;
  }

  @Override
  public int size() {
    return neightbors.size();
  }

  @Override
  public String toString() {
    return neightbors.toString();
  }

  @Override
  public Iterator<Cell> iterator() {
    return neightbors.iterator();
  }
}
