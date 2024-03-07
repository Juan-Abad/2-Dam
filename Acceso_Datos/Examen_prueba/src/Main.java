import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	private static ODB odb;

	public static void main(String[] args) {
		odb = ODBFactory.open("examen_pruebaDB.odb");
		//insertarBBDD_a_neodatis();
		
		Consultas consultas = new Consultas(odb);
		System.out.println(consultas.Calc_total_uds_vendidas());
		System.out.println(consultas.Calc_venta_por_color());
		for(ObjectValues obj: consultas.Producto_con_mayor_num_ventas()) {
			System.out.println(obj.toString());
		}
		odb.close();
	}

	public static void insertarBBDD_a_neodatis() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("ventas.xml"));
			document.getDocumentElement().normalize();
			NodeList ventas = document.getElementsByTagName("venta");

			ArrayList<Object> lista_ventas = new ArrayList<Object>();
			for (int i = 0; i < ventas.getLength(); i++) {
				Node ventaNode = ventas.item(i);
				if (ventaNode.getNodeType() == Node.ELEMENT_NODE) {
					Element venta = (Element) ventaNode;
					String fecha, producto, talla, color;
					Double precio;
					int cantidad;
					fecha = getNodo("fecha", venta);
					producto = getNodo("producto", venta);
					talla = getNodo("talla", venta);
					color = getNodo("color", venta);
					precio = Double.valueOf(getNodo("precio", venta));
					cantidad = Integer.parseInt(getNodo("cantidad", venta));
					lista_ventas.add(new Venta(fecha, producto, talla, color, precio, cantidad));
				}
			}

			for (Object obj : lista_ventas) {
				//System.out.println(obj.toString());
				odb.store(obj);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	private static String getNodo(String etiqueta, Element elem) {
		Node nodo = elem.getElementsByTagName(etiqueta).item(0);
		if (nodo != null && nodo.hasChildNodes()) {
			NodeList nodos = nodo.getChildNodes();
			Node valor = (Node) nodos.item(0);
			return valor.getNodeValue();
		} else {
			return "";
		}
	}
	
	public static void insertarBBDD_a_SQLite() {
		
	}

}
