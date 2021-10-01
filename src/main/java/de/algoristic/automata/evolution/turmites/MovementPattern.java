package de.algoristic.automata.evolution.turmites;

import java.util.Map;

public class MovementPattern {

  private final Map<Integer, Move> patterns;

  public MovementPattern(Map<Integer, Move> patterns) {
    this.patterns = patterns;
  }

  Move getMove(FieldState state) {
    int fieldValue = state.getValue();
    return patterns.get(fieldValue);
  }
}
