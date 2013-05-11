package ch.sipama.Controller;

import java.util.ArrayList;

public class AlphaBeta implements ISpielStrategie{

	/**
	 * Die Methode (max) sucht den besten nächsten Zug mit dem MiniMax-Algorithmus
	 * @param spdaten - Das Spielfeld
	 * @param restTiefe - die restliche Tiefe
	 * @param suchTiefe - die gewünschte Suchtiefe
	 * @return ermittelt - der ermittelte Zugwert
	 */

	private Spieldaten spdaten;
	private int suchtiefe;
	private ArrayList<Integer> alphaBetaLog;
	private int alpha;
	private int beta;

	public AlphaBeta(){
		spdaten = spdaten.getInstance();
		suchtiefe = 3;
		alphaBetaLog = new ArrayList<Integer>();
		for(int i=0; i<spdaten.getLog().size(); i++){
			alphaBetaLog.add(spdaten.getLog().get(i).getZahl());
		}
		alpha=0;
		beta=0;
		alphaBeta(alphaBetaLog, alpha, beta);
	}

	public int alphaBeta(ArrayList<Integer> alphaBetaLog, int alpha, int beta){
		if(alphaBetaLog.get(alphaBetaLog.size()-1)==1){
			if(alphaBetaLog.size()%2==0){											//Computer ist Spieler B
				alpha=1;
			}else{
				beta=-1;
			}
		}



		ArrayList<Integer> nachfolger = new ArrayList<Integer>();
		int zahl = alphaBetaLog.get(alphaBetaLog.size()-1);
		nachfolger = (ArrayList<Integer>) spdaten.getMoegZuege().get(zahl).getJgreen().clone();
		for(int i=0; i<alphaBetaLog.size(); i++){
			for(int j=nachfolger.size()-1; j>=0; j--){
				if(alphaBetaLog.get(i)==nachfolger.get(j)){
					nachfolger.remove(j);
				}	
			}
		}


		ArrayList<ArrayList> spielbaum = new ArrayList<ArrayList>();
		for(int i=0; i<nachfolger.size(); i++){
			ArrayList<Integer> spielast = (ArrayList<Integer>) alphaBetaLog.clone();
			spielast.add(nachfolger.get(i));
			spielbaum.add(spielast);
		}

		
		for(int i=0; i<spielbaum.size(); i++){
			if((alphaBetaLog.size()+1)%2==0){											//Spieler A ist am Zug
				beta = min(beta, alphaBeta(spielbaum.get(i), alpha, beta));
				if((int)spielbaum.get(i).get( spielbaum.get(i).size()-1)==1){
					alpha=1;
				}
			}else{
				if((int)spielbaum.get(i).get( spielbaum.get(i).size()-1)==1){
					beta=-1;
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



	private int min(int beta2, int alphaBeta) {

		if(beta2>alphaBeta){
			return alphaBeta;
		}
		else{
			return beta2;
		}
	}

	@Override
	public int naechsterPCSpielzug() {
		// TODO Auto-generated method stub
		return 0;
	}




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