package ej18_Reyes_Magos;

import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		Kid kid;
		Buzon buzon = new Buzon();

		ArrayList<Rey> reyes = new ArrayList<Rey>();
		reyes.add(new Rey("Baltasar", buzon));
		reyes.add(new Rey("Melchor", buzon));
		reyes.add(new Rey("Gaspar", buzon));

		kid = new Kid(buzon);
		kid.setListaReyes(reyes);
		kid.start();

		for (int i = 0; i < 99; i++) {
			kid = new Kid(buzon);
			kid.start();
		}
	}
}