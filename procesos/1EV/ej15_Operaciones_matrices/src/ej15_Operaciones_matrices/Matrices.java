package ej15_Operaciones_matrices;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
import java.util.ArrayList;

public class Matrices {// Clase Matrices
	private ArrayList<Integer> matriz1 = new ArrayList<Integer>();// ArrayList matriz1, contiene una matriz, la cual
																	// contiene Integer
	private ArrayList<Integer> matriz2 = new ArrayList<Integer>();// ArrayList matriz2, contiene una matriz, la cual
																	// contiene Integer
	private Integer[] matriz_resultado = new Integer[20000];// Array matriz_resultad, contiene una matriz, la cual
															// contiene 200000 Integer, es el resultado de la
															// multilicacion entre las matrices matriz1 y maatriz2

	public Matrices() {// contructor de la clase Matrices
		for (int i = 0; i < 20000; i++) {// for que añade a las matrices 1 y 2 numeros aleatorios entre 1 y 5000, se
											// crean 20000 numeros para cada matriz
			matriz1.add(((int) (Math.random() * 5000) + 1));
			matriz2.add(((int) (Math.random() * 5000) + 1));
		}
		System.out.println("Matrices completas");
	}

	synchronized public Integer[] getMatriz_resultado() {// metodo synchronized para obtener la matriz_resultado
		return matriz_resultado;
	}

	synchronized public void setMatriz_resultado(Integer[] matriz_resultado) {// metodo synchronized para cambiar la
																				// matriz_resultado
		this.matriz_resultado = matriz_resultado;
	}

	public ArrayList<Integer> getMatriz1() {
		return matriz1;
	}

	public ArrayList<Integer> getMatriz2() {
		return matriz2;
	}
}
