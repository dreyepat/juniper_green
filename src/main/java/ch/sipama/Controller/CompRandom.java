package ch.sipama.Controller;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Simone Sterren
 *
 */

public class CompRandom implements ISpielStrategie{

	private Spieldaten oSpdaten;

	public CompRandom() {
		oSpdaten = Spieldaten.getInstance();
	}
	
	public int naechsterPCSpielzug(){

		//Sofern der Spieler die Zahl 1 gezogen hat, wird die grösste Primzahl zurückgegeben
		if(oSpdaten.getLogZahl(oSpdaten.logListgroesse()-1)==1){
			return oSpdaten.getGroesstePrimzahl();
		}
		
		//Liste mit möglichen Spielzügen erstellen
		LinkedList<Integer> moeglicheSpielzuege = new LinkedList<Integer>(oSpdaten.naechsterSpielzug());

		//Zahl 1 entfernen, (falls Array grösser als 1 und überhaupt im Array enthalten)
		if(moeglicheSpielzuege.size()>1 && moeglicheSpielzuege.getFirst()==1){
			moeglicheSpielzuege.removeFirst();
		}

		//Eine der möglichen Zahlen auswählen und zurückgeben
		Random rnd=new Random();
		int z=rnd.nextInt(moeglicheSpielzuege.size()); 
		return moeglicheSpielzuege.get(z);
	}

}
