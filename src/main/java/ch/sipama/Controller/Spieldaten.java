package ch.sipama.Controller;

import java.util.ArrayList;


public class Spieldaten {

	//Instanzvariablen
	private static Spieldaten instance = null;
	private MoeglicheZuege mZuege;
	private String spielerA;
	private String spielerB;
	boolean spielstart;
	int zaehler;
	private ArrayList<Spielzug> log;
	private ArrayList<MoeglicheZuege> moegZuege;


	public static Spieldaten getInstance(){
		if (instance == null){
			instance = new Spieldaten();
		}
		return instance;
	}

	public void setSpieldaten(int zrange, String spielerA, String spielerB){
		this.spielerA = spielerA;
		this.spielerB = spielerB;
		spielstart = true;
		zaehler=0;
//		zahlenrange = new ArrayList<Integer>();
		log = new ArrayList<Spielzug>();

		moegZuege = new ArrayList<MoeglicheZuege>();
		for(int i=1; i<=zrange; i++){
			String liste = "";
			mZuege = new MoeglicheZuege();
			for(int j=1; j<=zrange; j++){
				if(j%i==0 || i%j==0) {
					mZuege.getJgreen().add(j);
					liste = liste + j + " ";
				}
			}
			moegZuege.add(mZuege);
			System.out.println(liste);
		}



	}

	public String logAnzeigen(){
		String spielverlauf = "<html><body>";
		for(int i=0; i<log.size(); i++){

			spielverlauf = spielverlauf + log.get(i).getSpieler() + ": " + log.get(i).getZahl() + "<br>";	
		}
		spielverlauf = spielverlauf + "</body></html>";
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
		Spielzug spielzug = new Spielzug(spieler, row, column, gezZahl);
		log.add(spielzug);
	}
	
	public boolean validieren(int gezogeneZahl){
		if(spielstart==true){
			if(gezogeneZahl % 2==0){
				spielstart = false;
				return true;
			}else{
				return false;
			}
		}else{
			if(log.get(log.size()-1).getZahl() % gezogeneZahl==0 || gezogeneZahl % log.get(log.size()-1).getZahl()==0){
				return true;
			}else{
				return false;
			}
		}
	}
	
	
	public String moegSPAnzeigen(){
		String moegSpielzuege = "<html><body> Mögliche Spielzüge:<br>";
		
//		for(int i=0; i<moegZuege.get(zahl).getJgreen().size(); i++){
//			System.out.println("" + moegZuege.get(zahl).getJgreen().get(i));
//		}
		
		ArrayList<Integer> spielHilfe = naechsterSpielzug();
		for(int i=0; i<spielHilfe.size(); i++){
			moegSpielzuege = moegSpielzuege + spielHilfe.get(i) + ", ";
		}
		moegSpielzuege = moegSpielzuege + "</body></html>";
		return moegSpielzuege;
	}
	
	
	public ArrayList<Integer> naechsterSpielzug(){
		int zahl = log.get(log.size()-1).getZahl()-1;
		
		ArrayList<Integer> spielHilfe = (ArrayList<Integer>) moegZuege.get(zahl).getJgreen().clone();
		for(int i=0; i<log.size(); i++){
			for(int j=spielHilfe.size()-1; j>=0; j--){
				if(log.get(i).getZahl()==spielHilfe.get(j)){
					spielHilfe.remove(j);
				}	
			}
		}		
		return spielHilfe;	
	}

}
