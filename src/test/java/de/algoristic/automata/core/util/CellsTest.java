package de.algoristic.automata.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.CellSpace;
import de.algoristic.automata.core.util.Cells;

public class CellsTest {

  @Test
  void generateCellsTest() {
    String seed = Integer.toBinaryString(30); // ="11110"
    List<Cell> cells = Cells.of(seed);
    assertEquals(5, cells.size());
    assertTrue(cells.get(0).isAlive());
    assertTrue(cells.get(2).isAlive());
    assertFalse(cells.get(4).isAlive());
  }

  @Test
  void generateCellSpacesTest() {
    String seed = "1011001";
    List<Cell> cells = Cells.of(seed);
    List<CellSpace> cellSpaces = Cells.wrap(cells);
    CellSpace first = cellSpaces.get(0);
    CellSpace second = cellSpaces.get(1);
    CellSpace third = cellSpaces.get(2);
    CellSpace last = cellSpaces.get(6);
    CellSpace secondLast = cellSpaces.get(5);
    assertEquals(first.getNext(), second);
    assertEquals(first.getPrevious(), last);

    assertEquals(last.getNext(), first);
    assertEquals(last.getPrevious(), secondLast);

    assertEquals(second.getNext(), third);
    assertEquals(second.getPrevious(), first);
  }
}
