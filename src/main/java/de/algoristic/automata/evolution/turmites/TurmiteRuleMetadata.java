package de.algoristic.automata.evolution.turmites;

public class TurmiteRuleMetadata {

  private int cycles;
  private int directionBase;
  private int antBase;

  TurmiteRuleMetadata(int cycles, int directionBase, int antBase) {
    this.cycles = cycles;
    this.directionBase = directionBase;
    this.antBase = antBase;
  }

  int getCycles() {
    return cycles;
  }

  int getDirectionBase() {
    return directionBase;
  }

  int getAntBase() {
    return antBase;
  }
}
