package de.algoristic.automata.core;

import de.algoristic.automata.evolution.NeighborhoodParameters;
import de.algoristic.automata.evolution.Rule;

public class Cell {

  private State state;
  protected int age;

  private CellSpace cellSpace;

  public Cell(State state) {
    this.age = 0;
    this.state = state;
  }

  public Cell(Cell cell) {
    this.state = cell.getState();
    this.age = cell.getAge();
  }

  public State getState() {
    return state;
  }

  public Cell stayAlive() {
    Cell cell = new Cell(this);
    cell.incrementAge();
    return cell;
  }

  public Neighborhood getNeighborhood(NeighborhoodParameters parameters) {
    Rule rule = parameters.getRule();
    return rule.getNeighborhood(this, parameters);
  }

  public int getAge() {
    return age;
  }

  private void incrementAge() {
    age++;
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

  public boolean hasState(State state) {
    return this.state.matches(state);
  }

  public boolean matches(Cell other) {
    if (other != null) {
      return this.state.matches(other.getState());
    } else {
      return false;
    }
  }

  public boolean isDirectSuccessor(Cell other) {
    return state.isSuccessor(other.getState());
  }

  @Override
  public String toString() {
    return String.valueOf(state.getRepresentation());
  }
}
