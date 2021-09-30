package de.algoristic.automata.core;

public abstract class AbstractState implements State {

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof State) {
      State other = (State) obj;
      return this.matches(other);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return String.valueOf(getRepresentation());
  }
}
