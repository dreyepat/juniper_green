package ch.sipama.Logik;

import java.util.ArrayList;

import ch.sipama.View.Zahlenfeld;

/*
 * @author Marco Lamm - 01.04.2013
 * 
 * Class is not connected to others - own main method for testing
 * 2 different variations
 * 
 */

public class Logik {

	private int x = 2;
	private int y = 48;
	private ArrayList<Integer> zahlen = new ArrayList<Integer>();

	// first chosen number

	public Logik() {

		arrayFuellen();
		// backtrack1();
		// backtrack2();
		backtrack3();

	}

	public void arrayFuellen() {
		for (int i = 1; i <= 7; i++) {
			zahlen.add(i);
		}
	}

	// Variante
	// 1---Methode:backtrack1()-----------------------------------------------------------------------------------------------------
	public void zahlGerade() {
		if (x % 2 == 0) {
			System.out.println("Zahl " + x
					+ " ist gerade - Nächste Zahl ziehen: " + y);
			System.out.println(zahlen.indexOf(zahlen));
			zahlCheck();
		} else {
			System.out.println("Ziehen Sie eine Gerade Zahl");
		}
	}

	// Check ob nachfolgende Zahl Teiler oder Vielfaches von x ist
	public void zahlCheck() {
		if (x % y == 0 || y % x == 0) {
			System.out.println(y + " ist Teiler oder Vielfaches von " + x);
		} else {
			System.out.println("Diese Zahl darfst du nicht ziehen - " + y
					+ " muss Teiler oder Vielfaches von " + x + " sein");
		}
	}

	public void zahlRemove() {
		zahlen.remove(zahlen);
	}

	// sucht alle Zahlen aus Sammlung "zahlen" mit Logic JuniperGreen-beginnt
	// mit höchster GeradZahl und sucht nur nach unten
	public void backtrack1() {

		int i = zahlen.size() - 1;

		System.out.print(zahlen.get(i) + " ");
		for (int k = i - 1; k >= 0; k--) {

			if (zahlen.get(i) % zahlen.get(k) == 0
					|| zahlen.get(k) % zahlen.get(i) == 0) {
				System.out.print(zahlen.get(k) + " ");

				i = k;
			}

		}
		if (zahlen.size() == 1) {
			System.out.println("stop");
		} else {

			zahlen.remove(zahlen.size() - 1);
			System.out.println(" ");
			backtrack1();
		}
	}

	// Variante 3--Methode:backtrack3()
	// ------------------------------------------------------------------------------------------------------

	public void backtrack3() {

		// int i = zahlen.size() - 1;

		for (int i = 0; i <= zahlen.size() - 1; i++) {

			for (int k = 0; k <= zahlen.size() - 1; k++) {

				if (zahlen.get(k) % zahlen.get(i) == 0
						|| zahlen.get(i) % zahlen.get(k) == 0) {
					System.out.print(zahlen.get(k) + " ");

				}

			}
			System.out.print("-- gezogene Zahl = " + (i + 1));

			if (zahlen.size() == 1) {
				System.out.println("stop");
			} else {

				// zahlen.remove(i);
				System.out.println(" ");
				// backtrack3();
			}
		}
		
	}

	// Variante 2--Methode:backtrack2()
	// ------------------------------------------------------------------------------------------------------

	public boolean zahlGerade2(int a) {
		if (a % 2 == 0) {
			return true;
		}
		return false;
	}

	public boolean zahlCheck2() {
		if (x % y == 0 || y % x == 0) {
			return true;
		}
		return false;

	}

	public void backtrack2() {
		int a = zahlen.get(6);
		if (zahlGerade2(a) == false) {
			System.out.println(" Wähle eine gerade Zahl! " + a
					+ " ist ungerade");

		} else {
			System.out.println("OK!  die Zahl " + a + " ist gerade-Danke");
		}

	}

	public static void main(String[] args) {
		new Logik();
	}

}
