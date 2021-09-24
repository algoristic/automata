package de.algoristic.automata.evolution;

import java.util.List;
import java.util.stream.Collectors;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.Neighborhood;
import de.algoristic.automata.evolution.dimensional.Grid;
import de.algoristic.automata.evolution.dimensional.Point;
import de.algoristic.automata.evolution.util.GameOfLifeRuleParser;

public class GameOfLifeRule implements Rule {

  private final List<Integer> stayAlivePossibilities;
  private final List<Integer> becomeAlivePossibilities;

  public GameOfLifeRule(
      List<Integer> stayAlivePossibilities,
      List<Integer> becomeAlivePossibilities) {
    this.stayAlivePossibilities = stayAlivePossibilities;
    this.becomeAlivePossibilities = becomeAlivePossibilities;
  }

  public static Rule getInstance(String ruleString) {
    GameOfLifeRuleParser parser = new GameOfLifeRuleParser(ruleString);
    return parser.parse();
  }

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
    int verticalSpace = generation.getVerticalSpace();
    Grid grid = Grid.fromOneDimension(generation.size(), verticalSpace);    
    Point cellPosition = grid.transpose(position);
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
      if (cell.isAlive()) {
        counter++;
      }
    }
    return counter;
  }

  public List<Integer> getStayAlivePossibilities() {
    return stayAlivePossibilities;
  }

  public List<Integer> getBecomeAlivePossibilities() {
    return becomeAlivePossibilities;
  }
}