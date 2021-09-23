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
      for (Cell ancestor : parentalGeneration) {
        Pattern neighborhood = ancestor.getNeighborhood(appliedRule);
        Cell successor = appliedRule.getOffspring(neighborhood);
        if(ancestor.isDirectSuccessor(successor)) {
          successor = ancestor.clone();
          successor.toNextGeneration();
        }
        filialCells.add(successor);
      }
      filialGeneration = Generation.getGeneration(filialCells);
    }
    return filialGeneration;
  }
}
