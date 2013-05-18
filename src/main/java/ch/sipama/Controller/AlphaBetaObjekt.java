package ch.sipama.Controller;

import java.util.ArrayList;

public class AlphaBetaObjekt {

	private int suchtiefe;
	private ArrayList<Integer> log;
	private ArrayList<Integer> spielZugListe;
	private int pointer;
	private int alpha;
	private int beta;
	private Spieldaten spdaten;


	public AlphaBetaObjekt(int suchtiefe, ArrayList<Integer> log){
		spdaten = spdaten.getInstance();
		this.log = (ArrayList<Integer>) log.clone();
		this.suchtiefe = suchtiefe + 1;
		pointer = 0;
		alpha = 0;
		beta = 0;
		spielZugListe = new ArrayList<Integer>();

		ArrayList<Integer> spielHilfe = new ArrayList<Integer>();
		int letzterZug = log.get(log.size()-1);
		System.out.println(""+ letzterZug);
		spielHilfe = (ArrayList<Integer>) spdaten.getMoegZuege().get(letzterZug).getJgreen().clone();
		for(int i=0; i<log.size(); i++){
			for(int j=spielHilfe.size()-1; j>=0; j--){
				if(log.get(i) == spielHilfe.get(j)){
					spielHilfe.remove(j);
				}
			}

			if(spielHilfe.get(0)==1){
				spielHilfe.remove(0);
			}
			spielZugListe = (ArrayList<Integer>) spielHilfe.clone();		
		}

	}


	public int auswerten(){
		if(spielZugListe.size()==0){
			return 1;
		}else{
			return 0;	
		}
	}



	public int getPointer() {
		return pointer;
	}


	public void setPointer(int pointer) {
		this.pointer = pointer;
	}


	public int getAlpha() {
		return alpha;
	}


	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}


	public int getBeta() {
		return beta;
	}


	public void setBeta(int beta) {
		this.beta = beta;
	}


	public ArrayList<Integer> getSpielZugListe() {
		return spielZugListe;
	}


	public int getSuchtiefe() {
		return suchtiefe;
	}


	public void setSuchtiefe(int suchtiefe) {
		this.suchtiefe = suchtiefe;
	}




	public ArrayList<Integer> getLog() {
		return log;
	}



}
