package de.algoristic.automata.util;

public abstract class BinaryStringUtils {

  public static String getBinaryString(long number) {
    return getBinaryString(number, 0);
  }

  public static String getBinaryString(long number, int minLength) {
    String binaryString = Long.toBinaryString(number);
    int lengthDifference = minLength - binaryString.length();
    for (int i = 0; i < lengthDifference; i++) {
      binaryString = '0' + binaryString;
    }
    return binaryString;
  }
}
