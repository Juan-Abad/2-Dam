package ej_010_LeerFichAleatorioUnReg;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ej_010_LeerFichAleatorioUnReg {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("archivo_aleatorio.dat", "rw");//creamos el objeto para acceder al archivo indicado por parametros, se indica que accedemos en read and write
            long longitud = randomAccess.length();//longitud del archivo
            randomAccess.seek(45);//posiciona el puntero en la posicion 45 porque sabemos el contenido del archivo, en las primeras 45 posiciones tenemos informaacion de ciudaades, pero despues de los animales
            while (randomAccess.getFilePointer() < longitud) {//obtenemos la posicion actual en la que esta leyendo el RandomAccessFile, se comprara con la longitud
                String animal = randomAccess.readLine();//se lee una linea del archivo
                System.out.println("Nombre: " + animal);//imprime el animal
            }
            randomAccess.close(); //Cerramos el acceso al archivo
        } catch (IOException e) {
            System.out.println("Error al leer el archivo aleatorio: " + e.getMessage());//mensaje de error
        }
    }
}

