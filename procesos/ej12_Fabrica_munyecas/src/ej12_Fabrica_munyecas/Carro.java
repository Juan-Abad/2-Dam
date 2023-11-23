package ej12_Fabrica_munyecas;

public class Carro extends Thread {
	private Integer numero_munyecas = 20;
	private final Integer MAX_MUNYECAS_EN_CARRO = 20;

	public void run() {
		while (true) {
			while (this.getNumero_munyecas() >= this.getMAX_MUNYECAS_EN_CARRO()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int espacio_disponible = this.getMAX_MUNYECAS_EN_CARRO() - this.getNumero_munyecas();
			int munyecasAAgregar = (int) (Math.random() * Math.min(5, espacio_disponible)) + 1;
			System.out.println("Carro con " + numero_munyecas + " muñecas disponibles, se le depositan "
					+ munyecasAAgregar + " muñecas");
			setNumero_munyecas(this.getNumero_munyecas() + munyecasAAgregar);
			notifyAll();
		}
	}

	public synchronized void cogerDelCarro() {
		while (numero_munyecas == 0) {
			try {
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		numero_munyecas -= 1;
		System.out.println("Trabajador " + Thread.currentThread() + " coge muñeca, quedan " + numero_munyecas
				+ " muñecas en el carro");
		notifyAll();
	}

	synchronized public Integer getNumero_munyecas() {
		return numero_munyecas;
	}

	synchronized public void setNumero_munyecas(Integer numero_munyecas) {
		this.numero_munyecas = numero_munyecas;
	}

	public Integer getMAX_MUNYECAS_EN_CARRO() {
		return MAX_MUNYECAS_EN_CARRO;
	}

}
