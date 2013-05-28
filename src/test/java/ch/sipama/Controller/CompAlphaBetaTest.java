package ch.sipama.Controller;

import static org.junit.Assert.*;
import org.junit.Test;

public class CompAlphaBetaTest {

	private Spieldaten oSpdaten;
	
	
	@Test
	public void testAll() {
		
		oSpdaten=Spieldaten.getInstance();
		oSpdaten.setSpieldaten(10, "sSpielerA", "sSpielerB");
		oSpdaten.spielzugAusfuehren(0, 3, 4);
		
		CompAlphaBeta cab = new CompAlphaBeta();
		assertNotNull(cab);
		
		assertEquals("Gewinnstrasse für 4: 8", 8, cab.naechsterPCSpielzug());

		
	}

}
