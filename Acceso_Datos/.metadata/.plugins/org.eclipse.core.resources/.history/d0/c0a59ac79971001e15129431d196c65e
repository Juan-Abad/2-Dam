package clases;	//pquete "clases", esta es la unica clase que contiene

import java.io.File;	//

public class ad1_clases {
	File file;
	
	public ad1_clases(String[] args) {
		file = new File(args[0]);
	}
	
	public void imprimir_ficheros() {
		/*if(file.isDirectory()) {
			File[] ficheros = file.listFiles();
			for(File f : ficheros) {
				System.out.println(f.getName());
			}
		}*/
		if(!file.exists()) { /*if, entra si el objeto 'fich' de la clase File no existe*/
            System.out.println("No existe el fichero o directorio ("+file.getPath()+")."); /*OutPut por consola indicando que el fichero no existe en la ruta indicada*/
        }else { /* si el fichero existe ejecuta el codigo */
            if(file.isFile()) {	/*if, entra si el objeto fich de la clase File es fichero*/
                System.out.println(file.getPath()+" es un fichero."); /*OutPut por consola indicando la ruta y diciendo que es un fichero*/
            }else {	/* si no es un fichero entra al else */
                System.out.println(file.getPath()+" es un directorio. Contenidos: ");	/**/
                File[] ficheros=file.listFiles(); // Ojo, ficheros o directorios
                for(File f : ficheros) {
                    String textoDescr=f.isDirectory() ? "/" :
                            f.isFile() ? "_" : "?";
                    System.out.println("("+textoDescr+") "+f.getName());
                }
            }
        }
	}
}
