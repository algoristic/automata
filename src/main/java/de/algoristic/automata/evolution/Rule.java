package de.algoristic.automata.evolution;

import de.algoristic.automata.core.Cell;
import de.algoristic.automata.evolution.util.RuleParser;

public class Rule {

  private final int decimalRule;
  private final RuleMetadata metadata;
  private final Patterns patterns;

  private Rule(final int decimalRule, final RuleMetadata metadata, final Patterns patterns) {
    this.decimalRule = decimalRule;
    this.metadata = metadata;
    this.patterns = patterns;
  }

  public static Rule getInstance(final int decimalRule) {
    RuleParser parser = new RuleParser(decimalRule);
    RuleMetadata metadata = parser.calculateRuleMetadata();
    Patterns patterns = parser.parseRulePatterns();
    return new Rule(decimalRule, metadata, patterns);
  }

  public Cell getOffspring(Pattern neighborhood) {
    return patterns.getOffspring(neighborhood);
  }

  public int getNumberOfPatterns() {
    return metadata.getNumberOfPatterns();
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

}
