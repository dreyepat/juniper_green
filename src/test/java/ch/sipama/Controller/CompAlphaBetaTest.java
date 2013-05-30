package ch.sipama.Controller;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

/**
 * @author Simone Sterren
 * 
 * 
 */

public class CompAlphaBetaTest {

	private Spieldaten oSpdaten;
	private AlphaBetaObjekt oAlphaBeta;


	@Test
	public void testAll() {

		oSpdaten=Spieldaten.getInstance();
		oSpdaten.setSpieldaten(10, "sSpielerA", "sSpielerB");
		oSpdaten.spielzugAusfuehren(0, 3, 4);
		oSpdaten.setbSpielstart(false);

		CompAlphaBeta cab = new CompAlphaBeta();
		assertNotNull(cab);

		assertEquals("Gewinnstrasse für 4: 8", 8, cab.naechsterPCSpielzug());

		oSpdaten.spielzugAusfuehren(0, 1, 2);
		oSpdaten.spielzugAusfuehren(0, 9, 10);

		assertEquals("Nur noch 1 Zahl möglich (neben 1): 5", 5, cab.naechsterPCSpielzug());

		oSpdaten.spielzugAusfuehren(0, 4, 5);

		assertEquals("Nur noch 1 übrig", 1, cab.naechsterPCSpielzug());

		oSpdaten.spielzugAusfuehren(0, 0, 1);
		assertEquals("Höchtes Primzahl zurückgeben", 7, cab.naechsterPCSpielzug());




		oSpdaten.setSpieldaten(10, "sSpielerA", "sSpielerB");
		oSpdaten.spielzugAusfuehren(0, 3, 4);
		oSpdaten.setbSpielstart(false);

		LinkedList<Integer>lListLogErweitert = new LinkedList<Integer>();
		lListLogErweitert.add(4);
		lListLogErweitert.add(2);
		oAlphaBeta = new AlphaBetaObjekt(lListLogErweitert);

		assertEquals("Gewinnstrategie nicht gefunden: -1", -1, cab.alphaBeta(oAlphaBeta));

		lListLogErweitert.removeLast();
		lListLogErweitert.add(8);

		oAlphaBeta = new AlphaBetaObjekt(lListLogErweitert);

		assertEquals("Gewinnstrategie gefunden: +1", 1, cab.alphaBeta(oAlphaBeta));
	}

}
