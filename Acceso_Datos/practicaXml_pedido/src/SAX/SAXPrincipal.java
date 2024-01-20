package SAX;

import java.io.*;
import java.util.LinkedHashMap;

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
	private LinkedHashMap<String, String> lista = new LinkedHashMap();
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
			if (nombre.equals("pedido") || nombre.equals("pedidos_deporte")) {
				System.out.printf("\n\t Principio Elemento: %s %n", nombre);
			}
			switch (nombre) {
			case "nombre":
				orden = 1;
				break;
			case "numero_pedido":
				orden = 2;
				break;
			default:
				orden = 0;
			}
		} else {
			if (nombre.equals("articulo")) {
				lista.put(atts.getValue(0), atts.getValue(1));
			}
		}
	}

	public void endElement(String uri, String nombre, String nombreC) {
		if (nombre.equals("pedido")) {
			System.out.println("Nombre: " + this.nombre + "\nNumero pedido: " + numero_pedido);
			for(String mapa: lista.keySet()) {
				System.out.println("Articulos:\n\tDescripción: "+mapa+"\n\tCantidad: "+lista.get(mapa));
			}
			nombre = "";
			numero_pedido = 0;
			lista.clear();
		}
		if (nombre.equals("pedido") || nombre.equals("pedidos_deporte")) {
			System.out.printf("Fin Elemento: %s \n", nombre);
		}
	}

	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String(ch, inicio, longitud);
		car = car.replaceAll("[\t\n]", "");
		if (car != null && car.matches(".*[a-zA-Z0-9].*")) {
			switch (orden) {
			case 1:
				nombre = car;
				break;
			case 2:
				numero_pedido = Integer.parseInt(car);
				break;
			}
		}
	}
}