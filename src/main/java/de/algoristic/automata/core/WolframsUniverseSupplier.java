package de.algoristic.automata.core;

import de.algoristic.automata.evolution.RuleSupplier;
import de.algoristic.automata.evolution.wolframsuniverse.ElementaryCellularAutomaton;

public class WolframsUniverseSupplier implements RuleSupplier<ElementaryCellularAutomaton> {

  final private int decimalRule;

  public WolframsUniverseSupplier(int decimalRule) {
    this.decimalRule = decimalRule;
  }

  @Override
  public String getRuleString() {
    return String.valueOf(decimalRule);
  }

  @Override
  public ElementaryCellularAutomaton get() {
    return ElementaryCellularAutomaton.getInstance(decimalRule);
  }

}
