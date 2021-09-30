package de.algoristic.automata.evolution;

public enum Rules {

  SEEDS("B2/S"),
  CONWAYS_LIFE("B3/S23"),
  EIGHT_LIFE("B3/S238"),
  CORAL("B3/S45678"),
  THREE_FOUR_Life("B34/S34"),
  BACTERIA("B34/S456"),
  BLINKERS("B345/S2"),
  ASSIMILATION("B345/S4567"),
  DIAMOEBA("B35678/S5678"),
  AMOEBA("B357/S1358"),
  STAINS("B3678/S235678"),
  DAY_AND_NIGHT("B3678/S34678");

  private String ruleString;

  private Rules(String ruleString) {
    this.ruleString = ruleString;
  }

  public String getRuleString() {
    return ruleString;
  }

  public Rule get() {
    return GameOfLife.getInstance(ruleString);
  }

  public static boolean contains(String ruleString) {
    for (Rules rule : values()) {
      if (rule.ruleString.equalsIgnoreCase(ruleString))
        return true;
    }
    return false;
  }

  public static String get(String ruleString) {
    for (Rules rule : values()) {
      if (rule.ruleString.contains(ruleString)) {
        return rule.name();
      }
    }
    return null;
  }
}
