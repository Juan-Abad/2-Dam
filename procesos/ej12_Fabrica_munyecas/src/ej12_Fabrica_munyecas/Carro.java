package ej12_Fabrica_munyecas;

public class Carro {
	private Integer numero_munyecas = 0;
	private final Integer MAX_MUNYECAS_EN_CARRO = 20;

	public synchronized void cogerDelCarro() {
		while (numero_munyecas == 0) {
			try {
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		numero_munyecas = numero_munyecas - 1;
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
