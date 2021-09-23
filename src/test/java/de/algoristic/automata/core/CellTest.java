package de.algoristic.automata.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.error.AutomatonException;

public class CellTest {

  @Test
  void generateAliveCellTest() {
    Cell cell = new Cell('1');
    assertTrue(cell.isAlive());
  }

  @Test
  void generateDeadCellTest() {
    Cell cell = new Cell('0');
    assertFalse(cell.isAlive());
  }

  @ParameterizedTest
  @ValueSource(chars = {'2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c'})
  void throwErrorOnInvalidCellTest(char invalidChar) {
    assertThrows(AutomatonException.class, () -> {
      new Cell(invalidChar);
    });
  }
}
