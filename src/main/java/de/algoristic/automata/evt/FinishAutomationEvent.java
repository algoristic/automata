package de.algoristic.automata.evt;

import java.util.List;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.core.Generation;

public class FinishAutomationEvent extends AbstractAutomatonEvent {

  private int rule;
  private List<Generation> generations;

  public FinishAutomationEvent(Automaton automaton, List<Generation> generations) {
    super();
    this.generations = generations;
    this.rule = automaton.getRule();
  }

  public List<Generation> getGenerations() {
    return generations;
  }

  public int getRule() {
    return rule;
  }
}
