package ej15_Operaciones_matrices;

public class Hilo extends Thread {
	private static Matrices matrices;
	private Integer idHilo;
	private static Integer siguienteID = 1;

	public Hilo() {
		this.idHilo = siguienteID;
		siguienteID++;
	}

	@Override
	public void run() {
		long tiempoInicio = System.currentTimeMillis();
		while (true) {
			if (idHilo == 1) {
				for (int i = 0; i < Hilo.getMatrices().getMatriz1().size() / 2; i++) {
					Hilo.getMatrices().getMatriz_resultado()[i] = Hilo.getMatrices().getMatriz1().get(i)
							* Hilo.getMatrices().getMatriz2().get(i);
				}
				break;
			} else if (idHilo == 2) {
				for (int i = Hilo.getMatrices().getMatriz1().size() / 2; i < Hilo.getMatrices().getMatriz1()
						.size(); i++) {
					Hilo.getMatrices().getMatriz_resultado()[i] = Hilo.getMatrices().getMatriz1().get(i)
							* Hilo.getMatrices().getMatriz2().get(i);
				}
				break;
			}
		}
		if (idHilo == 1) {
			for (int i = 0; i < Hilo.getMatrices().getMatriz_resultado().length; i++) {
				System.out.println(i + 1 + " --> " + Hilo.getMatrices().getMatriz1().get(i) + " "
						+ Hilo.getMatrices().getMatriz2().get(i) + " " + Hilo.getMatrices().getMatriz_resultado()[i]);
			}
			long tiempoFin = System.currentTimeMillis();

			long tiempoTranscurrido = tiempoFin - tiempoInicio;
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
