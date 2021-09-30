package de.algoristic.automata.evolution.util;

import java.util.ArrayList;
import java.util.List;
import de.algoristic.automata.evolution.GameOfLife;

public class GameOfLifeRuleParser {

  private final String ruleString;

  public GameOfLifeRuleParser(String ruleString) {
    this.ruleString = ruleString;
  }

  public GameOfLife parse() {
    String altRuleString = ruleString;
    boolean revert = false;
    if (altRuleString.startsWith("B")) revert = true;
    altRuleString = altRuleString.replace("B", " ");
    altRuleString = altRuleString.replace("S", " ");
    String[] arr = altRuleString.split("/");
    if (revert) {
      String tmp = arr[0];
      arr[0] = arr[1];
      arr[1] = tmp;
    }
    arr[0] = arr[0].replace(" ", "");
    arr[1] = arr[1].replace(" ", "");
    List<Integer> stayAlive = new ArrayList<>();
    List<Integer> becomeAlive = new ArrayList<>();
    for (int i = 0; i < arr[0].length(); i++) {
      char c = arr[0].charAt(i);
      String s = String.valueOf(c);
      int val = Integer.valueOf(s);
      stayAlive.add(val);
    }
    for (int i = 0; i < arr[1].length(); i++) {
      char c = arr[1].charAt(i);
      String s = String.valueOf(c);
      int val = Integer.valueOf(s);
      becomeAlive.add(val);
    }
    return new GameOfLife(stayAlive, becomeAlive);
  }

}
