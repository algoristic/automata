package de.algoristic.automata.evolution.turmites;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DirectionsTest {

  @Test
  void turningTest() {
    Direction direction = Direction.LEFT;

    Direction up = direction.turnRight();
    assertEquals(Direction.UP, up);

    Direction down = direction.turnLeft();
    assertEquals(Direction.DOWN, down);

    Direction left = up.turnLeft();
    assertEquals(Direction.LEFT, left);

    down = up.uTurn();
    assertEquals(Direction.DOWN, down);

    Direction right = left.uTurn();
    assertEquals(Direction.RIGHT, right);

    left = right.uTurn();
    assertEquals(Direction.LEFT, left);

    up = down.uTurn();
    assertEquals(Direction.UP, up);
  }

  @Test
  void calcDirectionsTest() {
    TurmitesRuleMetadata metadata_1 = new TurmitesRuleMetadata(2, 10, 100);
    StateCalculator calculator_1 = new StateCalculator(metadata_1);
    int dirVal_1 = calculator_1.getDirection(120);
    Direction down = Direction.getDirection(dirVal_1);
    assertEquals(Direction.DOWN, down);

    TurmitesRuleMetadata metadata_2 = new TurmitesRuleMetadata(16, 100, 1000);
    StateCalculator calculator_2 = new StateCalculator(metadata_2);
    int dirVal_2 = calculator_2.getDirection(1011);
    Direction up = Direction.getDirection(dirVal_2);
    assertEquals(Direction.UP, up);
  }
}
