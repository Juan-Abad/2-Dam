package ej_03_semaforo;

import java.util.ArrayList;
import java.util.TreeMap;

public class Alumno extends Thread {
	TreeMap<Integer, ArrayList<Patin>> mapa;
	private Almacen almacen;
	private boolean principiante = false;
	private int numero;
	private int par;

	public Alumno(TreeMap<Integer, ArrayList<Patin>> mapa, Almacen almacen) {
		this.mapa = mapa;
		this.almacen = almacen;
		if (((int) (Math.random() * 3)) == 0)
			principiante = true;
		this.numero = ((int) (Math.random() * 10 + 34));
	}

	public void run() {
		par = almacen.coger_patin(numero, principiante, mapa);
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		almacen.dejar_patin(numero, principiante, mapa, par);
	}
}
