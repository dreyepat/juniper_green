package ch.sipama.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import ch.sipama.Controller.Spieldaten;


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
	private JLabel lblAktSpieler;
	private JLabel lblGezZahl;
	private JRadioButton radSpielerVsSpieler;
	private JRadioButton radSpielerVsComp;
	private JButton btnNeustart;
	private JButton btnSpielzug;
	private JButton btnSpielAbbrechen;
	private JButton btnHilfe;
	private Spieldaten spieldaten;
	private JComboBox<String> compListe;
	private int zRange;
	private int spielmodi;

	public Spielfenster(){

		spieldaten = Spieldaten.getInstance();

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

		radSpielerVsSpieler = new JRadioButton("Spieler gegen Spieler");
		radSpielerVsSpieler.setSelected(true);
		radSpielerVsSpieler.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				txtSpielerB.setEditable(true);
				compListe.setEnabled(false);
			}
		});

		radSpielerVsComp = new JRadioButton("Spieler gegen Computer");
		radSpielerVsComp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				txtSpielerB.setEditable(false);
				compListe.setEnabled(true);
			}
		});


		ButtonGroup sModus = new ButtonGroup();
		sModus.add(radSpielerVsSpieler);
		sModus.add(radSpielerVsComp);

		String compStratListe[] = {"Sebastian", "Anna", "Tom"};
		compListe = new JComboBox<String>(compStratListe);
		compListe.setEnabled(false);

		txtSpielerA = new JTextField(12);
		txtSpielerB = new JTextField(12);

		einstellungen.add(lblSpielmodus);
		einstellungen.add(radSpielerVsSpieler);
		einstellungen.add(radSpielerVsComp);
		einstellungen.add(lblSpielerA);
		einstellungen.add(lblSpielerB);
		einstellungen.add(txtSpielerA);
		einstellungen.add(txtSpielerB);
		einstellungen.add(compListe);

		lblAktSpieler = new JLabel("");
		lblGezZahl = new JLabel("<html><body>Lege den Zahlenraum fest,<br>um das Spiel zu starten!</body></html>");
		einstellungen.add(lblAktSpieler);
		einstellungen.add(lblGezZahl);

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


		btnHilfe = new JButton("Hilfe");
		btnHilfe.setEnabled(false);
		einstellungen.add(btnHilfe);
		btnHilfe.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){

				String text = spieldaten.moegSPAnzeigen();
				JOptionPane.showMessageDialog(null, text, "Hilfe", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnSpielAbbrechen = new JButton ("Neues Spiel starten");
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
		layout.putConstraint(SpringLayout.NORTH, radSpielerVsComp, 0,	SpringLayout.SOUTH, radSpielerVsSpieler);
		layout.putConstraint(SpringLayout.WEST, radSpielerVsComp, 10, SpringLayout.EAST, lblSpielmodus);
		layout.putConstraint(SpringLayout.NORTH, compListe, 0,	SpringLayout.SOUTH, radSpielerVsComp);
		layout.putConstraint(SpringLayout.WEST, compListe, 31, SpringLayout.EAST, lblSpielmodus);



		layout.putConstraint(SpringLayout.NORTH, btnNeustart, 10, SpringLayout.SOUTH, lblSpielerB);
		layout.putConstraint(SpringLayout.WEST, btnNeustart, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, lblSpielerA, 20,	SpringLayout.SOUTH, compListe);
		layout.putConstraint(SpringLayout.WEST, lblSpielerA, 5, SpringLayout.WEST, einstellungen);
		layout.putConstraint(SpringLayout.NORTH, lblSpielerB, 20, SpringLayout.NORTH, lblSpielerA);
		layout.putConstraint(SpringLayout.WEST, lblSpielerB, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, txtSpielerA, 18,	SpringLayout.SOUTH, compListe);
		layout.putConstraint(SpringLayout.WEST, txtSpielerA, 5, SpringLayout.EAST, lblSpielerA);
		layout.putConstraint(SpringLayout.NORTH, txtSpielerB, 18, SpringLayout.NORTH, lblSpielerA);
		layout.putConstraint(SpringLayout.WEST, txtSpielerB, 5, SpringLayout.EAST, lblSpielerB);


		layout.putConstraint(SpringLayout.NORTH, lblAktSpieler, 40, SpringLayout.SOUTH, btnNeustart);
		layout.putConstraint(SpringLayout.WEST, lblAktSpieler, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, lblGezZahl, 10, SpringLayout.SOUTH, lblAktSpieler);
		layout.putConstraint(SpringLayout.WEST, lblGezZahl, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.NORTH, btnSpielzug, 20, SpringLayout.SOUTH, lblGezZahl);
		layout.putConstraint(SpringLayout.WEST, btnSpielzug, 5, SpringLayout.WEST, einstellungen);

		layout.putConstraint(SpringLayout.SOUTH, btnSpielAbbrechen, -5, SpringLayout.SOUTH, einstellungen);
		layout.putConstraint(SpringLayout.EAST, btnSpielAbbrechen, -5, SpringLayout.EAST, einstellungen);

		layout.putConstraint(SpringLayout.SOUTH, btnHilfe, -5, SpringLayout.SOUTH, einstellungen);
		layout.putConstraint(SpringLayout.WEST, btnHilfe, 5, SpringLayout.WEST, einstellungen);

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
			zRange = Integer.parseInt(getZahlenraum());
			if(zRange < 10 || zRange > 1000){
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
		spielmodi=0;
		if(radSpielerVsComp.isSelected()==true){
			spielmodi = compListe.getSelectedIndex()+1;
		}

		String spielerB="";
		if(spielmodi>0){
			spielerB = "PC: " + compListe.getItemAt(spielmodi-1);
		}else{
			spielerB = txtSpielerB.getText();
		}

		zFeld.neueTabelle(zRange, txtSpielerA.getText(), spielerB, spielmodi);
		zFeld.tabelleAktualisieren();
		lblGezZahl.setText("Ziehe eine gerade Zahl!");
		btnNeustart.setEnabled(false);
		btnSpielzug.setEnabled(true);
		btnSpielAbbrechen.setEnabled(true);
		btnHilfe.setEnabled(true);
		txtZahlenraum.setEditable(false);
		txtSpielerA.setEditable(false);
		txtSpielerB.setEditable(false);
		lblAktSpieler.setText(txtSpielerA.getText() + " ist am Zug!");
		compListe.setEnabled(false);
		radSpielerVsSpieler.setEnabled(false);
		radSpielerVsComp.setEnabled(false);
	}


	public void spielzug(){
		String spielzug = zFeld.spielzug();

		if(spielmodi>0 && spielzug.length()>0){
			lblAktSpieler.setText(txtSpielerA.getText() + " hat die Zahl " + spielzug + " gezogen.");
			String pcSpielzug = zFeld.pcSpielzug();
			lblGezZahl.setText("PC: " + compListe.getItemAt(spielmodi-1) + " hat die Zahl " + pcSpielzug + " gezogen.");

		}else if(spielzug.length()>0){
			lblGezZahl.setText("Zuletzt gezogene Zahl: " + spielzug);
			if(lblAktSpieler.getText().equals((txtSpielerA.getText() + " ist am Zug!"))){
				lblAktSpieler.setText(txtSpielerB.getText() + " ist am Zug!");
			}else{
				lblAktSpieler.setText(txtSpielerA.getText() + " ist am Zug!");
			}
		}
		if(spieldaten.isbSpielende()==true){
			btnSpielzug.setEnabled(false);
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
			btnHilfe.setEnabled(false);
			txtZahlenraum.setText("");
			txtSpielerA.setText("");
			txtSpielerB.setText("");
			lblGezZahl.setText("<html><body>Lege den Zahlenraum fest,<br>um das Spiel zu starten!</body></html>");
			lblAktSpieler.setText("");
			zFeld.neueTabelle(0, "", "", 0);
			radSpielerVsSpieler.setEnabled(true);
			radSpielerVsSpieler.setSelected(true);
			radSpielerVsComp.setEnabled(true);
		}
	}

	public void rueckgaengig() {
		if(spieldaten.isbSpielende()==true){
			JOptionPane.showMessageDialog(null, "<html><body>Das Spiel ist beendet.<br>Du kannst die Spielzüge nicht mehr rückgängig machen.</body></html>", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
		}else{
			if(spielmodi>0){
				if(spieldaten.getLog().size()<2){
					JOptionPane.showMessageDialog(null, "<html><body>Das Spiel wurde noch nicht gestartet.<br>Du kannst keine Spielzüge rückgängig machen.</body></html>", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
				}else if (spieldaten.getLog().size()>2){
					int row1 = spieldaten.getLog().get(spieldaten.getLog().size()-1).getRow();
					int column1 = spieldaten.getLog().get(spieldaten.getLog().size()-1).getColumn();
					String zahl1 = "" + spieldaten.getLog().get(spieldaten.getLog().size()-1).getZahl();
					zFeld.getModel().setValueAt(zahl1, row1, column1);

					int row2 = spieldaten.getLog().get(spieldaten.getLog().size()-2).getRow();
					int column2 = spieldaten.getLog().get(spieldaten.getLog().size()-2).getColumn();
					String zahl2 = "" + spieldaten.getLog().get(spieldaten.getLog().size()-2).getZahl();
					zFeld.getModel().setValueAt(zahl2, row2, column2);


					lblAktSpieler.setText(txtSpielerA.getText() + " hat die Zahl " + spieldaten.getLog().get(spieldaten.getLog().size()-4).getZahl() + " gezogen.");
					lblGezZahl.setText("PC: " + compListe.getItemAt(spielmodi-1) + " hat die Zahl " + spieldaten.getLog().get(spieldaten.getLog().size()-3).getZahl() + " gezogen.");

					spieldaten.getLog().remove(spieldaten.getLog().size()-1);
					spieldaten.getLog().remove(spieldaten.getLog().size()-1);



				}else{
					spielNeustarten();
				}
			}else{
				if(spieldaten.getLog().size()<1){
					JOptionPane.showMessageDialog(null, "<html><body>Das Spiel wurde noch nicht gestartet.<br>Du kannst keine Spielzüge rückgängig machen.</body></html>", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
				}else if (spieldaten.getLog().size()>1){
					int row = spieldaten.getLog().get(spieldaten.getLog().size()-1).getRow();
					int column = spieldaten.getLog().get(spieldaten.getLog().size()-1).getColumn();
					String zahl = "" + spieldaten.getLog().get(spieldaten.getLog().size()-1).getZahl();
					zFeld.getModel().setValueAt(zahl, row, column);

					lblAktSpieler.setText(spieldaten.getLog().get(spieldaten.getLog().size()-1).getSpieler() + " ist am Zug!");
					lblGezZahl.setText("Zuletzt gezogene Zahl: " + spieldaten.getLog().get(spieldaten.getLog().size()-2).getZahl());

					spieldaten.getLog().remove(spieldaten.getLog().size()-1);
				}else{
					spielNeustarten();
				}
			}
		}
	}

}
