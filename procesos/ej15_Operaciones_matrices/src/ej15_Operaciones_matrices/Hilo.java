package ej15_Operaciones_matrices;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Hilo extends Thread {// Clase Hilo, extiende de Thread
	private static Matrices matrices;// variable de la clase Matrices matrices, contendra un ainstancia de la clase
										// Matrices, se le añade la instancia en el main, directamente al ser estaticaa
										// todos los hilos tienen acceso.
	private Integer idHilo;
	private static Integer siguienteID = 1;

	public Hilo() {
		this.idHilo = siguienteID;
		siguienteID++;
	}

	@Override
	public void run() {// metodo que calcula el tiempo que tarda en crear la matriz_resultado
		long tiempoInicio = System.currentTimeMillis();// se marca el tiempo inicial en el que se empieza a ejecutar el
														// hilo
		while (true) {// no para de ejecutarse hasta que termine de crear la matriz
			if (idHilo == 1) {// el hilo con id 1 crea la mitadd primera de la matriz resultado, de 0 a la
								// mitad
				for (int i = 0; i < Hilo.getMatrices().getMatriz1().size() / 2; i++) {// crea la mitadd primera de la
																						// matriz resultado, de 0 a la
																						// mitad
					Hilo.getMatrices().getMatriz_resultado()[i] = Hilo.getMatrices().getMatriz1().get(i)
							* Hilo.getMatrices().getMatriz2().get(i);// se multiplica de la pos i de cada matriz, matriz
																		// 1 y 2 los valores Integer, y se añaade el
																		// resultado a la matriz resultado
				}
				break;
			} else if (idHilo == 2) {// el hilo con id 2 crea la mitadd primera de la matriz resultado, de mitad al
										// final
				for (int i = Hilo.getMatrices().getMatriz1().size() / 2; i < Hilo.getMatrices().getMatriz1()
						.size(); i++) {// crea la mitadd primera de la matriz resultado, de mitad al final
					Hilo.getMatrices().getMatriz_resultado()[i] = Hilo.getMatrices().getMatriz1().get(i)
							* Hilo.getMatrices().getMatriz2().get(i);// se multiplica de la pos i de cada matriz, matriz
																		// 1 y 2 los valores Integer, y se añaade el
																		// resultado a la matriz resultado
				}
				break;
			}
		}
		if (idHilo == 1) {// el hilo 1 imprime el resultado
			for (int i = 0; i < Hilo.getMatrices().getMatriz_resultado().length; i++) {
				System.out.println(i + 1 + " --> " + Hilo.getMatrices().getMatriz1().get(i) + " "
						+ Hilo.getMatrices().getMatriz2().get(i) + " " + Hilo.getMatrices().getMatriz_resultado()[i]);
			}
			long tiempoFin = System.currentTimeMillis();// se toma el tiempo actual

			long tiempoTranscurrido = tiempoFin - tiempoInicio;// se calcula el tiempo que ha tardado el programa en
																// creaar la matriz
			System.out.println("El hilo ha tardado " + tiempoTranscurrido + " milisegundos en realizar la operación.");

		}
	}

	public Integer getIdHilo() {
		return idHilo;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

	public static Matrices getMatrices() {
		return matrices;
	}

	public static void setMatrices(Matrices matrices) {
		Hilo.matrices = matrices;
	}

}
