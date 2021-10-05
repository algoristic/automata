package de.algoristic.automata.evolution.turmites;

public class TurmitesRuleMetadata {

  private int cycles;
  private int directionBase;
  private int antBase;

  TurmitesRuleMetadata(int cycles, int directionBase, int antBase) {
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
