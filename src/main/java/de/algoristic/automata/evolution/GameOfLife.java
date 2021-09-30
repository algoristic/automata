package de.algoristic.automata.evolution;

import java.util.List;
import java.util.stream.Collectors;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.Neighborhood;
import de.algoristic.automata.evolution.dimensional.Grid;
import de.algoristic.automata.evolution.dimensional.Point;
import de.algoristic.automata.evolution.util.GameOfLifeRuleParser;

public class GameOfLife implements Rule {

  private final List<Integer> stayAlivePossibilities;
  private final List<Integer> becomeAlivePossibilities;

  private boolean unlimitedSpace = false;

  public GameOfLife(
      List<Integer> stayAlivePossibilities,
      List<Integer> becomeAlivePossibilities) {
    this.stayAlivePossibilities = stayAlivePossibilities;
    this.becomeAlivePossibilities = becomeAlivePossibilities;
  }

  public static GameOfLife getInstance(String ruleString) {
    GameOfLifeRuleParser parser = new GameOfLifeRuleParser(ruleString);
    return parser.parse();
  }

  @Override
  public Cell getOffspring(Neighborhood neighborhood) {
    int amountOfAliveCells = countAliveCells(neighborhood);
    Cell cell = neighborhood.getCell();
    List<Integer> possibilities;
    if(cell.hasState(BinaryState.ALIVE)) {
      possibilities = stayAlivePossibilities;
    } else {
      possibilities = becomeAlivePossibilities;
    }
    if(possibilities.contains(amountOfAliveCells)) {
      return new Cell(BinaryState.ALIVE);
    } else {
      return new Cell(BinaryState.DEAD);
    }
  }

  @Override
  public Neighborhood getNeighborhood(Cell cell, NeighborhoodParameters parameters) {
    int position = parameters.getCurrentCellIndex();
    Generation generation = parameters.getGeneration();
    Grid grid = Grid.fromGeneration(generation);
    Point cellPosition = grid.transpose(position, unlimitedSpace);
    List<Point> mooreNeighborhood = cellPosition.getMooreNeighborhood();
    List<Cell> neighbors = mooreNeighborhood.stream()
      .map(Point::transposeToIndex)
      .map(generation::get)
      .collect(Collectors.toList());
    return new Neighborhood(cell, neighbors);
  }

  private int countAliveCells(Neighborhood neighborhood) {
    int counter = 0;
    for(Cell cell: neighborhood) {
      if (cell.hasState(BinaryState.ALIVE)) {
        counter++;
      }
    }
    return counter;
  }

  public void setSpaceUnlimited(boolean unlimitedSpace) {
    this.unlimitedSpace = unlimitedSpace;
  }

  public List<Integer> getStayAlivePossibilities() {
    return stayAlivePossibilities;
  }

  public List<Integer> getBecomeAlivePossibilities() {
    return becomeAlivePossibilities;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer("B");
    becomeAlivePossibilities.forEach(buffer::append);
    buffer.append("/S");
    stayAlivePossibilities.forEach(buffer::append);
    String result = buffer.toString();
    if(Rules.contains(result)) {
      result = Rules.get(result);
    } else {
      result = result.replace("/", "_");
    }
    return result;
  }
}
