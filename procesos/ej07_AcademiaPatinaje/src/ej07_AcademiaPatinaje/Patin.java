package ej07_AcademiaPatinaje;

public class Patin {

	private Integer idPatin = 0;
	private static Integer idSiguiente = 1;

	private Boolean patinDerecho = true;
	private Boolean patinIzquierdo = true;

	private boolean parCompleto = true;

	private Integer numeroPie;

	public Patin(Integer numPie) {
		idPatin = idSiguiente;
		idSiguiente++;
		this.numeroPie = numPie;
	}

	public Boolean getPatinDerecho() {
		return patinDerecho;
	}

	public void setPatinDerecho(Boolean patinDerecho) {
		if (patinDerecho != null)
			this.patinDerecho = patinDerecho;
		if (this.patinDerecho == false)
			parCompleto = false;
	}

	public Boolean getPatinIzquierdo() {
		return patinIzquierdo;
	}

	public void setPatinIzquierdo(Boolean patinIzquierdo) {
		if (patinIzquierdo != null)
			this.patinIzquierdo = patinIzquierdo;
		if (this.patinIzquierdo == false)
			parCompleto = false;
	}

	public Integer getNumeroPie() {
		return numeroPie;
	}

	public void setNumeroPie(Integer numeroPie) {
		this.numeroPie = numeroPie;
	}

	public boolean isParCompleto() {
		return parCompleto;
	}
}
