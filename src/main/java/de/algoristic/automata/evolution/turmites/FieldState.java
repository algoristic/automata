package de.algoristic.automata.evolution.turmites;

import de.algoristic.automata.core.AbstractState;
import de.algoristic.automata.core.State;

public class FieldState extends AbstractState {

  private final TurmiteState innerState;
  private final StateCalculator calculator;

  public FieldState(TurmiteState innerState, TurmiteRuleMetadata metadata) {
    this.innerState = innerState;
    this.calculator = new StateCalculator(metadata);
  }

  void flipField() {
    int value = innerState.getValue();
    int currentCycleState = calculator.getCycleState(value);
    int nextCycleState = calculator.getNextCycleState(currentCycleState);
    int newValue = calculator.setCycleState(value, nextCycleState);
    innerState.setValue(newValue);
  }

  @Override
  public int getValue() {
    int stateValue = innerState.getValue();
    return calculator.getCycleState(stateValue);
  }

  @Override
  public boolean isSuccessor(State other) {
    return false;
  }
}
