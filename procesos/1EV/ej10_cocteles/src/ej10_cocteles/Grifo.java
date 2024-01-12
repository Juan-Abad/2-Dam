package ej10_cocteles;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Grifo {// clase grifo
	private String bebida;// nombre de la bebida del grifo
	private Boolean disponible = true;// variable boolean que indica el estado del grifo, si esta en uso por un
										// camarero o no

	public Grifo(String bebida) {// constructor
		this.bebida = bebida;
	}

	public String getBebida() {
		return bebida;
	}

	public void setBebida(String bebida) {
		this.bebida = bebida;
	}

	synchronized public Boolean getDisponible() {
		return disponible;
	}

	synchronized public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

}
