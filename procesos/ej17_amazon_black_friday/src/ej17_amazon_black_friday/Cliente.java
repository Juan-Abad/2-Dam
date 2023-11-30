package ej17_amazon_black_friday;

public class Cliente extends Thread {
	private Amazon amazon;
	private Integer idCliente;
	private static Integer siguienteID = 1;

	public Cliente(Amazon amazon) {
		this.idCliente = siguienteID;
		siguienteID++;
		this.amazon = amazon;
	}

	public void run() {
		try {
			sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (amazon) {
			if (amazon.comprar_movil() != -1) {
				System.out.println("Compra: " + idCliente + ".-");
			} else {
				System.out.println(idCliente + ".- no compra");
			}
		}
	}
}
