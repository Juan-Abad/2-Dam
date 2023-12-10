package ej_002_VerInf;

import java.io.File;

public class ej_002_VerInf {
    public static void main(String[] args) {
        String rutaArchivo = "."; //ruta del directorio a mostrar, en este caso sera el directorio donde se ubica el programa

        File archivo = new File(rutaArchivo); //se instancia un objeto de la clase File, pasando por parametros la ruta del File

        if (archivo.exists()) { //se comprueba con el metodo de la clase File .exists() si el archivo o directorio en esa ruta existe
            System.out.println("Nombre del fichero: " + archivo.getName()); //imprime el nombre del File
            System.out.println("Ruta absoluta: " + archivo.getAbsolutePath()); //imprime la ruta absoluta del File
            System.out.println("Tamaño: " + archivo.length() + " bytes"); //imprime el tamaño del File
            System.out.println("¿Es directorio? " + archivo.isDirectory()); //imprime el booleano que devuelve el metodo isDirectory de la clase File, comprueba si es un directorio o no
            System.out.println("¿Es archivo? " + archivo.isFile()); //imprime el booleano que devuelve el metodo isFile de la clase File, comprueba si es un fichero o no
        } else {
            System.out.println("Error, el archivo no existe");
        }
    }
}
