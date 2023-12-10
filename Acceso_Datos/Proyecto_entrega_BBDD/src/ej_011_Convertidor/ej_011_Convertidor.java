package ej_011_Convertidor;

/* 
 * He analizado el ejercicio y lo he explicado
 * 
 * Genera un fichero HTML a partir de un fichero XML y otro XSL
*/
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class ej_011_Convertidor {

	public static void main(String argv[]) throws IOException { // main
		String hojaEstilo = "alumnosPlantilla.xsl";// nombre de la ruta al archivo XSL
		String datosAlumnos = "alumnos.xml";// nombre de la ruta al archivo XML
		File pagHTML = new File("mipagina.html");// se instancia un objeto de la clase File, con nombre "mipagina.html",
													// este File sera la tranformaacion del xsl y el xml
		FileOutputStream os = new FileOutputStream(pagHTML);// Crea una secuencia de salida de archivo para escribir en
															// el mismo archivo especificado por parametros.

		// source es un objeto que implementa la interfaz Source, contiene la
		// información necesaria para actuar como entrada fuente (fuente XML o
		// instrucciones de transformación)
		Source estilos = new StreamSource(hojaEstilo); // fuente XSL
		Source datos = new StreamSource(datosAlumnos); // fuente XML
		Result result = new StreamResult(os); // esta interfaz contiene la información necesaria para construir un árbol
												// de resultados de transformación, en este caso de la secuencia de
												// salida del archivo HTML

		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);// transforma un árbol de
																								// fuentes en un árbol
																								// de resultados,
																								// primero obtiene una
																								// instancia del
																								// TransformerFactory(clase
																								// abtstracta), y crea
																								// un transformador,
																								// pasandole el source
																								// de la hoja de estilos
																								// XSL
			transformer.transform(datos, result); // transforma el source del XML junto al XSL que ya tiene el
													// tranformer al crearlo, para crear el HTML en el result
		} catch (Exception e) {
			System.err.println("Error: " + e);// mensaje de error
		}
		os.close(); // cierra el fichero

	}// de main
}// de la clase
