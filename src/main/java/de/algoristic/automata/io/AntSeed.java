package de.algoristic.automata.io;

public class AntSeed implements Seed {

  private final int size;

  public AntSeed(int size) {
    if((size % 2) == 0) --size;
    this.size = size;
  }

  @Override
  public int getVerticalDimension() {
    return size;
  }

  @Override
  public String getContent() {
    StringBuffer buffer = new StringBuffer();
    final int center = (size / 2);
    for(int x = 0; x < size; x++) {
      for(int y = 0; y < size; y++) {
        if(x == center && y == center) {
          buffer.append('a');
        } else {
          buffer.append('0');
        }
      }
    }
    return buffer.toString();
  }
  
  
}
