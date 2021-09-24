package de.algoristic.automata.evt;

import de.algoristic.automata.evolution.Rule;

public class StartAutomationEvent extends AbstractAutomatonEvent {

  public StartAutomationEvent(Rule rule) {
    super(rule);
  }
}
