package ej15_Operaciones_matrices;

import java.util.ArrayList;

public class Matrices {
	private ArrayList<Integer> matriz1 = new ArrayList<Integer>();
	private ArrayList<Integer> matriz2 = new ArrayList<Integer>();
	private Integer[] matriz_resultado = new Integer[200000];

	public Matrices() {
		for (int i = 0; i < 200000; i++) {
			matriz1.add(((int) (Math.random() * 5000) + 1));
			matriz2.add(((int) (Math.random() * 5000) + 1));
		}
		System.out.println("Matrices completas");
	}

	synchronized public Integer[] getMatriz_resultado() {
		return matriz_resultado;
	}

	synchronized public void setMatriz_resultado(Integer[] matriz_resultado) {
		this.matriz_resultado = matriz_resultado;
	}

	public ArrayList<Integer> getMatriz1() {
		return matriz1;
	}

	public ArrayList<Integer> getMatriz2() {
		return matriz2;
	}
}
