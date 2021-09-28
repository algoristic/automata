package de.algoristic.automata.core;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import de.algoristic.automata.core.util.Cells;
import de.algoristic.automata.io.Seed;

public class Generation implements Iterable<Cell> {

  private final List<CellSpace> cellSpaces;
  private final int verticalSpace;

  private Generation(final List<CellSpace> cellSpaces, int verticalSpace) {
    this.cellSpaces = cellSpaces;
    this.verticalSpace = verticalSpace;
  }

  public static Generation getGeneration(Seed seed, CellSupplier cellSupplier) {
    String content = seed.getContent();
    int verticalDimension = seed.getVerticalDimension();
    List<Cell> cells = Cells.of(content, cellSupplier);
    return getGeneration(cells, verticalDimension);
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
