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
import de.algoristic.automata.prod.util.GameOfLife;

public class Invaders {

  @GameOfLife
  void invaders() {
    Path desktop = Paths.get("C:/Users/male233/Desktop/.automata/invaders");
    Seed seed = new TemplateFile("src/test/resources/prod/invader.txt");

    ColorModel colors = new Coolors()
      .withMapping(BinaryState.DEAD, Coolors.black)
      .withMapping(BinaryState.ALIVE, Coolors.orangeWeb)
      .withBackground(Coolors.oxfordBlue)
      .build();

    Printer<FinishBreedingEvent> listener = new Printer.Builder(desktop)
      .withColorMapping(colors)
      .buildEvolutionStepPrinter();

    Automaton automaton = Automaton.Builder
      .gameOfLife(Rules.CONWAYS_LIFE)
      .withSeed(seed)
      .withRuntime(9)
      .build();
    automaton.registerFinishBreedingListener(listener);
    automaton.run();
  }
}
