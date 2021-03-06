package de.algoristic.automata.prod;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.ColorSet;
import de.algoristic.automata.printer.Coolors;
import de.algoristic.automata.printer.Printer;
import de.algoristic.automata.prod.util.Automation;

@DisplayName("Elementary CAs completely on steroids")
public class ElementaryCAs_OnSteroids {

  Path destination = Paths.get("C:/Users/male233/Documents/MEGA/Bilder/automata/Elementary CAs/On Steroids");
  int cellSize = 8;
  int scaling = 2;
  int frameWidth = 8*15;
  int borderWidth = 0;
  int size = 75;

  @Automation
  @DisplayName("Rule 18")
  void rule18() {
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, new Color(41, 50, 65))
      .withMapping(BinaryState.ALIVE, new Color(238, 108, 77))
      .withBackground(new Color(41, 50, 65))
      .withFrameColor(new Color(41, 50, 65))
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(cellSize)
      .withScaling(scaling)
      .withFrameWidth(frameWidth)
      .withBorder(borderWidth)
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(18)
      .chaotic(size*2)
      .withRuntime(size*2)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }

  @Automation
  @DisplayName("Rule 30")
  void rule30() {
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.Sets.colorful.remove(Coolors.charcoal))
      .withMapping(BinaryState.ALIVE, Coolors.charcoal)
      .withFrameColor(Coolors.charcoal)
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(cellSize)
      .withScaling(scaling)
      .withFrameWidth(frameWidth)
      .withBorder(borderWidth)
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(30)
      .simple(size)
      .withRuntime(size)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }

  @Automation
  @DisplayName("Rule 90")
  void rule90() {
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.Sets.lightBlues)
      .withMapping(BinaryState.ALIVE, Coolors.richBlackFOGRA29)
      .withFrameColor(Coolors.richBlackFOGRA29)
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(cellSize)
      .withScaling(scaling)
      .withFrameWidth(frameWidth)
      .withBorder(borderWidth)
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(90)
      .chaotic(size)
      .withRuntime(size)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }

  @Automation
  @DisplayName("Rule 110")
  void rule110() {
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.Sets.colorful.remove(Coolors.charcoal))
      .withMapping(BinaryState.ALIVE, Coolors.charcoal)
      .withFrameColor(Coolors.charcoal)
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(cellSize)
      .withScaling(scaling)
      .withFrameWidth(frameWidth)
      .withBorder(borderWidth)
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(110)
      .chaotic(size)
      .withRuntime(size)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }

  @Automation
  @DisplayName("Rule 126")
  void rule126() {
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, new ColorSet(
        new Color(148, 210, 189),
        new Color(233, 216, 166),
        Coolors.imperialRed))
      .withMapping(BinaryState.ALIVE, new Color(0, 18, 25))
      .withFrameColor(new Color(0, 18, 25))
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(cellSize)
      .withScaling(scaling)
      .withFrameWidth(frameWidth)
      .withBorder(borderWidth)
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(126)
      .chaotic(size)
      .withRuntime(size)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }
}
