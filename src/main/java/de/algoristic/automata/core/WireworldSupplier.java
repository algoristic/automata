package de.algoristic.automata.core;

import de.algoristic.automata.evolution.RuleSupplier;
import de.algoristic.automata.evolution.wireworld.Wireworld;

public class WireworldSupplier implements RuleSupplier<Wireworld> {

  @Override
  public String getRuleString() {
    return "wireworld";
  }

  @Override
  public Wireworld get() {
    return new Wireworld();
  }
}
