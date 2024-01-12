/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Principal {// clase Principal

	public static void main(String[] args) {// main
		int suma = 0;// variable entera suma, gurada la suma de todas la temperaturas de las ciudades
		final int NUMEROCIUDADES = 20;// valor entero que guarda el numero de ciudades a calcular la media

		for (int i = 0; i < NUMEROCIUDADES; i++) {// for que vaa sumando las temperaturas de las ciudades
			suma += lanzarProceso(String.valueOf((int) (Math.random() * 50) + 50));// se suma a la variable suma la
																					// media de la ciudad, se llama al
																					// metodo lanzarProceso, paasandole
																					// por parametros un string, el cual
																					// contiene un numero aleatorio
																					// entre 49 y 100
		}
		System.out.println("La media de las ciudades es: " + (suma / NUMEROCIUDADES));// imprime la media de las
																						// ciudades
	}

	public static int lanzarProceso(String ciudad) {// metodo lanzaarProceso, recibe por paramentros el String con el
													// nombre de la ciudad

		File f = new File(".\\bin");// Se cream el obje File f, pasandole por parametros la ruta del directorio
									// donde se ubica el .class
		ProcessBuilder b = new ProcessBuilder("java", "Temperatura", ciudad);// se crea un ProcessBuilder b, al cual se
																				// le pasa por parametros la sentencia a
																				// ejecutar en el simbolo del sistema
		b.directory(f);// al processBuilder se le indica el directorio donde buscar los archivos a
						// ejecutar

		String resultado = "";// variable String para guardar el resultado de la temperaturaa media de laa
								// ciudad solicitada

		Process p;// se crea el proceso p
		try {
			p = b.start();// se el asigna al proceso el proceso obtenido de ejecutar el processBuilder
			InputStream salida = p.getInputStream();// se crea una instancia de InputStream, a la cual se le asigna la
													// salida InputStream del proceso p

			int letra = salida.read();// al entero letra se le asigna el valor entero leido por el InputStream salida
			while (letra != -1) {// si letra es igual a -1 significa que no hay más datos por leer, sigue en el
									// bucle si sigue habiendo datos por leer
				resultado += (char) (letra);// a la variable resultado se le va añadiendo la letra recibidaen cada
											// iteracion del bucle
				letra = salida.read();// se vuelve a leer datos de InputStream
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("La ciudad " + ciudad + " tiene una temperatura de " + resultado);// se imprime el resultado
																								// de la temperatura
																								// media de esa ciudad

		return Integer.parseInt(resultado);// devuelve el resultado pasado a entero
	}

}
