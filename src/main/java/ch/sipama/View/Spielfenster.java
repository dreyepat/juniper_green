package ch.sipama.View;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Spielfenster {

	//Instanzvariablen
	private JTextField txtZahlenraum;
	private JTable zahlenfeld;
	private JPanel einstellungen;
	private JSplitPane splitPane;
	private DefaultTableModel model;
	private JScrollPane einstellungenScrollPane;
	private JScrollPane feldScrollPane;
	private final Object lock = new Object();


	public Spielfenster(){

		feldScrollPane = new JScrollPane(zahlenfeld);

		einstellungen = new JPanel();
		SpringLayout layout = new SpringLayout();
		einstellungen.setLayout(layout);

		JRadioButton spielerA = new JRadioButton("Spieler A");
		JRadioButton spielerB = new JRadioButton("Spieler B");

		spielerA.setSelected(true);
		ButtonGroup spielergruppe = new ButtonGroup();
		spielergruppe.add( spielerA ); spielergruppe.add( spielerB );
		//		spielerA.setForeground(Color.red);
		//		spielerA.setBackground(Color.black);
		//		spielerA.setEnabled(false);
		//		spielerB.setEnabled(false);

		einstellungen.add(spielerA);
		einstellungen.add(spielerB);

		JButton btnNeustart = new JButton("Neustart");
		einstellungen.add(btnNeustart);
		btnNeustart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				spielNeustarten();
			}
		});


		JLabel lblZahlenraum = new JLabel("Zahlenraum:");
		einstellungen.add(lblZahlenraum);

		txtZahlenraum = new JTextField(5);
		einstellungen.add(txtZahlenraum);
		txtZahlenraum.setHorizontalAlignment(JTextField.RIGHT);

		layout.putConstraint(SpringLayout.NORTH, lblZahlenraum, 8, SpringLayout.NORTH, einstellungen);
		layout.putConstraint(SpringLayout.WEST, lblZahlenraum, 5, SpringLayout.WEST, einstellungen);
		layout.putConstraint(SpringLayout.NORTH, txtZahlenraum, 5, SpringLayout.NORTH, einstellungen);
		layout.putConstraint(SpringLayout.WEST, txtZahlenraum, 10, SpringLayout.EAST, lblZahlenraum);

		layout.putConstraint(SpringLayout.NORTH, btnNeustart, 10, SpringLayout.SOUTH, lblZahlenraum);
		layout.putConstraint(SpringLayout.WEST, btnNeustart, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, spielerA, 20,	SpringLayout.SOUTH, btnNeustart);
		layout.putConstraint(SpringLayout.WEST, spielerA, 5, SpringLayout.WEST, einstellungen);
		layout.putConstraint(SpringLayout.NORTH, spielerB, 20, SpringLayout.NORTH, spielerA);
		layout.putConstraint(SpringLayout.WEST, spielerB, 5, SpringLayout.WEST, einstellungen);

		einstellungenScrollPane = new JScrollPane(einstellungen);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, feldScrollPane, einstellungenScrollPane);
		splitPane.setOneTouchExpandable(false);
		splitPane.setDividerLocation(650);
		splitPane.setPreferredSize(new Dimension(900, 600));



	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public String getZahlenraum(){
		return txtZahlenraum.getText();
	}


	public void spielNeustarten(){
		try{
			int zRaum = Integer.parseInt(getZahlenraum());
			tabelleZeichnen(zRaum);
			txtZahlenraum.setText("");
		}catch (NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Trage eine Zahl zwischen 10 und 1000 ein!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			txtZahlenraum.setText("");
		}	
	}


	public void tabelleZeichnen(int zRaum){

		if(zRaum < 10 || zRaum > 1000){
			JOptionPane.showMessageDialog(null, "Trage eine Zahl zwischen 10 und 1000 ein!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			txtZahlenraum.setText("");
		}else if(zRaum <= 100){
			Vector<String> columnNames = new Vector<String>();
			for (int i=0; i<10; i++){
				columnNames.add(""+i);
			}

			//Neue DefaultTableModel erstellen und die Spaltentitel hinzufügen			
			model = new DefaultTableModel( columnNames, 0 );

			for(int i=0; i<10; i++){
				Vector<String> zeile = new Vector<String>();
				for(int j=0; j<(zRaum/10); j++){
					zeile.add("" + (10*i+(j+1)));
				}
				model.addRow(zeile);
			}
		}else{
			Vector<String> columnNames = new Vector<String>();
			for (int i=0; i<20; i++){
				columnNames.add(""+i);
			}

			//Neue DefaultTableModel erstellen und die Spaltentitel hinzufügen			
			model = new DefaultTableModel( columnNames, 0 );

			for(int i=0; i<10; i++){
				Vector<String> zeile = new Vector<String>();
				for(int j=0; j<(zRaum/20); j++){
					zeile.add("" + (20*i+(j+1)));
				}
				model.addRow(zeile);
			}
		}

		// Tabelle erstellen
		zahlenfeld = new JTable(model);
		zahlenfeld.setTableHeader(null);
		feldScrollPane.add(zahlenfeld);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, feldScrollPane, einstellungenScrollPane);
//		refresh2();
//		tabellenRefresh();
		splitPane.repaint();

	}
	
	public void tabellenRefresh(){
		Thread t = new Thread (new Runnable(){
			@Override
			public void run(){
				synchronized(lock){
					refresh2();
				}
			}
		});
		t.start();
	}
	
	public void refresh2(){
		zahlenfeld.repaint();
	}

}
