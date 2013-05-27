package ch.sipama.View;


import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ch.sipama.Controller.Spieldaten;


/**
 * Main Gui Class
 * @author Patrizia Dreyer
 */


public class Hauptgui extends JPanel {
	
	//Instanzvariablen
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Spielfenster oSpielfenster;
	private Spieldaten oSpdaten;
	
	//Defaultkonstruktor
	public Hauptgui(){
		createFrame();
		oSpdaten = Spieldaten.getInstance();
	}
	
	//Fenster erstellen
	public void createFrame(){
		
		//Rahmen erstellen
		frame = new JFrame("Juniper Green");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oSpielfenster = new Spielfenster();
		frame.getContentPane().add(oSpielfenster.getSplitPane());
		
		//Menübar einbinden, indem die entsprechende Methode aufgerufen wird
		createMenuBar();

		//Rahmengrösse definieren und sichtbar setzen
		frame.setBounds(200, 150, 950, 550);
		frame.setMinimumSize(new Dimension(450, 300));
		frame.setVisible(true);
	}
	
	
	//Menübar erstellen
	public void createMenuBar(){
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		
		//Menü 'Datei' mit den Befehlen
		JMenu dateiMenu = new JMenu("Datei");
		JMenu jgMenu = new JMenu("Juniper Green");
		JMenu infoMenu = new JMenu("Infos");
		bar.add(dateiMenu);
		bar.add(jgMenu);
		bar.add(infoMenu);
		
		//Menüpunkte erstellen und hinzufügen
		JMenuItem beenden = new JMenuItem("Beenden");
		dateiMenu.add(beenden);
		
		JMenuItem spielregeln = new JMenuItem("Spielregeln");
		jgMenu.add(spielregeln);
		JMenuItem spielverlauf = new JMenuItem("Spielverlauf");
		jgMenu.add(spielverlauf);
		JMenuItem rueckgaengig = new JMenuItem("Rückgängig");
		jgMenu.add(rueckgaengig);
		
		JMenuItem github = new JMenuItem("Zum Github-Repository");
		infoMenu.add(github);
		JMenuItem ueber = new JMenuItem("Über");
		infoMenu.add(ueber);
		
		
		//Action-Listener für die Menüpunkte
		
		//Schliesst das Programm	
		beenden.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				beenden();
			}
		});
		
		//Öffnet unsere Github-Seite
		github.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ladeURL("https://github.com/dreyepat/juniper_green"); 
			}
		});
		
		//Hinweis zur Version
		ueber.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sUeber="<html><body>Juniper Green<br><br>Version 2013.1.0<br><br>von<br>Patrizia Dreyer<br>Marco Lamm<br>Simone Sterren</body></html>";
				JOptionPane.showMessageDialog(null, sUeber, "Über", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		spielregeln.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				spielregelnAnzeigen();
			}
		});
		
		spielverlauf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				logAnzeigen();
			}
		});
		
		rueckgaengig.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				oSpielfenster.rueckgaengig();
			}
		});
		
	}
	
	
	//Fragt den User, ob das Programm wirklich beendet werden soll.
	//Wenn 'Ja' geklickt wird, wird das Programm geschlossen.
	public void beenden(){
		int wertInt = JOptionPane.showConfirmDialog(null, "Wirklich beenden?", "Beenden", 2);
		if (wertInt == 0){
			System.exit(0);
		}
	}
	
	public void spielregelnAnzeigen(){
		String spielregeln = "<html><body>Spielregeln<br><br>" +
				"1. Beide Spieler nehmen abwechselnd jeweils eine Zahl aus der Tabelle.<br>Die entfernten Zahlen werden nicht ersetzt und im weiteren Verlauf<br>nicht mehr verwendet.<br><br>" +
				"2. Im ersten Zug muss eine gerade Zahl gezogen werden. In allen weiteren<br>Zügen muss die gewählte Zahl ein Teiler oder ein Vielfaches der<br>zuletzt gezogenen sein.<br><br>" +
				"3. Wer keine Zahl mehr ziehen kann, hat verloren.</body></html>";
		JOptionPane.showMessageDialog(null, spielregeln, "Spielregeln", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	public void logAnzeigen(){
		JOptionPane.showMessageDialog(null, oSpdaten.logAnzeigen(), "Spielverlauf", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Öffnet den Standardbrowser des Benutzers mit unserer Github-Seite
	private void ladeURL(String seite) { 
        try { 
            Desktop.getDesktop().browse(new URI(seite)); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } catch (URISyntaxException e) { 
            e.printStackTrace(); 
        }
    } 

}