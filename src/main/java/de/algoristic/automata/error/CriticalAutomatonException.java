package de.algoristic.automata.error;

public class CriticalAutomatonException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CriticalAutomatonException(String message) {
    super(message);
  }
}
