package ej12_Fabrica_munyecas;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Carro extends Thread {
	private Integer numero_munyecas = 20;// variable que guarda el numero de muñecas actual en el carro, inicialmente 20
	private final Integer MAX_MUNYECAS_EN_CARRO = 20;// valor que guarda el numero maximo de muñecas que puede haber en
														// el carro

	public void run() {// metodo run del hilo Carro
		while (true) {// no para de ejecutarse, ya que el carro cada vez que hay menos de 20 muñecas
						// debe reponerse
			try {
				sleep(2000);// el hilo se duerme por que es el tiempo que tarda en reponerse el carro
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (numero_munyecas < MAX_MUNYECAS_EN_CARRO) {// comprueba si el numero de muñecas que hay en el carro es
															// menor que el maximo de muñecas permitidas en el carro
				int espacio_disponible = this.getMAX_MUNYECAS_EN_CARRO() - this.getNumero_munyecas();// variable que
																										// guarda el
																										// numero de
																										// muñecas que
																										// faltan para
																										// llegar al
																										// maximo
				int munyecasAAgregar = (int) (Math.random() * Math.min(5, espacio_disponible)) + 1;// se calcula un
																									// numero aleatorio
																									// de muñecas para
																									// agregar al carro,
																									// va entre 0 y el
																									// numero de muñecas
																									// que faltan, con
																									// maximo de 5
				System.out.println("Carro con " + numero_munyecas + " muñecas disponibles, se le depositan "
						+ munyecasAAgregar + " muñecas");
				setNumero_munyecas(this.getNumero_munyecas() + munyecasAAgregar);// se añadden las muñecas al carro
			}
		}
	}

	public synchronized void cogerDelCarro() {// metodo synchronized que coge del carro una sola muñeca
		while (numero_munyecas == 0) {// se comprueba si ha muñecas para retirar, si no el hilo entra y espera
			try {
				System.out
						.println("Trabajador " + Thread.currentThread().getId() + " está esperando nuevas muñecas...");
				wait();// el hilo espera ha ser notificado dde que hay muñecas disponibles
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		numero_munyecas -= 1;// se resta del numero de muñecas del carro una
		System.out.println("Trabajador " + Thread.currentThread().getId() + " coge muñeca, quedan " + numero_munyecas
				+ " muñecas en el carro");
		notify();// notifica que se han cogido muñecas del carro.
	}

	synchronized public Integer getNumero_munyecas() {// metodo synchronized para obtener el numero actual de muñecas en
														// el carro
		return numero_munyecas;
	}

	synchronized public void setNumero_munyecas(Integer numero_munyecas) {// es un metodo sincronizado para que solo
																			// pueda ser accedido por un hilo
		this.numero_munyecas = numero_munyecas;
		notifyAll();// se notifica que hay un cambio en el numero de muñecas
	}

	public Integer getMAX_MUNYECAS_EN_CARRO() {
		return MAX_MUNYECAS_EN_CARRO;
	}

}
