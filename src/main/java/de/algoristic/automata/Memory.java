package de.algoristic.automata;

import java.util.List;
import de.algoristic.automata.core.Generation;

public interface Memory {

  Generation getLastGeneration();
  void add(Generation generation);
  List<Generation> getSavedGenerations();

}
