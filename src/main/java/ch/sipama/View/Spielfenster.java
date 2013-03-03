package ch.sipama.View;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class Spielfenster {
	
	//Instanzvariablen
	private JTable zahlenfeld;
	private JPanel einstellungen;
	private JSplitPane splitPane;
	
	
	public Spielfenster(){
		
		String[] columnNames = { "a", "b", "c",	"d", "e", "f", "g", "h", "i", "j" };

		//Inhalt füllen
		Object[][] data = {
				
				{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" },
				{ "11", "12","13", "14", "15", "16", "17", "18", "19", "20" },
				{ "21", "22","23", "24", "25", "26", "27", "28", "29", "30" },
				{ "31", "32","33", "34", "35", "36", "37", "38", "39", "40" },
				{ "41", "42","43", "44", "45", "46", "47", "48", "49", "50" },
				{ "51", "52","53", "54", "55", "56", "57", "58", "59", "60" },
				{ "61", "62","63", "64", "65", "66", "67", "68", "69", "70" },
				{ "71", "72","73", "74", "75", "76", "77", "78", "79", "80" },
				{ "81", "82","83", "84", "85", "86", "87", "88", "89", "90" },
				{ "91", "92","93", "94", "95", "96", "97", "98", "99", "100" },
				
			 };

		// Tabelle erstellen
		zahlenfeld = new JTable(data, columnNames);
		zahlenfeld.setTableHeader(null);
		
		JScrollPane feldScrollPane = new JScrollPane(zahlenfeld);
		
		
		
		einstellungen = new JPanel();
		SpringLayout layout = new SpringLayout();
		einstellungen.setLayout(layout);
		
		
		JButton testbutton = new JButton("Test");
		einstellungen.add(testbutton);
		
		

		layout.putConstraint(SpringLayout.NORTH, testbutton, 5,	SpringLayout.NORTH, einstellungen);
		layout.putConstraint(SpringLayout.WEST, testbutton, 5, SpringLayout.WEST, einstellungen);
		
		
		JScrollPane einstellungenScrollPane = new JScrollPane(einstellungen);
		
		
		
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, feldScrollPane, einstellungenScrollPane);
		splitPane.setOneTouchExpandable(false);
		splitPane.setDividerLocation(650);
		
		splitPane.setPreferredSize(new Dimension(900, 600));
		
		
		
	}
	
	public JSplitPane getSplitPane() {
        return splitPane;
    }

}
