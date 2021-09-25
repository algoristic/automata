package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.function.Consumer;
import de.algoristic.automata.evt.AutomatonEvent;
import de.algoristic.automata.evt.AutomatonEventListener;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.evt.FinishBreedingEvent;

public abstract class Printer<E extends AutomatonEvent> implements AutomatonEventListener<E> {

  final private String filename;
  final private Path path;
  final private Color backgroundColor;
  final private Color aliveCellColor;
  final private Color deadCellColor;
  final private int size;
  final private int border;
  final private Consumer<File> callback;

  public Printer(String filename, Path path, Color backgroundColor, Color aliveCellColor, Color deadCellColor, int size, int border, Consumer<File> callback) {
    this.filename = filename;
    this.path = path;
    this.backgroundColor = backgroundColor;
    this.aliveCellColor = aliveCellColor;
    this.deadCellColor = deadCellColor;
    this.size = size;
    this.border = border;
    this.callback = callback;
  }

  protected String getFilename() {
    return filename;
  }

  protected Path getPath() {
    return path;
  }

  protected int getSize() {
    return size;
  }

  protected void callback(File file) {
    this.callback.accept(file);
  }

  protected int calcStart(int n) {
    return ((n * size) + ((n + 1) * border));
  }

  protected int[] getRgbArray(boolean alive) {
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

  protected BufferedImage getImage(int numberOfCells, int amountOfGenerations) {
    int width = (numberOfCells * size) + (numberOfCells * border) + border;
    int height = (amountOfGenerations * size) + (amountOfGenerations * border) + border;
    BufferedImage image = Images.getColoredImage(width, height, backgroundColor);
    return image;
  }

  public static class Builder {

    private final Path path;
    private String filename = "CA";
    private Color backgroundColor = Color.lightGray;
    private Color aliveCellColor = Color.black;
    private Color deadCellColor = Color.white;
    private int size = 8;
    private int border = 1;
    private int scaling = 1;
    private Consumer<File> callback = (file) -> {};

    public Builder(Path path) {
      this.path = path;
    }

    public Builder withFilename(String filename) {
      this.filename = filename;
      return this;
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

    public Builder withCallback(Consumer<File> callback) {
      this.callback = callback;
      return this;
    }

    public Printer<FinishAutomationEvent> buildElementaryPrinter() {
      int size = (this.size * scaling);
      int border = (this.border * scaling);
      return new ElementaryPrinter(filename, path, backgroundColor, aliveCellColor, deadCellColor, size, border, callback);
    }

    public Printer<FinishBreedingEvent> buildEvolutionStepBuilder() {
      int size = (this.size * scaling);
      int border = (this.border * scaling);
      return new GameOfLifeEvolutionStepPrinter(filename, path, backgroundColor, aliveCellColor, deadCellColor, size, border, callback);
    }
  }
}
