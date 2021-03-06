package de.algoristic.automata.evolution.gameoflife;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import de.algoristic.automata.core.BinaryCellSupplier;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.Neighborhood;
import de.algoristic.automata.evolution.NeighborhoodParameters;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.io.StringSeed;

public class GameOfLifeRuleTest {

  @Test
  void gameOfLifeTest() {
    List<String> grid = Arrays.asList(
      "0100000",
      "0010000",
      "0001001",
      "0000011");
    String seed = grid.stream().collect(Collectors.joining());
    Generation generation = Generation.getGeneration(new StringSeed(seed, grid.size()), new BinaryCellSupplier());
    Rule gameOfLife = new GameOfLife(Arrays.asList(2, 3), Arrays.asList(3));
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
