package de.algoristic.automata.evolution.turmites;

public class StateCalculator {

  private TurmitesRuleMetadata metadata;

  public StateCalculator(TurmitesRuleMetadata metadata) {
    this.metadata = metadata;
  }

  int getCycleState(int stateValue) {
    int base = metadata.getDirectionBase();
    int cycleState = (stateValue % base);
    return cycleState;
  }

  public int getNextCycleState(int cycle) {
    return ++cycle % metadata.getCycles();
  }

  public int getDirection(int stateValue) {
    int antBase = metadata.getAntBase();
    int directionBase = metadata.getDirectionBase();
    int newValue = (stateValue % antBase);
    return (newValue / directionBase);
  }

  public int setCycleState(int stateValue, int cycleState) {
    int base = metadata.getDirectionBase();
    int newValue = base * (stateValue / base);
    return (newValue + cycleState);
  }

  public boolean containsAnt(int stateValue) {
    int base = metadata.getAntBase();
    return (base <= stateValue);
  }

  public int removeAnt(int stateValue) {
    if(containsAnt(stateValue)) {
      return getCycleState(stateValue);
    } else {
      return stateValue;
    }
  }

  public int setAliveAnt(int stateValue, Direction antDirection) {
    if(containsAnt(stateValue)) {
      return stateValue;
    } else {
      int antBase = metadata.getAntBase();
      int directionBase = metadata.getDirectionBase();

      int ant = antBase;
      int direction = (directionBase * antDirection.getValue());
      int field = stateValue;

      return (ant + direction + field);
    }
  }

  public int getAntValue(int stateValue) {
    if(containsAnt(stateValue)) {
      return metadata.getAntBase();
    } else {
      return 0;
    }
  }
}
