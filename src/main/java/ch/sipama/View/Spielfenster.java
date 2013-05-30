package ch.sipama.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ch.sipama.Controller.Spieldaten;

/**
 * @author Simone Sterren
 *
 */

public class Spielfenster {

	//Instanzvariablen

	private JTextField txtZahlenraum;
	private JTextField txtSpielerA;
	private JTextField txtSpielerB;
	private JPanel pEinstellungen;
	private JSplitPane splitPane;
	private JScrollPane einstellungenScrollPane;
	private JScrollPane feldScrollPane;
	private Zahlenfeld oZFeld;
	private JLabel lblSpielinfo1;
	private JLabel lblSpielinfo2;
	private JRadioButton radSpielerVsSpieler;
	private JRadioButton radSpielerVsComp;
	private JButton btnNeustart;
	private JButton btnSpielzug;
	private JButton btnSpielAbbrechen;
	private JButton btnHilfe;
	private Spieldaten oSpdaten;
	private JComboBox<String> comboBoxCompListe;
	private int iZRange;
	private int iSpielmodi;

	public Spielfenster(){

		oSpdaten = Spieldaten.getInstance();

		oZFeld= new Zahlenfeld();

		oZFeld.tabelleZeichnen();
		oZFeld.tabelleUebernehmen();
		feldScrollPane = new JScrollPane(oZFeld.getZahlenfeld());

		pEinstellungen = new JPanel();
		SpringLayout layout = new SpringLayout();
		pEinstellungen.setLayout(layout);

		JLabel lblSpielerA = new JLabel("Spieler A:");
		JLabel lblSpielerB = new JLabel("Spieler B:");
		JLabel lblSpielmodus = new JLabel("Spielmodus:");

		radSpielerVsSpieler = new JRadioButton("Spieler gegen Spieler");
		radSpielerVsSpieler.setSelected(true);
		radSpielerVsSpieler.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				txtSpielerB.setEditable(true);
				comboBoxCompListe.setEnabled(false);
			}
		});

		radSpielerVsComp = new JRadioButton("Spieler gegen Computer");
		radSpielerVsComp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				txtSpielerB.setEditable(false);
				comboBoxCompListe.setEnabled(true);
			}
		});


		ButtonGroup sModus = new ButtonGroup();
		sModus.add(radSpielerVsSpieler);
		sModus.add(radSpielerVsComp);

		String compStratListe[] = {"Sebastian", "Anna", "Tom"};
		comboBoxCompListe = new JComboBox<String>(compStratListe);
		comboBoxCompListe.setEnabled(false);

		txtSpielerA = new JTextField(12);
		txtSpielerB = new JTextField(12);

		pEinstellungen.add(lblSpielmodus);
		pEinstellungen.add(radSpielerVsSpieler);
		pEinstellungen.add(radSpielerVsComp);
		pEinstellungen.add(lblSpielerA);
		pEinstellungen.add(lblSpielerB);
		pEinstellungen.add(txtSpielerA);
		pEinstellungen.add(txtSpielerB);
		pEinstellungen.add(comboBoxCompListe);

		lblSpielinfo1 = new JLabel("");
		lblSpielinfo2 = new JLabel("<html><body>Lege den Zahlenraum fest,<br>um das Spiel zu starten!</body></html>");
		pEinstellungen.add(lblSpielinfo1);
		pEinstellungen.add(lblSpielinfo2);

		btnNeustart = new JButton("Spiel starten!");
		pEinstellungen.add(btnNeustart);
		btnNeustart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				spielNeustarten();
			}
		});


		btnSpielzug = new JButton("Zahl ziehen");
		btnSpielzug.setEnabled(false);
		pEinstellungen.add(btnSpielzug);
		btnSpielzug.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				spielzug();

			}
		});


		btnHilfe = new JButton("Hilfe");
		btnHilfe.setEnabled(false);
		pEinstellungen.add(btnHilfe);
		btnHilfe.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){

				String text = oSpdaten.moegSPAnzeigen();
				JOptionPane.showMessageDialog(null, text, "Hilfe", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnSpielAbbrechen = new JButton ("Neues Spiel starten");
		btnSpielAbbrechen.setEnabled(false);
		pEinstellungen.add(btnSpielAbbrechen);
		btnSpielAbbrechen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				neuesSpielStarten();
			}
		});

		JLabel lblZahlenraum = new JLabel("Zahlenraum:");
		pEinstellungen.add(lblZahlenraum);

		txtZahlenraum = new JTextField(5);
		pEinstellungen.add(txtZahlenraum);
		txtZahlenraum.setHorizontalAlignment(JTextField.RIGHT);
		einstellungenScrollPane = new JScrollPane(pEinstellungen);

		layout.putConstraint(SpringLayout.NORTH, lblZahlenraum, 8, SpringLayout.NORTH, pEinstellungen);
		layout.putConstraint(SpringLayout.WEST, lblZahlenraum, 5, SpringLayout.WEST, pEinstellungen);
		layout.putConstraint(SpringLayout.NORTH, txtZahlenraum, 5, SpringLayout.NORTH, pEinstellungen);
		layout.putConstraint(SpringLayout.WEST, txtZahlenraum, 10, SpringLayout.EAST, lblZahlenraum);

		layout.putConstraint(SpringLayout.NORTH, lblSpielmodus, 20,	SpringLayout.SOUTH, lblZahlenraum);
		layout.putConstraint(SpringLayout.WEST, lblSpielmodus, 5, SpringLayout.WEST, pEinstellungen);

		layout.putConstraint(SpringLayout.NORTH, radSpielerVsSpieler, 16,	SpringLayout.SOUTH, lblZahlenraum);
		layout.putConstraint(SpringLayout.WEST, radSpielerVsSpieler, 10, SpringLayout.EAST, lblSpielmodus);
		layout.putConstraint(SpringLayout.NORTH, radSpielerVsComp, 0,	SpringLayout.SOUTH, radSpielerVsSpieler);
		layout.putConstraint(SpringLayout.WEST, radSpielerVsComp, 10, SpringLayout.EAST, lblSpielmodus);
		layout.putConstraint(SpringLayout.NORTH, comboBoxCompListe, 0,	SpringLayout.SOUTH, radSpielerVsComp);
		layout.putConstraint(SpringLayout.WEST, comboBoxCompListe, 31, SpringLayout.EAST, lblSpielmodus);



		layout.putConstraint(SpringLayout.NORTH, btnNeustart, 10, SpringLayout.SOUTH, lblSpielerB);
		layout.putConstraint(SpringLayout.WEST, btnNeustart, 5, SpringLayout.WEST, pEinstellungen);

		layout.putConstraint(SpringLayout.NORTH, lblSpielerA, 20,	SpringLayout.SOUTH, comboBoxCompListe);
		layout.putConstraint(SpringLayout.WEST, lblSpielerA, 5, SpringLayout.WEST, pEinstellungen);
		layout.putConstraint(SpringLayout.NORTH, lblSpielerB, 20, SpringLayout.NORTH, lblSpielerA);
		layout.putConstraint(SpringLayout.WEST, lblSpielerB, 5, SpringLayout.WEST, pEinstellungen);

		layout.putConstraint(SpringLayout.NORTH, txtSpielerA, 18,	SpringLayout.SOUTH, comboBoxCompListe);
		layout.putConstraint(SpringLayout.WEST, txtSpielerA, 5, SpringLayout.EAST, lblSpielerA);
		layout.putConstraint(SpringLayout.NORTH, txtSpielerB, 18, SpringLayout.NORTH, lblSpielerA);
		layout.putConstraint(SpringLayout.WEST, txtSpielerB, 5, SpringLayout.EAST, lblSpielerB);


		layout.putConstraint(SpringLayout.NORTH, lblSpielinfo1, 40, SpringLayout.SOUTH, btnNeustart);
		layout.putConstraint(SpringLayout.WEST, lblSpielinfo1, 5, SpringLayout.WEST, pEinstellungen);

		layout.putConstraint(SpringLayout.NORTH, lblSpielinfo2, 10, SpringLayout.SOUTH, lblSpielinfo1);
		layout.putConstraint(SpringLayout.WEST, lblSpielinfo2, 5, SpringLayout.WEST, pEinstellungen);

		layout.putConstraint(SpringLayout.NORTH, btnSpielzug, 20, SpringLayout.SOUTH, lblSpielinfo2);
		layout.putConstraint(SpringLayout.WEST, btnSpielzug, 5, SpringLayout.WEST, pEinstellungen);

		layout.putConstraint(SpringLayout.SOUTH, btnSpielAbbrechen, -5, SpringLayout.SOUTH, pEinstellungen);
		layout.putConstraint(SpringLayout.EAST, btnSpielAbbrechen, -5, SpringLayout.EAST, pEinstellungen);

		layout.putConstraint(SpringLayout.SOUTH, btnHilfe, -5, SpringLayout.SOUTH, pEinstellungen);
		layout.putConstraint(SpringLayout.WEST, btnHilfe, 5, SpringLayout.WEST, pEinstellungen);

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
			iZRange = Integer.parseInt(getZahlenraum());
			
			if(iZRange>50 && comboBoxCompListe.getSelectedIndex()==1){
				JOptionPane.showMessageDialog(null, "Beschränke den Zahlenraum bei Anna auf max. 50!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
				txtZahlenraum.setText("50");
				return;
			}
			
			
			if(iZRange < 10 || iZRange > 1000){
				JOptionPane.showMessageDialog(null, "Trage eine Zahl zwischen 10 und 1000 ein!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
				txtZahlenraum.setText("");
			}
			else{
				if((radSpielerVsSpieler.isSelected()==true && (txtSpielerA.getText().length()<1 || txtSpielerB.getText().length()<1)) || (radSpielerVsComp.isSelected()==true && txtSpielerA.getText().length()<1)){
					int o = JOptionPane.showConfirmDialog(null, "<html><body>Möchtest du das Spiel starten, ohne Namen eingetragen zu haben?<br><br>Klicke auf 'Abbrechen', um die Namen noch einzutragen!</html></body>", "Hinweis", 2);
					if(o==0){
						if(txtSpielerA.getText().length()<1){
							txtSpielerA.setText("Spieler A");
						}
						if(radSpielerVsComp.isSelected()==false && txtSpielerB.getText().length()<1){
							txtSpielerB.setText("Spieler B");
						}
						spielStarten();
					}
				}
				else{
					spielStarten();
				}
			}


		}catch (NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Trage eine Zahl zwischen 10 und 1000 ein!", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			txtZahlenraum.setText("");
		}

	}


	public void spielStarten(){
		iSpielmodi=0;
		if(radSpielerVsComp.isSelected()==true){
			iSpielmodi = comboBoxCompListe.getSelectedIndex()+1;
		}

		String spielerB="";
		if(iSpielmodi>0){
			spielerB = "PC: " + comboBoxCompListe.getItemAt(iSpielmodi-1);
		}else{
			spielerB = txtSpielerB.getText();
		}

		oZFeld.neueTabelle(iZRange, txtSpielerA.getText(), spielerB, iSpielmodi);
		oZFeld.tabelleAktualisieren();
		lblSpielinfo2.setText("Ziehe eine gerade Zahl!");
		btnNeustart.setEnabled(false);
		btnSpielzug.setEnabled(true);
		btnSpielAbbrechen.setEnabled(true);
		btnHilfe.setEnabled(true);
		txtZahlenraum.setEditable(false);
		txtSpielerA.setEditable(false);
		txtSpielerB.setEditable(false);
		lblSpielinfo1.setText(txtSpielerA.getText() + " ist am Zug!");
		comboBoxCompListe.setEnabled(false);
		radSpielerVsSpieler.setEnabled(false);
		radSpielerVsComp.setEnabled(false);
	}


	public void spielzug(){
		String spielzug = oZFeld.spielzug();

		if(iSpielmodi>0 && spielzug.length()>0){
			lblSpielinfo1.setText(txtSpielerA.getText() + " hat die Zahl " + spielzug + " gezogen.");
			String pcSpielzug = oZFeld.pcSpielzug();
			if(pcSpielzug.length()>0){
				lblSpielinfo2.setText("PC: " + comboBoxCompListe.getItemAt(iSpielmodi-1) + " hat die Zahl " + pcSpielzug + " gezogen.");
			}else{
				lblSpielinfo2.setText("PC: " + comboBoxCompListe.getItemAt(iSpielmodi-1) + " hat verloren!");
			}
			

		}else if(spielzug.length()>0){
			lblSpielinfo2.setText("Zuletzt gezogene Zahl: " + spielzug);
			if(lblSpielinfo1.getText().equals((txtSpielerA.getText() + " ist am Zug!"))){
				lblSpielinfo1.setText(txtSpielerB.getText() + " ist am Zug!");
			}else{
				lblSpielinfo1.setText(txtSpielerA.getText() + " ist am Zug!");
			}
		}
		if(oSpdaten.isbSpielende()){
			btnSpielzug.setEnabled(false);
		}
	}

	public void neuesSpielStarten(){
		int o = JOptionPane.showConfirmDialog(null, "Willst du das Spiel wirklich abbrechen?", "Hinweis", JOptionPane.OK_CANCEL_OPTION);
		if(o==0){
			txtZahlenraum.setEditable(true);
			txtSpielerA.setEditable(true);
			txtSpielerB.setEditable(true);
			btnNeustart.setEnabled(true);
			btnSpielzug.setEnabled(false);
			btnSpielAbbrechen.setEnabled(false);
			btnHilfe.setEnabled(false);
			txtZahlenraum.setText("");
			txtSpielerA.setText("");
			txtSpielerB.setText("");
			lblSpielinfo2.setText("<html><body>Lege den Zahlenraum fest,<br>um das Spiel zu starten!</body></html>");
			lblSpielinfo1.setText("");
			oZFeld.neueTabelle(0, "", "", 0);
			radSpielerVsSpieler.setEnabled(true);
			radSpielerVsSpieler.setSelected(true);
			radSpielerVsComp.setEnabled(true);
		}
	}

	public void rueckgaengig() {
		if(oSpdaten.isbSpielende()==true){
			JOptionPane.showMessageDialog(null, "<html><body>Das Spiel ist beendet.<br>Du kannst die Spielzüge nicht mehr rückgängig machen.</body></html>", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
		}else{
			if(iSpielmodi>0){
				if(oSpdaten.logListgroesse()<2){
					JOptionPane.showMessageDialog(null, "<html><body>Das Spiel wurde noch nicht gestartet.<br>Du kannst keine Spielzüge rückgängig machen.</body></html>", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
				}else if (oSpdaten.logListgroesse()>2){
					int row1 = oSpdaten.getLogRow(oSpdaten.logListgroesse()-1);
					int column1 = oSpdaten.getLogColumn(oSpdaten.logListgroesse()-1);
					String zahl1 = "" + oSpdaten.getLogZahl(oSpdaten.logListgroesse()-1);
					oZFeld.getModel().setValueAt(zahl1, row1, column1);

					int row2 = oSpdaten.getLogRow(oSpdaten.logListgroesse()-2);
					int column2 = oSpdaten.getLogColumn(oSpdaten.logListgroesse()-2);
					String zahl2 = "" + oSpdaten.getLogZahl(oSpdaten.logListgroesse()-2);
					oZFeld.getModel().setValueAt(zahl2, row2, column2);


					lblSpielinfo1.setText(txtSpielerA.getText() + " hat die Zahl " + oSpdaten.getLogZahl(oSpdaten.logListgroesse()-4) + " gezogen.");
					lblSpielinfo2.setText("PC: " + comboBoxCompListe.getItemAt(iSpielmodi-1) + " hat die Zahl " + oSpdaten.getLogZahl(oSpdaten.logListgroesse()-3) + " gezogen.");

					oSpdaten.rueckgaengigLog();
					oSpdaten.rueckgaengigLog();

				}else{
					spielNeustarten();
				}
			}else{
				if(oSpdaten.logListgroesse()<1){
					JOptionPane.showMessageDialog(null, "<html><body>Das Spiel wurde noch nicht gestartet.<br>Du kannst keine Spielzüge rückgängig machen.</body></html>", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
				}else if (oSpdaten.logListgroesse()>1){
					int row = oSpdaten.getLogRow(oSpdaten.logListgroesse()-1);
					int column = oSpdaten.getLogColumn(oSpdaten.logListgroesse()-1);
					String zahl = "" + oSpdaten.getLogZahl(oSpdaten.logListgroesse()-1);
					oZFeld.getModel().setValueAt(zahl, row, column);

					lblSpielinfo1.setText(oSpdaten.getLogSpieler(oSpdaten.logListgroesse()-1) + " ist am Zug!");
					lblSpielinfo2.setText("Zuletzt gezogene Zahl: " + oSpdaten.getLogZahl(oSpdaten.logListgroesse()-2));

					oSpdaten.rueckgaengigLog();
				}else{
					spielNeustarten();
				}
			}
		}
	}

}
