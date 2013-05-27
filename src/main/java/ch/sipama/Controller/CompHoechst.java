package ch.sipama.Controller;

import java.util.LinkedList;

public class CompHoechst implements ISpielStrategie{

	private Spieldaten oSpdaten;

	public CompHoechst() {
		oSpdaten = Spieldaten.getInstance();
	}


	public int naechsterPCSpielzug(){

		//Prüfen, ob der Spieler im Zug vorher die 1 gezogen hat - falls ja, die höchste Primzahl ziehen
		if(oSpdaten.getLogZahl(oSpdaten.logListgroesse()-1)==1){
			return oSpdaten.getGroesstePrimzahl();
		}

		//die höchst mögliche Zahl zurückgeben
		LinkedList<Integer> moeglicheSpielzuege = new LinkedList<Integer>(oSpdaten.naechsterSpielzug());
		return moeglicheSpielzuege.getLast();
	}

}
