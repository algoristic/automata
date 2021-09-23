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

  final private int scale;

  StaticPrinter(Path path, Color backgroundColor, Color cellColor, int scale) {
    super(path, backgroundColor, cellColor);
    this.scale = scale;
  }

  @Override
  public void on(FinishAutomationEvent event) {
    int rule = event.getRule();
    List<Generation> generations = event.getGenerations();
    int numberOfCells = generations.get(0).size();
    int amountOfGenerations = generations.size() - 1;
    BufferedImage image = Images.getColoredImage(numberOfCells * scale, amountOfGenerations * scale, backgroundColor);
    int rgb = cellColor.getRGB();
    int[] rgbArray = new int[scale];
    for(int i = 0; i < rgbArray.length; i++) {
      rgbArray[i] = rgb;
    }
    for(int y = 0; y < amountOfGenerations; y++) {
      Generation generation = generations.get(y);
      for(int x = 0; x < numberOfCells; x++) {
        Cell cell = generation.get(x);
        if(cell.isAlive()) {
          image.setRGB(x * scale, y * scale, scale, scale, rgbArray, 0, 0);
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

}
