package ej18_Reyes_Magos;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
import java.util.ArrayList;

public class Principal {// clase Principal, contiene el main

	public static void main(String[] args) {// main
		Kid kid;// objeto de la clase Kid, valor inicial null
		Buzon buzon = new Buzon();// objeto instanciado de la clase Buzon

		ArrayList<Rey> reyes = new ArrayList<Rey>();// ArrayList de reyes
		reyes.add(new Rey("Baltasar", buzon));// se añade al arrayList un rey, al que se le pasa por parametros el buzon
												// y el nombre del rey
		reyes.add(new Rey("Melchor", buzon));// se añade al arrayList un rey, al que se le pasa por parametros el buzon
												// y el nombre del rey
		reyes.add(new Rey("Gaspar", buzon));// se añade al arrayList un rey, al que se le pasa por parametros el buzon y
											// el nombre del rey

		kid = new Kid(buzon);// se crea un kid(hilo) al que se le pasa el buzon
		kid.setListaReyes(reyes);// se usa un setter para pasar la lista de reyes creada en el main a todos los
									// niños, al ser estatica se hace de esta forma
		kid.start();// se pone a correr al primer niño

		for (int i = 0; i < 99; i++) {// se crean el resto de niños y se ponen a correr, son en total 100 niños
			kid = new Kid(buzon);
			kid.start();
		}
	}
}