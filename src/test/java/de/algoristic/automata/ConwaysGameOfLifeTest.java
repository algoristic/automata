package de.algoristic.automata;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import de.algoristic.automata.evolution.gameoflife.GameOfLife;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.io.RandomSeed;
import de.algoristic.automata.io.Seed;
import de.algoristic.automata.io.TemplateFile;
import de.algoristic.automata.printer.LifeCyclePrinter;
import de.algoristic.automata.printer.Printer;

public class ConwaysGameOfLifeTest {

  static Path baseDirectory = Paths.get("C:/Users/male233/Desktop/automata/game_of_life");
  
  @ParameterizedTest
  @CsvSource(value = {
    "gosperglidergun, 59, false"})
  void seedTest(String seedFile, int runtime, boolean unlimitedSpace) {
    Seed seed = new TemplateFile("src/test/resources/" + seedFile + ".txt");
    LifeCyclePrinter printer = new LifeCyclePrinter(baseDirectory.resolve(seedFile + ".gif"));
    Printer<FinishBreedingEvent> automationStepPrinter = new Printer
      .Builder(baseDirectory)
      .withCallback(printer::addFile)
      .buildEvolutionStepBuilder();
    Automaton automaton = Automaton.Builder
      .gameOfLife()
      .withUnlimitedSpace(unlimitedSpace)
      .withSeed(seed)
      .withRuntime(runtime)
      .build();
    automaton.registerFinishBreedingListener(automationStepPrinter);
    automaton.run();
    printer.print(true, 100);
  }

  @ParameterizedTest
  @ValueSource(strings = {"B3/S23"})
  void randomTest(String ruleString) {
    GameOfLife rule = GameOfLife.getInstance(ruleString);
    LifeCyclePrinter printer = new LifeCyclePrinter(baseDirectory.resolve(rule + ".gif"));
    Seed seed = new RandomSeed(75);
    Printer<FinishBreedingEvent> automationStepPrinter = new Printer
        .Builder(baseDirectory)
        .withCallback(printer::addFile)
        .buildEvolutionStepBuilder();
    Automaton automaton = Automaton.Builder
        .gameOfLife(rule)
        .withSeed(seed)
        .withRuntime(100)
        .build();
    automaton.registerFinishBreedingListener(automationStepPrinter);
    automaton.run();
    printer.print();
  }
}
