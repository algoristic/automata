package de.algoristic.automata.printer;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.List;

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

}
