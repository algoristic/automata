package de.algoristic.automata.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import de.algoristic.automata.error.AutomatonException;
import de.algoristic.automata.evolution.Pattern;
import de.algoristic.automata.evolution.Rule;

public class Cell {

  public static final Character ALIVE = '1';
  public static final Character DEAD = '0';

  private final boolean isAlive;

  private CellSpace cellSpace;
  private int age;

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

  private Cell(final Cell cell) {
    this.isAlive = cell.isAlive();
    this.age = cell.getAge();
  }

  public Pattern getNeighborhood(Rule appliedRule) {
    List<Cell> neighbors = new ArrayList<>();
    Cell previous = this.getPrevious();
    for (int i = 0; i < appliedRule.getCellsBeforeCenter(); i++) {
      neighbors.add(previous);
      previous = previous.getPrevious();
    }
    Collections.reverse(neighbors);
    neighbors.add(this);
    Cell next = this.getNext();
    for (int i = 0; i < appliedRule.getCellsAfterCenter(); i++) {
      neighbors.add(next);
      next = next.getNext();
    }
    return new Neighborhood(neighbors);
  }

  public Cell clone() {
    return new Cell(this);
  }

  public boolean isAlive() {
    return isAlive;
  }

  public int getAge() {
    return age;
  }

  public void toNextGeneration() {
    age++;
  }

  private Cell getPrevious() {
    CellSpace previous = cellSpace.getPrevious();
    return previous.getContent();
  }

  private Cell getNext() {
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
