package de.algoristic.automata.evolution;

import java.util.List;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.Neighborhood;

public class GameOfLifeRule implements Rule {

  private List<Integer> stayAlivePossibilities;
  private List<Integer> becomeAlivePossibilities;
  
  @Override
  public Cell getOffspring(Neighborhood neighborhood) {
    int amountOfAliveCells = countAliveCells(neighborhood);
    Cell cell = neighborhood.getCell();
    List<Integer> possibilities;
    if(cell.isAlive()) {
      possibilities = stayAlivePossibilities;
    } else {
      possibilities = becomeAlivePossibilities;
    }
    if(possibilities.contains(amountOfAliveCells)) {
      return new Cell(Cell.ALIVE);
    } else {
      return new Cell(Cell.DEAD);
    }
  }

  @Override
  public Neighborhood getNeighborhood(Cell cell, NeighborhoodParameters parameters) {
    int position = parameters.getCurrentCellIndex();
    Generation generation = parameters.getGeneration();
    int vericalSpace = generation.getVerticalSpace();
    // TODO weitermachen
    // TODO siehe auch Cells.of(...)
    return null;
  }

  private int countAliveCells(Neighborhood neighborhood) {
    int counter = 0;
    for(Cell cell: neighborhood) {
      if (cell.isAlive()) {
        counter++;
      }
    }
    return counter;
  }
}
