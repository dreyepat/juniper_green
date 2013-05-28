package ch.sipama.Controller;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Simone Sterren
 * @param oSpdaten - Das Spielfeld
 * @return iRueckgabewert - der ermittelte Zugwert
 */

public class CompAlphaBeta implements ISpielStrategie{

	private Spieldaten oSpdaten;
	private AlphaBetaObjekt oAlphaBeta;
	private LinkedList<Integer> lListLog;


	public CompAlphaBeta(){
		oSpdaten = Spieldaten.getInstance();
	}


	@Override
	public int naechsterPCSpielzug(){
		int iLetzteZahl = oSpdaten.getLogZahl(oSpdaten.logListgroesse()-1);
		if(iLetzteZahl == 1){
			return oSpdaten.getGroesstePrimzahl();
		}

		//Liste mit bisherigen Spielzügen erstellen
		lListLog = new LinkedList<Integer>();
		for(int i=0; i<oSpdaten.logListgroesse(); i++){
			lListLog.add(oSpdaten.getLogZahl(i));
		}

		//Liste mit den nächsten möglichen Spielzügen erstellen:
		LinkedList<Integer> spielZugListe = new LinkedList<Integer>(oSpdaten.getZRaumListe(iLetzteZahl-1));
		for(int i=0; i<lListLog.size(); i++){
			for(int j=spielZugListe.size()-1; j>=0; j--){
				if(lListLog.get(i)==spielZugListe.get(j)){
					spielZugListe.remove(j);
				}
			}
		}


		//1 aus der Auswahl entfernen, falls die Liste mehr als 1 Element enthält
		if(spielZugListe.getFirst()==1 && spielZugListe.size()>1){
			spielZugListe.removeFirst();
		}
		if(spielZugListe.size()==1){
			return spielZugListe.getFirst();
		}


		for(int i=0; i<spielZugListe.size(); i++){

			LinkedList<Integer>lListLogErweitert = new LinkedList<Integer>(lListLog);
			lListLogErweitert.addLast(spielZugListe.get(i));
			oAlphaBeta= new AlphaBetaObjekt(lListLogErweitert);
			int iAuswertung = alphaBeta(oAlphaBeta);

			if(iAuswertung == 1){
				return spielZugListe.get(i);	
			}
		}

		Random rnd=new Random();
		int z=rnd.nextInt(spielZugListe.size()); 
		return spielZugListe.get(z);

	}

	public int alphaBeta(AlphaBetaObjekt oAlphaBeta){
		int iAuswertung=0;

		//sind wir bei einem Blatt gelandet?
		if(oAlphaBeta.auswerten()){
			if(oAlphaBeta.getlListLogGroesse()%2==0){
				return 1;
			}
			return -1;
		}
		
		for(int i=0; i<oAlphaBeta.getlListSpielZugListgroesse(); i++){

			//für jeden möglichen Spielzug den möglichen Spielverlauf um diese Zahl erweitern und ein neues Alphabeta-Objekt erstellen
			LinkedList<Integer>lListLogErweitert = new LinkedList<Integer>(oAlphaBeta.getlListLog());
			lListLogErweitert.addLast(oAlphaBeta.getlListSpielZugZahl(i));
			AlphaBetaObjekt oAlphaBetaNachfolger = new AlphaBetaObjekt(lListLogErweitert);

			//Auswertung vom Ast zwischenspeichern und anschliessend prüfen:
			iAuswertung=alphaBeta(oAlphaBetaNachfolger);

			//PC ist am Zug:
			if(oAlphaBetaNachfolger.getlListLogGroesse()%2==0){	
				//Auswertung zu Gunsten des PCs: Wert zurückgeben und weitere Suche abbrechen
				if(iAuswertung==1){
					return iAuswertung;
				}
				
			//Spieler ist am Zug:
			}else{
				//Auswertung ist zu Gunsten des Spielers: Wert zurückgeben und weitere Suche abbrechen
				if(iAuswertung==-1){
					return iAuswertung;
				}
			}
		}
		return iAuswertung;
	}
}
