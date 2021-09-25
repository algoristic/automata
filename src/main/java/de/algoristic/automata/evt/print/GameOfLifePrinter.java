package de.algoristic.automata.evt.print;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import de.algoristic.automata.evt.print.AnimatedGIFWriter.GIFFrame;

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
    print(true, 100);
  }

  public void print(boolean delete) {
    print(delete, 100);
  }

  public void print(int delay) {
    print(true, delay);
  }

  public void print(boolean delete, int delay) {
    AnimatedGIFWriter gifWriter = new AnimatedGIFWriter();
    File imageFile = filepath.toFile();
    try (OutputStream os = new FileOutputStream(imageFile)) {
      List<GIFFrame> frames = new ArrayList<>();
      for (File file : files) {
        BufferedImage b = ImageIO.read(file);
        GIFFrame frame = new GIFFrame(b, delay);
        frames.add(frame);
        if(delete) {
          file.delete();
        }
      }
      gifWriter.writeAnimatedGIF(frames, os);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
