package procesos01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class lan {

	public static void main(String[] args) throws IOException, InterruptedException {
		File directorio = new File("C:\\Users\\usuario\\Desktop\\2ºDam\\procesos\\prueba\\bin");
		// El proceso ejecutara el programa ej2 en java
		// AL estar. en la carpeta bin de eclipse tengo que cambiar el directorie de
		// ejecucion.
		ProcessBuilder pb = new ProcessBuilder("java", "prueba.lan"); // comando a ejecutar desde directorio
		pb.redirectInput(new File("C:\\Users\\usuario\\Desktop\\2ºDam\\procesos\\procesos01\\entrada.txt"));
		pb.directory(directorio); // cambio el directoriof
		Process p = pb.start();
		try {
			InputStream is = p.getInputStream(); // stream conectado a la salida del proceso
			// leg el stream y lo muestro en la salida standart
			int c;
			while ((c = is.read()) != -1)
				System.out.print((char) c);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}