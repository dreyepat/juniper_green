package ch.sipama.Controller;

import java.util.LinkedList;

/**
 * @author Simone Sterren
 *
 */

public class AlphaBetaObjekt {

	private LinkedList<Integer> lListSpielZugListe;
	private LinkedList<Integer> lListLog;
	private Spieldaten oSpdaten;


	//neues Alphabeta-Objekt erstellen wobei das bisherige Log übernommen wird
	public AlphaBetaObjekt(LinkedList<Integer> log){
		lListLog = new LinkedList<Integer>(log);
		oSpdaten = Spieldaten.getInstance();

		//eine neue Liste erstellen mit allen möglichen nächsten Spielzügen
		int letzterZug = lListLog.getLast();
		lListSpielZugListe = new LinkedList<Integer>(oSpdaten.getZRaumListe(letzterZug-1));
		for(int i=0; i<lListLog.size(); i++){
			for(int j=lListSpielZugListe.size()-1; j>=0; j--){
				if(lListLog.get(i)==lListSpielZugListe.get(j)){
					lListSpielZugListe.remove(j);
				}
			}
		}
		//Die 1 aus der Liste möglicher Spielzüge entfernen		
		if(lListSpielZugListe.getFirst()==1){
			lListSpielZugListe.removeFirst();
		}
	}


	//Prüfen, ob das Alphabeta-Objekt ein Blatt ist
	public boolean auswerten(){
		if(lListSpielZugListe.size()==0){
			return true;
		}
		return false;	
	}
	
	public int getlListLogGroesse(){
		return lListLog.size();
	}
	
	public int getlListSpielZugListgroesse(){
		return lListSpielZugListe.size();
	}
	
	public int getlListSpielZugZahl(int index){
		return lListSpielZugListe.get(index);
	}
	
	public void removelListSpielZugZahl(Object letzteZahl){
		lListSpielZugListe.remove(letzteZahl);
	}
	
	public boolean getlListSpielZugZahl1(Object letzteZahl){
		if(lListSpielZugListe.contains(letzteZahl)){
			return true;
		}
		return false;
	}

	public LinkedList<Integer> getlListLog() {
		return lListLog;
	}
	
	public int getlListLogZahl(int index){
		return lListLog.get(index);
	}
	
	
}
