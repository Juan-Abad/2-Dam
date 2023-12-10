package ej_005_EscribirFichObject;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class ej_005_EscribirFichObject {
    public static void main(String[] args) {
        try {
        	FileOutputStream fileOut = new FileOutputStream("src\\ej_005_EscribirFichObject\\animales.dat");//Creo un objeto de la clase FileOutputStream, le paso por parametros la ruta, crea un flujo de salida de archivo
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);//Creo un objeto de la clase ObjectOutputStream es utilizado para escribir objetos en el archivo, se le pasa el objeto FileOutputStream
            
        	Animal animal1 = new Animal(5);//instancio un objeto de la clase Animal
            Animal animal2 = new Animal(3);//instancio un objeto de la clase Animal

            objectOut.writeObject(animal1);//escribo en el fichero el objeto animal1
            objectOut.writeObject(animal2);//escribo en el fichero el objeto animal1

            System.out.println("Animales registrados en el archivo");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());//mensaje en caso de error
        }
    }
}
