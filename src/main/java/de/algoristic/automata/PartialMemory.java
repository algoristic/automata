package de.algoristic.automata;

import java.util.Arrays;
import java.util.List;
import de.algoristic.automata.core.Generation;

public class PartialMemory implements Memory {

  private Generation memory;

  public PartialMemory(Generation generation) {
    this.memory = generation;
  }

  @Override
  public Generation getLastGeneration() {
    return memory;
  }

  @Override
  public void add(Generation generation) {
    memory = generation;
  }

  @Override
  public List<Generation> getSavedGenerations() {
    return Arrays.asList(memory);
  }

}
