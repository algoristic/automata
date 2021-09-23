package de.algoristic.automata.evt;

public interface AutomatonEventListener<E extends AutomatonEvent> {

  public void on(E event);
}
