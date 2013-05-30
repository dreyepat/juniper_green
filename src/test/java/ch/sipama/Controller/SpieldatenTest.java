package ch.sipama.Controller;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Marco Lamm
 * 
 *
 */

public class SpieldatenTest {

	@Test
	public void testAll() {
		Spieldaten oSpdaten = Spieldaten.getInstance();
		assertNotNull(oSpdaten);
		
		oSpdaten.setSpieldaten(10, "sSpielerA", "sSpielerB");
		
		assertEquals("Neue Spieldaten - grösste Primzahl: 7", 7, oSpdaten.getGroesstePrimzahl());
		
		oSpdaten.spielzugAusfuehren(0, 3, 4);
		assertEquals("Spielende noch nicht erreicht: false", false, oSpdaten.isbSpielende());
		assertEquals("Letzte Zahl im Log: 4", 4, oSpdaten.getLogZahl(0));
		assertEquals("nächster Zug 5 - nicht erlaubt: false", false, oSpdaten.validieren(5));
		assertEquals("nächster Zug 8 - erlaubt: true", true, oSpdaten.validieren(8));
		
		
	}

}
