package ch.sipama.Controller;

public class Spielzug {
	
	private String spieler;
	private int row;
	private int column;
	private int zahl;
	
	
	public Spielzug(String spieler, int row, int column, int zahl){
		this.spieler = spieler;
		this.row = row;
		this.column = column;
		this.zahl = zahl;
	}
	
	
	public String getSpieler() {
		return spieler;
	}
	public void setSpieler(String spieler) {
		this.spieler = spieler;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getZahl() {
		return zahl;
	}
	public void setZahl(int zahl) {
		this.zahl = zahl;
	}
	
	

}
