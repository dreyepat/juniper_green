package ch.sipama.View;

import java.awt.Color;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import ch.sipama.Controller.CompAlphaBeta;
import ch.sipama.Controller.CompHoechst;
import ch.sipama.Controller.CompRandom;
import ch.sipama.Controller.ISpielStrategie;
import ch.sipama.Controller.Spieldaten;

public class Zahlenfeld{

	private String sSpielerA;
	private String sSpielerB;
	private int iZRange;
	private int iSpielmodi;
	private DefaultTableModel dTblModel;
	private JTable jTblZahlenfeld = new JTable();
	private Spieldaten oSpdaten;
	private ISpielStrategie iFSpielstrategie;

	public void tabelleZeichnen(){
		if(iZRange <= 150){
			int anzZeilen=iZRange/10;
			int auffueller=0;
			Vector<String> columnNames = new Vector<String>();
			for (int i=0; i<10; i++){
				columnNames.add(""+i);
			}

			if(iZRange%10 != 0){
				anzZeilen=anzZeilen+1;
				auffueller=10-(iZRange%10);
			}


			//Neue DefaultTableModel erstellen und die Spaltentitel hinzufügen			
			this.dTblModel = new DefaultTableModel( columnNames, 0 );

			for(int i=0; i<anzZeilen; i++){
				Vector<String> zeile = new Vector<String>();
				for(int j=0; j<10; j++){
					zeile.add("" + (10*i+(j+1)));
				}
				dTblModel.addRow(zeile);
			}
			for(int k=0; k<auffueller; k++){
				dTblModel.setValueAt("", dTblModel.getRowCount()-1, dTblModel.getColumnCount()-(k+1));

			}

			dTblModel.isCellEditable(anzZeilen, iZRange/10);

		}else{

			int anzZeilen=iZRange/20;
			int auffueller=0;

			Vector<String> columnNames = new Vector<String>();
			for (int i=0; i<20; i++){
				columnNames.add(""+i);
			}

			if(iZRange%20 != 0){
				anzZeilen=anzZeilen+1;
				auffueller=20-(iZRange%20);
			}

			dTblModel = new DefaultTableModel( columnNames, 0 );

			for(int i=0; i<anzZeilen; i++){
				Vector<String> zeile = new Vector<String>();
				for(int j=0; j<20; j++){
					zeile.add("" + (20*i+(j+1)));
				}
				dTblModel.addRow(zeile);
			}

			for(int k=0; k<auffueller; k++){
				dTblModel.setValueAt("", dTblModel.getRowCount()-1, dTblModel.getColumnCount()-(k+1));
			}
		}

		oSpdaten = Spieldaten.getInstance();
		oSpdaten.setSpieldaten(iZRange, sSpielerA, sSpielerB);
		if(iSpielmodi>0){
			switch(iSpielmodi){
			case 1:
				iFSpielstrategie = new CompRandom();
				break;
			case 2:
				iFSpielstrategie = new CompAlphaBeta();
				break;
			case 3:
				iFSpielstrategie = new CompHoechst();
				break;
			default:
				System.out.println("Keine Implementation für Spielmodi " + iSpielmodi + " vorhanden.");
			}
		}

	}


	public void tabelleUebernehmen(){

		jTblZahlenfeld = new JTable(dTblModel);
		jTblZahlenfeld.setTableHeader(null);
		jTblZahlenfeld.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTblZahlenfeld.setRowSelectionAllowed(true);
		jTblZahlenfeld.setColumnSelectionAllowed(true);
		jTblZahlenfeld.setRowHeight(60);
		jTblZahlenfeld.setBackground(Color.yellow);


	}

	public void tabelleAktualisieren(){
		jTblZahlenfeld.setModel(dTblModel);
	}

	public JTable getZahlenfeld(){
		return jTblZahlenfeld;
	}

	public DefaultTableModel getModel(){
		return dTblModel;
	}


	public void neueTabelle(int zRange, String spielerA, String spielerB, int spielmodi){
		this.iSpielmodi = spielmodi;
		int range = zRange;
		this.sSpielerA = spielerA;
		this.sSpielerB = spielerB;
		for(int i=dTblModel.getRowCount(); i>0; i--){
			dTblModel.removeRow(i-1);
		}
		dTblModel.fireTableDataChanged();
		if(range<=150 && iZRange>150){
			jTblZahlenfeld.removeColumnSelectionInterval(10, 19);
		}
		else if(range>150 && iZRange<=150){
			for (int i=0; i<10; i++){
				dTblModel.addColumn("" + (10+i));

			}
		}
		this.iZRange=range;
		tabelleZeichnen();
	}


//	public boolean icCellEditable(){
//		zahlenfeld.addMouseListener( new MouseAdapter()
//		{
//			@Override
//			public void mouseClicked( MouseEvent e )
//			{
//				int rowAtPoint    = zahlenfeld.rowAtPoint(e.getPoint());
//				int columnAtPoint = zahlenfeld.columnAtPoint(e.getPoint());
//				isCellEditable(rowAtPoint, columnAtPoint);
//				System.out.println(zahlenfeld.getValueAt(rowAtPoint, columnAtPoint));
//			}
//		} );
//		return false;
//	}
//
//	public boolean isCellEditable(int row, int col) {
//		return false;
//	}


	public String spielzug(){
		String gezZahl="";
		try{
			int row = jTblZahlenfeld.getSelectedRow();
			int column = jTblZahlenfeld.getSelectedColumn();
			gezZahl = (String) dTblModel.getValueAt(row, column);
			int gezogeneZahl = Integer.parseInt(gezZahl);

			if(oSpdaten.validieren(gezogeneZahl)==true){
				oSpdaten.spielzugAusfuehren(row, column, gezogeneZahl);
				dTblModel.setValueAt("", row, column);
				jTblZahlenfeld.clearSelection();
				return gezZahl;
			}else{
				JOptionPane.showMessageDialog(null, "Dies ist kein gültiger Spielzug!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
				return "";
			}
		}catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Diese Zahl wurde bereits gezogen!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			return "";
		}catch(ArrayIndexOutOfBoundsException oobe){
			JOptionPane.showMessageDialog(null, "Klicke auf eine Zahl in der Tabelle!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			return "";
		}
	}

	public String pcSpielzug(){
		String pcZahl = "";
		if(oSpdaten.isbSpielende()==false){
			int pcZug = iFSpielstrategie.naechsterPCSpielzug();
			pcZahl = pcZahl + pcZug;

			int row = 0;
			int column = 0;

			if(iZRange>=150){
				row = pcZug/20;
				if(pcZug%20==0){
					row = row-1;
					column=19;
				}else{
					column = pcZug%20-1;
				}

			}else{
				row = pcZug/10;
				if(pcZug%10==0){
					row = row-1;
					column = 9;
				}else{
					column = pcZug%10-1;
				}
			}

			oSpdaten.pcSpielzugAusfuehren(row, column, pcZug);
			dTblModel.setValueAt("", row, column);
			jTblZahlenfeld.clearSelection();
		}

		return pcZahl;
	}

	public ISpielStrategie getSpielStrategie() {
		return iFSpielstrategie;
	}

}
