package de.algoristic.automata.evolution.turmites;

import de.algoristic.automata.evolution.RuleSupplier;

public enum Rules implements RuleSupplier<Turmites> {

  RL, RLR, LLRR;

  @Override
  public String getRuleString() {
    return name();
  }

  @Override
  public Turmites get() {
    String ruleString = getRuleString();
    return Turmites.getInstance(ruleString);
  }
}
