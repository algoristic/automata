package de.algoristic.automata.evolution;

import java.util.List;
import de.algoristic.automata.core.Cell;

public interface Pattern {

  public List<Cell> getCells();

  public int size();

  public default boolean matches(Pattern other) {
    List<Cell> thisCells = this.getCells();
    List<Cell> otherCells = other.getCells();
    int size = this.size();
    if (size == other.size()) {
      for (int i = 0; i < size; i++) {
        Cell thisCell = thisCells.get(i);
        Cell otherCell = otherCells.get(i);
        if (!thisCell.matches(otherCell)) {
          return false;
        }
        ;
      }
      return true;
    } else {
      return false;
    }
  }
}
