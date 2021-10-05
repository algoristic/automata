package de.algoristic.automata.evolution;

public interface RuleSupplier<T extends Rule> {

  String getRuleString();
  T get();

}
