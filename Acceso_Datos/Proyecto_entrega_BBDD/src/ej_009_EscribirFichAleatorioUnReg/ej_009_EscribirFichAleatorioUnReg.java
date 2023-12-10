package ej_009_EscribirFichAleatorioUnReg;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ej_009_EscribirFichAleatorioUnReg {

	public static void main(String[] args) {
		String animales[] = { "conejo", "lechuza", "oso", "tortuga", "vaca" };//array de String que contiene lo nombres de los animales
		try {
			RandomAccessFile randomAccess = new RandomAccessFile("archivo_aleatorio.dat", "rw");//creamos el objeto para acceder al archivo indicado por parametros, se indica que accedemos en read and write
			long posicion= randomAccess.length();
			randomAccess.seek(posicion);//con el metodo seek colocamos el puntero en la posicion ultima del archivo, para empezar a leer o escribir desde esa posicion, esto lo hacemos asi porque conocemos la estructura del archivo
			randomAccess.write('\n');//escribe un salto de linea primero, porque sabemos la estructura del archivo al que aaccedemos y empezamos a escribir en una nueva linea
			for (int i = 0; i < animales.length; i++) {//bucle for, recorre el array de las ciudades, en el bucle se escribe el numero de ciudad, y su nombre, luego un salto dde linea
				randomAccess.write(i);//escribe en el archivo la posicion
				randomAccess.writeBytes(animales[i]);//escribe en el archivo el nombre del animal en esa posicion
				if(i<animales.length-1) randomAccess.write('\n');//escribe un salto de linea, excepto en la ultima iteracion
			}
			randomAccess.close();//cerramos el acceso al fichero
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo aleatorio: " + e.getMessage());//mensaje de error
		}
	}
}
