package ej16_Agentes_codificacion;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Principal {

	public static void main(String[] args) {
		String[] nombres = { "juan", "tom", "eva", "jaime", "lucas", "maria", "ivan", "laura", "ramon", "sonia",
				"andrea", "isabel", "daniel", "ana" };
		Nombre nombre = new Nombre();
		Ruso ruso;
		Americano americano;

		for (int i = 0; i < nombres.length; i++) {
			ruso = new Ruso(nombres[i], nombre);
			ruso.start();
		}
		for (int i = 0; i < 1; i++) {
			americano = new Americano(nombre);
			americano.start();
		}
	}
}