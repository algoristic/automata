package de.algoristic.automata.prod.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import de.algoristic.automata.printer.ColorModel;

public abstract class ProdUtils {

  public static void printColorModel(Path dir, ColorModel colorModel) {
    printColorModel(dir, "README.md", colorModel);
  }

  public static void printColorModel(Path dir, String filename, ColorModel colorModel) {
    try {
      Files.writeString(
        dir.resolve(filename),
        colorModel.toString(),
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
