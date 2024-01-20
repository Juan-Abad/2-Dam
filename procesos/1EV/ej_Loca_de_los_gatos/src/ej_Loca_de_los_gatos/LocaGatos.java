package ej_Loca_de_los_gatos;

public class LocaGatos extends Thread {
	private CuencoComida cuenco;

	public LocaGatos(CuencoComida cuenco) {
		this.cuenco = cuenco;
	}

	public void run() {
		while (true) {
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cuenco.relleno((int)((Math.random()*20)+1));
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
