package de.algoristic.automata.core;

public class BinaryState implements State {

  public static State ALIVE = new BinaryState('1');
  public static State DEAD = new BinaryState('0');
  
  private final boolean isAlive;

  public BinaryState(final char state) {
    isAlive = (state == '1');
  }

  public boolean isAlive() {
    return isAlive;
  }

  @Override
  public char getRepresentation() {
    return isAlive ? '1' : '0';
  }

  @Override
  public boolean isSuccessor(State other) {
    char representation = other.getRepresentation();
    return isAlive && (representation == '1');
  }
}
