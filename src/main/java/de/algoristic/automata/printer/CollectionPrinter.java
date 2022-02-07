package de.algoristic.automata.printer;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class CollectionPrinter {
  private final List<File> files;
  private final Path filepath;

  public CollectionPrinter(List<File> files, Path filepath) {
    this.files = files;
    this.filepath = filepath;
  }

  public void addFile(File file) {
    files.add(file);
  }
  
  public void print() {
    
  }
}
