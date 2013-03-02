package ch.sipama.View;

import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JTabbedPane;


/**
 * Main Gui Class
 * @author Patrizia Dreyer
 */


public class Hauptgui {
	
	//Instanzvariablen
	private JFrame frame;
	private Container contentPane;
	
	
	//Defaultkonstruktor
	public Hauptgui(){
		createFrame();
	}
	
	
	//Fenster erstellen
	public void createFrame(){
		
		//Rahmen erstellen
		frame = new JFrame("Juniper Green");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//MenÃ¼bar einbinden, indem die entsprechende Methode aufgerufen wird
		createMenuBar();
		
		//TabbedPane-Layout erstellen
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Spiel", new Feld());
		
		//TabbedPane in den Rahmen einfÃ¼gen
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(tabbedPane, BorderLayout.CENTER);
			
		//Rahmengrösse definieren und sichtbar setzen
		frame.setBounds(200, 150, 650, 450);
		frame.setMinimumSize(new Dimension(450, 300));
		frame.setVisible(true);
		
		
	}
	
	
	//MenÃ¼bar erstellen
	public void createMenuBar(){
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		
		//Menü 'Datei' mit den Befehlen
		JMenu dateiMenu = new JMenu("Datei");
		JMenu hilfeMenu = new JMenu("Hilfe");
		bar.add(dateiMenu);
		bar.add(hilfeMenu);
		
		//Menüpunkte erstellen und hinzufügen
		JMenuItem beenden = new JMenuItem("Beenden");
		dateiMenu.add(beenden);
		
		JMenuItem hilfe = new JMenuItem("Anleitung");
		hilfeMenu.add(hilfe);
		JMenuItem ueber = new JMenuItem("Über...");
		hilfeMenu.add(ueber);
		
		
		//Action-Listener für die Menüpunkte
		
		//Schliesst das Programm	
		beenden.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				beenden();
			}
		});
		
		//Öffnet unsere Github-Seite
		hilfe.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ladeURL("https://github.com/dreyepat/juniper_green"); 
			}
		});
		
		//Hinweis zur Version
		ueber.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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