package de.algoristic.automata.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import de.algoristic.automata.util.BinaryStringUtils;

public class BinaryStringUtilsTest {

  @Test
  void testFilling() {
    String binaryString = BinaryStringUtils.getBinaryString(4, 4);
    assertEquals("0100", binaryString);
  }

  @Test
  void testNonFilling() {
    String binaryString = BinaryStringUtils.getBinaryString(7);
    assertEquals("111", binaryString);
  }

}
