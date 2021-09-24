package de.algoristic.automata.evolution.dimensional;

import de.algoristic.automata.core.Generation;

public class Grid {

  private final int xAxis;
  private final int yAxis;

  public Grid(int xAxis, int yAxis) {
    this.xAxis = xAxis;
    this.yAxis = yAxis;
  }

  public static Grid fromGeneration(Generation generation) {
    int size = generation.size();
    int verticalSpace = generation.getVerticalSpace();
    return fromOneDimension(size, verticalSpace);
  }

  public static Grid fromOneDimension(int size, int verticalSpace) {
    int length = size / verticalSpace;
    int xAxis = length - 1; // grid works 0-based
    int yAxis = verticalSpace - 1;
    return new Grid(xAxis, yAxis);
  }
  
  public int getLeftBorder() {
    return 0;
  }

  public int getRightBorder() {
    return xAxis;
  }

  public int getUpperBorder() {
    return 0;
  }

  public int getLowerBorder() {
    return yAxis;
  }

  public Point transpose(int oneDimensionalPosition) {
    int x = oneDimensionalPosition % (xAxis + 1);
    int y = oneDimensionalPosition / (xAxis + 1);
    return new Point(x, y, this);
  }
}
