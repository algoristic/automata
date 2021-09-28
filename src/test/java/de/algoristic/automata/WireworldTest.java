package de.algoristic.automata;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.evt.print.ColorMapping;
import de.algoristic.automata.evt.print.LifeCyclePrinter;
import de.algoristic.automata.evt.print.Printer;
import de.algoristic.automata.io.Seed;
import de.algoristic.automata.io.TemplateFile;

public class WireworldTest {

  static Path baseDirectory = Paths.get("C:/Users/male233/Desktop/automata/wireworld");

  @ParameterizedTest
  @CsvSource(value = {
    "wireworld_001, 25, false"})
  void basicTest(String seedFile, int runtime, boolean unlimitedSpace) {
    Seed seed = new TemplateFile("src/test/resources/" + seedFile + ".txt");
    LifeCyclePrinter printer = new LifeCyclePrinter(baseDirectory.resolve(seedFile + ".gif"));
    Printer<FinishBreedingEvent> automationStepPrinter = new Printer
      .Builder(baseDirectory)
      .withCallback(printer::addFile)
      .withColorMapping(ColorMapping.WIREWORLD)
      .buildEvolutionStepBuilder();
    Automaton automaton = Automaton.Builder
        .wireworld()
        .withUnlimitedSpace(unlimitedSpace)
        .withSeed(seed)
        .withRuntime(runtime)
        .build();
      automaton.registerFinishBreedingListener(automationStepPrinter);
      automaton.run();
      printer.print(false, 100);
  }
}
