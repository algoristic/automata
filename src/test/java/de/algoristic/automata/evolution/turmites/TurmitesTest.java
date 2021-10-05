package de.algoristic.automata.evolution.turmites;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.evolution.RuleSupplier;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.io.AntSeed;
import de.algoristic.automata.io.Seed;
import de.algoristic.automata.printer.ColorMapping;
import de.algoristic.automata.printer.LifeCyclePrinter;
import de.algoristic.automata.printer.Printer;

public class TurmitesTest {

  static Path baseDirectory = Paths.get("C:/Users/male233/.automata-tests/turmites");

  static {
    baseDirectory.toFile().mkdirs();
  }

  @Test
  void basicTest() {
    Seed seed = new AntSeed(5);
    RuleSupplier supplier = Rules.LLRR;
    Turmites rule = ((Rules) supplier).getRule();
    TurmitesRuleMetadata metadata = rule.getMetadata();

    LifeCyclePrinter printer = new LifeCyclePrinter(baseDirectory.resolve(supplier + ".gif"));
    Printer<FinishBreedingEvent> automationStepPrinter = new Printer
      .Builder(baseDirectory)
      .withCallback(printer::addFile)
      .withFrameWidth(0)
      .withColorMapping(ColorMapping.TURMITES(metadata))
      .buildEvolutionStepPrinter();
    Automaton automaton = Automaton.Builder
      .turmites(rule)
      .withSeed(seed)
      .withRuntime(10)
      .build();
    automaton.registerFinishBreedingListener(automationStepPrinter);
    automaton.run();
    printer.print(25);
  }
  
}
