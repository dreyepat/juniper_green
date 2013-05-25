package ch.sipama.Controller;

import java.util.LinkedList;
import java.util.Random;

public class CompAlphaBeta implements ISpielStrategie{

	/**
	 * @param oSpdaten - Das Spielfeld
	 * @return iRueckgabewert - der ermittelte Zugwert
	 */

	private Spieldaten oSpdaten;
	private AlphaBetaObjekt oAlphaBeta;
	private LinkedList<Integer> lListLog;


	public CompAlphaBeta(){
		oSpdaten = Spieldaten.getInstance();
	}


	@Override
	public int naechsterPCSpielzug(){
		int iLetzteZahl = oSpdaten.getLogZahl(oSpdaten.logListgroesse()-1);
		System.out.println("Letzter Spielzug " + iLetzteZahl);
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


//		String liste= "";
//		for(int i=0; i<spielZugListe.size(); i++){
//			liste = liste + spielZugListe.get(i) + " ";
//		}
//		System.out.println("Mögliche Spielzüge für den PC: " + liste);


		for(int i=0; i<spielZugListe.size(); i++){

			LinkedList<Integer>lListLogErweitert = new LinkedList<Integer>(lListLog);
			lListLogErweitert.addLast(spielZugListe.get(i));
			oAlphaBeta= new AlphaBetaObjekt(null, lListLogErweitert);
			int iAuswertung = alphaBeta(oAlphaBeta);
//			System.out.println("Auswertung: " + iAuswertung);

			if(iAuswertung == 1){
				System.out.println("Siegesstrasse gefunden - Rückgabezahl: " + spielZugListe.get(i));
				return spielZugListe.get(i);	
			}
		}

		System.out.println("keine Siegesstrasse gefunden - Randommässig eine Zahl zurückgegeben");
		Random rnd=new Random();
		int z=rnd.nextInt(spielZugListe.size()); 
		return spielZugListe.get(z);

	}





	public int alphaBeta(AlphaBetaObjekt oAlphaBeta){

		int iAuswertung=0;
		
		//sind wir bei einem Blatt gelandet?
		if(oAlphaBeta.auswerten()){
			if(oAlphaBeta.getlListLogGroesse()%2==1){
//				System.out.println("Blattauswertung: -1, da PC am Zug");
				return -1;
			}else{
//				System.out.println("Blattauswertung: 1, da Spieler am Zug");
				return 1;
			}
		}

		for(int i=0; i<oAlphaBeta.getlListSpielZugListgroesse(); i++){

			//für jeden möglichen Spielzug den möglichen Spielverlauf um diese Zahl erweitern und ein neues Alphabeta-Objekt erstellen
			LinkedList<Integer>lListLogErweitert = new LinkedList<Integer>(oAlphaBeta.getlListLog());
			lListLogErweitert.addLast(oAlphaBeta.getlListSpielZugZahl(i));
//			System.out.println("Neues AlphabetaObjekt erstellt mit " + lListLogErweitert.getLast());
			AlphaBetaObjekt alphaBetaNachfolger = new AlphaBetaObjekt(oAlphaBeta, lListLogErweitert);

			//Auswertung vom Ast zwischenspeichern und anschliessend prüfen:
			iAuswertung=alphaBeta(alphaBetaNachfolger);

			//PC ist am Zug:
			if(alphaBetaNachfolger.getlListLogGroesse()%2==0){
				
				//Block zum löschen, sobald es läuft
//				System.out.println("PC-Zug mit Rückgabewert: " + iAuswertung);		
//				String liste1= "";
//				for(int k=0; k<alphaBetaNachfolger.getlListLogGroesse(); k++){
//					liste1 = liste1 + alphaBetaNachfolger.getlListLogZahl(k) + " ";
//				}
//				System.out.println("Bisheriger Spielverlauf: " + liste1);
//				String liste= "";
//				for(int j=0; j<alphaBetaNachfolger.getlListSpielZugListgroesse(); j++){
//					liste = liste + alphaBetaNachfolger.getlListSpielZugZahl(j) + " ";
//				}
//				System.out.println("Mögliche Spielzüge für den PC: " + liste);
//				
				//Auswertung zu meinen Gunsten - nicht weitersuchen und zurückgeben
				if(iAuswertung==1){
//					System.out.println("iAuswertung zu Gunsten des PCs, deshalb Wert zurückgeben");
					return iAuswertung;
				}
				
				//Falls nicht, prüfen, ob es noch weitere Spielzüge gibt, die für den PC besser sind:
//				System.out.println("Gibt es noch bessere Spielzüge für PC?");
				
			}else{
				//Block zum löschen, sobald es läuft
//				System.out.println("Spieler-Zug mit Rückgabewert: " + iAuswertung);		
//				String liste1= "";
//				for(int k=0; k<alphaBetaNachfolger.getlListLogGroesse(); k++){
//					liste1 = liste1 + alphaBetaNachfolger.getlListLogZahl(k) + " ";
//				}
//				System.out.println("Bisheriger Spielverlauf: " + liste1);
//				String liste= "";
//				for(int j=0; j<alphaBetaNachfolger.getlListSpielZugListgroesse(); j++){
//					liste = liste + alphaBetaNachfolger.getlListSpielZugZahl(j) + " ";
//				}
//				System.out.println("Mögliche Spielzüge für den Spieler: " + liste);
				
				//Auswertung ist zu Gunsten des Spielers
				if(iAuswertung==-1){
//					System.out.println("iAuswertung zu Gunsten des Spielers, deshalb Wert zurückgeben");
					return iAuswertung;
				}
				
				//Falls nicht, prüfen, ob es noch weitere Spielzüge gibt, die für den Spieler besser sind:
//				System.out.println("Gibt es noch bessere Spielzüge für Spieler?");
			}
		}

//		System.out.println("kein Resultat in der Forschleife - Zwischenwert wird weitergegeben: " + iAuswertung);
		return iAuswertung;
	}

