package de.algoristic.automata.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CellTest {

  @Test
  void generateAliveCellTest() {
    Cell cell = new Cell(BinaryState.ALIVE);
    assertTrue(cell.hasState(BinaryState.ALIVE));
  }

  @Test
  void generateDeadCellTest() {
    Cell cell = new Cell(BinaryState.DEAD);
    assertFalse(cell.hasState(BinaryState.ALIVE));
    assertTrue(cell.hasState(BinaryState.DEAD));
  }
}
