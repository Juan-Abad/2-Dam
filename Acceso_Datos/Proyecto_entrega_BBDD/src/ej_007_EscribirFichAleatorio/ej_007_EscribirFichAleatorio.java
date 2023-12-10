package ej_007_EscribirFichAleatorio;

import java.io.RandomAccessFile;
import java.io.IOException;

public class ej_007_EscribirFichAleatorio {
	public static void main(String[] args) {
		String ciudades[] = { "zaragoza", "teruel", "huesca", "barcelona", "madrid" };//array de String que contiene lo nombres de las ciudades
		try {
			RandomAccessFile randomAccess = new RandomAccessFile("archivo_aleatorio.dat", "rw");//creamos el objeto para acceder al archivo indicado por parametros, se indica que accedemos en read and write
			randomAccess.seek(0);//con el metodo seek colocamos el puntero en la posicion 0, para empezar a leer o escribir desde esa posicion
			for (int i = 0; i < ciudades.length; i++) {//bucle for, recorre el array de las ciudades, en el bucle se escribe el numero de ciudad, y su nombre, luego un salto dde linea
				randomAccess.write(i);//escribe en el archivo la posicion
				randomAccess.writeBytes(ciudades[i]);//escribe en el archivo el nombre de la ciudad en esa posicion
				if(i<ciudades.length-1) randomAccess.write('\n');//escribe un salto de linea, excepto en la ultima iteracion
			}
			randomAccess.close();//cerramos el acceso al fichero
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo aleatorio: " + e.getMessage());//mensaje de error
		}
	}
}
