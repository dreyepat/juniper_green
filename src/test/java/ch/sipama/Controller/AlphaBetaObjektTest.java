package ch.sipama.Controller;

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Test;

/**
 * @author Patrizia Dreyer
 * 
 *
 */


public class AlphaBetaObjektTest {
	
	private Spieldaten oSpdaten;

	@Test
	public void testAll() {
		oSpdaten = Spieldaten.getInstance();
		oSpdaten.setSpieldaten(10, "sSpielerA", "sSpielerB");
		oSpdaten.spielzugAusfuehren(0, 1, 2);
		LinkedList<Integer> log = new LinkedList<Integer>();
		log.add(2);
		
		AlphaBetaObjekt abObjekt = new AlphaBetaObjekt(log);
		assertNotNull(abObjekt);
		assertEquals("Auswertung - kein Blatt", false, abObjekt.auswerten());
		
		log.add(4);
		log.add(8);
		
		abObjekt = new AlphaBetaObjekt(log);
		assertEquals("Auswertung - ein Blatt", true, abObjekt.auswerten());
		
		
		
		
	}

}
