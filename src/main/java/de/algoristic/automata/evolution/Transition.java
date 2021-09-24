package de.algoristic.automata.evolution;

import java.util.ArrayList;
import java.util.List;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.Neighborhood;

public class Transition {

  private final Generation parentalGeneration;
  private final Rule rule;

  private Generation filialGeneration;

  public Transition(Generation parentalGeneration, Rule rule) {
    this.parentalGeneration = parentalGeneration;
    this.rule = rule;
  }

  public Generation produceFilialGeneration() {
    if (filialGeneration == null) {
      List<Cell> filialCells = new ArrayList<>();
      for(int index = 0; index < parentalGeneration.size(); index++) {
        Cell ancestor = parentalGeneration.get(index);
        NeighborhoodParameters parameters = new NeighborhoodParameters(rule, parentalGeneration, index);
        Neighborhood neighborhood = ancestor.getNeighborhood(parameters);
        Cell successor = rule.getOffspring(neighborhood);
        if(ancestor.isDirectSuccessor(successor)) {
          successor = ancestor.stayAlive();
        }
        filialCells.add(successor);
      }
      int verticalSpace = parentalGeneration.getVerticalSpace();
      filialGeneration = Generation.getGeneration(filialCells, verticalSpace);
    }
    return filialGeneration;
  }
}
