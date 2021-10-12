package de.algoristic.automata.printer;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class RandomizedMapping implements ColorMapping {

  private final int key;
  private final List<Color> colors;
  private final SecureRandom rnd;

  public RandomizedMapping(int key, ColorSet colorSet) {
    this(key, colorSet.getColors());
  }

  public RandomizedMapping(int key, List<Color> colors) {
    this.key = key;
    this.colors = colors;
    this.rnd = new SecureRandom();
  }

  @Override
  public int getKey() {
    return key;
  }

  @Override
  public Color getValue() {
    int bound = colors.size();
    int index = rnd.nextInt(bound);
    return colors.get(index);
  }

  @Override
  public String toString() {
    return colors.stream()
      .map(ColorUtils::toHex)
      .collect(Collectors.joining(", "));
  }
}
