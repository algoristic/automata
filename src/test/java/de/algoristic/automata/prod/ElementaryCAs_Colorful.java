package de.algoristic.automata.prod;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.Coolors;
import de.algoristic.automata.printer.Printer;
import de.algoristic.automata.prod.util.Automation;
import de.algoristic.automata.prod.util.ProdUtils;

@DisplayName("Elementary CAs displayed with a beautiful color-mix")
public class ElementaryCAs_Colorful {

  Path destination = Paths.get("C:/Users/male233/Documents/MEGA/Bilder/automata/Elementary CAs/Colorful");

  int cellSize = 8;
  int scaling = 3;
  int frameWidth = 8*15;
  int borderWidth = 7;
  int size = 51;

  @Automation
  @DisplayName("CAs with random initial configuration")
  void chaotic() {
    Path target = destination.resolve("chaotic");
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.black)
      .withMapping(BinaryState.ALIVE, Coolors.Sets.colorful)
      .withBackground(Coolors.black)
      .withFrameColor(Coolors.black)
      .build();
    ProdUtils.printColorModel(target, colors);
    IntStream.range(0, 256).forEach(rule -> {
      Printer<FinishAutomationEvent> printer = new Printer.Builder(target)
        .withColorMapping(colors)
        .withCellSize(cellSize)
        .withScaling(scaling)
        .withFrameWidth(frameWidth)
        .withBorder(borderWidth)
        .buildElementaryPrinter();
      Automaton automaton = Automaton.Builder
        .wolframsUniverse(rule)
        .chaotic(size)
        .withRuntime(size)
        .build();
      automaton.registerFinishAutomationListener(printer);
      automaton.run();
    });
  }

  @Automation
  @DisplayName("CAs with a single living cell as initial configuration")
  void simple() {
    Path target = destination.resolve("chaotic");
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.black)
      .withMapping(BinaryState.ALIVE, Coolors.Sets.maritime)
      .withBackground(Coolors.black)
      .withFrameColor(Coolors.black)
      .build();
    ProdUtils.printColorModel(target, colors);
    IntStream.range(0, 256).forEach(rule -> {
      Printer<FinishAutomationEvent> printer = new Printer.Builder(target)
        .withColorMapping(colors)
        .withCellSize(cellSize)
        .withScaling(scaling)
        .withFrameWidth(frameWidth)
        .withBorder(borderWidth)
        .buildElementaryPrinter();
      Automaton automaton = Automaton.Builder
        .wolframsUniverse(rule)
        .simple(size)
        .withRuntime(size)
        .build();
      automaton.registerFinishAutomationListener(printer);
      automaton.run();
    });
  }
}
