package de.algoristic.automata.evt;

public abstract class AbstractAutomatonEvent implements AutomatonEvent {

  private long timestamp;

  public AbstractAutomatonEvent() {
    this.timestamp = System.currentTimeMillis();
  }

  @Override
  public long getTimestamp() {
    return timestamp;
  }
}
