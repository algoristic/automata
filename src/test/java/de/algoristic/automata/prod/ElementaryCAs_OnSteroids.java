package de.algoristic.automata.prod;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.ColorSet;
import de.algoristic.automata.printer.Coolors;
import de.algoristic.automata.printer.Printer;
import de.algoristic.automata.prod.util.Automation;

public class ElementaryCAs_OnSteroids {

  Path destination = Paths.get("C:/Users/male233/Documents/MEGA/Bilder/automata/Elementary CAs/On Steroids");

  @Automation
  void rule110() {
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.Sets.colorful)
      .withMapping(BinaryState.ALIVE, Coolors.charcoal)
      .withBackground(Coolors.platinum)
      .withFrameColor(Coolors.charcoal)
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(8)
      .withScaling(2)
      .withFrameWidth(8*5)
      .withBorder(0)
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(110)
      .chaotic(75)
      .withRuntime(75)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }

  @Automation
  void rule126() {
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, new ColorSet(
        new Color(148, 210, 189),
        new Color(233, 216, 166),
        Coolors.imperialRed))
      .withMapping(BinaryState.ALIVE, new Color(0, 18, 25))
      .withBackground(Coolors.platinum)
      .withFrameColor(new Color(0, 18, 25))
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(8)
      .withScaling(2)
      .withFrameWidth(8*5)
      .withBorder(0)
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(126)
      .chaotic(75)
      .withRuntime(75)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }

  @Automation
  void rule90() {
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.Sets.lightBlues)
      .withMapping(BinaryState.ALIVE, Coolors.richBlackFOGRA29)
      .withBackground(Coolors.platinum)
      .withFrameColor(Coolors.richBlackFOGRA29)
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(8)
      .withScaling(2)
      .withFrameWidth(8*5)
      .withBorder(0)
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(90)
      .chaotic(75)
      .withRuntime(75)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }
}
