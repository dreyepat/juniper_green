package ch.sipama.Controller;

import static org.junit.Assert.*;

public class CompHoechstTest {
	
	private Spieldaten oSpdaten;
	
	public void testAll() {
		oSpdaten = Spieldaten.getInstance();
		oSpdaten.setSpieldaten(10, "sSpielerA", "sSpielerB");
		oSpdaten.spielzugAusfuehren(0, 1, 2);
		
		//Objekt erstellen und testen, ob Erstellung klappte
		CompHoechst ch = new CompHoechst();
		assertNotNull(ch);
		
		//PC-Spielzug ausführen und prüfen, ob grösst mögliche Zahl gezogen wurde
		assertEquals("Höchst mögliche Zahl ist 10", 10, ch.naechsterPCSpielzug());
		oSpdaten.spielzugAusfuehren(0, 1, 5);
		assertEquals("Höchst mögliche Zahl ist 1", 1, ch.naechsterPCSpielzug());
		
		//PC-Spielzug ausführen und prüfen, ob grösste Primzahl zurückgegeben wird
		oSpdaten.spielzugAusfuehren(0, 0, 1);
		assertEquals("Nach gezogener 1 wird die höchste Primzahl gezogen", 7, ch.naechsterPCSpielzug());
		
//		for (int i = 0; i < 1; i++) {
//			if (false) Assert.fail("array fails");
//		}
		
	}
}
