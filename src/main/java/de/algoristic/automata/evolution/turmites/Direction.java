package de.algoristic.automata.evolution.turmites;

import java.util.function.BiFunction;
import de.algoristic.automata.evolution.dimensional.Point;

public enum Direction {

  UP(0, (antPosition, thisPosition) -> thisPosition.isAbove(antPosition)),
  RIGHT(1, (antPosition, thisPosition) -> thisPosition.isRight(antPosition)),
  DOWN(2, (antPosition, thisPosition) -> thisPosition.isBelow(antPosition)),
  LEFT(3, (antPosition, thisPosition) -> thisPosition.isLeft(antPosition));

  private int value;
  private BiFunction<Point, Point, Boolean> fn;

  private Direction(int value, BiFunction<Point, Point, Boolean> fn) {
    this.value = value;
    this.fn = fn;
  }

  public int getValue() {
    return value;
  }

  public Direction turnRight() {
    int len = values().length;
    int nextValue = value;
    if (value == (len - 1)) {
      nextValue = 0;
    } else {
      nextValue++;
    }
    return getDirection(nextValue);
  }

  public Direction turnLeft() {
    int len = values().length;
    int nextValue = value;
    if (value == 0) {
      nextValue = (len - 1);
    } else {
      nextValue--;
    }
    return getDirection(nextValue);
  }

  public Direction uTurn() {
    Direction next = this.turnRight();
    return next.turnRight();
  }

  public boolean isOnCourse(Point antPosition, Point thisPosition) {
    return fn.apply(antPosition, thisPosition);
  }

  static Direction getDirection(int value) {
    for (Direction direction : values()) {
      if (value == direction.getValue()) {
        return direction;
      }
    }
    return null;
  }
}
