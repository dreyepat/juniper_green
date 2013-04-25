package ch.sipama.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Spielfenster {

	//Instanzvariablen

	private JTextField txtZahlenraum;
	private JTextField txtSpielerA;
	private JTextField txtSpielerB;
	private JPanel einstellungen;
	private JSplitPane splitPane;
	private JScrollPane einstellungenScrollPane;
	private JScrollPane feldScrollPane;
	private Zahlenfeld zFeld;
	private JLabel gezZahl;
	private JLabel lblAktSpieler;
	private JRadioButton radSpielerVsSpieler;
	private JRadioButton radSpielerVsComp1;
	private JRadioButton radSpielerVsComp2;
	private JRadioButton radSpielerVsComp3;
	private JButton btnNeustart;
	private JButton btnSpielzug;
	private JButton btnSpielAbbrechen;

	public Spielfenster(){

		zFeld= new Zahlenfeld();

		zFeld.tabelleZeichnen();
		zFeld.tabelleUebernehmen();
		feldScrollPane = new JScrollPane(zFeld.getZahlenfeld());

		einstellungen = new JPanel();
		SpringLayout layout = new SpringLayout();
		einstellungen.setLayout(layout);

		JLabel lblSpielerA = new JLabel("Spieler A:");
		JLabel lblSpielerB = new JLabel("Spieler B:");
		JLabel lblSpielmodus = new JLabel("Spielmodus:");

		radSpielerVsSpieler = new JRadioButton("Spieler vs Spieler");
		radSpielerVsComp1 = new JRadioButton("Spieler vs R2-D2");
		radSpielerVsComp2 = new JRadioButton("Spieler vs C-3PO");
		radSpielerVsComp3 = new JRadioButton("Spieler vs Meister Yoda");
		
		ButtonGroup sModus = new ButtonGroup();
		sModus.add(radSpielerVsSpieler);
		sModus.add(radSpielerVsComp1);
		sModus.add(radSpielerVsComp2);
		sModus.add(radSpielerVsComp3);
		
		txtSpielerA = new JTextField(12);
		txtSpielerB = new JTextField(12);

		einstellungen.add(lblSpielmodus);
		einstellungen.add(radSpielerVsSpieler);
		einstellungen.add(radSpielerVsComp1);
		einstellungen.add(radSpielerVsComp2);
		einstellungen.add(radSpielerVsComp3);
		einstellungen.add(lblSpielerA);
		einstellungen.add(lblSpielerB);
		einstellungen.add(txtSpielerA);
		einstellungen.add(txtSpielerB);


		lblAktSpieler = new JLabel("");
		gezZahl = new JLabel("<html><body>Lege den Zahlenraum fest,<br>um das Spiel zu starten!</body></html>");
		einstellungen.add(lblAktSpieler);
		einstellungen.add(gezZahl);

		btnNeustart = new JButton("Spiel starten!");
		einstellungen.add(btnNeustart);
		btnNeustart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				spielNeustarten();
			}
		});


		btnSpielzug = new JButton("Zahl ziehen");
		btnSpielzug.setEnabled(false);
		einstellungen.add(btnSpielzug);
		btnSpielzug.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				spielzug();

			}
		});

		btnSpielAbbrechen = new JButton ("Spiel abbrechen");
		btnSpielAbbrechen.setEnabled(false);
		einstellungen.add(btnSpielAbbrechen);
		btnSpielAbbrechen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				spielAbbrechen();
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
		
		layout.putConstraint(SpringLayout.NORTH, lblSpielmodus, 20,	SpringLayout.SOUTH, lblZahlenraum);
		layout.putConstraint(SpringLayout.WEST, lblSpielmodus, 5, SpringLayout.WEST, einstellungen);
		
		layout.putConstraint(SpringLayout.NORTH, radSpielerVsSpieler, 16,	SpringLayout.SOUTH, lblZahlenraum);
		layout.putConstraint(SpringLayout.WEST, radSpielerVsSpieler, 10, SpringLayout.EAST, lblSpielmodus);
		layout.putConstraint(SpringLayout.NORTH, radSpielerVsComp1, 0,	SpringLayout.SOUTH, radSpielerVsSpieler);
		layout.putConstraint(SpringLayout.WEST, radSpielerVsComp1, 10, SpringLayout.EAST, lblSpielmodus);
		layout.putConstraint(SpringLayout.NORTH, radSpielerVsComp2, 0,	SpringLayout.SOUTH, radSpielerVsComp1);
		layout.putConstraint(SpringLayout.WEST, radSpielerVsComp2, 10, SpringLayout.EAST, lblSpielmodus);
		layout.putConstraint(SpringLayout.NORTH, radSpielerVsComp3, 0,	SpringLayout.SOUTH, radSpielerVsComp2);
		layout.putConstraint(SpringLayout.WEST, radSpielerVsComp3, 10, SpringLayout.EAST, lblSpielmodus);

		layout.putConstraint(SpringLayout.NORTH, btnNeustart, 10, SpringLayout.SOUTH, lblSpielerB);
		layout.putConstraint(SpringLayout.WEST, btnNeustart, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, lblSpielerA, 20,	SpringLayout.SOUTH, radSpielerVsComp3);
		layout.putConstraint(SpringLayout.WEST, lblSpielerA, 5, SpringLayout.WEST, einstellungen);
		layout.putConstraint(SpringLayout.NORTH, lblSpielerB, 20, SpringLayout.NORTH, lblSpielerA);
		layout.putConstraint(SpringLayout.WEST, lblSpielerB, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, txtSpielerA, 18,	SpringLayout.SOUTH, radSpielerVsComp3);
		layout.putConstraint(SpringLayout.WEST, txtSpielerA, 5, SpringLayout.EAST, lblSpielerA);
		layout.putConstraint(SpringLayout.NORTH, txtSpielerB, 18, SpringLayout.NORTH, lblSpielerA);
		layout.putConstraint(SpringLayout.WEST, txtSpielerB, 5, SpringLayout.EAST, lblSpielerB);


		layout.putConstraint(SpringLayout.NORTH, lblAktSpieler, 40, SpringLayout.SOUTH, btnNeustart);
		layout.putConstraint(SpringLayout.WEST, lblAktSpieler, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, gezZahl, 10, SpringLayout.SOUTH, lblAktSpieler);
		layout.putConstraint(SpringLayout.WEST, gezZahl, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, btnSpielzug, 20, SpringLayout.SOUTH, gezZahl);
		layout.putConstraint(SpringLayout.WEST, btnSpielzug, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.SOUTH, btnSpielAbbrechen, -5, SpringLayout.SOUTH, einstellungen);
		layout.putConstraint(SpringLayout.EAST, btnSpielAbbrechen, -5, SpringLayout.EAST, einstellungen);

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
				txtZahlenraum.setText("");
			}
			else{
				if(txtSpielerA.getText().length()<1 || txtSpielerB.getText().length()<1){
					int o = JOptionPane.showConfirmDialog(null, "<html><body>Möchtest du das Spiel starten, ohne Namen eingetragen zu haben?<br><br>Klicke auf 'Abbrechen', um die Namen noch einzutragen!</html></body>", "Hinweis", 2);
					if(o==0){
						if(txtSpielerA.getText().length()<1){
							txtSpielerA.setText("Spieler A");
						}
						if(txtSpielerB.getText().length()<1){
							txtSpielerB.setText("Spieler B");
						}
						zFeld.neueTabelle(zRange, txtSpielerA.getText(), txtSpielerB.getText());
						zFeld.tabelleAktualisieren();
						gezZahl.setText("Ziehe eine gerade Zahl!");
						btnNeustart.setEnabled(false);
						btnSpielzug.setEnabled(true);
						btnSpielAbbrechen.setEnabled(true);
						txtZahlenraum.setEditable(false);
						txtSpielerA.setEditable(false);
						txtSpielerB.setEditable(false);
						lblAktSpieler.setText(txtSpielerA.getText() + " ist am Zug!");
					}
				}
				else{
					zFeld.neueTabelle(zRange, txtSpielerA.getText(), txtSpielerB.getText());
					zFeld.tabelleAktualisieren();
					gezZahl.setText("Ziehe eine gerade Zahl!");
					btnNeustart.setEnabled(false);
					btnSpielzug.setEnabled(true);
					btnSpielAbbrechen.setEnabled(true);
					txtZahlenraum.setEditable(false);
					txtSpielerA.setEditable(false);
					txtSpielerB.setEditable(false);
					lblAktSpieler.setText(txtSpielerA.getText() + " ist am Zug!");
				}
			}


		}catch (NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Trage eine Zahl zwischen 10 und 1000 ein!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			txtZahlenraum.setText("");
		}

	}

	public void spielzug(){
		String spielzug = zFeld.spielzug();
		if(spielzug.length()>0){
			gezZahl.setText("Zuletzt gezogene Zahl: " + spielzug);
			if(lblAktSpieler.getText().equals((txtSpielerA.getText() + " ist am Zug!"))){
				lblAktSpieler.setText(txtSpielerB.getText() + " ist am Zug!");
			}else{
				lblAktSpieler.setText(txtSpielerA.getText() + " ist am Zug!");
			}
		}
	}

	public void spielAbbrechen(){
		int o = JOptionPane.showConfirmDialog(null, "Willst du das Spiel wirklich abbrechen?", "Hinweis", JOptionPane.OK_CANCEL_OPTION);
		if(o==0){
			txtZahlenraum.setEditable(true);
			txtSpielerA.setEditable(true);
			txtSpielerB.setEditable(true);
			btnNeustart.setEnabled(true);
			btnSpielzug.setEnabled(false);
			btnSpielAbbrechen.setEnabled(false);
			txtZahlenraum.setText("");
			txtSpielerA.setText("");
			txtSpielerB.setText("");
			gezZahl.setText("<html><body>Lege den Zahlenraum fest,<br>um das Spiel zu starten!</body></html>");
			lblAktSpieler.setText("");
			zFeld.neueTabelle(0, "", "");
		}
	}


}
