package de.algoristic.automata.evolution.turmites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import de.algoristic.automata.core.State;

public class TurmiteStateTest {

  @Test
  void flipCycleTest() {
    TurmiteState turmiteState = new TurmiteState(121);
    TurmiteRuleMetadata metadata = new TurmiteRuleMetadata(2, 10, 100);
    FieldState state = new FieldState(turmiteState, metadata);
    state.flipField();
    assertEquals(0, state.getValue());
    assertEquals(120, turmiteState.getValue());
    state.flipField();
    assertEquals(1, state.getValue());
    assertEquals(121, turmiteState.getValue());
  }

  @Test
  void flipHigherCycleTest() {
    TurmiteState turmiteState = new TurmiteState(1211);
    TurmiteRuleMetadata metadata = new TurmiteRuleMetadata(14, 100, 1000);
    FieldState state = new FieldState(turmiteState, metadata);
    state.flipField();
    assertEquals(12, state.getValue());
    assertEquals(1212, turmiteState.getValue());
    state.flipField();
    assertEquals(13, state.getValue());
    assertEquals(1213, turmiteState.getValue());
    state.flipField();
    assertEquals(0, state.getValue());
    assertEquals(1200, turmiteState.getValue());
  }

  @Test
  void fieldWithoutAntTest() {
    TurmiteState turmiteState = new TurmiteState(11);
    TurmiteRuleMetadata metadata = new TurmiteRuleMetadata(14, 100, 1000);
    FieldState state = new FieldState(turmiteState, metadata);
    state.flipField();
    assertEquals(12, state.getValue());
    assertEquals(12, turmiteState.getValue());
  }

  @Test
  void aliveAntTest() {
    TurmiteState turmiteState = new TurmiteState(121);
    TurmiteRuleMetadata metadata = new TurmiteRuleMetadata(2, 10, 100);
    AntState state = new AntState(turmiteState, metadata);
    assertTrue(state.isAliveAnt());
  }

  @Test
  void stateMatchingTest() {
    TurmiteRuleMetadata metadata = new TurmiteRuleMetadata(2, 10, 100);
    State state_1 = new TurmiteState(121);
    State state_2 = new TurmiteState(1);
    assertTrue(AntState.isAlive(state_1, metadata));
    assertFalse(AntState.isAlive(state_2, metadata));
  }

  @Test
  void deadAntTest() {
    TurmiteState turmiteState = new TurmiteState(11);
    TurmiteRuleMetadata metadata = new TurmiteRuleMetadata(14, 100, 1000);
    AntState state = new AntState(turmiteState, metadata);
    assertFalse(state.isAliveAnt());
  }
}
