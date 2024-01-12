package ej_Loca_de_los_gatos;

public class CasaDeLosGatos {

	public static void main(String[] args) {
		CuencoComida cuenco = new CuencoComida();
		LocaGatos loca = new LocaGatos(cuenco);
		loca.start();
		Gatito gato;
		for(int i=0; i<22; i++) {
			gato = new Gatito(cuenco);
			gato.start();
		}
	}

}
