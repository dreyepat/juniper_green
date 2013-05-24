package ch.sipama.Controller;

import java.util.LinkedList;

public class AlphaBetaObjekt {

	private AlphaBetaObjekt oVorgaenger;
	private LinkedList<Integer> lListSpielZugListe;
	private LinkedList<Integer> lListLog;

	

	private Spieldaten oSpdaten;


	public AlphaBetaObjekt(AlphaBetaObjekt vorgaenger, LinkedList<Integer> log){
		oVorgaenger = vorgaenger;
		lListLog = new LinkedList<Integer>(log);
		
		String sLog = "";
		for(int i=0; i<lListLog.size(); i++){
			sLog = sLog + lListLog.get(i) + " ";
		}
		System.out.println("virtuelles Log: " + sLog);
		
		oSpdaten = Spieldaten.getInstance();

		int letzterZug = lListLog.getLast();
		lListSpielZugListe = new LinkedList<Integer>(oSpdaten.getZRaumListe(letzterZug-1));
		for(int i=0; i<lListLog.size(); i++){
			for(int j=lListSpielZugListe.size()-1; j>=0; j--){
				if(lListLog.get(i)==lListSpielZugListe.get(j)){
					lListSpielZugListe.remove(j);
				}
			}
		}
				
		if(lListSpielZugListe.getFirst()==1){
			lListSpielZugListe.removeFirst();
		}
		
		
		String liste= "";
		for(int i=0; i<lListSpielZugListe.size(); i++){
			liste = liste + lListSpielZugListe.get(i) + " ";
		}
		System.out.println("Mögliche Spielzüge vom neuen AlphaBetaObjekt: " + liste);
		
		
	}


	public boolean auswerten(){
		if(lListSpielZugListe.size()==0){
			return true;
		}
		return false;	
	}

	public AlphaBetaObjekt getoVorgaenger() {
		return oVorgaenger;
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
	
}
