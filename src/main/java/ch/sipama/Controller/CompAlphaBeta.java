package ch.sipama.Controller;

import java.util.LinkedList;

public class CompAlphaBeta implements ISpielStrategie{

	/**
	 * @param oSpdaten - Das Spielfeld
	 * @return iRueckgabewert - der ermittelte Zugwert
	 */

	private Spieldaten oSpdaten;
	private int iRueckgabewert;
	private float fProzent;
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

		String liste= "";
		for(int i=0; i<spielZugListe.size(); i++){
			liste = liste + spielZugListe.get(i) + " ";
		}
		System.out.println("Mögliche Spielzüge für den PC: " + liste);


		//1 aus der Auswahl entfernen, falls die Liste mehr als 1 Element enthält
		if(spielZugListe.getFirst()==1 && spielZugListe.size()>1){
			spielZugListe.removeFirst();
		}
		if(spielZugListe.size()==1){
			return spielZugListe.getFirst();
		}



		for(int i=0; i<spielZugListe.size(); i++){
			float fMax=0;
			float fMin=0;
			LinkedList<Integer>lListLogErweitert = new LinkedList<Integer>(lListLog);
			lListLogErweitert.addLast(spielZugListe.get(i));
			System.out.println("Neues AlphabetaObjekt erstellt mit " + lListLogErweitert.getLast() + " als mögliche Zahl und Aanzahl Geschwister: " + spielZugListe.size());
			oAlphaBeta= new AlphaBetaObjekt(null, lListLogErweitert, spielZugListe.size());
			float fAuswertung = alphaBeta(oAlphaBeta, fMax, fMin);
			System.out.println("Auswertung: " + fAuswertung);

			if(i==0){
				fProzent = fAuswertung;
				iRueckgabewert = spielZugListe.getFirst();
				System.out.println("Erster Rückgabewert zwischengespeichert: " + iRueckgabewert);
			}

			if(fAuswertung > fProzent){
				fProzent = fAuswertung;
				iRueckgabewert = spielZugListe.get(i);
				System.out.println("Neuer zwischenwert mit " + fProzent + " Gewinnchance und " + iRueckgabewert + " als neue Returnzahl");
			}

		}
		System.out.println("PC-Spielzug mit Rückgabewert: " + iRueckgabewert);
		return iRueckgabewert;

	}





	public float alphaBeta(AlphaBetaObjekt oAlphaBeta, float fMax, float fMin){

		//sind wir bei einem Blatt gelandet?
		if(oAlphaBeta.auswerten()){
			if(oAlphaBeta.getlListLogGroesse()%2==0){
				fMin=-1;
				System.out.println("Blattauswertung: -1, da PC am Zug");
				return fMin;
			}else{
				fMax=1;
				System.out.println("Blattauswertung: 1, da Spieler am Zug");
				return fMax;
			}
		}
		
		System.out.println("Auswertung Schritt 1: kein Blatt");
		for(int i=0; i<oAlphaBeta.getlListSpielZugListgroesse(); i++){

			LinkedList<Integer>lListLogErweitert = new LinkedList<Integer>(oAlphaBeta.getlListLog());
			lListLogErweitert.addLast(oAlphaBeta.getlListSpielZugZahl(i));
			System.out.println("Neues AlphabetaObjekt erstellt mit " + lListLogErweitert.getLast() + " als mögliche Zahl und Aanzahl Geschwister: " + oAlphaBeta.getlListSpielZugListgroesse());
			oAlphaBeta= new AlphaBetaObjekt(oAlphaBeta, lListLogErweitert, oAlphaBeta.getlListSpielZugListgroesse());

			if(oAlphaBeta.getlListLogGroesse()%2==0){
				System.out.println("PC ist am Zug - neues Alphabeta-Objekt");
				fMax = alphaBeta(oAlphaBeta, fMax, fMin);
				System.out.println("fMax dazwischen: " + fMax);
				if(fMax==-1){
					lListLogErweitert.removeLast();
				}
				if(fMax==1){
					System.out.println("test: fMax" + fMax + " fMin: " + fMin);
					return fMax;
				}
			}else{
				System.out.println("Spieler ist am Zug - neues Alphabeta-Objekt");
				fMin = alphaBeta(oAlphaBeta, fMax, fMin);
				if(fMin==1){
					lListLogErweitert.removeLast();
				}	
				if(fMin==-1){
					System.out.println("test2 - fMin: " + fMin + " fMax: " + fMax);
					return fMin;
				}
			}
		}
		System.out.println("Return 0");
		return 0;

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