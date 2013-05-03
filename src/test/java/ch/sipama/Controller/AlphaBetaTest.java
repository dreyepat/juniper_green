package ch.sipama.Controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlphaBetaTest {
	
	//AlphaBeta test = new AlphaBeta();

	@Test
	public void testMaxWert() {
		Object ermittelt = null;
		assertNotNull("MaxWert", ermittelt);
	}

	@Test
	public void testMinWert() {
		Object ermittelt = null;
		assertNotNull("MinWert", ermittelt);
	}

	@Test
	public void testNaechsterPCSpielzug() {
		assertNotNull("nextPCSpielzug", 0);
	}
	
	@Test
	public void countPoints() {
		assertNotNull("countPoints", 0);
	}

}
