package de.algoristic.automata.core;

public class WireworldCellSupplier implements CellSupplier {

  @Override
  public Cell apply(Character t) {
    State state;
    if(WireworldState.isValid(t)) {
      state = WireworldState.valueOf(t);
    } else {
      state = WireworldState.EMPTY;
    }
    return new Cell(state);
  }

}
