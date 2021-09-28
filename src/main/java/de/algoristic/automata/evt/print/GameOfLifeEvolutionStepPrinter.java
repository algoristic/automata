package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import de.algoristic.automata.core.BinaryState;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.dimensional.Grid;
import de.algoristic.automata.evolution.dimensional.Point;
import de.algoristic.automata.evt.FinishBreedingEvent;

public class GameOfLifeEvolutionStepPrinter extends Printer<FinishBreedingEvent> {

  public GameOfLifeEvolutionStepPrinter(String filename, Path path, Color backgroundColor, ColorMapping colorMapping, int size, int border, Consumer<File> callback) {
    super(filename, path, backgroundColor, colorMapping, size, border, callback);
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
    int[] alive = getRgbArray(BinaryState.ALIVE);
    int[] dead = getRgbArray(BinaryState.DEAD);
    for(int x = 0; x <= grid.getRightBorder(); x++) {
      for(int y = 0; y <= grid.getLowerBorder(); y++) {
        Point p = new Point(x, y, grid);
        int index = p.transposeToIndex();
        Cell cell = generation.get(index);
        int startX = calcStart(x);
        int startY = calcStart(y);
        int[] color;
        if (cell.hasState(BinaryState.ALIVE)) {
          color = alive;
        } else {
          color = dead;
        }
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
