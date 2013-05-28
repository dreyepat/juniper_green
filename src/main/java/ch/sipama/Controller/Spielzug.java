package ch.sipama.Controller;

/**
 * @author Marco Lamm
 *
 */

public class Spielzug {
	
	private String sSpieler;
	private int iRow;
	private int iColumn;
	private int iZahl;
	
	
	public Spielzug(String spieler, int row, int column, int zahl){
		sSpieler = spieler;
		iRow = row;
		iColumn = column;
		iZahl = zahl;
	}
	
	
	public String getSpieler() {
		return sSpieler;
	}
	public void setSpieler(String spieler) {
		this.sSpieler = spieler;
	}
	public int getRow() {
		return iRow;
	}
	public void setRow(int row) {
		this.iRow = row;
	}
	public int getColumn() {
		return iColumn;
	}
	public void setColumn(int column) {
		this.iColumn = column;
	}
	public int getZahl() {
		return iZahl;
	}
	public void setZahl(int zahl) {
		this.iZahl = zahl;
	}
	
}
