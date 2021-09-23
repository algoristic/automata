package de.algoristic.automata.evolution;

import java.util.ArrayList;
import java.util.List;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;

public class Transition {

  private final Generation parentalGeneration;
  private final Rule appliedRule;

  private Generation filialGeneration;

  public Transition(Generation parentalGeneration, Rule appliedRule) {
    this.parentalGeneration = parentalGeneration;
    this.appliedRule = appliedRule;
  }

  public Generation produceFilialGeneration() {
    if (filialGeneration == null) {
      List<Cell> filialCells = new ArrayList<>();
      for (Cell cell : parentalGeneration) {
        Pattern neighborhood = cell.getNeighborhood(appliedRule);
        Cell filialCell = appliedRule.getOffspring(neighborhood);
        filialCells.add(filialCell);
      }
      filialGeneration = Generation.getGeneration(filialCells);
    }
    return filialGeneration;
  }
}
