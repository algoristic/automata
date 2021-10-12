package de.algoristic.automata.prod;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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

@DisplayName("Elementary CAs, displayed as simple as possible")
public class ElementaryCAs_Classic {

  Path destination = Paths.get("C:/Users/male233/Documents/MEGA/Bilder/automata/Elementary CAs/Classic");
  List<Integer> rules = Arrays.asList(30, 54, 60, 90, 105, 106, 110, 122, 150);

  int cellSize = 8;
  int scaling = 2;
  int frameWidth = 8*20;
  int borderWidth = 2;
  int size = 101;

  @Automation
  @DisplayName("CAs with random initial configuration")
  void chaotic() {
    Path target = destination.resolve("chaotic");
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.richBlackFOGRA29)
      .withMapping(BinaryState.ALIVE, Coolors.platinum)
      .withBackground(Coolors.richBlackFOGRA29)
      .withFrameColor(Coolors.richBlackFOGRA29)
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
    Path target = destination.resolve("simple");
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.richBlackFOGRA29)
      .withMapping(BinaryState.ALIVE, Coolors.platinum)
      .withBackground(Coolors.richBlackFOGRA29)
      .withFrameColor(Coolors.richBlackFOGRA29)
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
