package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import javax.imageio.ImageIO;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evt.AutomatonEventListener;
import de.algoristic.automata.evt.FinishAutomationEvent;

public class Printer implements AutomatonEventListener<FinishAutomationEvent> {

  final private Path path;
  final private Color backgroundColor;
  final private Color aliveCellColor;
  final private Color deadCellColor;
  final private int size;
  final private int border;

  public Printer(Path path, Color backgroundColor, Color aliveCellColor, Color deadCellColor, int size, int border) {
    this.path = path;
    this.backgroundColor = backgroundColor;
    this.aliveCellColor = aliveCellColor;
    this.deadCellColor = deadCellColor;
    this.size = size;
    this.border = border;
  }

  @Override
  public void on(FinishAutomationEvent event) {
    int rule = 0;//event.getRule();
    List<Generation> generations = event.getGenerations();
    int numberOfCells = generations.get(0).size();
    int amountOfGenerations = generations.size() - 1;
    BufferedImage image = getImage(numberOfCells, amountOfGenerations);
    int[] alive = getRgbArray(true);
    int[] dead = getRgbArray(false);
    for (int y = 0; y < amountOfGenerations; y++) {
      Generation generation = generations.get(y);
      for (int x = 0; x < numberOfCells; x++) {
        Cell cell = generation.get(x);
        int startX = calcStart(x);
        int startY = calcStart(y);
        int[] color;
        if (cell.isAlive()) {
          color = alive;
        } else {
          color = dead;
        }
        image.setRGB(startX, startY, size, size, color, 0, 0);
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

  private int[] getRgbArray(boolean alive) {
    Color color;
    if(alive) {
      color = aliveCellColor;
    } else {
      color = deadCellColor;
    }
    int rgb = color.getRGB();
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

  public static class Builder {

    private final Path path;
    private Color backgroundColor = Color.lightGray;
    private Color aliveCellColor = Color.black;
    private Color deadCellColor = Color.white;
    private int size = 8;
    private int border = 1;
    private int scaling = 1;

    public Builder(Path path) {
      this.path = path;
    }

    public Builder withBackgroundColor(Color backgroundColor) {
      this.backgroundColor = backgroundColor;
      return this;
    }

    public Builder withAliveCellColor(Color aliveCellColor) {
      this.aliveCellColor = aliveCellColor;
      return this;
    }

    public Builder withDeadCellColor(Color deadCellColor) {
      this.deadCellColor = deadCellColor;
      return this;
    }

    public Builder withCellSize(int size) {
      this.size = size;
      return this;
    }

    public Builder withBorder(int border) {
      this.border = border;
      return this;
    }

    public Builder withScaling(int scaling) {
      this.scaling = scaling;
      return this;
    }

    public Printer build() {
      int size = (this.size * scaling);
      int border = (this.border * scaling);
      return new Printer(path, backgroundColor, aliveCellColor, deadCellColor, size, border);
    }
  }
}
