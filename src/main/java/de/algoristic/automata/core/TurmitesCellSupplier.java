package de.algoristic.automata.core;

import de.algoristic.automata.evolution.turmites.AntState;
import de.algoristic.automata.evolution.turmites.Direction;
import de.algoristic.automata.evolution.turmites.FieldState;
import de.algoristic.automata.evolution.turmites.TurmitesRuleMetadata;
import de.algoristic.automata.evolution.turmites.TurmiteState;

public class TurmitesCellSupplier implements CellSupplier {

  private final TurmitesRuleMetadata metadata;

  public TurmitesCellSupplier(TurmitesRuleMetadata metadata) {
    this.metadata = metadata;
  }

  @Override
  public Cell apply(Character t) {
    State state;
    if(t == 'a') {
      TurmiteState turmite = new TurmiteState(0);
      FieldState fieldState = new FieldState(turmite, metadata);
      fieldState.flipField();
      AntState antState = new AntState(turmite, metadata);
      antState.setAliveAnt(Direction.UP);
      state = turmite;
    } else {
      state = new TurmiteState(0);
    }
    return new Cell(state);
  }

}
