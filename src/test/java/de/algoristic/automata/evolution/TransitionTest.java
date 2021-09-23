package de.algoristic.automata.evolution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import de.algoristic.automata.core.Cell;
import de.algoristic.automata.core.Generation;
import de.algoristic.automata.evolution.Rule;
import de.algoristic.automata.evolution.Transition;

public class TransitionTest {
	
	@Test
	void basicTransitionTest() {
		Rule rule = Rule.getInstance(30);
		Generation parentalGeneration = Generation.getGeneration("0011100");
		Transition transition = new Transition(parentalGeneration, rule);
		Generation filialGeneration = transition.produceFilialGeneration();
		String s = "";
		for(Cell cell: filialGeneration) {
			s += cell;
		}
		assertEquals("0110010", s);
	}
	
}
