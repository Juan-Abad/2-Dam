package ej18_Reyes_Magos;

import java.util.ArrayList;

public class Kid extends Thread {
	private Buzon buzon;
	private static ArrayList<Rey> listaReyes;
	private Integer kidID;
	private static Integer siguienteID = 1;

	public Kid(Buzon buzon) {
		this.kidID = siguienteID;
		siguienteID++;
		this.buzon = buzon;
	}

	public void run() {
		if (((int) (Math.random() * 3)) == 0) {
			try {
				Thread.sleep((int) Math.random()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buzon.recibir_carta(null);
		} else {
			listaReyes.get(((int) (Math.random() * 2 + 1))).recibir_kid();
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
