package ej07_AcademiaPatinaje;

import java.util.ArrayList;
import java.util.TreeMap;

public class Almacen {
	private TreeMap<Integer, ArrayList<Patin>> mapaPatines;

	public Almacen() {
		mapaPatines = new TreeMap<>();
		for (int i = 34; i < 44; i++) {
			ArrayList<Patin> listaParesPatin = new ArrayList<>();
			for (int j = 0; j < 2; j++) {
				// Aquí puedes pasar un valor específico al constructor de Patin
				Patin patin = new Patin(i);
				listaParesPatin.add(patin);
			}
			mapaPatines.put(i, listaParesPatin);
		}
	}

	synchronized public void almacenarPatin(Integer numeroPie, Boolean pieDerecho) {
		if (mapaPatines.containsKey(numeroPie)) {
			for (Patin patin : mapaPatines.get(numeroPie)) {
				if (patin.isParCompleto() == false) {
					if (pieDerecho != null) {
						if (pieDerecho == true && patin.getPatinDerecho() == false) {
							patin.setPatinDerecho(true);
						} else if (pieDerecho == false && patin.getPatinIzquierdo() == false) {
							patin.setPatinIzquierdo(true);
						}
					} else if (patin.getPatinDerecho() == false && patin.getPatinIzquierdo() == false) {
						patin.setPatinDerecho(true);
						patin.setPatinIzquierdo(true);
					}
				}
				System.out.println(patin.toString());
			}
		} else {
			System.out.println("Número de pie no exitente");
		}
	}

	synchronized public void retirarPatin(Integer numeroPie, Boolean pieDerecho) {
		if (mapaPatines.containsKey(numeroPie)) {
			for (Patin patin : mapaPatines.get(numeroPie)) {
				if (patin.isParCompleto() == false) {
					if (pieDerecho != null) {
						if (pieDerecho == true && patin.getPatinDerecho() == true) {
							patin.setPatinDerecho(false);
						} else if (pieDerecho == false && patin.getPatinIzquierdo() == true) {
							patin.setPatinIzquierdo(false);
						}
					} else if (patin.getPatinDerecho() == true && patin.getPatinIzquierdo() == true) {
						patin.setPatinDerecho(false);
						patin.setPatinIzquierdo(false);
					}
				}
				System.out.println(patin.toString());
			}
		} else {
			System.out.println("Número de pie no exitente");
		}
	}
}
