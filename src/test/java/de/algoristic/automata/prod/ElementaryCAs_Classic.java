package de.algoristic.automata.prod;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.Coolors;
import de.algoristic.automata.printer.Printer;
import de.algoristic.automata.prod.util.Automation;

public class ElementaryCAs_Classic {

  Path destination = Paths.get("C:/Users/male233/Documents/MEGA/Bilder/automata/Elementary CAs/Classic");
  List<Integer> rules = Arrays.asList(30, 54, 60, 90, 105, 106, 110, 122, 150);

  @Automation
  void completeSet() {
    rules.forEach(rule -> {
      ColorModel colors = new Coolors()
          .withMapping(BinaryState.DEAD, Coolors.richBlackFOGRA29)
          .withMapping(BinaryState.ALIVE, Coolors.platinum)
          .withBackground(Coolors.richBlackFOGRA29)
          .withFrameColor(Coolors.richBlackFOGRA29)
          .build();
        Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
          .withColorMapping(colors)
          .withCellSize(8)
          .withScaling(2)
          .withFrameWidth(8*10)
          .withBorder(2)
          .buildElementaryPrinter();
        Automaton automaton = Automaton.Builder
          .wolframsUniverse(rule)
          .chaotic(123)
          .withRuntime(123)
          .build();
        automaton.registerFinishAutomationListener(printer);
        automaton.run();
    });
  }
}
