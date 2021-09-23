package de.algoristic.automata.evolution;

import java.util.List;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.error.CriticalAutomatonException;

public class Patterns {

  private final List<RulePattern> patterns;

  public Patterns(final List<RulePattern> patterns) {
    this.patterns = patterns;
  }

  public int size() {
    return patterns.size();
  }

  @Override
  public String toString() {
    return "Patterns [patterns=" + patterns + "]";
  }

  public Cell getOffspring(Pattern neighborhood) {
    for (RulePattern rulePattern : patterns) {
      if (rulePattern.matches(neighborhood)) {
        return rulePattern.getResultCell();
      }
    }
    throw new CriticalAutomatonException(
        "found no pattern matching neighborhood. this should never happen.");
  }
}
