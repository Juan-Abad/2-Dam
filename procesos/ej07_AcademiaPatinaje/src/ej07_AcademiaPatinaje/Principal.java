package ej07_AcademiaPatinaje;

public class Principal {

	public static void main(String[] args) {
		Almacen almacen = new Almacen();
		Patinador patinador;
		// Creamos los patinadores
		for (int i = 0; i < 50; i++) {
			if (((int) (Math.random() * 6)) == 1) {
				patinador = new Patinador(true, almacen);
			} else {
				patinador = new Patinador(false, almacen);
			}
			patinador.start();
		}
	}
}
