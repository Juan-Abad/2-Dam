package ej10_cocteles;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
import java.util.ArrayList;

public class Camarero extends Thread {
	private static ArrayList<Grifo> listaGrifos = new ArrayList<Grifo>();// lista statica, guarda las instancias de los
																			// grifos, todos los Camareros tienen acceso
																			// a los grifos

	private Integer idCamarero;
	private static Integer siguienteID = 1;

	public Camarero() {
		this.idCamarero = siguienteID;
		siguienteID++;
	}

	public void run() {// en este metodo los camareros estaran continuamente de forma aleatoria
						// realizando un coctel u otro dependiendo del vaalor aleatorio del switch
		while (true) {
			switch (((int) (Math.random() * 3))) {
			case 0:// es el coctel de soda y tabasco
				usarGrifo(0);
				usarGrifo(2);
				break;
			case 1:// es el coctel de soda y zumo
				usarGrifo(0);
				usarGrifo(1);
				break;
			case 2:// es el coctel de soda, zumo, tabasco
				usarGrifo(0);
				usarGrifo(1);
				usarGrifo(2);
				break;
			}
			System.out.println("Bebida terminada por hilo: " + idCamarero);
			try {
				sleep((int) (Math.random() * 2000));// el camarero espera a volver a hacer un coctel
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized public void usarGrifo(int grifo_a_usar) {// metodo usar grifo, recibe por argumentos el grifo que
															// solicita usar el camarero, metodo synchronized
		synchronized (listaGrifos.get(grifo_a_usar)) {// se sincroniza el grifo que solicita el camarero
			listaGrifos.get(grifo_a_usar).setDisponible(false);// cambia el valor de disponibilidad del grifo, y auqe
																// los esta usando
			System.out.println("Hilo: " + idCamarero + " usando grifo " + listaGrifos.get(grifo_a_usar).getBebida());
			try {
				sleep((int) (Math.random() * 1000));// el camarero usa el grifo ddurante un tiempo aleatorio
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			listaGrifos.get(grifo_a_usar).setDisponible(true);// despues de usarlo, se cambia el staddo del grifo a
																// disponible
			System.out.println("Hilo: " + idCamarero + " libera grifo " + listaGrifos.get(grifo_a_usar).getBebida());
		}
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
