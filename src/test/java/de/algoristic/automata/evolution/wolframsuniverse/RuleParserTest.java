package de.algoristic.automata.evolution.wolframsuniverse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import de.algoristic.automata.evolution.gameoflife.GameOfLife;
import de.algoristic.automata.evolution.gameoflife.GameOfLifeRuleParser;
import de.algoristic.automata.evolution.wolframsuniverse.ElementaryRuleParser;
import de.algoristic.automata.evolution.wolframsuniverse.Patterns;
import de.algoristic.automata.evolution.wolframsuniverse.RuleMetadata;

public class RuleParserTest {

  @ParameterizedTest
  @CsvSource(value = {
    "0, 8, 256, 3, 1",
    "30, 8, 256, 3, 1",
    "90, 8, 256, 3, 1",
    "110, 8, 256, 3, 1",
    "255, 8, 256, 3, 1",
    "256, 16, 65536, 4, 2",
    "1337, 16, 65536, 4, 2",
    "65535, 16, 65536, 4, 2"})
  void testMetadataGeneration(
      int rule,
      int expectedNumOfPatterns,
      int expectedRuleSpace,
      int expectedNeighborhood,
      int expectedCenterIndex) {
    ElementaryRuleParser ruleParser = new ElementaryRuleParser(rule);
    RuleMetadata metadata = ruleParser.calculateRuleMetadata();
    assertEquals(expectedNumOfPatterns, metadata.getNumberOfPatterns());
    assertEquals(expectedRuleSpace, metadata.getRuleSpace());
    assertEquals(expectedNeighborhood, metadata.getSizeOfNeighborhood());
    assertEquals(expectedCenterIndex, metadata.getCenterIndex());
  }

  @ParameterizedTest
  @CsvSource(value = {
    "0, 8",
    "30, 8",
    "90, 8",
    "110, 8",
    "255, 8",
    "256, 16",
    "1337, 16",
    "65535, 16",
    "65536, 32"})
  void testPatternParsing(int decimalRule, int expectedPatternAmount) {
    ElementaryRuleParser ruleParser = new ElementaryRuleParser(decimalRule);
    Patterns patterns = ruleParser.parseRulePatterns();
    assertEquals(expectedPatternAmount, patterns.size());
  }

  @ParameterizedTest
  @CsvSource(value = {
      "23/3, 2, 1",
      "B3/S23, 2, 1",
      "B234/S, 0, 3",
      "/234, 0, 3"
  })
  void gameOfLifeRuleParserTest(String ruleString, int stayAliveNum, int becomAliveNum) {
    GameOfLifeRuleParser parser = new GameOfLifeRuleParser(ruleString);
    GameOfLife rule = parser.parse();
    assertEquals(stayAliveNum, rule.getStayAlivePossibilities().size());
    assertEquals(becomAliveNum, rule.getBecomeAlivePossibilities().size());
  }
}
