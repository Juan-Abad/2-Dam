package main;

import clases.ad1_clases;

public class ad1_main {

	public static void main(String[] args) {
		String[] argumentos = {"C:\\"};
		if(args.length>=1) argumentos[0]= args[0];
		ad1_clases object = new ad1_clases(argumentos);
		object.imprimir_ficheros();
		
	}

}
