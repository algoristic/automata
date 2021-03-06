package de.algoristic.automata.evolution.wireworld;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.io.Seed;
import de.algoristic.automata.io.TemplateFile;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.LifeCyclePrinter;
import de.algoristic.automata.printer.Printer;

public class WireworldTest {

  static Path baseDirectory = Paths.get("C:/Users/male233/.automata-tests/wireworld");

  static {
    baseDirectory.toFile().mkdirs();
  }

  @ParameterizedTest
  @CsvSource(value = {
      "wireworld_001, 11, true",
      "wireworld_002, 21, true",
      "wirworld_xor, 17, false"})
  void basicTest(String seedFile, int runtime, boolean unlimitedSpace) {
    Seed seed = new TemplateFile("src/test/resources/" + seedFile + ".txt");
    LifeCyclePrinter printer = new LifeCyclePrinter(baseDirectory.resolve(seedFile + ".gif"));
    Printer<FinishBreedingEvent> automationStepPrinter = new Printer
      .Builder(baseDirectory)
      .withCallback(printer::addFile)
      .withColorMapping(ColorModel.WIREWORLD)
      .buildEvolutionStepPrinter();
    Automaton automaton = Automaton.Builder
      .wireworld()
      .withUnlimitedSpace(unlimitedSpace)
      .withSeed(seed)
      .withRuntime(runtime)
      .build();
    automaton.registerFinishBreedingListener(automationStepPrinter);
    automaton.run();
    printer.print(true, 100);
  }
}
