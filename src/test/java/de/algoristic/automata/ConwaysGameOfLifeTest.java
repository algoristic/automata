package de.algoristic.automata;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import de.algoristic.automata.evolution.Rules;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.evt.print.GameOfLifePrinter;
import de.algoristic.automata.evt.print.Printer;

public class ConwaysGameOfLifeTest {

  static Path baseDirectory = Paths.get("C:/Users/male233/Desktop/automata/game_of_life");
  
  @Test
  void blinkerTest() {
    GameOfLifePrinter printer = new GameOfLifePrinter(baseDirectory.resolve("blinker.gif"));
    Printer<FinishBreedingEvent> automationStepPrinter = new Printer
        .Builder(baseDirectory)
        .withFilename("blinker")
        .withCallback(printer::addFile)
        .buildEvolutionStepBuilder();
    Automaton automaton = Automaton.Builder
      .gameOfLife(Rules.DIAMOEBA)
      .random()
      .withRuntime(250)
      .build();
    automaton.registerFinishBreedingListener(automationStepPrinter);
    automaton.run();
    printer.print();
  }
}
