package ej15_Operaciones_matrices;

public class Principal {

	public static void main(String[] args) {
		Matrices matrices = new Matrices();
		Hilo hilo;
		Hilo.setMatrices(matrices);;
		for (int i = 0; i < 2; i++) {
			hilo = new Hilo();
			hilo.start();
		}
	}

}
