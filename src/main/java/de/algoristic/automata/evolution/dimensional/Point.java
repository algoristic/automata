package de.algoristic.automata.evolution.dimensional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Point {

  private static List<Function<Point, Point>> mooreNeighborhoodOperations = Arrays.asList(
    Point::left,
    Point::right,
    Point::up,
    Point::down,
    Point::upLeft,
    Point::upRight,
    Point::downLeft,
    Point::downRight);

  private final int x;
  private final int y;
  private final Grid grid;

  public Point(int x, int y, Grid grid) {
    this.x = x;
    this.y = y;
    this.grid = grid;
  }

  public int transposeToIndex() {
    int xAxis = grid.getRightBorder();
    return (y * (xAxis + 1) + x);
  }

  public List<Point> getMooreNeighborhood() {
    return mooreNeighborhoodOperations.stream()
      .map(fn -> fn.apply(this))
      .filter(Point::isValid)
      .collect(Collectors.toList());
  }

  public Point up() {
    int newY = (y - 1);
    return new Point(x, newY, grid);
  }

  public Point down() {
    int newY = (y + 1);
    return new Point(x, newY, grid);
  }

  public Point left() {
    int newX = (x - 1);
    return new Point(newX, y, grid);
  }

  public Point right() {
    int newX = (x + 1);
    return new Point(newX, y, grid);
  }

  public Point upLeft() {
    Point point = this.up();
    return point.left();
  }

  public Point upRight() {
    Point point = this.up();
    return point.right();
  }

  public Point downLeft() {
    Point point = this.down();
    return point.left();
  }

  public Point downRight() {
    Point point = this.down();
    return point.right();
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  boolean isValid() {
    int leftBorder = grid.getLeftBorder();
    int rightBorder = grid.getRightBorder();
    int upperBorder = grid.getUpperBorder();
    int lowerBorder = grid.getLowerBorder();
    boolean isValid = true;
    isValid &= (x >= leftBorder);
    isValid &= (x <= rightBorder);
    isValid &= (y >= upperBorder);
    isValid &= (y <= lowerBorder);
    return isValid;
  }
}
