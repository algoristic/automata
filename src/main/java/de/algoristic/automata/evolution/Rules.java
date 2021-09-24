package de.algoristic.automata.evolution;

public interface Rules {

  public static ElementaryRule RULE_110 = (ElementaryRule) ElementaryRule.getInstance(110);
  public static GameOfLifeRule CONWAYS_LIFE = (GameOfLifeRule) GameOfLifeRule.getInstance("B3/S23");
  public static GameOfLifeRule DIAMOEBA = (GameOfLifeRule) GameOfLifeRule.getInstance("B35678/S5678");
  
}
