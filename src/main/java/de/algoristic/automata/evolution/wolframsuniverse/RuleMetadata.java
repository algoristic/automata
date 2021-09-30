package de.algoristic.automata.evolution.wolframsuniverse;

public class RuleMetadata {

  private final int numberOfPatterns;
  private final int sizeOfNeighborhood;
  private final int ruleSpace;
  private final int cellsBeforeCenter;
  private final int centerIndex;
  private final int cellsAfterCenter;

  public RuleMetadata(
      int numberOfPatterns,
      int sizeOfNeighborhood,
      int ruleSpace,
      int cellsBeforeCenter,
      int centerIndex,
      int cellsAfterCenter) {
    this.numberOfPatterns = numberOfPatterns;
    this.sizeOfNeighborhood = sizeOfNeighborhood;
    this.ruleSpace = ruleSpace;
    this.cellsBeforeCenter = cellsBeforeCenter;
    this.centerIndex = centerIndex;
    this.cellsAfterCenter = cellsAfterCenter;
  }

  public int getNumberOfPatterns() {
    return numberOfPatterns;
  }

  public int getSizeOfNeighborhood() {
    return sizeOfNeighborhood;
  }

  public int getRuleSpace() {
    return ruleSpace;
  }

  public int getCellsBeforeCenter() {
    return cellsBeforeCenter;
  }

  public int getCenterIndex() {
    return centerIndex;
  }

  public int getCellsAfterCenter() {
    return cellsAfterCenter;
  }
}
