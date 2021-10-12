package de.algoristic.automata.printer;

import java.awt.Color;
import de.algoristic.automata.core.State;

public class StraightMapping implements ColorMapping {
  final private int key;
  final private Color value;

  public StraightMapping(int key, Color value) {
    this.key = key;
    this.value = value;
  }

  public StraightMapping(State state, Color value) {
    this(state.getValue(), value);
  }

  @Override
  public int getKey() {
    return key;
  }

  @Override
  public Color getValue() {
    return value;
  }

  @Override
  public String toString() {
    return ColorUtils.toHex(value);
  }
}
