package de.algoristic.automata.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import de.algoristic.automata.core.BinaryCellSupplier;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.CellSpace;
import de.algoristic.automata.core.util.Cells;

public class CellsTest {

  @Test
  void generateCellsTest() {
    String seed = Integer.toBinaryString(30); // ="11110"
    List<Cell> cells = Cells.of(seed, new BinaryCellSupplier());
    assertEquals(5, cells.size());
    assertTrue(cells.get(0).hasState(BinaryState.ALIVE));
    assertTrue(cells.get(2).hasState(BinaryState.ALIVE));
    assertFalse(cells.get(4).hasState(BinaryState.ALIVE));
  }

  @Test
  void generateCellSpacesTest() {
    String seed = "1011001";
    List<Cell> cells = Cells.of(seed, new BinaryCellSupplier());
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
