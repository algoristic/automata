package de.algoristic.automata.evolution.turmites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class TurmiteRuleParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "RL, 2, 10, 100",
      "LLRR, 4, 10, 100",
      "LLRRRLRLRLLR, 12, 100, 1000",
      "LLRRULRLRLUR, 12, 100, 1000",
      "LLRRLKLRRLL, 10, 100, 1000",
      "LLRRLLRLL, 9, 10, 100"
  })
  void parseRuleMetadata(String ruleString, int expectedCycles, int expectedDirectionBase, int expectedAntBase) {
    TurmitesRuleParser parser = new TurmitesRuleParser(ruleString);
    TurmitesRuleMetadata metadata = parser.parseMetadata();
    assertEquals(expectedCycles, metadata.getCycles());
    assertEquals(expectedDirectionBase, metadata.getDirectionBase());
    assertEquals(expectedAntBase, metadata.getAntBase());
  }

  @ParameterizedTest
  @CsvSource(value = {
      "RL, 0, R",
      "LLRR, 1, L",
      "LLRRRLRLRLLR, 9, L",
      "LLRRULRLRLUR, 10, U",
      "LLRRLKLRRLL, 5, L",
      "LLRRLLRLL, 8, L"
  })
  void parseMovementPattern(String ruleString, int cycle, char resultCommand) {
    TurmitesRuleParser parser = new TurmitesRuleParser(ruleString);
    TurmitesRuleMetadata metadata = parser.parseMetadata();
    MovementPattern pattern = parser.parseMovementPattern();
    TurmiteState turmiteState = new TurmiteState(cycle);
    FieldState state = new FieldState(turmiteState, metadata);
    Move move = pattern.getMove(state);
    Move expectedMove = Move.valueOf(resultCommand);
    assertEquals(expectedMove, move);
  }
}
