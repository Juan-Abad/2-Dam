package ej_04_semaforo;

public class Comprador extends Thread {
	private Integer num_entradas = 0;
	private Concierto concierto;

	public Comprador(Concierto concierto) {
		this.concierto = concierto;
	}

	@Override
	public void run() {
		while (true) {
			int numero_entradas = (int) ((Math.random() * 5) + 1);
			concierto.comprar_entradas(numero_entradas, this);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int numero_entradas_vender = (int) (Math.random() * num_entradas - 1);
			concierto.vender_entradas(numero_entradas_vender, this);
		}
	}

	public Integer getNum_entradas() {
		return num_entradas;
	}

	public void setNum_entradas(Integer num_entradas) {
		this.num_entradas = num_entradas;
	}

	public Concierto getConcierto() {
		return concierto;
	}
}
