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

import Datos.AccesoDatos;

public class Main {

	private static ODB odb;

	public static void main(String[] args) {
		odb = ODBFactory.open("inventario.odb");
		
		//Metodo para insertar los datos del xml a la base de datos neodatis
		//insertarBBDD_a_neodatis();

		Consultas consultas = new Consultas(odb);	
		//consultas neodatis
		//consultas.Articulo_con_mayor_num_unidades();
		//System.out.println("\nCantidad de articulos por tipo: \n"+consultas.Cant_articulos_por_tipo());
		//consultas.Calc_inventario_total();
		
		//codigo necesario para conectarse a SQLite y crear la tabla inventario
		AccesoDatos.conectar("jdbc:sqlite:inventario.db"); //se conecta a la BBDD indicada
		AccesoDatos.crearTabla(); //crea la tabla en la BBDD, en caso de que no sea la primera vez se duplicarán los datos
		
		//Insertando BBDD de neodatis a SQLite
		//insertarBBDD_a_SQLite(consultas);
		
		//SQLite consultas, es necesario descomentar los metodos de AccesoDatos de conectar y crear tabla(por si acaso)
		//AccesoDatos.Articulo_con_mayor_num_unidades();
		//AccesoDatos.Cant_articulos_por_tipo();
		//AccesoDatos.Calc_inventario_total();
		odb.close();
	}

	public static void insertarBBDD_a_neodatis() {
		System.out.println("Insertando...");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("inventario.xml"));
			document.getDocumentElement().normalize();
			NodeList ventas = document.getElementsByTagName("articulo");

			ArrayList<Object> lista_articulos = new ArrayList<Object>();
			for (int i = 0; i < ventas.getLength(); i++) {
				Node ventaNode = ventas.item(i);
				if (ventaNode.getNodeType() == Node.ELEMENT_NODE) {
					Element venta = (Element) ventaNode;
					String fecha, producto, tipo;
					Double precio;
					int cantidad;
					fecha = getNodo("fecha", venta);
					producto = getNodo("producto", venta);
					tipo = getNodo("tipo", venta);
					precio = Double.valueOf(getNodo("precio", venta));
					cantidad = Integer.parseInt(getNodo("cantidad", venta));
					lista_articulos.add(new Articulo(fecha, producto, tipo, precio, cantidad));
				}
			}
			
			for (Object obj : lista_articulos) {
				System.out.println(obj.toString());
				odb.store(obj);
			}
			System.out.println("Los datos se han insertado en la base de datos neodatis");
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

	public static void insertarBBDD_a_SQLite(Consultas consultas) {
		System.out.println("\nInsertando datos a tabla inventario de SQLite:\n");
		Articulo articulo;
		for (Articulo objeto_articulo : consultas.obtenerBBDD()) {
			articulo = new Articulo(objeto_articulo.getFecha(), objeto_articulo.getProducto(), objeto_articulo.getTipo(), objeto_articulo.getPrecio(), objeto_articulo.getCantidad());
			System.out.println("Articulo insertado: \n\t"+articulo.toString());
			AccesoDatos.insertarArticulo(objeto_articulo.getFecha(), objeto_articulo.getProducto(), objeto_articulo.getTipo(), objeto_articulo.getPrecio(), objeto_articulo.getCantidad());
		}
	}

}
