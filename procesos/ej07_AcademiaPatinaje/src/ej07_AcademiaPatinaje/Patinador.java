package ej07_AcademiaPatinaje;

public class Patinador extends Thread {
	private Almacen almacen;
	private Boolean principiante;

	private Integer numeroPie;

	private Integer idPatinador;
	private static Integer siguienteID = 1;

	public Patinador(Boolean principiante, Almacen almacen) {//contructor del patinador, recibe si el patinador es principiante, y el almacen
		this.idPatinador = siguienteID;
		siguienteID++;
		this.principiante = principiante;
		this.almacen = almacen;
		this.numeroPie = ((int) (Math.random() * 10 + 34));//generamos aleatoriamente el numero de pie del patinador
	}

	@Override
	public void run() {//el patinador retirara patin y almacenara el patin continuamente
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
