package de.algoristic.automata.printer;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.State;
import de.algoristic.automata.core.WireworldState;
import de.algoristic.automata.evolution.turmites.AntState;
import de.algoristic.automata.evolution.turmites.FieldState;
import de.algoristic.automata.evolution.turmites.TurmiteState;
import de.algoristic.automata.evolution.turmites.TurmitesRuleMetadata;

public class ColorMapping {

  final public static ColorMapping BINARY;
  final public static ColorMapping WIREWORLD;
  public static ColorMapping TURMITES(TurmitesRuleMetadata metadata) {
    return new TurmitesColorMapping(metadata);
  }

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

  protected Map<Integer, Color> mapping = new HashMap<>();

  public Color put(State state, Color color) {
    return mapping.put(state.getValue(), color);
  }

  public Color get(State state) {
    return mapping.get(state.getValue());
  }

  public void flush() {
    mapping = new HashMap<>();
  }

  Color put(Integer integer, Color color) {
    return mapping.put(integer, color);
  }

  private static class TurmitesColorMapping extends ColorMapping {

    {
      mapping.put(0, Color.white);
      mapping.put(1, Color.black);
      mapping.put(2, Color.gray);
      mapping.put(3, Color.green);
      mapping.put(4, Color.cyan);
    }

    private final TurmitesRuleMetadata metadata;

    public TurmitesColorMapping(TurmitesRuleMetadata metadata) {
      this.metadata = metadata;
    }

    @Override
    public Color get(State state) {
      if(AntState.isAlive(state, metadata)) {
        return Color.red;
      }
      TurmiteState turmiteState = new TurmiteState(state);
      FieldState fieldState = new FieldState(turmiteState, metadata);
      return super.get(fieldState);
    }

  }
}
