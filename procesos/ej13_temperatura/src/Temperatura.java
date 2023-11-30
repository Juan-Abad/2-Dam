/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */

public class Temperatura {// claase Temperatura

	public static void main(String[] args) {// main
		int temperatura = 0;// variable temperatura, almacena la temperatura de cada ciudad

		if (args.length != 1) {// si laa longitud de los argumentos es diferente a uno muestra ERROR
			System.out.println("ERROR");
		} else {
			temperatura = (int) ((Math.random() * 50) - 10);// se crea aleatoriamente una temperatura para la ciudad
		}
		try {
			Thread.sleep(500);// tiempo de espera
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(temperatura);// se imprime la temperatura
	}

}
