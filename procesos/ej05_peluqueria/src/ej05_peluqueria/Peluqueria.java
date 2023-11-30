package ej05_peluqueria;

import java.util.ArrayList;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
class Peluqueria {
	private ArrayList<Peluquero> listPeluqueros = new ArrayList<Peluquero>();// arrayÑist que contiene a los peluquero
																				// de la peluqueria

	public void atenderCliente(Cliente cliente) {// metodo para atender a los clientes, si el cliente no encuentra
													// peluquero diaponible sigue intentandolo
		boolean ocup = true;
		while (ocup) {
			for (Peluquero peluquero : listPeluqueros) {// recorre el array de los peluqueros para comprobar si hay
														// alguno disponible
				if (peluquero.isOcupado()) {
					ocup = true;
					continue;// continua intentando encontrarle un peluquero
				} else {
					peluquero.atenderCliente(cliente);// atiende aal cliente
					ocup = false;
				}
			}
		}
	}

	public ArrayList<Peluquero> getListPeluqueros() {
		return listPeluqueros;
	}

	public void setListPeluqueros(ArrayList<Peluquero> listPeluqueros) {
		this.listPeluqueros = listPeluqueros;
	}

}