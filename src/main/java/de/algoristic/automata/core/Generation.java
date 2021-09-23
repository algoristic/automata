package de.algoristic.automata.core;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import de.algoristic.automata.core.util.Cells;

public class Generation implements Iterable<Cell> {

  private final List<CellSpace> cellSpaces;
  private final int verticalSpace;

  private Generation(final List<CellSpace> cellSpaces, int verticalSpace) {
    this.cellSpaces = cellSpaces;
    this.verticalSpace = verticalSpace;
  }

  public static Generation getGeneration(String seed) {
    List<Cell> cells = Cells.of(seed);
    return getGeneration(cells, 1);
  }

  public static Generation getGeneration(List<String> grid) {
    List<Cell> cells = Cells.of(grid);
    return getGeneration(cells, grid.size());
  }

  public static Generation getGeneration(List<Cell> cells, int verticalSpace) {
    List<CellSpace> cellSpaces = Cells.wrap(cells);
    return new Generation(cellSpaces, verticalSpace);
  }

  public String toBinaryString() {
    return cellSpaces.stream()
      .map(CellSpace::toString)
      .collect(Collectors.joining());
  }

  public int size() {
    return cellSpaces.size();
  }

  public int getVerticalSpace() {
    return verticalSpace;
  }

  public Cell get(int index) {
    CellSpace cellSpace = cellSpaces.get(index);
    return cellSpace.getContent();
  }

  @Override
  public Iterator<Cell> iterator() {
    return cellSpaces.stream()
      .map(CellSpace::getContent)
      .iterator();
  }

  @Override
  public String toString() {
    return cellSpaces.toString();
  }
}
