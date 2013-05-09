package ch.sipama.Controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Spieldaten {

	//Instanzvariablen
	private static Spieldaten instance = null;
	private MoeglicheZuege mZuege;
	private String spielerA;
	private String spielerB;
	boolean bSpielstart;
	boolean bSpielende;
	private int zaehler; //für Anzahl Spielzüge
	private ArrayList<Spielzug> log;
	private ArrayList<MoeglicheZuege> moegZuege;
	private int groesstePrimzahl;


	

	public static Spieldaten getInstance(){
		if (instance == null){
			instance = new Spieldaten();
		}
		return instance;
	}
	
	public boolean isbSpielende() {
		return bSpielende;
	}

	public ArrayList<Spielzug> getLog() {
		return log;
	}

	public int getGroesstePrimzahl() {
		return groesstePrimzahl;
	}
	
	public void setSpieldaten(int zrange, String spielerA, String spielerB){
		this.spielerA = spielerA;
		this.spielerB = spielerB;
		bSpielstart = true;
		bSpielende = false;
		zaehler=0;
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
		if(zrange>0){
			groesstePrimzahl=0;
			int i=moegZuege.size()-1;
			while(groesstePrimzahl<1){
				if(moegZuege.get(i).getJgreen().size()==2){
					groesstePrimzahl=moegZuege.get(i).getJgreen().get(1);
				}
				i--;
			}
			System.out.println("grösste Primzahl: " + groesstePrimzahl);
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

		ArrayList<Integer> spielHilfe = naechsterSpielzug();
		if(spielHilfe.size()==0){
			String spielende = "<html><body>Es gibt keine weiteren Spielzüge mehr!<br><br>Herzlichen Glückwunsch - " + spieler + " hat gewonnen!</body></html>";
			JOptionPane.showMessageDialog(null, spielende, "Spiel beendet", JOptionPane.INFORMATION_MESSAGE);
			bSpielende = true;
		}
	}

	
	
	public void pcSpielzugAusfuehren(int row, int column, int gezZahl){
		if(bSpielende==false){
			zaehler++;
			Spielzug spielzug = new Spielzug(spielerB, row, column, gezZahl);
			log.add(spielzug);

			ArrayList<Integer> spielHilfe = naechsterSpielzug();
			if(spielHilfe.size()==0){
				String spielende = "<html><body>Es gibt keine weiteren Spielzüge mehr!<br><br>Herzlichen Glückwunsch - " + spielerB + " hat gewonnen!</body></html>";
				JOptionPane.showMessageDialog(null, spielende, "Spiel beendet", JOptionPane.INFORMATION_MESSAGE);
				bSpielende = true;
			}
		}
	}


	public boolean validieren(int gezogeneZahl){
		if(bSpielstart==true){
			if(gezogeneZahl % 2==0){
				bSpielstart = false;
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



	public ArrayList<Integer> naechsterSpielzug(){
		ArrayList<Integer> spielHilfe = new ArrayList<Integer>();
		if(bSpielende==false && bSpielstart == false){
			int zahl = log.get(log.size()-1).getZahl()-1;
			spielHilfe = (ArrayList<Integer>) moegZuege.get(zahl).getJgreen().clone();
			for(int i=0; i<log.size(); i++){
				for(int j=spielHilfe.size()-1; j>=0; j--){
					if(log.get(i).getZahl()==spielHilfe.get(j)){
						spielHilfe.remove(j);
					}	
				}
			}
		}else if(bSpielstart == true){
			for(int i=2; i<=moegZuege.size(); i++){
				if(i%2==0){
					spielHilfe.add(i);
				}
			}
		}
		return spielHilfe;	
	}


	public String moegSPAnzeigen(){
		String moegSpielzuege = "<html><body> Mögliche Spielzüge:<br>";
		ArrayList<Integer> spielHilfe = naechsterSpielzug();
		for(int i=0; i<spielHilfe.size(); i++){
			moegSpielzuege = moegSpielzuege + spielHilfe.get(i) + ", ";
			if(i%10==0 && i>0){
				moegSpielzuege = moegSpielzuege + "<br>";
			}
		}
		moegSpielzuege = moegSpielzuege.substring(0, moegSpielzuege.length()-2);
		moegSpielzuege = moegSpielzuege + "</body></html>";
		return moegSpielzuege;
	}


}
