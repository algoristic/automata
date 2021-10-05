package de.algoristic.automata.evolution.turmites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurmitesRuleParser {

  static List<Character> validCommands;
  static {
    validCommands = new ArrayList<>();
    for (Move move : Move.values()) {
      Character command = move.getCommand();
      validCommands.add(command);
    }
  }

  private String ruleString;

  public TurmitesRuleParser(String ruleString) {
    init(ruleString);
  }

  void init(String ruleString) {
    StringBuffer buffer = new StringBuffer();
    for (Character command : ruleString.toCharArray()) {
      if (validCommands.contains(command)) {
        buffer.append(command);
      }
    }
    this.ruleString = buffer.toString();
  }

  public TurmitesRuleMetadata parseMetadata() {
    int cycles = ruleString.length();
    int cyclesBase = String.valueOf(cycles).length();
    int directionBase = (int) Math.pow(10, cyclesBase);
    int antBase = (int) Math.pow(10, ++cyclesBase);
    return new TurmitesRuleMetadata(cycles, directionBase, antBase);
  }

  public MovementPattern parseMovementPattern() {
    Map<Integer, Move> moves = new HashMap<>();
    for(int cycle = 0; cycle < ruleString.length(); cycle++) {
      Character command = ruleString.charAt(cycle);
      Move move = Move.valueOf(command);
      moves.put(cycle, move);
    }
    return new MovementPattern(moves);
  }

}
