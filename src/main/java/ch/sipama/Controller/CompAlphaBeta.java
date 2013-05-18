package ch.sipama.Controller;

import java.util.ArrayList;
import java.util.Random;

public class CompAlphaBeta implements ISpielStrategie{

	/**
	 * Die Methode (max) sucht den besten nächsten Zug mit dem MiniMax-Algorithmus
	 * @param spdaten - Das Spielfeld
	 * @param restTiefe - die restliche Tiefe
	 * @param suchTiefe - die gewünschte Suchtiefe
	 * @return ermittelt - der ermittelte Zugwert
	 */

	private Spieldaten spdaten;
	private int suchtiefe;
	private int rueckgabewert;
	private AlphaBetaObjekt alphaBetaO;
	private ArrayList<Integer> log;
	private ArrayList<AlphaBetaObjekt> objektListe;





	public CompAlphaBeta(){
		spdaten = Spieldaten.getInstance();
		suchtiefe = 3;
		objektListe = new ArrayList<AlphaBetaObjekt>();
	}


	@Override
	public int naechsterPCSpielzug(){
		ArrayList<Integer> spielZugListe = (ArrayList<Integer>) spdaten.naechsterSpielzug().clone();

		if(spielZugListe.get(0)==1 && spielZugListe.size()>1){
			spielZugListe.remove(0);
		}
		if(spielZugListe.size()==1){
			return spielZugListe.get(0);

		}else{
			log = new ArrayList<Integer>();
			for(int i=0; i<spdaten.getLog().size(); i++){
				log.add(spdaten.getLog().get(i).getZahl());
			}
			alphaBetaO= new AlphaBetaObjekt(0, log);
			objektListe.add(alphaBetaO);

			alphaBeta(alphaBetaO.getPointer());
			return rueckgabewert;
		}
	}





