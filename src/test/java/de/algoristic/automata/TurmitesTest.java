package de.algoristic.automata;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import de.algoristic.automata.evolution.RuleSupplier;
import de.algoristic.automata.evolution.turmites.Rules;
import de.algoristic.automata.evolution.turmites.Turmites;
import de.algoristic.automata.evolution.turmites.TurmitesRuleMetadata;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.evt.print.ColorMapping;
import de.algoristic.automata.evt.print.LifeCyclePrinter;
import de.algoristic.automata.evt.print.Printer;
import de.algoristic.automata.io.AntSeed;
import de.algoristic.automata.io.Seed;

public class TurmitesTest {

  static Path baseDirectory = Paths.get("C:/Users/male233/Desktop/automata/turmites");

  @Test
  void basicTest() {
    Seed seed = new AntSeed(75);
    RuleSupplier supplier = Rules.LLRR;
    Turmites rule = ((Rules) supplier).getRule();
    TurmitesRuleMetadata metadata = rule.getMetadata();

    LifeCyclePrinter printer = new LifeCyclePrinter(baseDirectory.resolve(supplier + ".gif"));
    Printer<FinishBreedingEvent> automationStepPrinter = new Printer
      .Builder(baseDirectory)
      .withCallback(printer::addFile)
      .withFrameWidth(0)
      .withColorMapping(ColorMapping.TURMITES(metadata))
      .buildEvolutionStepBuilder(100);
    Automaton automaton = Automaton.Builder
      .turmites(rule)
      .withSeed(seed)
      .withRuntime(100000)
      .build();
    automaton.registerFinishBreedingListener(automationStepPrinter);
    automaton.run();
    printer.print(false, 25);
  }
  
}
