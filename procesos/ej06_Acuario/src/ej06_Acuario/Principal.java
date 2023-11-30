package ej06_Acuario;
/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {//main
		final int aforo = 50;//variable que guarada el aforo maximo del acuario
		Semaphore e = new Semaphore(aforo);//creamos un semaforo, con limite para 50
		int[] famHilo = new int[30];
		Visitante[] visitantHilo = new Visitante[30];

		for (int i = 0; i < famHilo.length; i++) {//creamos los hilos, pasandoles el numero de familiares de forma aleatoria y el semaforo
			famHilo[i] = ((int) (Math.random()*5+1));
			visitantHilo[i] = new Visitante(("Familia "+i),famHilo[i], e);
		}
		for(Visitante v : visitantHilo) {
			v.start();
		}
	}

}
