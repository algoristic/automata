package de.algoristic.automata.evolution;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.Neighborhood;

public class GameOfLifeRuleTest {

  @Test
  void gameOfLifeTest() {
    List<String> grid = Arrays.asList(
      "0100000",
      "0010000",
      "0001001",
      "0000011");
    String seed = grid.stream().collect(Collectors.joining());
    Generation generation = Generation.getBinaryGeneration(seed, grid.size());
    Rule gameOfLife = new GameOfLifeRule(Arrays.asList(2, 3), Arrays.asList(3));
    {
      int idx = 9;
      NeighborhoodParameters params = new NeighborhoodParameters(gameOfLife, generation, idx);
      Cell c = generation.get(idx);
      Neighborhood neighborhood = gameOfLife.getNeighborhood(c, params);
      Cell successor = gameOfLife.getOffspring(neighborhood);
      assertTrue(successor.hasState(BinaryState.ALIVE));
    }
    {
      int idx = 19;
      NeighborhoodParameters params = new NeighborhoodParameters(gameOfLife, generation, idx);
      Cell c = generation.get(idx);
      Neighborhood neighborhood = gameOfLife.getNeighborhood(c, params);
      Cell successor = gameOfLife.getOffspring(neighborhood);
      assertTrue(successor.hasState(BinaryState.ALIVE));
    }
    {
      int idx = 1;
      NeighborhoodParameters params = new NeighborhoodParameters(gameOfLife, generation, idx);
      Cell c = generation.get(idx);
      Neighborhood neighborhood = gameOfLife.getNeighborhood(c, params);
      Cell successor = gameOfLife.getOffspring(neighborhood);
      assertFalse(successor.hasState(BinaryState.ALIVE));
    }
  }
}
