package ej09_VentaEntradas;

import java.util.ArrayList;

public class Entrada {
	private static ArrayList<Entrada> listaEntradas = new ArrayList<Entrada>();

	private static Integer numeroEntradasDisponibles;
	private Integer idEntrada;
	private static Integer siguienteID = 1;

	private Boolean isAvailable = true;

	public Entrada(int numeroMaxEntradas) {
		this.idEntrada = siguienteID;
		siguienteID++;
		Entrada.numeroEntradasDisponibles = numeroMaxEntradas;
		if (listaEntradas.size() == 0) {
			listaEntradas.add(this);
			for (int i = 0; i < numeroMaxEntradas - 1; i++) {
				listaEntradas.add(new Entrada(numeroMaxEntradas));
			}
		}
	}

	public static Integer getNumeroEntradasDisponibles() {
		return numeroEntradasDisponibles;
	}

	public static void setNumeroEntradasDisponibles(Integer numeroEntradasDisponibles) {
		Entrada.numeroEntradasDisponibles = numeroEntradasDisponibles;
	}

	public Integer getIdEntrada() {
		return idEntrada;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

	public void setIsAvailable(Boolean isAvailable) {
		if (isAvailable != null) {
			this.isAvailable = isAvailable;
		}
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public static ArrayList<Entrada> getListaEntradas() {
		return listaEntradas;
	}

}
