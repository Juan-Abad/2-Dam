import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Datos.AccesoDatos;

public class Lanzadora_a_BBDD {

	public static void main(String[] args) {
		AccesoDatos.conectar("jdbc:sqlite:abadHernandezEx.db");
		AccesoDatos.crearTabla();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("Pedidos_Tiendas.xml"));
			document.getDocumentElement().normalize();
			NodeList pedidos = document.getElementsByTagName("pedido");
			
			for (int i = 0; i < pedidos.getLength(); i++) {
				Node pedid = pedidos.item(i);
				if (pedid.getNodeType() == Node.ELEMENT_NODE) {
					Element pedido = (Element) pedid;
					getNodo("numero-cliente", pedido);
					getNodo("numero-pedido", pedido);
					getNodo("fecha", pedido);
					NodeList articulos = pedido.getElementsByTagName("articulo");
					for (int j = 0; j < articulos.getLength(); j++) {
						Node arti = articulos.item(j);
						if (arti.getNodeType() == Node.ELEMENT_NODE) {
							Element articulo = (Element) arti;
							getNodo("codigo", articulo);
							getNodo("cantidad", articulo);
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
