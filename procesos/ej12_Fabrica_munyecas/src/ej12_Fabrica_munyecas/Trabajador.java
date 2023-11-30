package ej12_Fabrica_munyecas;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Trabajador extends Thread {// Clase trabajador, extiende de Thread
	private Carro carro;
	private Integer idTrabajador;
	private static Integer idSiguiente = 1;

	public Trabajador(Carro carro) {// conructor de la clase
		this.idTrabajador = idSiguiente;
		idSiguiente++;
		this.carro = carro;
	}

	public void run() {// metodo del hilo, se ejecuta constantemente, coge muñeacas del carro y las
						// embala
		while (true) {
			carro.cogerDelCarro();// coge muñeca del carro
			try {
				sleep((int) (Math.random() * 1000));// tiempo que tarda en embalar una muñeca
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Trabajador " + this.getIdTrabajador() + " ha embalado una muñeca");
		}
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Integer getIdTrabajador() {
		return idTrabajador;
	}

	public static Integer getIdSiguiente() {
		return idSiguiente;
	}
}
