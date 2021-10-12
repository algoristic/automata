package de.algoristic.automata.printer;

import java.awt.Color;
import java.util.Map;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.State;
import de.algoristic.automata.core.WireworldState;
import de.algoristic.automata.evolution.turmites.AntState;
import de.algoristic.automata.evolution.turmites.FieldState;
import de.algoristic.automata.evolution.turmites.TurmiteState;
import de.algoristic.automata.evolution.turmites.TurmitesRuleMetadata;

public class ColorModel {

  final public static ColorModel BINARY = new Coolors()
    .withMapping(BinaryState.ALIVE, Color.black)
    .withMapping(BinaryState.DEAD, Color.white)
    .build();
  final public static ColorModel WIREWORLD = new Coolors()
    .withMapping(WireworldState.EMPTY, Color.black)
    .withMapping(WireworldState.ELECTRON_HEAD, Color.blue)
    .withMapping(WireworldState.ELECTRON_TAIL, Color.red)
    .withMapping(WireworldState.CONDUCTOR, Color.yellow)
    .build();
  final public static ColorModel TURMITES(TurmitesRuleMetadata metadata) {
    return new Coolors()
      .withMapping(0, Color.white)
      .withMapping(1, Color.black)
      .withMapping(2, Color.gray)
      .withMapping(3, Color.green)
      .withMapping(4, Color.cyan)
      .build(metadata);
  }

  final protected Map<Integer, ColorMapping> mappings;
  final private Color backgroundColor;
  final private Color frameColor;

  public ColorModel(Map<Integer, ColorMapping> mappings, Color backgroundColor, Color frameColor) {
    this.mappings = mappings;
    this.backgroundColor = backgroundColor;
    this.frameColor = frameColor;
  }

  Color getBackgroundColor() {
    return backgroundColor;
  }

  Color getFrameColor() {
    return frameColor;
  }

  public Color get(State state) {
    Integer key = state.getValue();
    ColorMapping mapping = mappings.get(key);
    return mapping.getValue();
  }

  @Override
  public String toString() {
    String indent = "  ";
    StringBuffer buffer = new StringBuffer("ColorModel:\n");
    buffer.append(indent).append("background: ").append(ColorUtils.toHex(backgroundColor)).append("\n");
    buffer.append(indent).append("frame: ").append(ColorUtils.toHex(frameColor)).append("\n");
    buffer.append(indent).append("mappings:\n");
    for(Integer state : mappings.keySet()) {
      buffer.append(indent);
      buffer.append(indent);
      buffer.append("state: ").append(state).append(" => ");
      buffer.append(mappings.get(state));
      buffer.append("\n");
    }
    return buffer.toString();
  }

  static class TurmitesColorMapping extends ColorModel {

    private final TurmitesRuleMetadata metadata;   

    public TurmitesColorMapping(Map<Integer, ColorMapping> mappings, Color backgroundColor, Color frameColor, TurmitesRuleMetadata metadata) {
      super(mappings, backgroundColor, frameColor);
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
