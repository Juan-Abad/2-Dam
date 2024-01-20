package ej17_amazon_black_friday;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Cliente extends Thread {// Clase Cliente, extiende de Thread
	private Amazon amazon;// objeto de la clase Amazon, valor inicial null
	private Integer idCliente;
	private static Integer siguienteID = 1;

	public Cliente(Amazon amazon) {// contructor de clase, recibe por parametros el objeto de la clase Amazon
		this.idCliente = siguienteID;
		siguienteID++;
		this.amazon = amazon;
	}

	public void run() {// metodo run del hilo
		try {
			sleep((int) (Math.random() * 1000));// se duerme el hilo entre 0 y 999 milisegundos
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (amazon) {// se sincroniza el objeto amazon
			if (amazon.comprar_movil() != -1) {// si hay moviles disponible lo compraria, ylo imprime, si no imprime que
												// no hay compra, el metodo comprar_movil devuelve -1 si no se ha
												// comprado movil y otro valor en caso de haber comprado un movil
				System.out.println("Compra: " + idCliente + ".-");
			} else {
				System.out.println(idCliente + ".- no compra");
			}
		}
	}
}
