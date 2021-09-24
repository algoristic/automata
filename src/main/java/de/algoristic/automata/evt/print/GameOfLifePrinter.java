package de.algoristic.automata.evt.print;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class GameOfLifePrinter {

  private final List<File> files;
  private final Path filepath;

  public GameOfLifePrinter(Path filepath) {
    this.files = new ArrayList<>();
    this.filepath = filepath;
  }

  public void addFile(File file) {
    files.add(file);
  }

  public void print() {
    try {
      BufferedImage first = ImageIO.read(files.get(0));
      int type = first.getType();
      File imageFile = filepath.toFile();
      ImageOutputStream output = new FileImageOutputStream(imageFile);
      GifSequenceWriter writer = new GifSequenceWriter(output, type, 50, true);
      for (File file : files) {
        BufferedImage b = ImageIO.read(file);
        writer.writeToSequence(b);
//        file.delete();
      }
      writer.close();
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
