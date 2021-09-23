package de.algoristic.automata.core;

/** Wrapper around the single Cells of a generation, to link them together. */
public class CellSpace {

  private final Cell content;
  private CellSpace previous;
  private CellSpace next;

  public CellSpace(final Cell content) {
    this.content = content;
    this.content.setCellSpace(this);
  }

  public Cell getContent() {
    return content;
  }

  public CellSpace getPrevious() {
    return previous;
  }

  public void linkPrevious(CellSpace previous) {
    this.previous = previous;
  }

  public CellSpace getNext() {
    return next;
  }

  public void LinkNext(CellSpace next) {
    this.next = next;
  }

  @Override
  public String toString() {
    return content.toString();
  }
}
