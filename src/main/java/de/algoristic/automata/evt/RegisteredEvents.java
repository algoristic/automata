package de.algoristic.automata.evt;

import java.util.ArrayList;
import java.util.List;

public class RegisteredEvents {

  private List<AutomatonEventListener<StartAutomationEvent>> startListeners = new ArrayList<>();
  private List<AutomatonEventListener<FinishAutomationEvent>> finishListeners = new ArrayList<>();
  private List<AutomatonEventListener<StartBreedingEvent>> startBreedingListeners =
      new ArrayList<>();
  private List<AutomatonEventListener<FinishBreedingEvent>> finishBreedingListeners =
      new ArrayList<>();

  public void registerStartAutomationListener(
      AutomatonEventListener<StartAutomationEvent> listener) {
    startListeners.add(listener);
  }

  public void throwStartAutomationEvent(StartAutomationEvent event) {
    registerEvent(event, startListeners);
  }

  public void registerFinishAutomationListener(
      AutomatonEventListener<FinishAutomationEvent> listener) {
    finishListeners.add(listener);
  }

  public void throwFinishAutomationEvent(FinishAutomationEvent event) {
    registerEvent(event, finishListeners);
  }

  public void registerStartBreedingListener(AutomatonEventListener<StartBreedingEvent> listener) {
    startBreedingListeners.add(listener);
  }

  public void throwStartBreedingEvent(StartBreedingEvent event) {
    registerEvent(event, startBreedingListeners);
  }

  public void registerFinishBreedingListener(AutomatonEventListener<FinishBreedingEvent> listener) {
    finishBreedingListeners.add(listener);
  }

  public void throwFinishBreedingEvent(FinishBreedingEvent event) {
    registerEvent(event, finishBreedingListeners);
  }

  public <E extends AutomatonEvent> void registerEvent(
      E event, List<AutomatonEventListener<E>> listeners) {
    listeners.forEach(
        listener -> {
          listener.on(event);
        });
  }
}
