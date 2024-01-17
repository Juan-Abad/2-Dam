package SAX;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.xml.sax.helpers.*;
import org.xml.sax.*;

public class SAXPrincipal {

	public static void main(String[] args) throws FileNotFoundException, IOException, SAXException {
		XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
		GestionContenido gestor = new GestionContenido();
		procesadorXML.setContentHandler(gestor);
		InputSource fileXML = new InputSource("PEDIDOS.xml");
		procesadorXML.parse(fileXML);
	}
}

class GestionContenido extends DefaultHandler {

	private String nombre;
	private Integer numero_pedido;
	private HashMap<String, Integer> mapa_articulos = new HashMap<>();
	private Integer orden = 0;

	public GestionContenido() {
		super();
	}

	public void startDocument() {
		System.out.println("Comienzo del Documento XML");
	}

	public void endDocument() {
		System.out.println("Final del Documento XML");
	}

	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		if (atts.getLength() == 0) {
			System.out.printf("\t Principio Elemento: %s %n", nombre);
			switch (nombre) {
			case "nombre":
				orden = 1;
			case "numero_pedido":
				orden = 2;
			case "articulos":
				mapa_articulos.put(atts.getLocalName(0), Integer.parseInt(atts.getValue(0)));
				mapa_articulos.put(atts.getLocalName(1), Integer.parseInt(atts.getValue(1)));
			}
		} else {
			System.out.println("\t\tAtributos: \n\t\t\t" + atts.getLocalName(0) + ": " + atts.getValue(0) + "\n\t\t\t"
					+ atts.getLocalName(1) + ": " + atts.getValue(1));
		}
	}

	public void endElement(String uri, String nombre, String nombreC) {
		if (nombre.equals("pedido") || nombre.equals("pedidos_deporte")) {
			System.out.printf("Fin Elemento: %s \n", nombre);
		}
		if(nombre.equals("pedido")) {
			System.out.println();
		}
	}

	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String(ch, inicio, longitud);
		car = car.replaceAll("[\t\n]", "");
		if (car != null && car.matches(".*[a-zA-Z0-9].*")) {
			switch (orden) {
			case 1: nombre = car;
			case 2: numero_pedido = Integer.parseInt(car);
			}
			System.out.printf("\t\t Caracteres: %s %n", car);
		}
	}
}