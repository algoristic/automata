package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.nio.file.Path;
import de.algoristic.automata.evt.AutomatonEventListener;
import de.algoristic.automata.evt.FinishAutomationEvent;

public abstract class Printer implements AutomatonEventListener<FinishAutomationEvent> {

  protected final Path path;
  protected final Color backgroundColor;
  protected final Color cellColor;

  public Printer(Path path, Color backgroundColor, Color cellColor) {
    this.path = path;
    this.backgroundColor = backgroundColor;
    this.cellColor = cellColor;
  }

  public Path getPath() {
    return path;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public Color getCellColor() {
    return cellColor;
  }

  public abstract static class Builder {

    protected final Path path;
    protected Color backgroundColor = Color.white;
    protected Color cellColor = Color.black;

    protected Builder(Path path) {
      this.path = path;
    }

    public static StaticPrinterBuilder staticPrinter(Path path) {
      return new StaticPrinterBuilder(path);
    }

    public Builder withBackgroundColor(Color backgroundColor) {
      this.backgroundColor = backgroundColor;
      return this;
    }

    public Builder withCellColor(Color cellColor) {
      this.cellColor = cellColor;
      return this;
    }

    public abstract Printer build();
  }

  public static class StaticPrinterBuilder extends Builder {

    private int scale = 1;

    private StaticPrinterBuilder(Path path) {
      super(path);
    }

    public StaticPrinterBuilder withScale(int scale) {
      this.scale = scale;
      return this;
    }

    @Override
    public Printer build() {
      return new StaticPrinter(path, backgroundColor, cellColor, scale);
    }
  }
}
