package de.algoristic.automata.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TemplateFile implements Seed {

  private final Path path;
  private List<String> content;

  public TemplateFile(String file) {
    this.path = Paths.get(file);
    try {
      content = Files.readAllLines(path);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int getVerticalDimension() {
    return content.size();
  }

  @Override
  public String getContent() {
    return content.stream()
      .map(line -> line.replace(" ", "0"))
      .map(line -> line.replace("x", "1"))
      .collect(Collectors.joining());
  }
}
