package de.algoristic.automata.core;

import java.util.Arrays;

public enum WireworldState implements State {

  EMPTY('0', 0),
  ELECTRON_HEAD('h', 1),
  ELECTRON_TAIL('t', 2),
  CONDUCTOR('c', 3);

  private char constructor;
  private int value;

  private WireworldState(char constructor, int value) {
    this.constructor = constructor;
    this.value = value;
  }


  public static boolean isValid(char constructor) {
    return Arrays.asList(values())
      .stream()
      .filter(state -> (state.constructor == constructor))
      .findAny()
      .isPresent();
  }

  public static State valueOf(char constructor) {
    for (WireworldState state : values()) {
      if (state.constructor == constructor) {
        return state;
      }
    }
    return EMPTY;
  }

  @Override
  public boolean isSuccessor(State other) {
    return false;
  }

  @Override
  public int getValue() {
    return value;
  }
}
