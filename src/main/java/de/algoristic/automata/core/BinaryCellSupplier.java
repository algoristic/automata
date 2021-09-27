package de.algoristic.automata.core;

public class BinaryCellSupplier implements CellSupplier {

  @Override
  public Cell apply(Character t) {
    if(t == '1') {
      return new Cell(BinaryState.ALIVE);
    } else {
      return new Cell(BinaryState.DEAD);
    }
  }

}
