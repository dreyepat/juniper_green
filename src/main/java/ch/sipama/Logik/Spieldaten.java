package ch.sipama.Logik;

import java.util.ArrayList;
import ch.sipama.Controller.Spielzug;

public class Spieldaten {
	
	//Instanzvariablen
	String spielerA;
	String spielerB;
	boolean spielstart;
	int zaehler;
	private ArrayList<Integer> zahlenrange;
	private ArrayList<Spielzug> log;
	private ArrayList<MoeglicheZuege> moegZuege;
	
	
	public Spieldaten(int zrange, String spielerA, String spielerB){
		this.spielerA = spielerA;
		this.spielerB = spielerB;
		spielstart = true;
		zaehler=0;
		zahlenrange = new ArrayList<Integer>();
		log = new ArrayList<Spielzug>();
		
		for(int i=0; i<zrange; i++){
			zahlenrange.add(i+1);
		}
	}
	
	
	
	public String logAnzeigen(){
		String spielverlauf = "<html><body>";
		System.out.println(""+ log.size());
		for(int i=0; i<log.size(); i++){
		
		spielverlauf = spielverlauf + log.get(i).getSpieler() + " :" + log.get(i).getZahl() + "<br>";	
		}
		spielverlauf = spielverlauf + "</html></body>";
		System.out.println(spielverlauf);
		return spielverlauf;
	}
	
	
	public void spielzugAusfuehren(int row, int column, int gezZahl){
		String spieler;
		zaehler++;
		if(zaehler % 2==0){
			spieler = spielerB;
		}else{
			spieler = spielerA;
		}
		zahlenrange.remove(gezZahl);
		Spielzug spielzug = new Spielzug(spieler, row, column, gezZahl);
		log.add(spielzug);
		System.out.println(""+log.size());
		int i = log.size()-1;
		System.out.println(log.get(i).getSpieler() + " :" + log.get(i).getZahl());
		
	}

}
