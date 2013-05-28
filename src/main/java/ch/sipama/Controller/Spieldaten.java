package ch.sipama.Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 * @author Simone Sterren
 *
 */

public class Spieldaten {

	//Instanzvariablen
	private static Spieldaten instance = null;
	private String sSpielerA;
	private String sSpielerB;
	private boolean bSpielstart;
	private boolean bSpielende;
	private int iZaehler; //für Anzahl Spielzüge
	private LinkedList<Spielzug> lListLog;
	private LinkedList<Integer> lListJgreen; 
	private ArrayList<LinkedList<Integer>> aListZRaumListen;
	private int iGroesstePrimzahl;

	public static Spieldaten getInstance(){
		if (instance == null){
			instance = new Spieldaten();
		}
		return instance;
	}

	public void setSpieldaten(int zrange, String spielerA, String spielerB){
		sSpielerA = spielerA;
		sSpielerB = spielerB;
		bSpielstart = true;
		bSpielende = false;

		//Anzahl Spielzüge zwischenspeichern
		iZaehler=0;

		//Liste für den Spielverlauf mit 'Spielzug'-Objekten
		lListLog = new LinkedList<Spielzug>();

		//Für jede Zahl des Zahlenraums alle Teiler und Vielfache bestimmen und in der aListZRaumListen abspeichern
		aListZRaumListen = new ArrayList<LinkedList<Integer>>();
		for(int i=1; i<=zrange; i++){
			lListJgreen = new LinkedList<Integer>();
			for(int j=1; j<=zrange; j++){
				if(j%i==0 || i%j==0) {
					lListJgreen.add(j);
				}
			}
			aListZRaumListen.add(lListJgreen);
		}		

		//Grösste Primzahl suchen und in Variable speichern
		if(zrange>0){
			iGroesstePrimzahl=0;
			int i=aListZRaumListen.size()-1;
			while(iGroesstePrimzahl<1){
				if(aListZRaumListen.get(i).size()==2){
					iGroesstePrimzahl=aListZRaumListen.get(i).get(1);
				}
				i--;
			}
		}
	}

	public String logAnzeigen(){
		String spielverlauf = "<html><body>";
		for(int i=0; i<lListLog.size(); i++){
			spielverlauf = spielverlauf + lListLog.get(i).getSpieler() + ": " + lListLog.get(i).getZahl() + "<br>";	
		}
		if(spielverlauf.length()==12){
			spielverlauf = spielverlauf + "Es wurde bisher noch kein Spielzug ausgeführt!";	
		}
		spielverlauf = spielverlauf + "</body></html>";
		return spielverlauf;
	}

	public void spielzugAusfuehren(int row, int column, int gezZahl){
		String spieler;
		iZaehler++;
		if(iZaehler % 2==0){
			spieler = sSpielerB;
		}else{
			spieler = sSpielerA;
		}

		//Spielzuginformationen ins Log speichern
		Spielzug spielzug = new Spielzug(spieler, row, column, gezZahl);
		lListLog.add(spielzug);

		//Prüfen, ob das Spiel zu Ende ist
		LinkedList<Integer> spielHilfe = naechsterSpielzug();
		if(spielHilfe.size()==0){
			String spielende = "<html><body>Es gibt keine weiteren Spielzüge mehr!<br><br>Herzlichen Glückwunsch - " + spieler + " hat gewonnen!</body></html>";
			JOptionPane.showMessageDialog(null, spielende, "Spiel beendet", JOptionPane.INFORMATION_MESSAGE);
			bSpielende = true;
		}
	}

	public void pcSpielzugAusfuehren(int row, int column, int gezZahl){
		if(bSpielende==false){
			iZaehler++;
			Spielzug spielzug = new Spielzug(sSpielerB, row, column, gezZahl);
			lListLog.add(spielzug);

			LinkedList<Integer> spielHilfe = naechsterSpielzug();
			if(spielHilfe.size()==0){
				String spielende = "<html><body>Es gibt keine weiteren Spielzüge mehr!<br><br>Herzlichen Glückwunsch - " + sSpielerB + " hat gewonnen!</body></html>";
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
			}
			return false;

		}else{
			if(lListLog.get(lListLog.size()-1).getZahl() % gezogeneZahl==0 || gezogeneZahl % lListLog.get(lListLog.size()-1).getZahl()==0){
				return true;
			}
			return false;
		}
	}

	public LinkedList<Integer> naechsterSpielzug(){
		LinkedList<Integer> spielHilfe;

		if (bSpielende) {
			spielHilfe = new LinkedList<Integer>();
			return spielHilfe;
		}

		if (bSpielstart) {
			spielHilfe = new LinkedList<Integer>(aListZRaumListen.get(1));
			spielHilfe.remove(0);
			return spielHilfe;
		}

		spielHilfe = new LinkedList<Integer>(aListZRaumListen.get(lListLog.getLast().getZahl()-1));

		for (int i=0; i < lListLog.size(); i++) {
			for(int j=spielHilfe.size()-1; j>=0; j--){
				if(lListLog.get(i).getZahl()==spielHilfe.get(j)){
					spielHilfe.remove(j);
				}	
			}
		}
		return spielHilfe;	
	}

	public String moegSPAnzeigen(){
		String moegSpielzuege = "<html><body> Mögliche Spielzüge:<br>";
		LinkedList<Integer> spielHilfe = naechsterSpielzug();
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

	public int logListgroesse(){
		return lListLog.size();
	}
	
	public String getLogSpieler(int index) {
		return lListLog.get(index).getSpieler();
	}
	
	public int getLogZahl(int index){
		return lListLog.get(index).getZahl();
	}

	public int getLogRow(int index){
		return lListLog.get(index).getRow();
	}
	
	public int getLogColumn(int index){
		return lListLog.get(index).getColumn();
	}
	
	public LinkedList<Integer> getZRaumListe(int index){
		return aListZRaumListen.get(index);
	}
	
	public boolean isbSpielende() {
		return bSpielende;
	}

	public int getGroesstePrimzahl() {
		return iGroesstePrimzahl;
	}

	public void rueckgaengigLog() {
		lListLog.removeLast();		
	}
}
