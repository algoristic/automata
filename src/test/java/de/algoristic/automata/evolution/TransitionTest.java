package de.algoristic.automata.evolution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import de.algoristic.automata.core.BinaryCellSupplier;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evolution.Transition;
import de.algoristic.automata.evolution.gameoflife.GameOfLife;
import de.algoristic.automata.evolution.wolframsuniverse.ElementaryCellularAutomaton;
import de.algoristic.automata.io.StringSeed;

public class TransitionTest {

  @Test
  void elementaryTransitionTest() {
    Rule rule = ElementaryCellularAutomaton.getInstance(30);
    Generation parentalGeneration = Generation.getGeneration(new StringSeed("0011100", 1), new BinaryCellSupplier());
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
    Rule rule = GameOfLife.getInstance("B3/S23");
    Generation parentalGeneration = Generation.getGeneration(new StringSeed("010010010", 3), new BinaryCellSupplier());
    Transition transition = new Transition(parentalGeneration, rule);
    Generation filialGeneration = transition.produceFilialGeneration();
    String s = "";
    for (Cell cell : filialGeneration) {
      s += cell;
    }
    assertEquals("000111000", s);
  }
}
