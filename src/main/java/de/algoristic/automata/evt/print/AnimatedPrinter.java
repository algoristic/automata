package de.algoristic.automata.evt.print;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evt.FinishAutomationEvent;

class AnimatedPrinter extends Printer {

  AnimatedPrinter(Path path, Color backgroundColor, Color cellColor) {
    super(path, backgroundColor, cellColor);
  }

  @Override
  public void on(FinishAutomationEvent event) {
    List<File> files = new ArrayList<>();
    int rule = event.getRule();
    int rgb = cellColor.getRGB();
    List<Generation> generations = event.getGenerations();
    int numberOfCells = generations.get(0).size();
    int amountOfGenerations = generations.size() - 1;
    for (int step = 0; step < amountOfGenerations; step++) {
      BufferedImage img = Images.getColoredImage(numberOfCells, amountOfGenerations, backgroundColor);
      for (int y = 0; y <= step; y++) {
        Generation generation = generations.get(y);
        for (int x = 0; x < numberOfCells; x++) {
          Cell cell = generation.get(x);
          if (cell.isAlive()) {
            img.setRGB(x, y, rgb);
          }
        }
      }
      File tmpImageFile = printTempFile(img, rule, step);
      files.add(tmpImageFile);
    }
    printMainFile(files, rule);
  }

  private File printTempFile(BufferedImage img, int rule, int step) {
    System.out.println("Print rule " + rule + "; step " + step);
    String tmpFileName = MessageFormat.format("{0}_automaton_{1}.gif", rule, step);
    Path tmpImagePath = path.resolve(tmpFileName);
    File tmpImageFile = tmpImagePath.toFile();
    try {
      ImageIO.write(img, "gif", tmpImageFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return tmpImageFile;
  }

  private void printMainFile(List<File> files, int rule) {
    try {
      BufferedImage first = ImageIO.read(files.get(0));
      int type = first.getType();
      Path imagePath = path.resolve("rule_" + rule + ".gif");
      File imageFile = imagePath.toFile();
      ImageOutputStream output = new FileImageOutputStream(imageFile);
      GifSequenceWriter writer = new GifSequenceWriter(output, type, 1, true);
      for (File file : files) {
        BufferedImage b = ImageIO.read(file);
        writer.writeToSequence(b);
        file.delete();
      }
      writer.close();
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