	public void alphaBeta(int i){
		int naechsterZug = alphaBetaO.getSpielZugListe().get(alphaBetaO.getPointer());
		alphaBetaO = new AlphaBetaObjekt(objektListe.get(objektListe.size()-1).getSuchtiefe(), objektListe.get(objektListe.size()-1).getLog());

		//im neuen alphaBetaO das Log um die Zahl erweitern, auf den im Vorgängerobjekt der Pointer zeigt
		alphaBetaO.getLog().add(naechsterZug);

		//im Vorgängerobjekt den Pointer um 1 erhöhen
		objektListe.get(objektListe.size()-1).setPointer(i+1);

		//das neue alphaBetaO dem Spielbaum hinzufügen, den man auswertet
		objektListe.add(alphaBetaO);


		//neues Objekt auswerten, ob es ein Blatt ist:
		int auswertung = alphaBetaO.auswerten();

		//wenn Suchtiefe noch nicht erreicht ist und es kein Blatt ist, den Alphabeta-Algorithmus erneut aufrufen
		if(auswertung==0 && alphaBetaO.getSuchtiefe()<suchtiefe){
			alphaBeta(alphaBetaO.getPointer());



			//wenn es ein Blatt ist:	
		}else if(auswertung==0 && alphaBetaO.getSuchtiefe()==suchtiefe){
			while(objektListe.size()>1){
				objektListe.remove(objektListe.size()-1);
				if(objektListe.get(objektListe.size()-1).getSpielZugListe().size()>objektListe.get(objektListe.size()-1).getPointer()){
					alphaBetaO=objektListe.get(objektListe.size()-1);
					alphaBeta(alphaBetaO.getPointer());
				}	
			}
			//Vorgehen, wenn kein "Gewinnerpfad" gefunden wurde - Zufallszahl aus der ersten Liste
			Random rnd=new Random();
			int z=rnd.nextInt(objektListe.get(0).getSpielZugListe().size());
			rueckgabewert = objektListe.get(0).getSpielZugListe().get(z);

		}else if(auswertung==1 && alphaBetaO.getSuchtiefe()%2==0){
			if(objektListe.size()>1){
				objektListe.get(objektListe.size()-2).setAlpha(objektListe.get(objektListe.size()-2).getAlpha()+1);
				//Auswertung nach oben fortsetzen....
			}else{
				rueckgabewert = objektListe.get(0).getSpielZugListe().get(objektListe.get(0).getPointer()-1);
			}

		}else if(auswertung==1 && alphaBetaO.getSuchtiefe()%2==1){
			if(objektListe.size()>1){
				objektListe.get(objektListe.size()-2).setBeta(objektListe.get(objektListe.size()-2).getBeta()-1);
				//Auswertung nach oben fortsetzen...	
			}else{
				//bei 'Verliererpfad' die Zahl aus der Liste möglicher Spielzüge entfernen, sofern mehr als 1 zur Auswahl steht
				if(objektListe.get(0).getSpielZugListe().size()>1){
					objektListe.get(0).setPointer(objektListe.get(0).getPointer()-1);
					objektListe.get(0).getSpielZugListe().remove(objektListe.get(0).getPointer());
				}else{
					//wir verlieren... *snief*
					rueckgabewert = objektListe.get(0).getSpielZugListe().get(0);
				}
			}
		}






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







	//	private int column;
	//	private int row;
	//	private int gezZahl;
	//	private int suchTiefe;
	//	private int oppId = 0;
	//	
	//	public int maxWert(int restTiefe, int alpha, int beta) {
	//		int ermittelt = 0;
	//		int zugWert = 0;
	//		int counter = 0;
	//		int id = 0;
	//		spdaten = Spieldaten.getInstance();
	//		
	//		if(id==1) {
	//			int oppId = 4;
	//		} else {
	//			int oppID = 1;
	//		}	
	//		
	//		ermittelt= Integer.MIN_VALUE;
	//		
	//		for(int i=0; i<column; i++) {
	//			counter = 0;
	//			if(spdaten.validieren(i)) {
	//				
	//				spdaten.pcSpielzugAusfuehren(row, column, gezZahl);		//Spielzug ausführen spielzugAusfuehren(row, column, i);
	//				
	//				if(restTiefe <= 1 || spdaten.bSpielstart == true) { 		//board.isFull() 
	//					for (int ii=0; ii<row; ii++) { 
	//						if(spdaten.bSpielstart == false) {				//board.isOccupied(ii,i)				
	//							counter++;
	//						} else {
	//							zugWert = countPoints(gezZahl, counter, id);
	//							break;
	//						}
	//					}
	//				}
	//				else {
	//					zugWert = minWert(restTiefe-1,alpha,beta);
	//					
	//				}
	//					
	////				spdaten.rueckgangig(i,id);								//board.unmove(i,id)
	//				
	//				if (zugWert >= beta)
	//		            return beta;
	//				
	//				if(zugWert>ermittelt) {
	//					ermittelt = zugWert;
	//					
	//					if(restTiefe == suchTiefe) {
	//						
	//						if(spdaten.validieren(i)) {						//board.isValidMove(i)
	//							i = spdaten.naechsterSpielzug().get(i);
	//						}
	//					}
	//				}
	//			}
	//		}
	//		return ermittelt;
	//	}
	//	
	//	
	//	public int countPoints(int gezZahl, int counter, int id) {
	//		gezZahl = 0;
	//		
	//		return gezZahl;
	//	}
	//
	//
	//	/**
	//	 * @param spdaten - Das Spielfeld
	//	 * @param restTiefe - die restliche Tiefe
	//	 * @param suchTiefe - die gewünschte Suchtiefe
	//	 * @return ermittelt - der ermittelte Zugwert
	//	 */
	//	public int minWert(int restTiefe, int alpha, int beta) {
	//		int ermittelt = 0; 
	//		int zugWert = 0;
	//		int counter = 0;
	//		
	//		ermittelt = Integer.MAX_VALUE;
	//		
	//		//col
	//		for(int i=0; i<column; i++){
	//			counter=0;
	//			if(spdaten.validieren(i)) {	
	//				
	//				spdaten.spielzugAusfuehren(row, column, i);
	//				    
	//				if(restTiefe<=1 || spdaten.bSpielstart == true) { 
	//					for (int ii=0; ii<row; ii++) { 
	//						if(spdaten.bSpielstart == false) {
	//							counter++;
	//						} else {
	//							zugWert = -countPoints(i, counter, oppId);
	//							break;
	//						}						
	//					}
	//				}
	//				else {
	//					zugWert = maxWert(restTiefe-1,alpha,beta);
	//				}
	//								
	////				spdaten.rueckgangig(i,id);								//board.unmove(i,id)
	//				
	//				if (zugWert <= alpha)
	//		            return alpha;
	//                                
	//				
	//				if(zugWert<ermittelt) {
	//					ermittelt = zugWert;
	//				}
	//			}
	//		}
	//		return ermittelt;
	//	}




}