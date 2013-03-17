package ch.sipama.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Spielfenster {

	//Instanzvariablen
	
	private JTextField txtZahlenraum;
	private JPanel einstellungen;
	private JSplitPane splitPane;
	private JScrollPane einstellungenScrollPane;
	private JScrollPane feldScrollPane;
	private Zahlenfeld zFeld;

	public Spielfenster(){

		zFeld= new Zahlenfeld();
		
		zFeld.tabelleZeichnen();
		zFeld.tabelleUebernehmen();
		feldScrollPane = new JScrollPane(zFeld.getZahlenfeld());

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
		einstellungenScrollPane = new JScrollPane(einstellungen);

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
			int zRange = Integer.parseInt(getZahlenraum());
			if(zRange < 10 || zRange > 1000){
				JOptionPane.showMessageDialog(null, "Trage eine Zahl zwischen 10 und 1000 ein!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				zFeld.neueTabelle(zRange);
				zFeld.tabelleAktualisieren();
			}
			
			
		}catch (NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Trage eine Zahl zwischen 10 und 1000 ein!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
		}
		txtZahlenraum.setText("");
	}
}
