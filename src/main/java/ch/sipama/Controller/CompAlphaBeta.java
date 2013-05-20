package ch.sipama.Controller;

import java.util.ArrayList;
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
	private int iSuchtiefe;
	private int iRueckgabewert;
	private AlphaBetaObjekt oAlphaBeta;
	private LinkedList<Integer> lListLog;
	private ArrayList<AlphaBetaObjekt> aListObjektListe;





	public CompAlphaBeta(){
		oSpdaten = Spieldaten.getInstance();
		iSuchtiefe = 3;
		aListObjektListe = new ArrayList<AlphaBetaObjekt>();
	}


	@Override
	public int naechsterPCSpielzug(){

		if(oSpdaten.getLog().get(oSpdaten.getLog().size()-1).getZahl() == 1){
			return oSpdaten.getGroesstePrimzahl();
		}else{
			ArrayList<Integer> spielZugListe = (ArrayList<Integer>) oSpdaten.naechsterSpielzug().clone();

			if(spielZugListe.get(0)==1 && spielZugListe.size()>1){
				spielZugListe.remove(0);
			}
			if(spielZugListe.size()==1){
				return spielZugListe.get(0);
			}
			else{
				lListLog = new LinkedList<Integer>();
				for(int i=0; i<oSpdaten.getLog().size(); i++){
					lListLog.add(oSpdaten.getLog().get(i).getZahl());
				}
				oAlphaBeta= new AlphaBetaObjekt(0, lListLog);
				aListObjektListe.add(oAlphaBeta);

				alphaBeta(oAlphaBeta.getPointer());
				return iRueckgabewert;
			}
		}
	}





	public void alphaBeta(int i){
		int naechsterZug = oAlphaBeta.getSpielZugListe().get(oAlphaBeta.getPointer());
		oAlphaBeta = new AlphaBetaObjekt(aListObjektListe.get(aListObjektListe.size()-1).getSuchtiefe(), aListObjektListe.get(aListObjektListe.size()-1).getLog());

		//im neuen alphaBetaO das Log um die Zahl erweitern, auf den im Vorgängerobjekt der Pointer zeigt
		oAlphaBeta.getLog().add(naechsterZug);

		//im Vorgängerobjekt den Pointer um 1 erhöhen
		aListObjektListe.get(aListObjektListe.size()-1).setPointer(i+1);

		//das neue alphaBetaO dem Spielbaum hinzufügen, den man auswertet
		aListObjektListe.add(oAlphaBeta);

		//neues Objekt auswerten, ob es ein Blatt ist:
		float auswertung = oAlphaBeta.auswerten();

		//wenn Suchtiefe noch nicht erreicht ist und es kein Blatt ist, den Alphabeta-Algorithmus erneut aufrufen
		if(auswertung==0 && oAlphaBeta.getSuchtiefe()<iSuchtiefe){
			alphaBeta(oAlphaBeta.getPointer());



		//wenn es kein Blatt ist:	
		}else if(auswertung==0 && oAlphaBeta.getSuchtiefe()==iSuchtiefe){ 
			while(aListObjektListe.size()>1){
				aListObjektListe.get(aListObjektListe.size()-2).setAlpha(aListObjektListe.get(aListObjektListe.size()-2).getAlpha());
				aListObjektListe.get(aListObjektListe.size()-2).setBeta(aListObjektListe.get(aListObjektListe.size()-2).getBeta());
				aListObjektListe.remove(aListObjektListe.size()-1);
				if(aListObjektListe.get(aListObjektListe.size()-1).getSpielZugListe().size()>aListObjektListe.get(aListObjektListe.size()-1).getPointer()){
					oAlphaBeta=aListObjektListe.get(aListObjektListe.size()-1);
					alphaBeta(oAlphaBeta.getPointer());
					break;
				}	
			}
			//Vorgehen, wenn kein "Gewinnerpfad" gefunden wurde - Zufallszahl aus der ersten Liste
			Random rnd=new Random();
			int z=rnd.nextInt(aListObjektListe.get(0).getSpielZugListe().size());
			iRueckgabewert = aListObjektListe.get(0).getSpielZugListe().get(z);
			
			
		//Blatt des Spielers A (Computer gewinnt)
		}else if(auswertung==1 && oAlphaBeta.getSuchtiefe()%2==0){
			if(aListObjektListe.size()>1){
				aListObjektListe.get(aListObjektListe.size()-2).setAlpha(1);
				aListObjektListe.get(aListObjektListe.size()-2).setBeta(aListObjektListe.get(aListObjektListe.size()-2).getBeta());
				aListObjektListe.remove(aListObjektListe.size()-1);  //Cutoff von weiteren möglichen Spielverläufen (Da Gewinnpfad gefunden)
				oAlphaBeta = aListObjektListe.get(aListObjektListe.size()-1);
				
				//Auswahlmöglichkeiten des Spielers A aus dem vorherigen Zug weiter auswerten
				if(oAlphaBeta.getSpielZugListe().size()>oAlphaBeta.getPointer()){
					alphaBeta(oAlphaBeta.getPointer());
				//Falls Auswahlliste des Spielers A	fertig abgearbeitet, die Werte alpha und beta prüfen
				}else{
					
				}
				
				
				
				
				//Auswertung nach oben fortsetzen....
			}else{
				iRueckgabewert = aListObjektListe.get(0).getSpielZugListe().get(aListObjektListe.get(0).getPointer()-1);
			}

			
		//
		}else if(auswertung==1 && oAlphaBeta.getSuchtiefe()%2==1){
			if(aListObjektListe.size()>1){
				aListObjektListe.get(aListObjektListe.size()-2).setAlpha(aListObjektListe.get(aListObjektListe.size()-2).getAlpha());
				aListObjektListe.get(aListObjektListe.size()-2).setBeta(-1);
				//Auswertung nach oben fortsetzen...	
			}else{
				//bei 'Verliererpfad' die Zahl aus der Liste möglicher Spielzüge entfernen, sofern mehr als 1 zur Auswahl steht
				if(aListObjektListe.get(0).getSpielZugListe().size()>1){
					aListObjektListe.get(0).setPointer(aListObjektListe.get(0).getPointer()-1);
					aListObjektListe.get(0).getSpielZugListe().remove(aListObjektListe.get(0).getPointer());
				}else{
					//wir verlieren... *snief*
					iRueckgabewert = aListObjektListe.get(0).getSpielZugListe().get(0);
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




}