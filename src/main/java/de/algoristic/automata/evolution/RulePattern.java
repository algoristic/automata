package de.algoristic.automata.evolution;

import java.util.List;
import de.algoristic.automata.core.Cell;

public class RulePattern implements Pattern {

  private final List<Cell> cellPattern;
  private final Cell resultCell;

  public RulePattern(final List<Cell> cellPattern, final Cell resultCell) {
    this.cellPattern = cellPattern;
    this.resultCell = resultCell;
  }

  public Cell getResultCell() {
    return new Cell(resultCell);
  }

  @Override
  public int size() {
    return cellPattern.size();
  }

  @Override
  public List<Cell> getCells() {
    return cellPattern;
  }

  @Override
  public String toString() {
    return "Pattern [cellPattern=" + cellPattern + ", resultCell=" + resultCell + "]";
  }
}
