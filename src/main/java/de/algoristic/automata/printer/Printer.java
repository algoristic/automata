package de.algoristic.automata.printer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.function.Consumer;
import de.algoristic.automata.core.State;
import de.algoristic.automata.evt.AutomatonEvent;
import de.algoristic.automata.evt.AutomatonEventListener;
import de.algoristic.automata.evt.FinishAutomationEvent;
import de.algoristic.automata.evt.FinishBreedingEvent;

public abstract class Printer<E extends AutomatonEvent> implements AutomatonEventListener<E> {

  final private String filename;
  final private Path path;
  final private Color backgroundColor;
  final private Color frameColor;
  final private ColorMapping colorMapping;
  final private int size;
  final private int border;
  final private int frameWidth;
  final private Consumer<File> callback;

  public Printer(
      String filename,
      Path path,
      Color backgroundColor,
      Color frameColor,
      ColorMapping colorMapping,
      int size,
      int border,
      int frameWidth,
      Consumer<File> callback) {
    this.filename = filename;
    this.path = path;
    this.backgroundColor = backgroundColor;
    this.frameColor = frameColor;
    this.colorMapping = colorMapping;
    this.size = size;
    this.border = border;
    this.frameWidth = frameWidth;
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
    return ((n * size) + ((n + 1) * border) + frameWidth);
  }

  protected int[] getRgbArray(State state) {
    Color color = colorMapping.get(state);
    return getRgbArray(color, size);
  }

  protected BufferedImage getImage(int width, int height) {
    int widthInPixels = (width * size) + (width * border) + border + (2 * frameWidth);
    int heightInPixels = (height * size) + (height * border) + border + (2 * frameWidth);
    BufferedImage image = Images.getColoredImage(widthInPixels, heightInPixels, backgroundColor);
    fillFrame(image);
    return image;
  }

  private void fillFrame(BufferedImage image) {
    int imageWidth = image.getWidth();
    int imageHeight = image.getHeight();
    int[] widthRgbArray = getRgbArray(frameColor, imageWidth);
    int[] heightRgbArray = getRgbArray(frameColor, frameWidth);
    image.setRGB(0, 0, imageWidth, frameWidth, widthRgbArray, 0, 0);
    image.setRGB(0, (imageHeight - frameWidth), imageWidth, frameWidth, widthRgbArray, 0, 0);
    image.setRGB(0, 0, frameWidth, imageHeight, heightRgbArray, 0, 0);
    image.setRGB((imageWidth - frameWidth), 0, frameWidth, imageHeight, heightRgbArray, 0, 0);
  }

  private int[] getRgbArray(Color color, int size) {
    int rgb = color.getRGB();
    int[] rgbArray = new int[size];
    for (int i = 0; i < rgbArray.length; i++) {
      rgbArray[i] = rgb;
    }
    return rgbArray;
  }

  public static class Builder {

    private final Path path;
    private String filename = "CA";
    private Color backgroundColor = Color.lightGray;
    private Color frameColor = Color.CYAN;
    private ColorMapping colorMapping = ColorMapping.BINARY;
    private int size = 10;
    private int border = 1;
    private int frameWidth = 20;
    private int scaling = 1;
    private Consumer<File> callback = (file) -> {};

    public Builder(Path path) {
      this.path = path;
      init();
    }

    private void init() {
      File dir = path.toFile();
      dir.mkdirs();
    }

    public Builder withFilename(String filename) {
      this.filename = filename;
      return this;
    }

    public Builder withBackgroundColor(Color backgroundColor) {
      this.backgroundColor = backgroundColor;
      return this;
    }

    public Builder withFrameColor(Color frameColor) {
      this.frameColor = frameColor;
      return this;
    }

    public Builder withColorMapping(ColorMapping colorMapping) {
      this.colorMapping = colorMapping;
      return this;
    }

    public Builder withFrameWidth(int frameWidth) {
      this.frameWidth = frameWidth;
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
      return new ElementaryPrinter(filename, path, backgroundColor, frameColor, colorMapping, size, border, frameWidth, callback);
    }

    public Printer<FinishBreedingEvent> buildEvolutionStepPrinter() {
      return buildEvolutionStepPrinter(1);
    }

    public Printer<FinishBreedingEvent> buildEvolutionStepPrinter(int nthGeneration) {
      int size = (this.size * scaling);
      int border = (this.border * scaling);
      return new EvolutionStepPrinter(filename, path, backgroundColor, frameColor, colorMapping, size, border, frameWidth, callback, nthGeneration);
    }
  }
}
