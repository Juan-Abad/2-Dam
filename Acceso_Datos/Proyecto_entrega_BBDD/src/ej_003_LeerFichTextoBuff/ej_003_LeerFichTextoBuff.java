package ej_003_LeerFichTextoBuff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ej_003_LeerFichTextoBuff {
	public static void main(String[] args) {
		try {//controla las excepciones que pueda provocar al crear el BufferedReader
			String linea = "", resultado = ""; //varibles tipo String, linea guarda el resultado de leer con el BufferedReader en cada iteraacion, resultado contiene la cadena final
			File archivo = new File("src\\ej_003_LeerFichTextoBuff\\archivo.txt");//se instancia un objeto de la clase File, pasandole por parametros la ruta del archivo
			BufferedReader br = new BufferedReader(new FileReader(archivo));//se instancia in objeto de la clase BufferedReader, por parametros se le pasa un FileReader, el cual tiene el objeto File
			while ((linea = br.readLine()) != null) {//bucle que comprueba en cada iteracion si la linea leida es nula o no
				resultado += linea;//añade en el String resultado el valor actual de linea(la linea leida en esa iteracion del bucle)
			}
			System.out.println(resultado);//se imprime la ruta absoluta del File y el texto que se encuentra en el archivo
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());//mensaje de error en caso de que salte una exepcion
		}
	}
}
