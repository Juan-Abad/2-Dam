package ej10_cocteles;

import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		ArrayList<Camarero> camarerosList = new ArrayList<Camarero>();
		for (int i = 0; i < 3; i++) {
			camarerosList.add(new Camarero());
		}
		Camarero.getListaGrifos().add(new Grifo("Soda"));
		Camarero.getListaGrifos().add(new Grifo("Zumo"));
		Camarero.getListaGrifos().add(new Grifo("Tabasco"));
	}

}
