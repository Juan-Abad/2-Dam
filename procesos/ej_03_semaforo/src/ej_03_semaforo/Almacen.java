package ej_03_semaforo;

import java.util.ArrayList;
import java.util.TreeMap;

public class Almacen {
	TreeMap<Integer, ArrayList<Patin>> mapa;

	public Almacen(TreeMap<Integer, ArrayList<Patin>> mapa) {
		this.mapa = mapa;
	}

	synchronized public int coger_patin(int numero, boolean principiante, TreeMap<Integer, ArrayList<Patin>> mapa) {
		int par = -1;
		if (principiante) {
			while (mapa.get(numero).get(0).getPatin_dch().availablePermits() < 1
					&& mapa.get(numero).get(1).getPatin_dch().availablePermits() < 1) {
				try {
					System.out.println(
							"Alumno " + Thread.currentThread() + " esperando, principiante y numero " + numero);
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (mapa.get(numero).get(0).getPatin_dch().availablePermits() >= 1) {
				par = 0;
			} else if (mapa.get(numero).get(1).getPatin_dch().availablePermits() >= 1) {
				par = 1;
			}
			try {
				System.out.println("Alumno " + Thread.currentThread() + " coge, principiante y numero " + numero);
				mapa.get(numero).get(par).getPatin_dch().acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			while ((mapa.get(numero).get(0).getPatin_dch().availablePermits() < 1
					|| mapa.get(numero).get(0).getPatin_izq().availablePermits() < 1)
					&& (mapa.get(numero).get(1).getPatin_dch().availablePermits() < 1
							|| mapa.get(numero).get(1).getPatin_izq().availablePermits() < 1)) {
				try {
					System.out.println("Alumno " + Thread.currentThread() + " esperando y numero " + numero);
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (mapa.get(numero).get(0).getPatin_dch().availablePermits() >= 1
					&& mapa.get(numero).get(0).getPatin_izq().availablePermits() >= 1) {
				par = 0;
			} else if (mapa.get(numero).get(1).getPatin_dch().availablePermits() >= 1
					&& mapa.get(numero).get(1).getPatin_izq().availablePermits() >= 1) {
				par = 1;
			}
			try {
				System.out.println("Alumno " + Thread.currentThread() + " coge, no principiante y numero " + numero);
				mapa.get(numero).get(par).getPatin_dch().acquire();
				mapa.get(numero).get(par).getPatin_izq().acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return par;
	}

	synchronized public void dejar_patin(int numero, boolean principiante, TreeMap<Integer, ArrayList<Patin>> mapa,
			int par) {
		if (principiante) {
			System.out.println("Alumno " + Thread.currentThread() + ", deja patin, principiaante, numero " + numero);
			mapa.get(numero).get(par).getPatin_dch().release();
		} else {
			System.out
					.println("Alumno " + Thread.currentThread() + ", deja patines, no principiante, numero " + numero);
			mapa.get(numero).get(par).getPatin_dch().release();
			mapa.get(numero).get(par).getPatin_izq().release();
		}
		notifyAll();
	}
}
