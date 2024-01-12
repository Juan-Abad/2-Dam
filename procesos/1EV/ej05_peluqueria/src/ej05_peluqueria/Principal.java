package ej05_peluqueria;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Principal {// clase Principal

	public static void main(String[] args) {// main
		Peluqueria peluqueria = new Peluqueria();// objeto peluqueria

		for (int i = 0; i < 4; i++) {// se crean los peluqueros
			Peluquero peluquero = new Peluquero(peluqueria);
			peluqueria.getListPeluqueros().add(peluquero);
		}

		for (int i = 1; i <= 30; i++) {// se crean los clientes
			Cliente cliente = new Cliente(peluqueria);
			cliente.start();
		}

	}

}
