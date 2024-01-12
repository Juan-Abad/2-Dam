package ej_03_semaforo;

import java.util.ArrayList;
import java.util.TreeMap;

public class Principal {

	public static void main(String[] args) {
		TreeMap<Integer, ArrayList<Patin>> mapa= new TreeMap<Integer, ArrayList<Patin>>();
		Alumno alumno;
		for(int i=34; i<=43; i++) {
			mapa.put(i, new ArrayList<Patin>());
			mapa.get(i).add(new Patin(i, 1));
			mapa.get(i).add(new Patin(i, 2));
		}
		Almacen almacen = new Almacen(mapa);
		/*for(Integer i: mapa.keySet()) {
			System.out.println("numero: "+i+", par: 0, patin derecho "+mapa.get(i).get(0).getPatin_dch().availablePermits()+", patin izq: "+mapa.get(i).get(0).getPatin_izq().availablePermits());
			System.out.println("numero: "+i+", par: 1, patin derecho "+mapa.get(i).get(1).getPatin_dch().availablePermits()+", patin izq: "+mapa.get(i).get(1).getPatin_izq().availablePermits());
		}*/
		
		for(int i=0; i<50; i++) {
			alumno = new Alumno(mapa, almacen);
			alumno.start();
		}
	}

}
