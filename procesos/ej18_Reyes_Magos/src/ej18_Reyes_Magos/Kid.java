package ej18_Reyes_Magos;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
import java.util.ArrayList;

public class Kid extends Thread {// clase Kid extiende de Thread
	private Buzon buzon;// objeto Buzon, valor inicial null
	private static ArrayList<Rey> listaReyes;// ArrayList estarico de reyes, este arrayList lo tienen todos los niños,
												// contiene los tres reyes creados en el main, para que el niño pueda
												// acceder al rey aleatorio y llamar al metodo recibir_kid
	private Integer kidID;
	private static Integer siguienteID = 1;

	public Kid(Buzon buzon) {// contructor
		this.kidID = siguienteID;
		siguienteID++;
		this.buzon = buzon;
	}

	public void run() {// metodo run del hilo,el niño aleatoriamente tiene un 33% de ir sin rey a
						// enviar la carta, y el 66% de elegir un rey aleatoriamente
		if (((int) (Math.random() * 3)) == 0) {
			try {
				Thread.sleep((int) Math.random() * 1000);// tiempo que tarda en dejar la carta
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buzon.recibir_carta(null);// deja la carta, el metodo recibe por parametros null indicando que es carta
										// enviada sin rey
		} else {
			listaReyes.get(((int) (Math.random() * 2 + 1))).recibir_kid();// aleatoriamente el niño elige un rey y llama
																			// al metodo recibir_kid
		}
	}

	public static ArrayList<Rey> getListaReyes() {
		return listaReyes;
	}

	public static void setListaReyes(ArrayList<Rey> listaReyes) {
		Kid.listaReyes = listaReyes;
	}

	public Integer getKidID() {
		return kidID;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

}
