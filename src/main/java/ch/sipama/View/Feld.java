package ch.sipama.View;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * @ author  Patrizia Dreyer
 */

public class Feld extends JPanel {

	// Instanzvariablen
	private static final long serialVersionUID = 1L;

	public Feld() {

		// Layout für diesen Tab setzen
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		// Spaltentitel setzen
		String[] columnNames = { "", "a", "b", "c",	"d", "e", "f", "g", "h", "i", "j" };

		//Inhalt füllen
		Object[][] data = {
				
				{ "1", "1", "2", "3",	"4", "5", "6", "7", "8", "9", "10" },
				{ "2", "11", "12","13", "14", "15", "16", "17", "18", "19", "20" },
				{ "3", "21", "22","23", "24", "25", "26", "27", "28", "29", "30" },
				{ "4", "31", "32","33", "34", "35", "36", "37", "38", "39", "40" },
				{ "5", "41", "42","43", "44", "45", "46", "47", "48", "49", "50" },
				{ "6", "51", "52","53", "54", "55", "56", "57", "58", "59", "60" },
				{ "7", "61", "62","63", "64", "65", "66", "67", "68", "69", "70" },
				{ "8", "71", "72","73", "74", "75", "76", "77", "78", "79", "80" },
				{ "9", "81", "82","83", "84", "85", "86", "87", "88", "89", "90" },
				{ "10", "91", "92","93", "94", "95", "96", "97", "98", "99", "100" },
				
			 };


		// Tabelle erstellen
		JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(600, 300));
		table.setLayout(new FlowLayout());
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);

		//Spaltenbreiten definieren
		TableColumn column = null;

		
		//Tabelle dem GUI hinzufÃ¼gen und platzieren
		this.add(scrollPane);
		
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 5,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 5,
				SpringLayout.WEST, this);

		
	}

}
