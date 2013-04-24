package ch.sipama.Logik;

import java.util.ArrayList;

public class MoeglicheZuege {
	
	private Spieldaten spdaten;
	private ArrayList<Integer> jgreen;
	
	
	public ArrayList<Integer> getJgreen() {
		return jgreen;
	}


	public MoeglicheZuege(){
		spdaten = Spieldaten.getInstance();
		jgreen = new ArrayList<Integer>();
	}
	
	
}
