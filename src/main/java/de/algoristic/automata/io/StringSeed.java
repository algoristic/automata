package de.algoristic.automata.io;

public class StringSeed implements Seed {

  private final int height;
  private final String content;

  public StringSeed(String content, int height) {
    this.height = height;
    this.content = content;
  }

  @Override
  public int getVerticalDimension() {
    return height;
  }

  @Override
  public String getContent() {
    return content;
  }


}
