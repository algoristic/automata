package de.algoristic.automata.evolution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evolution.ElementaryRule;
import de.algoristic.automata.evolution.Transition;

public class TransitionTest {

  @Test
  void elementaryTransitionTest() {
    Rule rule = ElementaryRule.getInstance(30);
    Generation parentalGeneration = Generation.getGeneration("0011100");
    Transition transition = new Transition(parentalGeneration, rule);
    Generation filialGeneration = transition.produceFilialGeneration();
    String s = "";
    for (Cell cell : filialGeneration) {
      s += cell;
    }
    assertEquals("0110010", s);
  }

  @Test
  void gameOfLifeTest() {
    Rule rule = GameOfLifeRule.getInstance("B3/S23");
    Generation parentalGeneration = Generation.getGeneration("010010010", 3);
    Transition transition = new Transition(parentalGeneration, rule);
    Generation filialGeneration = transition.produceFilialGeneration();
    String s = "";
    for (Cell cell : filialGeneration) {
      s += cell;
    }
    assertEquals("000111000", s);
  }
}
