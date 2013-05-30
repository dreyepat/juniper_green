package ch.sipama.Controller;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Marco Lamm
 *
 */

public class CompHoechstTest {
	
	private Spieldaten oSpdaten;
	
	@Test
	public void testAll() {
		oSpdaten = Spieldaten.getInstance();
		oSpdaten.setSpieldaten(10, "sSpielerA", "sSpielerB");
		oSpdaten.spielzugAusfuehren(0, 3, 4);
		oSpdaten.setbSpielstart(false);
		
		//Objekt erstellen und testen, ob Erstellung klappte
		CompHoechst ch = new CompHoechst();
		assertNotNull(ch);
		
		//PC-Spielzug ausführen und prüfen, ob grösst mögliche Zahl gezogen wurde
		assertEquals("Höchst mögliche Zahl ist 8", 8, ch.naechsterPCSpielzug());
		
		//PC-Spielzug ausführen und prüfen, ob grösste Primzahl zurückgegeben wird
		oSpdaten.spielzugAusfuehren(0, 0, 1);
		
		assertEquals("Nach gezogener 1 wird die höchste Primzahl gezogen", 7, ch.naechsterPCSpielzug());
		
	}
}
