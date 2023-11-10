package ej07_AcademiaPatinaje;

public class Patinadores {
	private Boolean principiante;

	private Integer numeroPie;

	public Patinadores(Boolean principiante) {
		this.principiante = principiante;
		this.numeroPie = ((int) (Math.random()*+34));
	}

}
