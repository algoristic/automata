package de.algoristic.automata.evolution.dimensional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GridTest {

  @Test
  void gridCreationTest() {
    Grid grid = Grid.fromOneDimension(28, 4);
    assertEquals(0, grid.getLeftBorder());
    assertEquals(6, grid.getRightBorder());
    assertEquals(0, grid.getUpperBorder());
    assertEquals(3, grid.getLowerBorder());
  }

  @Test
  void pointTranspositionTest() {
    Grid grid = Grid.fromOneDimension(28, 4);
    Point p1 = grid.transpose(9);
    assertEquals(2, p1.getX());
    assertEquals(1, p1.getY());
    assertTrue(p1.isValid());
    
    Point p2 = grid.transpose(19);
    assertEquals(5, p2.getX());
    assertEquals(2, p2.getY());
    assertTrue(p2.isValid());
    
    Point p3 = grid.transpose(30);
    assertEquals(2, p3.getX());
    assertEquals(4, p3.getY());
    assertFalse(p3.isValid());
    assertTrue(p3.up().isValid());
  }
}
