package ej10_cocteles;

public class Grifo {
	private String bebida;
	private Boolean disponible = true;

	public Grifo(String bebida) {
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
