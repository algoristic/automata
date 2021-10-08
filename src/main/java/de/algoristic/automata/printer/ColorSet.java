package de.algoristic.automata.printer;

import java.awt.Color;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColorSet {

  private final List<Color> colors;

  public ColorSet(Color first, Color... optional) {
    this.colors = Stream.concat(
        Stream.of(first),
        Stream.of(optional))
      .collect(Collectors.toList());
  }

  public ColorSet(List<Color> colors) {
    this.colors = colors;
  }

  public ColorSet add(Color color) {
    colors.add(color);
    return this;
  }

  public ColorSet remove(Color color) {
    colors.remove(color);
    return this;
  }

  List<Color> getColors() {
    return colors;
  }
}
