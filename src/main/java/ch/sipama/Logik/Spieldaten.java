package ch.sipama.Logik;

import java.util.ArrayList;
import ch.sipama.Controller.Spielzug;

public class Spieldaten {
	
	//Instanzvariablen
	private ArrayList<Spielzug> log;
	
	
	public Spieldaten(){
		log = new ArrayList<Spielzug>();
	}
	
	
	
	public String logAnzeigen(){
		String spielverlauf = "<html><body>";
		for(int i=0; i<log.size(); i++){
		spielverlauf = spielverlauf + log.get(i).getSpieler() + " :" + log.get(i).getZahl() + "<br>";	
		}
		spielverlauf = spielverlauf + "</html></body>";
		return spielverlauf;
	}

}
