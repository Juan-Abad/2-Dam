package ej_006_LeerFichObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ej_006_LeerFichObject {

	public static void main(String[] args) {
		try {
			FileInputStream fileIn = new FileInputStream("src\\ej_006_LeerFichObject\\animales.dat");//Creo un objeto de la clase FileInputStream, le paso por parametros la ruta, crea un flujo de entrada al archivo
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);//Creo un objeto de la clase ObjectInputStream es utilizado para leer objetos del el archivo, se le pasa el objeto FileInputStream
			while (fileIn.available() > 0) {//bucle, que mientras el flujo de salida sea mayor a 0 continuara leyendo
				Animal animal = (Animal) objectIn.readObject();//lee un objeto Animal y lo castea
				System.out.println("Edad: " + animal.getEdad());//imprime la edad del animal en cada iteracion del bucle
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());//mensaje de error
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());//mensaje de error
		}
	}

}
