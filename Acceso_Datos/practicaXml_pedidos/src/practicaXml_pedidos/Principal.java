package practicaXml_pedidos;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Principal {

	public static void main(String[] args) {
		AccesoDatos.conectar("jdbc:sqlite:abadHernandezEx.db");
		AccesoDatos.crearTabla();
		AccesoDatos.borrarDatosTabla();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("PEDIDOS.xml"));
			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
			NodeList pedidos = document.getElementsByTagName("pedido");
			System.out.println("Nodos pedido a recorrer: " + pedidos.getLength()+"\n");

			for (int i = 0; i < pedidos.getLength(); i++) {
				Node pedid = pedidos.item(i);
				if (pedid.getNodeType() == Node.ELEMENT_NODE) {
					Element pedido = (Element) pedid;
					System.out.println("Nombre cliente: " + getNodo("nombre", pedido));
					System.out.println("Numero de pedido: " + getNodo("numero_pedido", pedido));
					System.out.println("Articulos:");
					NodeList articulos = pedido.getElementsByTagName("articulo");
					for (int j = 0; j < articulos.getLength(); j++) {
						Node arti = articulos.item(j);
						if (arti.getNodeType() == Node.ELEMENT_NODE) {
							Element articulo = (Element) arti;
							System.out.println("\tArticulo:" + j + "\n\t\tDescripción: "
									+ articulo.getAttribute("descripcion") + "\n\t\tCantidad: "
									+ articulo.getAttribute("cantidad")+"\n");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodos = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node nodo = (Node) nodos.item(0);
		return nodo.getNodeValue();
	}

}
