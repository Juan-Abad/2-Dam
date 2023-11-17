package ej09_VentaEntradas;

import java.util.ArrayList;

public class Entrada {
	private static ArrayList<Entrada> listaEntradas = new ArrayList<Entrada>();

	private static Integer numeroEntradasDisponibles;
	private Integer idEntrada;
	private static Integer siguienteID = 1;

	private Boolean isAvailable = true;

	public Entrada() {
        this.idEntrada = siguienteID++;
    }

    public static void inicializarEntradas(int numeroMaxEntradas) {
        numeroEntradasDisponibles = numeroMaxEntradas;
        for (int i = 0; i < numeroMaxEntradas-1; i++) {
            listaEntradas.add(new Entrada());
        }
    }

	synchronized public static Integer getNumeroEntradasDisponibles() {
		return numeroEntradasDisponibles;
	}

	synchronized public static void setNumeroEntradasDisponibles(Integer numeroEntradasDisponibles) {
		Entrada.numeroEntradasDisponibles = numeroEntradasDisponibles;
	}

	synchronized public Integer getIdEntrada() {
		return idEntrada;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

	synchronized public void setIsAvailable(Boolean isAvailable) {
		if (isAvailable != null) {
			this.isAvailable = isAvailable;
		}
	}

	synchronized public Boolean getIsAvailable() {
		return isAvailable;
	}

	synchronized public static ArrayList<Entrada> getListaEntradas() {
		return listaEntradas;
	}

}
