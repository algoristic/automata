package de.algoristic.automata.evolution.turmites;

import de.algoristic.automata.core.AbstractState;
import de.algoristic.automata.core.State;

public class AntState extends AbstractState {

  private final TurmiteState innerState;
  private final TurmiteRuleMetadata metadata;
  private final StateCalculator calculator;

  public AntState(TurmiteState innerState, TurmiteRuleMetadata metadata) {
    this.innerState = innerState;
    this.metadata = metadata;
    this.calculator = new StateCalculator(metadata);
  }

  Direction getDirection() {
    int value = innerState.getValue();
    int directionValue = calculator.getDirection(value);
    return Direction.getDirection(directionValue);
  }

  boolean isAliveAnt() {
    int value = innerState.getValue();
    return calculator.containsAnt(value);
  }

  void setAliveAnt(Direction direction) {
    int value = innerState.getValue();
    int newValue = calculator.setAliveAnt(value, direction);
    innerState.setValue(newValue);
  }

  @Override
  public int getValue() {
    int value = innerState.getValue();
    return calculator.getAntValue(value);
  }

  @Override
  public boolean isSuccessor(State other) {
    return false;
  }

  @Override
  public boolean matches(State other) {
    AntState otherAnt;
    if (other instanceof TurmiteState) {
      TurmiteState otherTurmite = (TurmiteState) other;
      otherAnt = new AntState(otherTurmite, metadata);
    } else if (other instanceof AntState) {
      otherAnt = (AntState) other;
    } else {
      TurmiteState otherTurmite = new TurmiteState(other);
      otherAnt = new AntState(otherTurmite, metadata);
    }
    return (this.isAliveAnt() && otherAnt.isAliveAnt());
  }

  public static boolean isAlive(State state, TurmiteRuleMetadata metadata) {
    return alive(metadata).matches(state);
  }

  public static State alive(TurmiteRuleMetadata metadata) {
    int value = metadata.getAntBase();
    TurmiteState innerState = new TurmiteState(value);
    return new AntState(innerState, metadata);
  }
}
