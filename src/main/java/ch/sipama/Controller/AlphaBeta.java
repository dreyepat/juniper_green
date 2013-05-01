package ch.sipama.Controller;

public class AlphaBeta implements ISpielStrategie{
	
	/**
	 * Die Methode (max) sucht den besten nächsten Zug mit dem MiniMax-Algorithmus
	 * @param spdaten - Das Spielfeld
	 * @param restTiefe - die restliche Tiefe
	 * @param suchTiefe - die gewünschte Suchtiefe
	 * @return ermittelt - der ermittelte Zugwert
	 */
	
	private Spieldaten spdaten;
	private int col;
	private int row;
	private int suchTiefe;
	private int oppId = 0;
	
	public int maxWert(int restTiefe, int alpha, int beta) {
		int ermittelt = 0;
		int zugWert = 0;
		int counter = 0;
		int id = 0;
		spdaten = Spieldaten.getInstance();
		
		if(id==1) {
			int oppId = 4;
		} else {
			int oppID = 1;
		}	
		
		ermittelt= Integer.MIN_VALUE;
		
		for(int i=0; i<col; i++) {
			counter = 0;
			if(spdaten.validieren(i)) {
				
				spdaten.spielzugAusfuehren(row, col, i);				//Spielzug ausführen
				
				if(restTiefe <= 1 || spdaten.spielstart == true) { 		//board.isFull() 
					for (int ii=0; ii<row; ii++) { 
						if(spdaten.spielstart == false) {				//board.isOccupied(ii,i)				
							counter++;
						} else {
							zugWert = countPoints(i, counter, id);
							break;
						}
					}
				}
				else {
					zugWert = minWert(restTiefe-1,alpha,beta);
					
				}
					
//				spdaten.rueckgangig(i,id);								//board.unmove(i,id)
				
				if (zugWert >= beta)
		            return beta;
				
				if(zugWert>ermittelt) {
					ermittelt = zugWert;
					
					if(restTiefe == suchTiefe) {
						
						if(spdaten.validieren(i)) {						//board.isValidMove(i)
							i = spdaten.naechsterSpielzug().get(i);
						}
					}
				}
			}
		}
		return ermittelt;
	}
	
	
	private int countPoints(int i, int counter, int id) {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * @param spdaten - Das Spielfeld
	 * @param restTiefe - die restliche Tiefe
	 * @param suchTiefe - die gewünschte Suchtiefe
	 * @return ermittelt - der ermittelte Zugwert
	 */
	public int minWert(int restTiefe, int alpha, int beta) {
		int ermittelt = 0; 
		int zugWert = 0;
		int counter = 0;
		
		ermittelt = Integer.MAX_VALUE;
		
		//col
		for(int i=0; i<col; i++){
			counter=0;
			if(spdaten.validieren(i)) {	
				
				spdaten.spielzugAusfuehren(row, col, i);
				    
				if(restTiefe<=1 || spdaten.spielstart == true) { 
					for (int ii=0; ii<row; ii++) { 
						if(spdaten.spielstart == false) {
							counter++;
						} else {
							zugWert = -countPoints(i, counter, oppId);
							break;
						}						
					}
				}
				else {
					zugWert = maxWert(restTiefe-1,alpha,beta);
				}
								
//				spdaten.rueckgangig(i,id);								//board.unmove(i,id)
				
				if (zugWert <= alpha)
		            return alpha;
                                
				
				if(zugWert<ermittelt) {
					ermittelt = zugWert;
				}
			}
		}
		return ermittelt;
	}


	@Override
	public int naechsterPCSpielzug() {
		// TODO Auto-generated method stub
		return 0;
	}

}