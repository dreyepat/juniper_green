package ch.sipama.Logik;

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
	int[] zahlen = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
			18, 19, 20 };
	int a=zahlen[6];  // first chosen number
	
	public Logik() {
//		backtrack1();
		backtrack2();
	}

	//  Variante 1---Methode:backtrack1()-----------------------------------------------------------------------------------------------------
	public void zahlGerade() {
		if (x % 2 == 0) {
			System.out.println("Zahl " + x
					+ " ist gerade - Nächste Zahl ziehen: " + y);
			System.out.println(zahlen[1]);
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

	// sucht alle Zahlen aus Sammlung "zahlen" mit Logic JuniperGreen-beginnt
	// mit höchster GeradZahl und sucht nur nach unten
	public void backtrack1() {
		for (int i = zahlen.length; i > 0; i--) {
			if (zahlen[i - 1] % 2 == 0) {
				System.out.println(zahlen[i - 1]);

				for (int k = i - 2; k >= 0; k--) {
					if (zahlen[k] % zahlen[i - 1] == 0
							|| zahlen[i - 1] % zahlen[k] == 0) {
						System.out.println(zahlen[k]);
						i = k + 1;
					}
				}
				i--;
			} else {
				System.out.println("Du musst mit einer geraden Zahl beginnen");
				break;
			}
		}

	}

//   Variante 2--Methode:backtrack2() ------------------------------------------------------------------------------------------------------

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

		if (zahlGerade2(a) == false) {
			System.out.println(" Wähle eine gerade Zahl! "+a+" ist ungerade");

		} else {
			System.out.println("OK!  die Zahl " + a + " ist gerade-Danke");
		}
		
		
	}
		
	

	public static void main(String[] args) {
		new Logik();
	}

}
