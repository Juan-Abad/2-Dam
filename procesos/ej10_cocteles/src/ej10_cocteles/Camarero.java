package ej10_cocteles;

import java.util.ArrayList;

public class Camarero extends Thread {
	private static ArrayList<Grifo> listaGrifos = new ArrayList<Grifo>();

	private Integer idCamarero;
	private static Integer siguienteID = 1;

	public Camarero() {
		this.idCamarero = siguienteID;
		siguienteID++;
	}

	public void run() {
		while (true) {
			switch (((int) (Math.random() * 2))) {
			case 0:
				usarGrifo(0);
				usarGrifo(2);
				break;
			case 1:
				usarGrifo(0);
				usarGrifo(1);
				break;
			case 2:
				usarGrifo(0);
				usarGrifo(1);
				usarGrifo(2);
				break;
			}
			System.out.println("Bebida terminada por hilo: " + Thread.currentThread());
			try {
				sleep((int) (Math.random() * 2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized public void usarGrifo(int grifo_a_usar) {
		while (!listaGrifos.get(grifo_a_usar).getDisponible()) {
			try {
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		synchronized (listaGrifos.get(grifo_a_usar)) {
			listaGrifos.get(grifo_a_usar).setDisponible(false);
			System.out.println(
					"Hilo: " + Thread.currentThread() + " usando grifo " + listaGrifos.get(grifo_a_usar).getBebida());
			try {
				sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			listaGrifos.get(grifo_a_usar).setDisponible(true);
			System.out.println(
					"Hilo: " + Thread.currentThread() + " libera grifo " + listaGrifos.get(grifo_a_usar).getBebida());
		}
		notifyAll();
	}

	public static ArrayList<Grifo> getListaGrifos() {
		return listaGrifos;
	}

	public static void setListaGrifos(ArrayList<Grifo> listaGrifos) {
		Camarero.listaGrifos = listaGrifos;
	}

	public Integer getIdCamarero() {
		return idCamarero;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

}
