package ch.sipama.View;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Zahlenfeld {
	private int zahlenrange;
	private DefaultTableModel model;
	private JTable zahlenfeld = new JTable();

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
	}


	public void tabelleUebernehmen(){
		
		zahlenfeld = new JTable(model);
		zahlenfeld.setTableHeader(null);
		zahlenfeld.setFillsViewportHeight(true);
		zahlenfeld.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		zahlenfeld.setRowSelectionAllowed(true);
        zahlenfeld.setColumnSelectionAllowed(true);
   //   zahlenfeld.setDefaultEditor( Object.class, new TestEditor());
	
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
	

	public void neueTabelle(int zRange){
		int range = zRange;
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
	

	
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
    	if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
    
    
    public void spielzug(){
    	int row = zahlenfeld.getSelectedRow();
    	int column = zahlenfeld.getSelectedColumn();
    	model.setValueAt("test", row, column);
    	zahlenfeld.clearSelection();
    }
    
    
}
