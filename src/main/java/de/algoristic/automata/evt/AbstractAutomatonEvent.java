package de.algoristic.automata.evt;

import de.algoristic.automata.evolution.Rule;

public abstract class AbstractAutomatonEvent implements AutomatonEvent {

  private final long timestamp;
  private final Rule rule;

  public AbstractAutomatonEvent(Rule rule) {
    this.timestamp = System.currentTimeMillis();
    this.rule = rule;
  }

  @Override
  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public Rule getRule() {
    return rule;
  }
}
