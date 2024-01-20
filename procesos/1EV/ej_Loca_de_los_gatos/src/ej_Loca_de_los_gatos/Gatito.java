package ej_Loca_de_los_gatos;

public class Gatito extends Thread {
	private Integer idGatito;
	private static Integer siguienteID = 1;
	private CuencoComida cuenco;

	public Gatito(CuencoComida cuenco) {
		idGatito = siguienteID;
		siguienteID++;
		this.cuenco = cuenco;
	}

	public void run() {
		while (true) {
			cuenco.como(((int)((Math.random()*15)+1)), this);
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void dormir() {
		try {
			System.out.println("gato " + idGatito + " se va a dormir porque no tiene comida suficiente");
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public CuencoComida getCuenco() {
		return cuenco;
	}

	public void setCuenco(CuencoComida cuenco) {
		this.cuenco = cuenco;
	}

	public Integer getIdGatito() {
		return idGatito;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}
	
}
