package de.algoristic.automata.evolution.util;

import java.util.ArrayList;
import java.util.List;
import de.algoristic.automata.core.BinaryCellSupplier;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.CellSupplier;
import de.algoristic.automata.core.util.Cells;
import de.algoristic.automata.evolution.Patterns;
import de.algoristic.automata.evolution.RuleMetadata;
import de.algoristic.automata.evolution.RulePattern;
import de.algoristic.automata.util.BinaryStringUtils;

public class ElementaryRuleParser {

  private final int decimalRule;

  private Patterns patterns;
  private RuleMetadata metadata;
  private CellSupplier supplier;

  public ElementaryRuleParser(int decimalRule) {
    this.decimalRule = decimalRule;
    this.supplier = new BinaryCellSupplier();
  }

  public Patterns parseRulePatterns() {
    if (patterns == null) {
      List<RulePattern> patternList = new ArrayList<>();
      RuleMetadata metadata = this.calculateRuleMetadata();
      int numberOfPatterns = metadata.getNumberOfPatterns();
      int neighborHood = metadata.getSizeOfNeighborhood();
      String binaryRuleString = BinaryStringUtils.getBinaryString(decimalRule, numberOfPatterns);
      int cellPatternValue = (numberOfPatterns - 1);
      for (int index = 0; index < numberOfPatterns; index++) {
    	String cellPatternTemplate = BinaryStringUtils.getBinaryString(cellPatternValue, neighborHood);
    	List<Cell> cellPattern = Cells.of(cellPatternTemplate, supplier);
        char resultChar = binaryRuleString.charAt(index);
        Cell resultCell = supplier.apply(resultChar);
        RulePattern pattern = new RulePattern(cellPattern, resultCell);
        patternList.add(pattern);
        cellPatternValue--;
      }
      patterns = new Patterns(patternList);
    }
    return patterns;
  }

  public RuleMetadata calculateRuleMetadata() {
    if (metadata == null) {
      int numberOfPatterns = 0;
      int sizeOfNeighborhood = 2;
      int ruleSpace = 0;
      int centerIndex = 0;
      while (decimalRule >= ruleSpace) {
        sizeOfNeighborhood++;
        numberOfPatterns = (int) Math.pow(2, sizeOfNeighborhood);
        ruleSpace = (int) Math.pow(2, numberOfPatterns);
        centerIndex = sizeOfNeighborhood / 2;
      }
      int cellsBeforeCenter = centerIndex;
      int cellsAfterCenter = (sizeOfNeighborhood - (centerIndex + 1));
      metadata = new RuleMetadata(numberOfPatterns, sizeOfNeighborhood, ruleSpace, cellsBeforeCenter, centerIndex, cellsAfterCenter);
    }
    return metadata;
  }
}
