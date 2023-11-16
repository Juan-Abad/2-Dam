package ej07_AcademiaPatinaje;

public class Patinador extends Thread {
	private Almacen almacen;
	private Boolean principiante;

	private Integer numeroPie;

	private Integer idPatinador;
	private static Integer siguienteID = 1;

	public Patinador(Boolean principiante, Almacen almacen) {
		this.idPatinador = siguienteID;
		siguienteID++;
		this.principiante = principiante;
		this.almacen = almacen;
		this.numeroPie = ((int) (Math.random() * 10 + 34));
	}

	@Override
	public void run() {
		while (true) {
			almacen.retirarPatin(numeroPie, principiante);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			almacen.almacenarPatin(numeroPie, principiante);
		}
	}

}
