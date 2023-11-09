package ej06_Acuario;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		final int aforo = 50;
		Semaphore e = new Semaphore(aforo);
		int[] famHilo = new int[30];
		Visitante[] visitantHilo = new Visitante[30];

		for (int i = 0; i < famHilo.length; i++) {
			famHilo[i] = ((int) (Math.random()*5+1));
			visitantHilo[i] = new Visitante(("Familia "+i),famHilo[i], e);
		}
		for(Visitante v : visitantHilo) {
			v.start();
		}
	}

}
