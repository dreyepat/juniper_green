package ch.sipama.Controller;

import static org.junit.Assert.*;
import org.junit.Test;

public class CompHoechstTest {
	
	private Spieldaten oSpdaten;

	@Test
	public void testCompHoechst() {
		assertNotNull("Spieldaten", Spieldaten.getInstance());
	}

	@Test
	public void testNaechsterPCSpielzug() {
		assertNotNull("Spielzuege", 0);
	}

}
