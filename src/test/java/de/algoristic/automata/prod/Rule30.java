package de.algoristic.automata.prod;

import java.nio.file.Path;
import java.nio.file.Paths;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.Coolors;
import de.algoristic.automata.printer.Printer;
import de.algoristic.automata.prod.util.Automation;

public class Rule30 {

  Path desktop = Paths.get("C:/Users/male233/.automata/rule30");

  ColorModel colors = new Coolors()
    .withMapping(BinaryState.DEAD, Coolors.transparent)
    .withMapping(BinaryState.ALIVE, Coolors.oxfordBlue)
    .withBackground(Coolors.transparent)
    .build();

  Printer<FinishAutomationEvent> listener = new Printer.Builder(desktop)
    .withColorMapping(colors)
    .withFrameWidth(0)
    .withCellSize(9)
    .withScaling(2)
    .buildElementaryPrinter();

  @Automation
  void rule30() {
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(30)
      .chaotic(75)
      .withRuntime(75)
      .build();
    automaton.registerFinishAutomationListener(listener);
    automaton.run();
  }
}
