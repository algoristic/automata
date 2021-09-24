package de.algoristic.automata.evt;

import de.algoristic.automata.evolution.Rule;

public interface AutomatonEvent {

  long getTimestamp();
  Rule getRule();
}
