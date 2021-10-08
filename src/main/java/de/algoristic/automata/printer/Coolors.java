package de.algoristic.automata.printer;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import de.algoristic.automata.core.State;
import de.algoristic.automata.evolution.turmites.TurmitesRuleMetadata;

public class Coolors {

  public static Color transparent = new Color(0, 0, 0, 0);
  public static Color white = new Color(255, 255, 255);
  public static Color black = new Color(0, 0, 0);

//  public static Color  = new Color(rgb);
  public static Color slateGray = new Color(108, 117, 125);
  public static Color davysGrey = new Color(73, 80, 87);
  public static Color gunmetal = new Color(52, 58, 64);
  public static Color charlestonGreen = new Color(33, 37, 41);
  public static Color richBlackFOGRA29 = new Color(0, 18, 25);

  public static Color powderBlue = new Color(168, 218, 220);
  public static Color celadonBlue = new Color(69, 123, 157);
  public static Color prussianBlue = new Color(29, 53, 87);

  public static Color babyPowder = new Color(255, 255, 252); // nearly white
  public static Color honeyDew = new Color(241, 250, 238); // light yellow gray
  public static Color platinum = new Color(232, 232, 228); // very light gray
  public static Color charcoal = new Color(38, 70, 83); // blue-black
  public static Color oxfordBlue = new Color(20, 33, 61); //dark blue
  public static Color bdazzledBlue = new Color(61, 90, 128); // dark blue
  public static Color persianGreen = new Color(42, 157, 143); // green-blue
  public static Color orangeWeb = new Color(252, 163, 17); // orange-yellow mix // carrot
  public static Color orangeYellowCrayola = new Color(233, 196, 106);
  public static Color burntSienna = new Color(238, 108, 77); // strong orange
  public static Color isabelline = new Color(248, 237, 235); // very light orange
  public static Color sandyBrown = new Color(244, 162, 97); // more orange than brown
  public static Color imperialRed = new Color(230, 57, 70); // strong red
  

  public static class Sets {
    public static ColorSet colorful = new ColorSet(
        charcoal,
        persianGreen,
        orangeYellowCrayola,
        sandyBrown,
        burntSienna);
    public static ColorSet shadesOfBlue = new ColorSet(
        new Color(3, 4, 94),
        new Color(2, 62, 138),
        new Color(0, 119, 182),
        new Color(0, 150, 199),
        new Color(0, 180, 216),
        new Color(72, 202, 228),
        new Color(144, 224, 239),
        new Color(173, 232, 244),
        new Color(202, 240, 248));
    public static ColorSet darkBlues = new ColorSet(
        new Color(3, 4, 94),
        new Color(2, 62, 138),
        new Color(0, 119, 182),
        new Color(0, 150, 199));
    public static ColorSet lightBlues = new ColorSet(
        new Color(169, 214, 229),
        new Color(137, 194, 217),
        new Color(97, 165, 194),
        new Color(70, 143, 175));
    public static ColorSet lightGrays = new ColorSet(
        new Color(248, 249, 250),
        new Color(233, 236, 239),
        new Color(222, 226, 230));
    public static ColorSet retroChic = new ColorSet(
        imperialRed,
        honeyDew,
        powderBlue,
        celadonBlue,
        prussianBlue);
    public static ColorSet lightRainbow = new ColorSet(
        new Color(255, 173, 173),
        new Color(255, 214, 165),
        new Color(253, 255, 182),
        new Color(202, 255, 191),
        new Color(155, 246, 255),
        new Color(160, 196, 255),
        new Color(189, 178, 255),
        new Color(255, 198, 255));
  }

  protected Color backgroundColor = Color.lightGray;
  protected Color frameColor = Color.lightGray;
  protected Map<Integer, ColorMapping> mappings = new HashMap<>();

  public Coolors withBackground(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }

  public Coolors withFrameColor(Color frameColor) {
    this.frameColor = frameColor;
    return this;
  }

  public Coolors withMapping(Integer key, Color value) {
    ColorMapping mapping = new StraightMapping(key, value);
    return withMapping(mapping);
  }

  public Coolors withMapping(State state, Color value) {
    Integer key = state.getValue();
    return withMapping(key, value);
  }

  public Coolors withMapping(State state, Color firstColor, Color... optional) {
    ColorSet colorSet = new ColorSet(firstColor, optional);
    return withMapping(state, colorSet);
  }

  public Coolors withMapping(State state, ColorSet colorSet) {
    Integer key = state.getValue();
    ColorMapping mapping = new RandomizedMapping(key, colorSet);
    return withMapping(mapping);
  }

  public Coolors withMapping(ColorMapping mapping) {
    Integer key = mapping.getKey();
    this.mappings.put(key, mapping);
    return this;
  }

  public ColorModel build() {
    return new ColorModel(mappings, backgroundColor, frameColor);
  }

  public ColorModel build(TurmitesRuleMetadata metadata) {
    return new ColorModel.TurmitesColorMapping(mappings, backgroundColor, frameColor, metadata);
  }
}
