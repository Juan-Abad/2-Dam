package ej05_peluqueria;

public class Principal {

	public static void main(String[] args) {
		Peluqueria peluqueria = new Peluqueria();
		Peluquero peluquero;
		Cliente cliente;
		for (int i = 0; i < 4; i++) {
			peluquero=new Peluquero();
			peluquero.start();
		}
		for (int i = 0; i < 4; i++) {
			cliente =new Cliente(peluqueria);
		}
	}

}
