package ch.sipama.Controller;

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Test;

/**
 * @author Patrizia Dreyer
 *
 */
public class CompRandomTest {

	private Spieldaten oSpdaten;
	private CompRandom cr = new CompRandom();

	public boolean enthaeltZahl() {

		LinkedList<Integer> array = new LinkedList<Integer>(oSpdaten.naechsterSpielzug());
		if(array.contains(cr.naechsterPCSpielzug())){
			return true;
		}
		return false;

	}

	@Test
	public void testAll() {

		oSpdaten=Spieldaten.getInstance();
		oSpdaten.setSpieldaten(10, "sSpielerA", "sSpielerB");
		oSpdaten.spielzugAusfuehren(0, 3, 4);
		oSpdaten.setbSpielstart(false);

		//Objekt erstellen
		assertNotNull(cr);

		assertTrue(enthaeltZahl());
		
		oSpdaten.spielzugAusfuehren(0, 0, 1);
		
		assertEquals("Höchste Primzahl ziehen", 7, cr.naechsterPCSpielzug());

	}

}
