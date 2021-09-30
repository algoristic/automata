package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.State;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.dimensional.Grid;
import de.algoristic.automata.evolution.dimensional.Point;
import de.algoristic.automata.evt.FinishBreedingEvent;

public class EvolutionStepPrinter extends Printer<FinishBreedingEvent> {

  public EvolutionStepPrinter(String filename, Path path, Color backgroundColor, Color frameColor, ColorMapping colorMapping, int size, int border, int frameWidth, Consumer<File> callback) {
    super(filename, path, backgroundColor, frameColor, colorMapping, size, border, frameWidth, callback);
  }

  @Override
  public void on(FinishBreedingEvent event) {
    int evolutionsStep = event.getStep();
    Rule rule = event.getRule();
    if(evolutionsStep == 0) {
      Generation parentalGeneration = event.getParentalGeneration();
      printGeneration(rule, evolutionsStep, parentalGeneration);
    }
    Generation filialGeneration = event.getFilialGeneration();
    printGeneration(rule, ++evolutionsStep, filialGeneration);
  }

  private void printGeneration(Rule rule, int step, Generation generation) {
    Grid grid = Grid.fromGeneration(generation);
    BufferedImage image = getImage(grid.getRightBorder() + 1, grid.getLowerBorder() + 1);
    int size = getSize();
    Path path = getPath();
    for(int x = 0; x <= grid.getRightBorder(); x++) {
      for(int y = 0; y <= grid.getLowerBorder(); y++) {
        Point p = new Point(x, y, grid);
        int index = p.transposeToIndex();
        Cell cell = generation.get(index);
        int startX = calcStart(x);
        int startY = calcStart(y);
        State state = cell.getState();
        int[] color = getRgbArray(state);
        image.setRGB(startX, startY, size, size, color, 0, 0);
      }
    }
    String name = getFilename();
    String filename = MessageFormat.format("{0}_{1}_{2}.gif", rule, name, step);
    Path imagePath = path.resolve(filename);
    File imageFile = imagePath.toFile();
    try {
      ImageIO.write(image, "gif", imageFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
    callback(imageFile);
  }
}
