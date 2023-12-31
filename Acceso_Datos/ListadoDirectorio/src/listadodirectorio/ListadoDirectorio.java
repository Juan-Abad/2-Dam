

package listadodirectorio; // paquete "listadodirectorio", solo contiene esta clase

import java.io.File; //import de la clase File de java

public class ListadoDirectorio { //Clase "ListadoDirectorio", contiene el main
  
    public static void main(String[] args) {  //comienzo del main
        //String ruta="."; /*variable	String.	Contener la ruta del directorio, valor inicial "." la ruta inicial*
    	String ruta="C:\\";	/*variable	String.	Contener la ruta del directorio, en este caso la ruta es el disco C:\*/
        if(args.length>=1) ruta=args[0]; /*entra al if si la longitud de los argumentos es mayor o igual a 1. el string ruta recibe el valor del argumento*/
        File fich=new File(ruta); /*Variable File de nombre fich, toma el valor de un objeto de la clase File que tiene por argumentos el string ruta*/
        if(!fich.exists()) { /*if, entra si el objeto 'fich' de la clase File no existe*/
            System.out.println("No existe el fichero o directorio ("+ruta+")."); /*OutPut por consola indicando que el fichero no existe en la ruta indicada*/
        }else { /* si el fichero existe ejecuta el codigo */
            if(fich.isFile()) {	/*if, entra si el objeto fich de la clase File es fichero*/
                System.out.println(ruta+" es un fichero."); /*OutPut por consola indicando la ruta y diciendo que es un fichero*/
            }else {	/* si no es un fichero entra al else */
                System.out.println(ruta+" es un directorio. Contenidos: ");	/**/
                File[] ficheros=fich.listFiles(); // Ojo, ficheros o directorios
                for(File f : ficheros) {	//for-each que itera sobre el array "ficheros" de la clase File
                    String textoDescr=f.isDirectory() ? "/" :
                            f.isFile() ? "_" : "?";	//Variable String nombre="textoDescr", con valor inicial "/" si es un directorio o "_" si es un file
                    System.out.println("("+textoDescr+") "+f.getName());	//Output por consola del String textoDescr mas el nombre del fichero
                }	//fin for
            }	//fin if
        }	//fin if
    }	//fin main
}	//fin clase
