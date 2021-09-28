package de.algoristic.automata.core;

import java.util.Arrays;

public class WireworldState extends AbstractState {

  private final static char E = '0';
  private final static char H = 'h';
  private final static char T = 't';
  private final static char C = 'c';

  public static State EMPTY = new WireworldState(E);
  public static State ELECTRON_HEAD = new WireworldState(H);
  public static State ELECTRON_TAIL = new WireworldState(T);
  public static State CONDUCTOR = new WireworldState(C);

  public static boolean isValid(char representation) {
    return Arrays.asList(H, T, C).contains(representation);
  }

  public static State valueOf(char representation) {
    return new WireworldState(representation);
  }

  private final char representation;

  private WireworldState(char representation) {
    this.representation = representation;
  }

  @Override
  public boolean isSuccessor(State other) {
    return false;
  }

  @Override
  public char getRepresentation() {
    return representation;
  }
}
