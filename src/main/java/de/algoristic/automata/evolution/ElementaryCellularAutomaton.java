package de.algoristic.automata.evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Neighborhood;
import de.algoristic.automata.evolution.util.ElementaryRuleParser;

public class ElementaryCellularAutomaton implements Rule {

  private final int decimalRule;
  private final RuleMetadata metadata;
  private final Patterns patterns;

  private ElementaryCellularAutomaton(final int decimalRule, final RuleMetadata metadata, final Patterns patterns) {
    this.decimalRule = decimalRule;
    this.metadata = metadata;
    this.patterns = patterns;
  }

  public static Rule getInstance(final int decimalRule) {
    ElementaryRuleParser parser = new ElementaryRuleParser(decimalRule);
    RuleMetadata metadata = parser.calculateRuleMetadata();
    Patterns patterns = parser.parseRulePatterns();
    return new ElementaryCellularAutomaton(decimalRule, metadata, patterns);
  }

  @Override
  public Cell getOffspring(Neighborhood neighborhood) {
    return patterns.getOffspring(neighborhood);
  }

  @Override
  public Neighborhood getNeighborhood(Cell cell, NeighborhoodParameters parameters) {
    List<Cell> neighbors = new ArrayList<>();
    Cell previous = cell.getPrevious();
    for (int i = 0; i < this.getCellsBeforeCenter(); i++) {
      neighbors.add(previous);
      previous = previous.getPrevious();
    }
    Collections.reverse(neighbors);
    neighbors.add(cell);
    Cell next = cell.getNext();
    for (int i = 0; i < this.getCellsAfterCenter(); i++) {
      neighbors.add(next);
      next = next.getNext();
    }
    return new Neighborhood(cell, neighbors);
  }

  public int getSizeOfNeighborhood() {
    return metadata.getSizeOfNeighborhood();
  }

  public int getRuleSpace() {
    return metadata.getRuleSpace();
  }

  public int getDecimalRule() {
    return decimalRule;
  }

  public int getCenterIndex() {
    return metadata.getCenterIndex();
  }

  public int getCellsBeforeCenter() {
    return metadata.getCellsBeforeCenter();
  }

  public int getCellsAfterCenter() {
    return metadata.getCellsAfterCenter();
  }

  @Override
  public String toString() {
    return String.valueOf(decimalRule);
  }
}
