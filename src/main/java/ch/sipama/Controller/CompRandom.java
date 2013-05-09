package ch.sipama.Controller;

import java.util.ArrayList;
import java.util.Random;

public class CompRandom implements ISpielStrategie{

	private Spieldaten spieldaten;



	public CompRandom() {
		spieldaten = Spieldaten.getInstance();
	}


	public int naechsterPCSpielzug(){

		if(spieldaten.getLog().get(spieldaten.getLog().size()-1).getZahl()==1){
			return spieldaten.getGroesstePrimzahl();
		}else{
			ArrayList<Integer> moeglicheSpielzuege = (ArrayList<Integer>) spieldaten.naechsterSpielzug().clone();

			//Zahl 1 entfernen, falls im Array mehr als 1 Zahl ist und falls die erste Zahl des Arrays wirklich eine 1 ist
			if(moeglicheSpielzuege.size()>1 && moeglicheSpielzuege.get(0)==1){
				moeglicheSpielzuege.remove(0);
			}

			if(moeglicheSpielzuege.size()>0){
				Random rnd=new Random();
				int z=rnd.nextInt(moeglicheSpielzuege.size()); 

				return moeglicheSpielzuege.get(z);
			}
		}

		return 0;
	}

}
