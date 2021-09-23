package de.algoristic.automata.evolution;

import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Neighborhood;

public interface Rule {

  Cell getOffspring(Neighborhood neighborhood);
  Neighborhood getNeighborhood(Cell cell, NeighborhoodParameters parameters);

}
