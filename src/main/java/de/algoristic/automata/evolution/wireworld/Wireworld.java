package de.algoristic.automata.evolution.wireworld;

import static de.algoristic.automata.core.WireworldState.CONDUCTOR;
import static de.algoristic.automata.core.WireworldState.ELECTRON_HEAD;
import static de.algoristic.automata.core.WireworldState.ELECTRON_TAIL;
import static de.algoristic.automata.core.WireworldState.EMPTY;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.Neighborhood;
import de.algoristic.automata.core.State;
import de.algoristic.automata.evolution.NeighborhoodParameters;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.dimensional.Grid;
import de.algoristic.automata.evolution.dimensional.Point;


public class Wireworld implements Rule {

  private static Map<State, Function<List<Cell>, State>> transition;

  static {
    transition = new HashMap<>();
    transition.put(EMPTY, (ls) -> EMPTY);
    transition.put(ELECTRON_HEAD, (ls) -> ELECTRON_TAIL);
    transition.put(ELECTRON_TAIL, (ls) -> CONDUCTOR);
    transition.put(CONDUCTOR, (ls) -> {
      long count = ls.stream()
        .filter(cell -> cell.hasState(ELECTRON_HEAD))
        .count();
      if((count <= 2) && (count >= 1)) {
        return ELECTRON_HEAD;
      } else {
        return CONDUCTOR;
      }
    });
    
  }

  private boolean unlimitedSpace = false;

  public void setSpaceUnlimited(boolean unlimitedSpace) {
    this.unlimitedSpace = unlimitedSpace;
  }

  @Override
  public Cell getOffspring(Neighborhood neighborhood) {
    Cell cell = neighborhood.getCell();
    List<Cell> neighbors = neighborhood.getCells();
    State state = cell.getState();
    Function<List<Cell>, State> transitionFunction = transition.get(state);
    state = transitionFunction.apply(neighbors);
    return new Cell(state);
  }

  @Override
  public Neighborhood getNeighborhood(Cell cell, NeighborhoodParameters parameters) {
    int position = parameters.getCurrentCellIndex();
    Generation generation = parameters.getGeneration();
    Grid grid = Grid.fromGeneration(generation);
    Point cellPosition = grid.transpose(position, unlimitedSpace);
    List<Point> mooreNeighborhood = cellPosition.getMooreNeighborhood();
    List<Cell> neighbors = mooreNeighborhood.stream()
      .map(Point::transposeToIndex)
      .map(generation::get)
      .collect(Collectors.toList());
    return new Neighborhood(cell, neighbors);
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Wireworld@");
//    buffer.append(this.hashCode());
    return buffer.toString();
  }
}