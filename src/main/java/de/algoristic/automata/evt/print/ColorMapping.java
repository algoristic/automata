package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.State;
import de.algoristic.automata.core.WireworldState;

public class ColorMapping {

  final public static ColorMapping BINARY;
  final public static ColorMapping WIREWORLD;

  static {
    BINARY = new ColorMapping();
    BINARY.put(BinaryState.ALIVE, Color.black);
    BINARY.put(BinaryState.DEAD, Color.white);

    WIREWORLD = new ColorMapping();
    WIREWORLD.put(WireworldState.EMPTY, Color.black);
    WIREWORLD.put(WireworldState.ELECTRON_HEAD, Color.blue);
    WIREWORLD.put(WireworldState.ELECTRON_TAIL, Color.red);
    WIREWORLD.put(WireworldState.CONDUCTOR, Color.yellow);
  }

  private Map<Character, Color> mapping = new HashMap<>();

  public Color put(State state, Color color) {
    return mapping.put(state.getRepresentation(), color);
  }

  public Color get(State state) {
    return mapping.get(state.getRepresentation());
  }

  public void flush() {
    mapping = new HashMap<>();
  }
}
