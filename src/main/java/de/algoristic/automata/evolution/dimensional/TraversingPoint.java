package de.algoristic.automata.evolution.dimensional;

public class TraversingPoint extends Point {

  public TraversingPoint(int x, int y, Grid grid) {
    super(x, y, grid);
  }

  @Override
  public Point up() {
    int newY = (y - 1);
    if(!isValidPosition(x, newY)) {
      newY = grid.getLowerBorder();
    }
    return new TraversingPoint(x, newY, grid);
  }

  @Override
  public Point down() {
    int newY = (y + 1);
    if(!isValidPosition(x, newY)) {
      newY = grid.getUpperBorder();
    }
    return new TraversingPoint(x, newY, grid);
  }

  @Override
  public Point left() {
    int newX = (x - 1);
    if(!isValidPosition(newX, y)) {
      newX = grid.getRightBorder();
    }
    return new TraversingPoint(newX, y, grid);
  }

  @Override
  public Point right() {
    int newX = (x + 1);
    if(!isValidPosition(newX, y)) {
      newX = grid.getLeftBorder();
    }
    return new TraversingPoint(newX, y, grid);
  }

  @Override
  boolean isValid() {
    return true;
  }
}
