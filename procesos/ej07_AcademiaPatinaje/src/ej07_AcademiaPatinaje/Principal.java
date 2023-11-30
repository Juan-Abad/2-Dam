package ej07_AcademiaPatinaje;

public class Principal {

	public static void main(String[] args) {
		Almacen almacen = new Almacen();//creamos el almacen
		Patinador patinador;
		// Creamos los patinadores
		for (int i = 0; i < 50; i++) {//for para crear patinadores, que aleatoriamente sean principiante o no
			if (((int) (Math.random() * 6)) == 1) {
				patinador = new Patinador(true, almacen);
			} else {
				patinador = new Patinador(false, almacen);
			}
			patinador.start();//ponemos a correr el hilo
		}
	}
}
