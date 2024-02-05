import java.io.File;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Datos.AccesoDatos;
import utilidades.Leer;

public class Lanzadora_a_BBDD {

	public static void main(String[] args) {
		AccesoDatos.conectar("jdbc:sqlite:abadHerna.db");
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
					Long numero_pedido = Long.valueOf(getNodo("numero-pedido", pedido));
					Long numero_cliente = Long.valueOf(getNodo("numero-cliente", pedido));
					String fecha = getNodo("fecha", pedido);
					if (!AccesoDatos.comprobarPedido(Integer.valueOf(getNodo("numero-pedido", pedido)))) {
						if (AccesoDatos.comprobarCliente(numero_cliente)) {
							AccesoDatos.insertarPedido(numero_pedido, numero_cliente, fecha);
							NodeList articulos = pedido.getElementsByTagName("articulo");
							for (int j = 0; j < articulos.getLength(); j++) {
								Node arti = articulos.item(j);
								if (arti.getNodeType() == Node.ELEMENT_NODE) {
									Element articulo = (Element) arti;
									Integer cantidad = Integer.parseInt(getNodo("cantidad", articulo));
									Long codigo_articulo = Long.valueOf(getNodo("codigo", articulo));
									AccesoDatos.insertarPedido_articulo(numero_pedido, codigo_articulo, cantidad);
								}
							}
						} else {
							System.out.println(
									"Cliene " + getNodo("numero-cliente", pedido) + " no existente en la BBDD");
							AccesoDatos.insertarCliente(Long.valueOf(getNodo("numero-cliente", pedido)), null, null,
									null);
							System.out.println(
									"Cliente" + getNodo("numero-cliente", pedido) + " creado con valores nulos");
						}
					} else {
						switch (Leer.pedirEntero(
								"Ya existe el pedido, quiere reemplazarlo, modificarlo o ignorar. Responda con (1,2,3) respectivamente",
								"^[1-3]{1}")) {
						case 1:
							AccesoDatos.eliminarPedido(numero_pedido);
							AccesoDatos.insertarPedido(numero_pedido, numero_cliente, fecha);
							System.out.println("Peido reemplazado");
							break;
						case 2:
							System.out.println("Pedido de la BBDD:");
							AccesoDatos.mostratPedido(numero_pedido);
							AccesoDatos.mostrarPedido_articulo(numero_pedido);
							while() {
								Leer.pedirEntero("", "");
							}
							break;
						case 3:
							System.out.println("Pedido ignorado");
							break;
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
