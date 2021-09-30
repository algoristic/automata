package de.algoristic.automata.core;

public interface State {

  int getValue();
  boolean isSuccessor(State other);
  default boolean matches(State other) {
    if(other != null) {
      int thisRepresentation = this.getValue();
      int otherRepresentation = other.getValue();
      return (thisRepresentation == otherRepresentation);
    } else {
      return false;
    }
  }
}
