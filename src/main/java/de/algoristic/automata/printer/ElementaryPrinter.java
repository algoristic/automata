package de.algoristic.automata.printer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.core.State;
import de.algoristic.automata.evt.FinishAutomationEvent;

public class ElementaryPrinter extends Printer<FinishAutomationEvent> {

  public ElementaryPrinter(String filename, Path path, ColorModel colorMapping, int size, int border, int frameWidth, String format, Consumer<File> callback) {
    super(filename, path, colorMapping, size, border, frameWidth, format, callback);
  }

  @Override
  public void on(FinishAutomationEvent event) {
    String rule = event.getRule().toString();
    List<Generation> generations = event.getGenerations();
    int numberOfCells = generations.get(0).size();
    int amountOfGenerations = generations.size() - 1;
    BufferedImage image = getImage(numberOfCells, amountOfGenerations);
    int size = getSize();
    Path path = getPath();
    for (int y = 0; y < amountOfGenerations; y++) {
      Generation generation = generations.get(y);
      for (int x = 0; x < numberOfCells; x++) {
        Cell cell = generation.get(x);
        int startX = calcStart(x);
        int startY = calcStart(y);
        State state = cell.getState();
        int[] color = getRgbArray(state);
        image.setRGB(startX, startY, size, size, color, 0, 0);
      }
    }
    String filename = getFilename();
    String format = getFormat();
    Path imagePath = path.resolve(filename + "_rule_" + rule + "." + format);
    File imageFile = imagePath.toFile();
    try {
      ImageIO.write(image, format, imageFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
