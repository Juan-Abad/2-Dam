package ej_008_LeerFichAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ej_008_LeerFichAleatorio {

    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("archivo_aleatorio.dat", "rw");// se abre el archivo en modo lectura y escritura
            String ciudad_cadena = "";//cadena que almacena la informacion que leeremos
            randomAccess.seek(0);//con el metodo seek colocamos el puntero en la posicion 0, para empezar a leer o escribir desde esa posicion
            ciudad_cadena = randomAccess.readLine();//se lee la primera línea del archivo y la guarda en la cadena
            while (ciudad_cadena != null) {//bucle while, comprueba en cada iteracion si la cadena no es nula, en este bucle se imprime en cada iteracion la informacion leida del archivo
                System.out.println("Nombre leído: " + ciudad_cadena);//imprime la informacion de la linea en cda iteracion
                ciudad_cadena = randomAccess.readLine();//se lee otra linea
            }
            randomAccess.close();//cerramos el acceso al archivo
        } catch (IOException e) {
        	System.out.println("Error al leer el archivo aleatorio: " + e.getMessage());//mensaje de error
        }
    }
}

