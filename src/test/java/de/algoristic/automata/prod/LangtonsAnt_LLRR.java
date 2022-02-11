package de.algoristic.automata.prod;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.evolution.turmites.AntState;
import de.algoristic.automata.evolution.turmites.Rules;
import de.algoristic.automata.evolution.turmites.Turmites;
import de.algoristic.automata.evolution.turmites.TurmitesRuleMetadata;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.io.AntSeed;
import de.algoristic.automata.io.Seed;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.Coolors;
import de.algoristic.automata.printer.LifeCyclePrinter;
import de.algoristic.automata.printer.Printer;
import de.algoristic.automata.prod.util.Automation;

@DisplayName("Langton's ant with a rule of LLRR")
public class LangtonsAnt_LLRR {

  Path destination = Paths.get("C:/Users/male233/Documents/MEGA/Bilder/automata/Langtons ant/LLRR");

  int runtime = 150000;

  @Automation
  @DisplayName("Computing...")
  void compute() {
    Turmites rule = Rules.LLRR.get();
    TurmitesRuleMetadata metadata = rule.getMetadata();
    Seed seed = new AntSeed(65);
    ColorModel colors = new Coolors()
      .withMapping(0, Coolors.oxfordBlue)
      .withMapping(1, Coolors.platinum)
      .withMapping(2, Coolors.burntSienna)
      .withMapping(3, Coolors.persianGreen)
      .withMapping(AntState.alive(metadata), Coolors.imperialRed)
      .withBackground(Coolors.black)
      .withFrameColor(Coolors.black)
      .build(metadata);
    Path out = destination.resolve("LLRR.gif");
    LifeCyclePrinter printer = new LifeCyclePrinter(out);
    Printer<FinishBreedingEvent> automationStepPrinter = new Printer
      .Builder(destination)
      .withCallback(printer::addFile)
      .withFrameWidth(0)
      .withColorMapping(colors)
      .buildEvolutionStepPrinter(1000);
    Automaton automaton = Automaton.Builder
      .turmites(rule)
      .withSeed(seed)
      .withRuntime(runtime)
      .build();
    automaton.registerFinishBreedingListener(automationStepPrinter);
    automaton.run();
    printer.print(false, 50);
  }
}
