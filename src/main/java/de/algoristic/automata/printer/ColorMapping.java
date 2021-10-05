package de.algoristic.automata.printer;

import java.awt.Color;
import de.algoristic.automata.core.State;

public class ColorMapping {

  final private int key;
  final private Color value;

  public ColorMapping(State state, Color value) {
    this(state.getValue(), value);
  }

  public ColorMapping(int key, Color value) {
    this.key = key;
    this.value = value;
  }

  int getKey() {
    return key;
  }

  Color getValue() {
    return value;
  }
}
