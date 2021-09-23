package de.algoristic.automata.core;

import de.algoristic.automata.error.AutomatonException;
import de.algoristic.automata.evolution.NeighborhoodParameters;
import de.algoristic.automata.evolution.Rule;

public class Cell {

  public static final Character ALIVE = '1';
  public static final Character DEAD = '0';

  protected final boolean isAlive;
  protected int age;

  private CellSpace cellSpace;

  public Cell(final char state) {
    this.age = 0;
    if (ALIVE.equals(state)) {
      isAlive = true;
    } else {
      if (DEAD.equals(state)) {
        isAlive = false;
      } else {
        throw new AutomatonException("could not instantiate cell, due to invalid state: \"" + state + "\"");
      }
    }
  }

  private Cell(Cell cell) {
    this.isAlive = cell.isAlive();
    this.age = cell.getAge();
    this.age++;
  }

  public Cell clone() {
    return new Cell(this);
  }

  public Neighborhood getNeighborhood(NeighborhoodParameters parameters) {
    Rule rule = parameters.getRule();
    return rule.getNeighborhood(this, parameters);
  }

  public boolean isAlive() {
    return isAlive;
  }

  public int getAge() {
    return age;
  }

  public Cell getPrevious() {
    CellSpace previous = cellSpace.getPrevious();
    return previous.getContent();
  }

  public Cell getNext() {
    CellSpace next = cellSpace.getNext();
    return next.getContent();
  }

  public void setCellSpace(CellSpace cellSpace) {
    this.cellSpace = cellSpace;
  }

  public boolean matches(Cell other) {
    if (other != null) {
      return !(this.isAlive() ^ other.isAlive());
    } else {
      return false;
    }
  }

  public boolean isDirectSuccessor(Cell other) {
    if (other != null) {
      return (this.isAlive() && other.isAlive());
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return (isAlive ? ALIVE : DEAD).toString();
  }
}
