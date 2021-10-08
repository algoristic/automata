package de.algoristic.automata.prod;

import java.nio.file.Path;
import java.nio.file.Paths;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.evolution.gameoflife.Rules;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.io.Seed;
import de.algoristic.automata.io.TemplateFile;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.Coolors;
import de.algoristic.automata.printer.Printer;
import de.algoristic.automata.prod.util.Automation;

public class OpenSea {

  @Automation
  void createProfile() {
    Path destination = Paths.get("C:/Users/male233/Documents/MEGA/Bilder/automata/opensea.io");
    Seed seed = new TemplateFile("src/test/resources/invader.txt");
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.white, Coolors.platinum, Coolors.honeyDew)
      .withMapping(BinaryState.ALIVE, Coolors.burntSienna)
      .withBackground(Coolors.isabelline)
      .withFrameColor(Coolors.bdazzledBlue)
      .build();
    Printer<FinishBreedingEvent> listener = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withFrameWidth(0)
      .withCellSize(9)
      .withScaling(5)
      .withFilename("__PROFILE")
      .buildEvolutionStepPrinter();
    Automaton automaton = Automaton.Builder
      .gameOfLife(Rules.CONWAYS_LIFE)
      .withSeed(seed)
      .withRuntime(1)
      .build();
    automaton.registerFinishBreedingListener(listener);
    automaton.run();
  }

  @Automation
  void createBanner() {
    Path destination = Paths.get("C:/Users/male233/.automata/opensea.io");
    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.Sets.colorful)
      .withMapping(BinaryState.ALIVE, Coolors.Sets.shadesOfBlue)
      .withBackground(Coolors.platinum)
      .build();
    Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
      .withColorMapping(colors)
      .withCellSize(8)
      .withScaling(2)
      .withFrameWidth(0)
      .withFilename("__BANNER")
      .buildElementaryPrinter();
    Automaton automaton = Automaton.Builder
      .wolframsUniverse(90)
      .chaotic(250)
      .withRuntime(100)
      .build();
    automaton.registerFinishAutomationListener(printer);
    automaton.run();
  }
}
