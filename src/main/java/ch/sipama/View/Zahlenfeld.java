package ch.sipama.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import ch.sipama.Controller.CompRandom;
import ch.sipama.Controller.Spieldaten;

public class Zahlenfeld{

	private String spielerA;
	private String spielerB;
	private int zahlenrange;
	private DefaultTableModel model;
	private JTable zahlenfeld = new JTable();
	private Spieldaten spdaten;
	private CompRandom compRandom = new CompRandom();
	
	
	
	
	public void tabelleZeichnen(){
		if(zahlenrange <= 150){
			int anzZeilen=zahlenrange/10;
			int auffueller=0;
			Vector<String> columnNames = new Vector<String>();
			for (int i=0; i<10; i++){
				columnNames.add(""+i);
			}

			if(zahlenrange%10 != 0){
				anzZeilen=anzZeilen+1;
				auffueller=10-(zahlenrange%10);
			}

			
			//Neue DefaultTableModel erstellen und die Spaltentitel hinzufügen			
			this.model = new DefaultTableModel( columnNames, 0 );

			for(int i=0; i<anzZeilen; i++){
				Vector<String> zeile = new Vector<String>();
				for(int j=0; j<10; j++){
					zeile.add("" + (10*i+(j+1)));
				}
				model.addRow(zeile);
			}
			for(int k=0; k<auffueller; k++){
				model.setValueAt("", model.getRowCount()-1, model.getColumnCount()-(k+1));

			}
			
			model.isCellEditable(anzZeilen, zahlenrange/10);

		}else{
			
			int anzZeilen=zahlenrange/20;
			int auffueller=0;
			
			Vector<String> columnNames = new Vector<String>();
			for (int i=0; i<20; i++){
				columnNames.add(""+i);
			}
			
			if(zahlenrange%20 != 0){
				anzZeilen=anzZeilen+1;
				auffueller=20-(zahlenrange%20);
			}

			model = new DefaultTableModel( columnNames, 0 );

			for(int i=0; i<anzZeilen; i++){
				Vector<String> zeile = new Vector<String>();
				for(int j=0; j<20; j++){
					zeile.add("" + (20*i+(j+1)));
				}
				model.addRow(zeile);
			}
			
			for(int k=0; k<auffueller; k++){
				model.setValueAt("", model.getRowCount()-1, model.getColumnCount()-(k+1));
			}
		}
		
		spdaten = Spieldaten.getInstance();
		spdaten.setSpieldaten(zahlenrange, spielerA, spielerB);
		
		
	}


	public void tabelleUebernehmen(){
		
		zahlenfeld = new JTable(model);
		zahlenfeld.setTableHeader(null);
		zahlenfeld.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		zahlenfeld.setRowSelectionAllowed(true);
        zahlenfeld.setColumnSelectionAllowed(true);
        
        
	
	}

	public void tabelleAktualisieren(){
		zahlenfeld.setModel(model);
	}

	public JTable getZahlenfeld(){
		return zahlenfeld;
	}
	
	public DefaultTableModel getModel(){
		return model;
	}
	

	public void neueTabelle(int zRange, String spielerA, String spielerB){
		int range = zRange;
		this.spielerA = spielerA;
		this.spielerB = spielerB;
		for(int i=model.getRowCount(); i>0; i--){
			model.removeRow(i-1);
		}
		model.fireTableDataChanged();
		if(range<=150 && zahlenrange>150){
			zahlenfeld.removeColumnSelectionInterval(10, 19);
		}
		else if(range>150 && zahlenrange<=150){
			for (int i=0; i<10; i++){
				model.addColumn("" + (10+i));

			}
		}
		this.zahlenrange=range;
		tabelleZeichnen();
	}
	

	public boolean icCellEditable(){
		zahlenfeld.addMouseListener( new MouseAdapter()
	    {
	      @Override
	      public void mouseClicked( MouseEvent e )
	      {
	        int rowAtPoint    = zahlenfeld.rowAtPoint(e.getPoint());
	        int columnAtPoint = zahlenfeld.columnAtPoint(e.getPoint());
	        isCellEditable(rowAtPoint, columnAtPoint);
	        System.out.println(zahlenfeld.getValueAt(rowAtPoint, columnAtPoint));
	      }
	    } );
		return false;
	}
	
    public boolean isCellEditable(int row, int col) {
            return false;
    }
    
    
    public String spielzug(){
    	String gezZahl="";
    	try{
    		int row = zahlenfeld.getSelectedRow();
        	int column = zahlenfeld.getSelectedColumn();
        	gezZahl = (String) model.getValueAt(row, column);
    		int gezogeneZahl = Integer.parseInt(gezZahl);
    		
    		if(spdaten.validieren(gezogeneZahl)==true){
    			spdaten.spielzugAusfuehren(row, column, gezogeneZahl);
    			System.out.println("" + compRandom.getNextSpielzug());
            	model.setValueAt("", row, column);
            	zahlenfeld.clearSelection();
            	return gezZahl;
    		}else{
    			JOptionPane.showMessageDialog(null, "Dies ist kein gültiger Spielzug!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
    			return "";
    		}
    	}catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Diese Zahl wurde bereits gezogen!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			return "";
		}    	
    }
	
    
    
}
