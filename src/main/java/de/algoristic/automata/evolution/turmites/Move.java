package de.algoristic.automata.evolution.turmites;

import java.util.function.Function;

public enum Move {

  LEFT('L', (direction) -> direction.turnLeft()),
  TURN('U', (direction) -> direction.uTurn()),
  RIGHT('R', (direction) -> direction.turnRight());

  private char command;
  private Function<Direction, Direction> modifier;

  private Move(char command, Function<Direction, Direction> modifier) {
    this.command = command;
    this.modifier = modifier;
  }

  public Direction modify(Direction direction) {
    return this.modifier.apply(direction);
  }

  char getCommand() {
    return command;
  }

  public static Move valueOf(char command) {
    for (Move modificator : values()) {
      if (command == modificator.getCommand())
        return modificator;
    }
    return null;
  }
}
