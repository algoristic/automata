package de.algoristic.automata.core;

public interface State {

  char getRepresentation();
  boolean isSuccessor(State other);
  default boolean matches(State other) {
    if(other != null) {
      char thisRepresentation = this.getRepresentation();
      char otherRepresentation = other.getRepresentation();
      return (thisRepresentation == otherRepresentation);
    } else {
      return false;
    }
  }
}
