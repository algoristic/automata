package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import javax.imageio.ImageIO;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evt.FinishAutomationEvent;

class StaticPrinter extends Printer {

  final private int size;
  final private int border;

  StaticPrinter(Path path, Color backgroundColor, Color cellColor, int size, int border) {
    super(path, backgroundColor, cellColor);
    this.size = size;
    this.border = border;
  }

  @Override
  public void on(FinishAutomationEvent event) {
    int rule = event.getRule();
    List<Generation> generations = event.getGenerations();
    int numberOfCells = generations.get(0).size();
    int amountOfGenerations = generations.size() - 1;
    BufferedImage image = getImage(numberOfCells, amountOfGenerations);
    int[] rgbArray = getRgbArray();
    for (int y = 0; y < amountOfGenerations; y++) {
      Generation generation = generations.get(y);
      for (int x = 0; x < numberOfCells; x++) {
        Cell cell = generation.get(x);
        if (cell.isAlive()) {
          int startX = calcStart(x);
          int startY = calcStart(y);
          image.setRGB(startX, startY, size, size, rgbArray, 0, 0);
        }
      }
    }
    Path imagePath = path.resolve("rule_" + rule + ".gif");
    File imageFile = imagePath.toFile();
    try {
      ImageIO.write(image, "gif", imageFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private int calcStart(int n) {
    return ((n * size) + ((n + 1) * border));
  }

  private int[] getRgbArray() {
    int rgb = cellColor.getRGB();
    int[] rgbArray = new int[size];
    for (int i = 0; i < rgbArray.length; i++) {
      rgbArray[i] = rgb;
    }
    return rgbArray;
  }

  private BufferedImage getImage(int numberOfCells, int amountOfGenerations) {
    int width = (numberOfCells * size) + (numberOfCells * border);
    int height = (amountOfGenerations * size) + (numberOfCells * border);
    BufferedImage image = Images.getColoredImage(width, height, backgroundColor);
    return image;
  }
}
