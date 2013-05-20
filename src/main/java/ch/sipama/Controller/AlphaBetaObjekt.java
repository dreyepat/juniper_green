package ch.sipama.Controller;

import java.util.LinkedList;

public class AlphaBetaObjekt {

	private AlphaBetaObjekt oVorgaenger;
	private int iSuchtiefe;
	private LinkedList<Integer> lListSpielZugListe;
	private int iPointer;
	private float fAlpha;
	private float fBeta;
	private Spieldaten oSpdaten;


	public AlphaBetaObjekt(int suchtiefe, LinkedList<Integer> log){
		oSpdaten = Spieldaten.getInstance();
		this.iSuchtiefe = suchtiefe + 1;
		iPointer = 0;
		fAlpha = 0;
		fBeta = 0;
		
		int letzterZug = log.get(log.size()-1);
		lListSpielZugListe = new LinkedList<Integer>(oSpdaten.zRaumListe(letzterZug-1));
		if(lListSpielZugListe.getFirst()==1){
			lListSpielZugListe.removeFirst();
		}
	}


	public float auswerten(){
		if(lListSpielZugListe.size()==0){
			return 1;
		}else{
			return 0;	
		}
	}


	public int getPointer() {
		return iPointer;
	}


	public void setPointer(int pointer) {
		this.iPointer = pointer;
	}


	public float getAlpha() {
		return fAlpha;
	}


	public void setAlpha(float alpha) {
		this.fAlpha = alpha;
	}


	public float getBeta() {
		return fBeta;
	}


	public void setBeta(float beta) {
		this.fBeta = beta;
	}


	public int getSuchtiefe() {
		return iSuchtiefe;
	}

	public void setSuchtiefe(int suchtiefe) {
		this.iSuchtiefe = suchtiefe;
	}

}
