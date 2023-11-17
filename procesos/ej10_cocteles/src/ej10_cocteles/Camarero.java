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

	synchronized public void usarGrifo() {

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
