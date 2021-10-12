package de.algoristic.automata.printer;

import java.awt.Color;

public abstract class ColorUtils {

  public static String toHex(Color color) {
    int r = color.getRed();
    int g = color.getGreen();
    int b = color.getBlue();
    return String.format("#%02X%02X%02X", r, g, b);
  }

}
