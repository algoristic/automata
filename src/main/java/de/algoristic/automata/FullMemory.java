package de.algoristic.automata;

import java.util.ArrayList;
import java.util.List;
import de.algoristic.automata.core.Generation;

public class FullMemory implements Memory {

  private final List<Generation> generations = new ArrayList<>();

  public FullMemory(Generation generation) {
    generations.add(generation);
  }

  @Override
  public Generation getLastGeneration() {
    int lastGenerationIndex = (generations.size() - 1);
    return generations.get(lastGenerationIndex);
  }

  @Override
  public void add(Generation generation) {
    generations.add(generation);
  }

  @Override
  public List<Generation> getSavedGenerations() {
    return generations;
  }
}
