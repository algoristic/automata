package de.algoristic.automata.evolution.turmites;

import java.util.List;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.Neighborhood;
import de.algoristic.automata.core.State;
import de.algoristic.automata.evolution.NeighborhoodParameters;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.dimensional.Grid;
import de.algoristic.automata.evolution.dimensional.Point;

public class Turmites implements Rule {

  private final MovementPattern movementPattern;
  private final TurmitesRuleMetadata metadata;

  private boolean unlimitedSpace = false;

  public Turmites(MovementPattern movementPattern, TurmitesRuleMetadata metadata) {
    this.movementPattern = movementPattern;
    this.metadata = metadata;
  }

  @Override
  public Cell getOffspring(Neighborhood neighborhood) {
    TurmiteNeighborhood turmiteNeighborhood = (TurmiteNeighborhood) neighborhood;
    Cell cell = turmiteNeighborhood.getCell();
    State state = cell.getState();
    TurmiteState turmiteState = new TurmiteState(state);
    FieldState fieldState = new FieldState(turmiteState, metadata);
    if(AntState.isAlive(state, metadata)) { // the ant just moves away...
      return new Cell(new TurmiteState(fieldState));
    } else {
      if(turmiteNeighborhood.hasNeighbor()) {
        // move ant to cell (if moving towards this direction)
        Cell ant = turmiteNeighborhood.getNeighbor();
        State genericState = ant.getState();
        TurmiteState antTurmiteState = new TurmiteState(genericState);
        AntState antState = new AntState(antTurmiteState, metadata);
        Point thisPosition = turmiteNeighborhood.getCellPosition();
        Point antPosition = turmiteNeighborhood.getNeighborPosition();
        Direction direction = antState.getDirection();
        if(antMovesInThisDirection(thisPosition, antPosition, direction)) {
          // rotate ant based on color (cycle)
          Move move = movementPattern.getMove(fieldState);
          Direction newDirection = move.modify(direction);
          TurmiteState newTurmiteState = new TurmiteState(turmiteState);
          FieldState newFieldState = new FieldState(newTurmiteState, metadata);
          // flip color
          newFieldState.flipField();
          AntState newState = new AntState(newTurmiteState, metadata);
          newState.setAliveAnt(newDirection);
          return new Cell(newTurmiteState);
        } else { // leave cell as is
          return new Cell(new TurmiteState(state));
        }
      } else {
        return new Cell(new TurmiteState(state));
      }
    }
  }

  @Override
  public Neighborhood getNeighborhood(Cell cell, NeighborhoodParameters parameters) {
    int position = parameters.getCurrentCellIndex();
    Generation generation = parameters.getGeneration();
    Grid grid = Grid.fromGeneration(generation);
    Point cellPosition = grid.transpose(position, unlimitedSpace);
    List<Point> vonNeumannNeighborhood = cellPosition.getVonNeumannNeiborhood();

    Cell ant = null;
    Point antPosition = null;

    for(Point point : vonNeumannNeighborhood) {
      int index = point.transposeToIndex();
      Cell neighbor = generation.get(index);
      State state = neighbor.getState();
      if(AntState.isAlive(state, metadata)) {
        ant = neighbor;
        antPosition = point;
        break;
      }
    }

    if(ant == null) {
      return new TurmiteNeighborhood(cell, cellPosition);
    } else {
      return new TurmiteNeighborhood(cell, ant, cellPosition, antPosition);
    }
  }

  public TurmitesRuleMetadata getMetadata() {
    return metadata;
  }

  public void setSpaceUnlimited(boolean unlimitedSpace) {
    this.unlimitedSpace = unlimitedSpace;
  }

  private boolean antMovesInThisDirection(Point thisPosition, Point antPosition, Direction direction) {
    return direction.isOnCourse(antPosition, thisPosition);
  }

  public static Turmites getInstance(String ruleString) {
    TurmitesRuleParser parser = new TurmitesRuleParser(ruleString);
    TurmitesRuleMetadata metadata = parser.parseMetadata();
    MovementPattern pattern = parser.parseMovementPattern();
    return new Turmites(pattern, metadata);
  }
}
