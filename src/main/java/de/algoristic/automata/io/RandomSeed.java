package de.algoristic.automata.io;

import java.util.Random;

public class RandomSeed implements Seed {

  private final int height;
  private String content;

  public RandomSeed(int width, int height) {
    this.height = height;
    init(width, height);
  }
  
  public RandomSeed(int size) {
    this.height = size;
    init(size, size);
  }

  private void init(int width, int height) {
    Random rnd = new Random();
    StringBuffer buffer = new StringBuffer();
    for(int y = 0; y < height; y++) {
      for(int x = 0; x < width; x++) {
        if(rnd.nextBoolean()) {
          buffer.append('1');
        } else {
          buffer.append('0');
        }
      }
    }
    content = buffer.toString();
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
