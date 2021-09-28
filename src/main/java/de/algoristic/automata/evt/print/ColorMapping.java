package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.State;

public class ColorMapping {

  public static ColorMapping BINARY;
  
  static {
    BINARY = new ColorMapping();
    BINARY.put(BinaryState.ALIVE, Color.black);
    BINARY.put(BinaryState.DEAD, Color.white);
  }

  private Map<State, Color> mapping = new HashMap<>();

  public Color put(State state, Color color) {
    return mapping.put(state, color);
  }

  public Color get(State state) {
    return mapping.get(state);
  }

  public Map<State, Color> getMapping() {
    return mapping;
  }

  public void flush() {
    mapping = new HashMap<>();
  }
}
