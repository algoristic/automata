package de.algoristic.automata.evolution.dimensional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class PointTest {

  @Test
  void basicPointTest() {
    Grid grid = new Grid(6, 3);       // grid spans x-axis from 0 to 6 and y-axis from 0 to 3
    Point p1 = new Point(5, 2, grid); // visual: 0123456
    Point up = p1.up();               //         0123456
    Point down = p1.down();           //         0123456
    Point left = p1.left();           //         0123456
    Point right = p1.right();
    assertEquals(5, up.getX());
    assertEquals(1, up.getY());
    assertEquals(5, down.getX());
    assertEquals(3, down.getY());
    assertEquals(4, left.getX());
    assertEquals(2, left.getY());
    assertEquals(6, right.getX());
    assertEquals(2, right.getY());
  }
  
  @Test
  void validationTest() {
    Grid grid = new Grid(6, 3);
    Point p1 = new Point(6, 2, grid);
    p1 = p1.right();
    assertFalse(p1.isValid());
    
    Point p2 = new Point(1, 0, grid);
    p2 = p2.upLeft();
    assertFalse(p2.isValid());
    
    Point p3 = new Point(3, 2, grid);
    p3 = p3.upRight();
    assertTrue(p3.isValid());
  }
  
  @Test
  void neighborhoodTest() {
    Grid grid = new Grid(6, 3);
    Point p1 = new Point(5, 2, grid);
    List<Point> neighborhood = p1.getMooreNeighborhood();
    assertEquals(8, neighborhood.size());
    
    Point p2 = new Point(0, 3, grid);
    neighborhood = p2.getMooreNeighborhood();
    assertEquals(3, neighborhood.size());
  }
}
