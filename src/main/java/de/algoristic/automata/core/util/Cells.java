package de.algoristic.automata.core.util;

import java.util.ArrayList;
import java.util.List;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.CellSpace;

public abstract class Cells {

  public static List<Cell> of(String seed) {
    List<Cell> cells = new ArrayList<>();
    for (int i = 0; i < seed.length(); i++) {
      char c = seed.charAt(i);
      Cell cell = new Cell(c);
      cells.add(cell);
    }
    return cells;
  }

  public static List<Cell> of(List<String> grid) {
    // TODO implement
    return null;
  }

  // TODO what to do, when generation empty? error or just nothing? ...
  public static List<CellSpace> wrap(List<Cell> cells) {
    List<CellSpace> cellSpaces = new ArrayList<>();
    for (int index = 0; index < cells.size(); index++) {
      Cell currentCell = cells.get(index);
      CellSpace currentCellSpace = new CellSpace(currentCell);
      cellSpaces.add(currentCellSpace);
      int amount = cellSpaces.size();
      {
        int previous = ((index == 0) ? (amount - 1) : (index - 1));
        CellSpace previousCellSpace = cellSpaces.get(previous);
        currentCellSpace.linkPrevious(previousCellSpace);
        previousCellSpace.LinkNext(currentCellSpace);
      }
      {
        int next = ((index == (amount - 1)) ? 0 : (index + 1));
        CellSpace nextCellSpace = cellSpaces.get(next);
        currentCellSpace.LinkNext(nextCellSpace);
        nextCellSpace.linkPrevious(currentCellSpace);
      }
    }
    return cellSpaces;
  }
}
