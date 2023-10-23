package clases; //pquete "clases", esta es la unica clase que contiene

import java.io.File; //import de la clase File de Java

public class ad1_clases { // clase ad1_clases
	File file; // Objeto de la clase File

	public ad1_clases(String[] args) { // constructor de la clase
		file = new File(args[0]);
	}

	public void imprimir_ficheros() { // metodo que imprime los ficheros de un directorio
		/*
		 * if(file.isDirectory()) { File[] ficheros = file.listFiles(); for(File f :
		 * ficheros) { System.out.println(f.getName()); } }
		 */
		if (!file.exists()) { /* if, entra si el objeto 'fich' de la clase File no existe */
			System.out.println("No existe el fichero o directorio (" + file.getPath()
					+ ")."); /* OutPut por consola indicando que el fichero no existe en la ruta indicada */
		} else { /* si el fichero existe ejecuta el codigo */
			if (file.isFile()) { /* if, entra si el objeto fich de la clase File es fichero */
				System.out.println(file.getPath()
						+ " es un fichero."); /* OutPut por consola indicando la ruta y diciendo que es un fichero */
			} else { /* si no es un fichero entra al else */
				System.out.println(file.getPath() + " es un directorio. Contenidos: "); // imprime la ruta y sus
																						// contenidos
				File[] ficheros = file.listFiles(); // Ojo, ficheros o directorios
				for (File f : ficheros) { // for-each, recorre el array ficheros
					String textoDescr = f.isDirectory() ? "/" : f.isFile() ? "_" : "?"; // variable string, guarda "/"
																						// si el fichero es un
																						// directorio o "_" si el
																						// fichero es un File
					System.out.println("(" + textoDescr + ") " + f.getName()); // imprime el indicaddor de si es un
																				// fichero o file, y el nombre del mismo
				} // fin for-each
			} // fin if
		} // fin if
	} // fin metodo imprimir_ficheros
} // fin clase ad1_clases
