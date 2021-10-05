package de.algoristic.automata.printer;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import de.algoristic.automata.core.State;
import de.algoristic.automata.evolution.turmites.TurmitesRuleMetadata;

public class Coolors {

  public static Color black = new Color(0, 0, 0);
  public static Color oxfordBlue = new Color(20, 33, 61);
  public static Color orangeWeb = new Color(252, 163, 17);

  protected Color backgroundColor = Color.lightGray;
  protected Color frameColor = Color.lightGray;
  protected Map<Integer, Color> mapping = new HashMap<>();

  public Coolors withBackground(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }

  public Coolors withFrameColor(Color frameColor) {
    this.frameColor = frameColor;
    return this;
  }

  public Coolors withMapping(Integer key, Color value) {
    this.mapping.put(key, value);
    return this;
  }

  public Coolors withMapping(State state, Color value) {
    Integer key = state.getValue();
    return withMapping(key, value);
  }

  public Coolors withMapping(ColorMapping mapping) {
    Integer key = mapping.getKey();
    Color value = mapping.getValue();
    return withMapping(key, value);
  }

  public ColorModel build() {
    return new ColorModel(mapping, backgroundColor, frameColor);
  }

  public ColorModel build(TurmitesRuleMetadata metadata) {
    return new ColorModel.TurmitesColorMapping(mapping, backgroundColor, frameColor, metadata);
  }
}
