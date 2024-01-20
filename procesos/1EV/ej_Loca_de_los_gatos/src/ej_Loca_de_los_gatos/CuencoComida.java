package ej_Loca_de_los_gatos;

public class CuencoComida {
	private Integer saldo = 20;

	synchronized public void como(int cantidad, Gatito gato) {
		while(getSaldo()<cantidad) {
			try {
				System.out.println("gato "+gato.getIdGatito()+" quiere comer: "+cantidad);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setSaldo(getSaldo() - cantidad);
		System.out.println("gato "+gato.getIdGatito()+" se come "+cantidad+" quedan: "+getSaldo());
		notifyAll();
	}

	synchronized public void relleno(int cantidad) {
		saldo=(saldo + cantidad);
		System.out.println("La señora de los gatos hecha: "+cantidad+" , total galletitas en cuenco: "+getSaldo());
		notifyAll();
	}

	synchronized public Integer getSaldo() {
		return saldo;
	}

	synchronized public void setSaldo(Integer saldo) {
		if (saldo < 0) {
			this.saldo = 0;
		} else {
			this.saldo = saldo;
		}
		
	}

}
