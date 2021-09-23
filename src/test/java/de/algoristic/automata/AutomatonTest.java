package de.algoristic.automata;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import de.algoristic.automata.evt.print.Printer;

public class AutomatonTest {

  static Path baseDirectory = Paths.get("C:/Users/male233/Desktop/automata/elementary");
  static Path chaoticDir = baseDirectory.resolve("chaotic");
  static Path simpleDir = baseDirectory.resolve("simple");

  static Printer chaoticPrinter = Printer
    .Builder
    .staticPrinter(chaoticDir)
    .build();
  static Printer simplePrinter = Printer
    .Builder
    .staticPrinter(simpleDir)
    .build();

  static List<Integer> simpleRules = Arrays.asList(18, 22, 26, 30, 45, 60, 73, 75, 82, 86, 90, 101, 102, 105, 109, 110, 124, 126, 129, 135, 137, 146, 149, 150, 153, 154, 161, 165, 167, 169, 181, 182, 193, 195, 210, 214, 218, 225);
  static List<Integer> chaoticRules = Arrays.asList(18, 22, 30, 54, 60, 62, 90, 105, 106, 110, 122, 126, 146, 150);

  @Test
  void chaoticTest() {
    int width = 127;
    int height = width;
    chaoticRules.forEach(rule -> {
      Automaton automaton =
        Automaton.Builder
          .chaotic(width)
          .withRule(rule)
          .withRuntime(height)
          .build();
      automaton.registerFinishAutomationListener(chaoticPrinter);
      automaton.run();
    });
  }

  @Test
  void simpleTest() {
    int width = 151;
    int height = width;
    simpleRules.forEach(rule -> {
      Automaton automaton = 
        Automaton.Builder
          .simple(width)
          .withRule(rule)
          .withRuntime(height)
          .build();
      automaton.registerFinishAutomationListener(simplePrinter);
      automaton.run();
    });
  }
}
