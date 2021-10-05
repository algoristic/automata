package de.algoristic.automata.evolution.wolframsuniverse;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.printer.Printer;

public class WolframsUniverseTest {

  static Path baseDirectory = Paths.get("C:/Users/male233/.automata-tests");
  static Path chaoticDir = baseDirectory.resolve("elementary-chaotic");
  static Path simpleDir = baseDirectory.resolve("elementary-simple");

  static {
    chaoticDir.toFile().mkdirs();
    simpleDir.toFile().mkdirs();
  }

  static Color CYAN = new Color(51, 204, 204);
  static Printer<FinishAutomationEvent> chaoticPrinter = new Printer.Builder(chaoticDir).buildElementaryPrinter();
  static Printer<FinishAutomationEvent> simplePrinter = new Printer.Builder(simpleDir).buildElementaryPrinter();

  static List<Integer> simpleRules = Arrays.asList(18, 22, 26, 30, 45, 60, 73, 75, 82, 86, 90, 101, 102, 105, 109, 110, 124, 126, 129, 135, 137, 146, 149, 150, 153, 154, 161, 165, 167, 169, 181, 182, 193, 195, 210, 214, 218, 225);
  static List<Integer> chaoticRules = Arrays.asList(18, 22, 30, 54, 60, 62, 90, 105, 106, 110, 122, 126, 146, 150);

  @Test
  void chaoticTest() {
    int width = 101;
    int height = width;
    chaoticRules.forEach(rule -> {
      Automaton automaton =
        Automaton.Builder
          .wolframsUniverse(rule)
          .chaotic(width)
          .withRuntime(height)
          .build();
      automaton.registerFinishAutomationListener(chaoticPrinter);
      automaton.run();
    });
  }

  @Test
  void simpleTest() {
    int width = 101;
    int height = width;
    simpleRules.forEach(rule -> {
      Automaton automaton = 
        Automaton.Builder
          .wolframsUniverse(rule)
          .simple(width)
          .withRuntime(height)
          .build();
      automaton.registerFinishAutomationListener(simplePrinter);
      automaton.run();
    });
  }
}
