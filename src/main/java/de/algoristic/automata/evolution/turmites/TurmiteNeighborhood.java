package de.algoristic.automata.evolution.turmites;

import java.util.Arrays;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Neighborhood;
import de.algoristic.automata.evolution.dimensional.Point;

public class TurmiteNeighborhood extends Neighborhood {

  private final Point cellPosition;
  private final Point neighborPosition;

  public TurmiteNeighborhood(Cell cell, Point cellPosition) {
    super(cell, Arrays.asList());
    this.cellPosition = cellPosition;
    this.neighborPosition = null;
  }

  public TurmiteNeighborhood(Cell cell, Cell neighbor, Point cellPosition, Point neighborPosition) {
    super(cell, Arrays.asList(neighbor));
    this.cellPosition = cellPosition;
    this.neighborPosition = neighborPosition;
  }

  boolean hasNeighbor() {
    return !neightbors.isEmpty();
  }

  Cell getNeighbor() {
    return neightbors.get(0);
  }

  Point getCellPosition() {
    return cellPosition;
  }

  Point getNeighborPosition() {
    return neighborPosition;
  }
}
