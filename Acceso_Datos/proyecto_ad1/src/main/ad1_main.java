package main;	//paquete main, incluye solo esta clase la cual contiene el main

import clases.ad1_clases;	//import de la clase ad1_clases del paquete clases

public class ad1_main {	//clase ad1_main

	public static void main(String[] args) {	//metodo main
		String[] argumentos = {"C:\\"};	//variable Array de Strings, valor por defecto ="C:\\"
		if(args.length>=1) argumentos[0]= args[0];	//if, si la longitud de los argumentos del main es mayor o igual a 1, el array argumentos en pos 0 seran los args en pos 0
		ad1_clases object = new ad1_clases(argumentos);	//objeto "object" de la clase ad1_clases, se instancia la clase pasandole al constructor el string argumentos
		object.imprimir_ficheros();	//el objeto "object" de la clase ad1_clases ejecuta el metodo de clase imprimir_ficheros()
		
	}	//fin main

}	//fin clase ad1_main
