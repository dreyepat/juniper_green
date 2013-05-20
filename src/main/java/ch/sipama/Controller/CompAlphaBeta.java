package ch.sipama.Controller;

import java.util.LinkedList;
import java.util.Random;

public class CompAlphaBeta implements ISpielStrategie{

	/**
	 * Die Methode (max) sucht den besten nächsten Zug mit dem MiniMax-Algorithmus
	 * @param oSpdaten - Das Spielfeld
	 * @param restTiefe - die restliche Tiefe
	 * @param suchTiefe - die gewünschte Suchtiefe
	 * @return ermittelt - der ermittelte Zugwert
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
		if(iLetzteZahl-1 == 1){
			return oSpdaten.getGroesstePrimzahl();
		}
		LinkedList<Integer> spielZugListe = new LinkedList<Integer>(oSpdaten.getZRaumListe(iLetzteZahl));

		if(spielZugListe.getFirst()==1 && spielZugListe.size()>1){
			spielZugListe.removeFirst();
		}
		if(spielZugListe.size()==1){
			return spielZugListe.getFirst();
		}

		lListLog = new LinkedList<Integer>();
		for(int i=0; i<oSpdaten.logListgroesse(); i++){
			lListLog.add(oSpdaten.getLogZahl(i));
		}

		//Ausgangswerte setzen
		iRueckgabewert = spielZugListe.getFirst();
		fProzent = 0;

		for(int i=1; i<spielZugListe.size(); i++){
			float fMax=0;
			float fMin=0;
			LinkedList<Integer>lListLogErweitert = new LinkedList<Integer>(lListLog);
			lListLogErweitert.addLast(spielZugListe.get(i));
			oAlphaBeta= new AlphaBetaObjekt(null, lListLogErweitert, spielZugListe.size());
			float fAuswertung = alphaBeta(oAlphaBeta, fMax, fMin);
			
			if(fAuswertung > fProzent){
				fProzent = fAuswertung;
				iRueckgabewert = spielZugListe.get(i);
			}

		}

		return iRueckgabewert;

	}





	public float alphaBeta(AlphaBetaObjekt oAlphaBeta, float fMax, float fMin){

		//sind wir bei einem Blatt gelandet?
		if(oAlphaBeta.auswerten()){
			if(oAlphaBeta.getlListLogGroesse()%2==0){
				return fMin + 1/oAlphaBeta.getiGeschwister();
			}else{
				return fMax + 1/oAlphaBeta.getiGeschwister();
			}
		}

		for(int i=0; i<oAlphaBeta.getlListSpielZugListgroesse(); i++){
			if(oAlphaBeta.getlListLogGroesse()%2==0){
				if(fMax< alphaBeta(oAlphaBeta, fMax, fMin)){
					fMax = alphaBeta(oAlphaBeta, fMax, fMin);
				}

				if(fMax==1){
					return fMax;
				}
			}else{
				if(fMin< alphaBeta(oAlphaBeta, fMax, fMin)){
					fMin = alphaBeta(oAlphaBeta, fMax, fMin);
				}

				if(fMin==1){
					return fMin;
				}
			}
		}
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