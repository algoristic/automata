package de.algoristic.automata.printer;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Images {

  private static final int TYPE = BufferedImage.TYPE_INT_RGB;

  public static BufferedImage blackImage(int width, int height) {
    return getColoredImage(width, height, Color.black);
  }

  public static BufferedImage whiteImage(int width, int height) {
    return getColoredImage(width, height, Color.white);
  }

  public static BufferedImage getColoredImage(int width, int height, Color backgroundColor) {
    int rgb = backgroundColor.getRGB();
    BufferedImage image = new BufferedImage(width, height, TYPE);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        image.setRGB(x, y, rgb);
      }
    }
    return image;
  }

}