	//	1 function ALPHABETA(KNOTEN V, INT A, INT B): INT  				Knoten: gezZahl, Zahl A, Zahl B und Augabewert: "nächster Spielzug" als Zahl
	//	2 if (v hat keinen Nachfolger) return f(v); {Blattbewertung}	if v=1, höchste Primzahl oder verlieren
	//	3 f ¨ur alle Nachfolger w von v do								
	//	4 if (MAX-Spieler ist bei v am Zug)
	//	4a a = max(a, alphabeta(w, a, b));
	//	4b if (a >= b) return a; {Beta-Cutoff}
	//	5 else
	//	5a b = min(b, alphabeta(w, a, b));
	//	5b if (a >= b) return b; {Alpha-Cutoff}
	//	end if
	//	6 if (MAX-Spieler ist bei v am Zug)
	//	6 return a;
	//	6 else
	//	6 return b;
	//	end if
	//	end function





}



//		System.out.println("PC-Zug mit Rückgabewert: " + iAuswertung);
//		
//		String liste= "";
//		for(int j=0; j<oAlphaBeta.getlListSpielZugListgroesse(); j++){
//			liste = liste + oAlphaBeta.getlListSpielZugZahl(j) + " ";
//		}
//		System.out.println("Mögliche Spielzüge für den PC: ist das korrekt? " + liste);
//		
//		
//		if(iAuswertung==-1){
//			int letzteZahl = lListLogErweitert.getLast();
//			lListLogErweitert.removeLast();
//			if(oAlphaBeta.getlListSpielZugListgroesse()>0){
//				boolean bZentfernt = oAlphaBeta.getlListSpielZugZahl1(letzteZahl); 
//				System.out.println("SpielzuglistGrösse vorher: " + oAlphaBeta.getlListSpielZugListgroesse() + " Boolean - Objekt gefunden: " + bZentfernt);
//				oAlphaBeta.removelListSpielZugZahl(letzteZahl);
//				System.out.println("SpielzuglistGrösse nachher: " + oAlphaBeta.getlListSpielZugListgroesse() + " iAuswertung = " + iAuswertung);
//				i--;
//			}else{
//				oAlphaBeta = oAlphaBeta.getoVorgaenger();
//				lListLogErweitert.removeLast();
//				if(!oAlphaBeta.auswerten()){
//					iAuswertung=0;
//					System.out.println("iAuswertung = 0 gesetzt");
//					//return iAuswertung;
//				}
//			}
//		}
//		if(iAuswertung==1){
//			System.out.println("Siegesstrasse für PC - iAuswertung=1 wird zurückgegeben");
//			oAlphaBeta = oAlphaBeta.getoVorgaenger();
//			return iAuswertung;
//		}
//		
//		
//		
//	}else{
//		System.out.println("Spieler ist am Zug - neues Alphabeta-Objekt");
//		iAuswertung = alphaBeta(oAlphaBeta);
//		if(iAuswertung==1){
//			int letzteZahl = lListLogErweitert.getLast();
//			lListLogErweitert.removeLast();
//			if(oAlphaBeta.getlListSpielZugListgroesse()>0){
//				boolean bZentfernt = oAlphaBeta.getlListSpielZugZahl1(letzteZahl);
//				System.out.println("SpielzuglistGrösse vorher: " + oAlphaBeta.getlListSpielZugListgroesse() + " Boolean - Objekt gefunden: " + bZentfernt);
//				oAlphaBeta.removelListSpielZugZahl(letzteZahl);
//				System.out.println("SpielzuglistGrösse nachher: " + oAlphaBeta.getlListSpielZugListgroesse() + " iAuswertung = " + iAuswertung);
//				i--;
//			}else{
//				oAlphaBeta = oAlphaBeta.getoVorgaenger();
//				lListLogErweitert.removeLast();
//				if(!oAlphaBeta.auswerten()){
//					iAuswertung=0;
//					System.out.println("iAuswertung = 0 gesetzt");
//					//return iAuswertung;
//				}
//			}
//		}	
//		if(iAuswertung==-1){
//			System.out.println("Siegesstrasse für Spieler - iAuswertung=-1 wird zurückgegeben");
//			oAlphaBeta = oAlphaBeta.getoVorgaenger();
//			return iAuswertung;
//		}
//	}
//	
//}