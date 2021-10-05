package de.algoristic.automata.evolution.turmites;

import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.RuleSupplier;

public enum Rules implements RuleSupplier {

  RL, RLR, LLRR;

  @Override
  public String getRuleString() {
    return name();
  }

  @Override
  public Rule get() {
    return getRule();
  }

  public Turmites getRule() {
    String ruleString = getRuleString();
    return Turmites.getInstance(ruleString);
  }
}
