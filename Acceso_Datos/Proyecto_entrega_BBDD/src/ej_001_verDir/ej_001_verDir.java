package ej_001_verDir;

import java.io.File;

public class ej_001_verDir {
    public static void main(String[] args) {
        String rutaDirectorio = "."; //ruta del directorio a mostrar, en este caso sera el directorio donde se ubica el programa

        File directorio = new File(rutaDirectorio); //se instancia un objeto de la clase File, pasando por parametros la ruta del File

        if (directorio.isDirectory()) { //se comprueba con el metodo de la clase File .isDirectory() si es un directorio
            String[] archivos = directorio.list();//se guarda en un Array de String la lista obtenida del metodo .list de la clase File
            System.out.println("Archivos en el directorio " + rutaDirectorio + ":");//imprime la ruta
            for (String archivo : archivos) {//for-each que recorre los String del Array creado anteriormente
                System.out.println(archivo);//imprime el String en cada iteracion, el String contiene el nombre de un fichero o directorio
            }
        } else {
            System.out.println("ERROR, la ruta no es correcta");//mensaje de error en caso de que la ruta proporcionada no sea un directorio
        }
    }
}

