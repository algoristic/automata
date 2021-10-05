package de.algoristic.automata.prod;

import java.nio.file.Path;
import java.nio.file.Paths;
import de.algoristic.automata.Automaton;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.evolution.gameoflife.Rules;
import de.algoristic.automata.evt.FinishBreedingEvent;
import de.algoristic.automata.io.Seed;
import de.algoristic.automata.io.TemplateFile;
import de.algoristic.automata.printer.ColorModel;
import de.algoristic.automata.printer.Coolors;
import de.algoristic.automata.printer.Printer;
import de.algoristic.automata.prod.util.Automation;

public class Invaders {

  Path desktop = Paths.get("C:/Users/male233/.automata/spaceinvader");
  Seed seed = new TemplateFile("src/test/resources/invader.txt");

  ColorModel colors = new Coolors()
    .withMapping(BinaryState.DEAD, Coolors.transparent)
    .withMapping(BinaryState.ALIVE, Coolors.burntSienna)
    .withBackground(Coolors.transparent)
    .withFrameColor(Coolors.bdazzledBlue)
    .build();

  Printer<FinishBreedingEvent> listener = new Printer.Builder(desktop)
    .withColorMapping(colors)
    .withFrameWidth(3)
    .withCellSize(9)
    .withScaling(5)
    .buildEvolutionStepPrinter();

  @Automation
  void invaders() {
    Automaton automaton = Automaton.Builder
      .gameOfLife(Rules.CONWAYS_LIFE)
      .withSeed(seed)
      .withRuntime(9)
      .build();
    automaton.registerFinishBreedingListener(listener);
    automaton.run();
  }
}
