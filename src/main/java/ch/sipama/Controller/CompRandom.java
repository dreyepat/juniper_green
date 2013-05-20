package ch.sipama.Controller;

import java.util.LinkedList;
import java.util.Random;

public class CompRandom implements ISpielStrategie{

	private Spieldaten oSpdaten;

	public CompRandom() {
		oSpdaten = Spieldaten.getInstance();
	}
	
	public int naechsterPCSpielzug(){

		if(oSpdaten.getLogZahl(oSpdaten.logListgroesse()-1)==1){
			return oSpdaten.getGroesstePrimzahl();
		}
		LinkedList<Integer> moeglicheSpielzuege = new LinkedList<Integer>(oSpdaten.naechsterSpielzug());

		//Zahl 1 entfernen, falls im Array mehr als 1 Zahl ist und falls die erste Zahl des Arrays wirklich eine 1 ist
		if(moeglicheSpielzuege.size()>1 && moeglicheSpielzuege.getFirst()==1){
			moeglicheSpielzuege.removeFirst();
		}

		//Eine der möglichen Zahlen auswählen und zurückgeben
		Random rnd=new Random();
		int z=rnd.nextInt(moeglicheSpielzuege.size()); 
		return moeglicheSpielzuege.get(z);
	}

}
