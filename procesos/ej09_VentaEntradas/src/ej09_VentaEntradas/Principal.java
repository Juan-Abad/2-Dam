package ej09_VentaEntradas;

public class Principal {

	public static void main(String[] args) {
		int numMaxEntradas = 200;
		Comprador comprador;
		Entrada entrada = new Entrada();
		entrada.inicializarEntradas(numMaxEntradas);
		for(int i=0; i<10;i++) {
			comprador = new Comprador();
			comprador.start();
		}
	}

}
