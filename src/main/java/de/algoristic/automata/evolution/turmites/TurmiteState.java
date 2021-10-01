package de.algoristic.automata.evolution.turmites;

import de.algoristic.automata.core.AbstractState;
import de.algoristic.automata.core.State;

public class TurmiteState extends AbstractState {

  private int value;

  public TurmiteState(State state) {
    this(state.getValue());
  }

  public TurmiteState(int value) {
    this.value = value;
  }

  void setValue(int value) {
    this.value = value;
  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public boolean isSuccessor(State other) {
    return false;
  }
}
