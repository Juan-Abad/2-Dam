import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

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
		Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtener la ruta del archivo XML desde el archivo de configuración
        String rutaXML = properties.getProperty("ruta_xml"); //obtiene la ruta del archivo de configuración
        if (rutaXML == null) { //comprueba que la ruta del archivo de configuración no es nula, si lo es se termina el programa
            System.err.println("La propiedad 'ruta_xml' no está definida en el archivo de configuración.");
            System.exit(0);
        }
		AccesoDatos.conectar("jdbc:sqlite:abadHerna.db"); //se conecta a la BBDD indicada
		AccesoDatos.crearTabla(); //crea las tablas en la BBDD
		
		// codigo que inserta los clientes iniciales

		/*
		AccesoDatos.insertarCliente(Long.valueOf("1234567890"), "Juan Abad", "Av.Navarra", Long.valueOf("978235252"));
		AccesoDatos.insertarCliente(Long.valueOf("9876543210"), "Pedro Martinez", "Av.Madrid",
				Long.valueOf("973636452"));
		AccesoDatos.insertarCliente(Long.valueOf("1234567891"), "Martîn Salazar", "Av.Cataluña",
				Long.valueOf("972564678"));
		AccesoDatos.insertarCliente(Long.valueOf("1234567892"), "Pepe Garcia", "Jarque de Moncayo",
				Long.valueOf("971536252"));
		AccesoDatos.insertarCliente(Long.valueOf("1234567893"), "Gustavo Prados", "Pablo Neruda",
				Long.valueOf("970356789"));

		// codigo que inserta los articulos iniciales con cantidades de stock iniciales

		AccesoDatos.insertarArticulo(Long.valueOf("123456"), "", "Balones baloncesto", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("234567"), "", "Balones futbol", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("345678"), "", "Balones futbol sala", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("456789"), "", "Medias", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("567890"), "", "Botas futbol", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("678901"), "", "Espinilleras", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("789012"), "", "Camisetas futbol", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("890123"), "", "Camisetas baloncesto", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("123457"), "", "Zapatillas baloncesto", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("234568"), "", "Gorra", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("901234"), "", "Pantalones balonceso", 500);
		AccesoDatos.insertarArticulo(Long.valueOf("012345"), "", "Pantalones futbol", 10);
		AccesoDatos.desconectar();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File archivo = new File(rutaXML);
	        if (!archivo.exists()) {
	            System.out.println("El archivo no existe.");
	            System.exit(0);
	        }
			Document document = builder.parse(archivo);
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
						if (!AccesoDatos.comprobarCliente(numero_cliente)) {
							System.out.println(
									"Cliene " + getNodo("numero-cliente", pedido) + " no existente en la BBDD");
							if (Leer.pedirEntero(
									"Rechazar el pedido , o crear el cliente. Seleccione 1 o 2 respectivamente",
									"^[1-2]{1}") == 1)
								continue;
							if (AccesoDatos.insertarCliente(Long.valueOf(getNodo("numero-cliente", pedido)), null, null,
									null)) {
								System.out.println(
										"Cliente " + getNodo("numero-cliente", pedido) + " creado con valores nulos");

							}
						}
						AccesoDatos.insertarPedido(numero_pedido, numero_cliente, fecha);
						NodeList articulos = pedido.getElementsByTagName("articulo");
						for (int j = 0; j < articulos.getLength(); j++) {
							Node arti = articulos.item(j);
							if (arti.getNodeType() == Node.ELEMENT_NODE) {
								Element articulo = (Element) arti;
								Integer cantidad = Integer.parseInt(getNodo("cantidad", articulo));
								Long codigo_articulo = Long.valueOf(getNodo("codigo", articulo));
								if (!AccesoDatos.comprobarArticulo(codigo_articulo)) {
									if (Leer.pedirEntero("El articulo con codigo " + codigo_articulo
											+ " no existe en la BBDD, seleccione 1 o 2 si quiere crearlo o rechazar el pedido "+numero_pedido,
											"^[1-2]{1}") == 1) {
										AccesoDatos.insertarArticulo(codigo_articulo, null, null, 500);
										AccesoDatos.insertarPedido_articulo(numero_pedido, codigo_articulo, cantidad);
									} else {
										AccesoDatos.eliminarPedido(numero_pedido);
										break;
									}
								} else {
									if (AccesoDatos.comprobar_stock_articulo(codigo_articulo) > cantidad) {
										AccesoDatos.insertarPedido_articulo(numero_pedido, codigo_articulo, cantidad);
									} else {
										System.out.println("El articulo " + codigo_articulo + ", del pedido "
												+ numero_pedido + ", no tiene suficiente stock.");
										switch (Leer.pedirEntero(
												"Seleccione 1, 2 o 3, si quiere aceptar el articulo en el pedido, rechazar el articulo, o rechazar el pedido",
												"^[1-3]{1}")) {
										case 1:
											AccesoDatos.insertarPedido_articulo(numero_pedido, codigo_articulo,
													cantidad);
											break;
										case 2:
											System.out.println("Articulo con codigo " + codigo_articulo
													+ ", del pedido " + numero_pedido + " rechazado");
											break;
										case 3:
											AccesoDatos.eliminarPedido(numero_pedido);
											System.out.println("Pedido "+numero_pedido+" eliminado");
											break;
										}
									}
								}
							}
						}
					} else {
						switch (Leer.pedirEntero(
								"Ya existe el pedido, quiere reemplazarlo, modificarlo o ignorar. Responda con (1,2,3) respectivamente",
								"^[1-3]{1}")) {
						case 1:
							if (!AccesoDatos.comprobarCliente(numero_cliente)) {
								System.out.println(
										"Cliene " + getNodo("numero-cliente", pedido) + " no existente en la BBDD");
								if (AccesoDatos.insertarCliente(Long.valueOf(getNodo("numero-cliente", pedido)), null, null,
										null)) {
									System.out.println(
											"Cliente " + getNodo("numero-cliente", pedido) + " creado con valores nulos");
								}
							}
							boolean eliminado, insertado;
							eliminado = AccesoDatos.eliminarPedido(numero_pedido);
							insertado = AccesoDatos.insertarPedido(numero_pedido, numero_cliente, fecha);
							NodeList articulos = pedido.getElementsByTagName("articulo");
							for (int j = 0; j < articulos.getLength(); j++) {
								Node arti = articulos.item(j);
								if (arti.getNodeType() == Node.ELEMENT_NODE) {
									Element articulo = (Element) arti;
									Integer cantidad = Integer.parseInt(getNodo("cantidad", articulo));
									Long codigo_articulo = Long.valueOf(getNodo("codigo", articulo));
									if (!AccesoDatos.comprobarArticulo(codigo_articulo)) {
										if (Leer.pedirEntero("El articulo con codigo " + codigo_articulo
												+ " no existe en la BBDD, seleccione 1 o 2 si quiere crearlo o rechazar el pedido",
												"^[1-2]{1}") == 1) {
											AccesoDatos.insertarArticulo(codigo_articulo, null, null, 500);
											AccesoDatos.insertarPedido_articulo(numero_pedido, codigo_articulo,
													cantidad);
										} else {
											AccesoDatos.eliminarPedido(numero_pedido);
											break;
										}
									} else {
										if (AccesoDatos.comprobar_stock_articulo(codigo_articulo) > cantidad) {
											AccesoDatos.insertarPedido_articulo(numero_pedido, codigo_articulo,
													cantidad);
										} else {
											System.out.println("El articulo " + codigo_articulo + ", del pedido "
													+ numero_pedido + ", no tiene suficiente stock.");
											switch (Leer.pedirEntero(
													"Seleccione 1, 2 o 3, si quiere aceptar el articulo en el pedido, rechazar el articulo, o rechazar el pedido",
													"^[1-3]{1}")) {
											case 1:
												AccesoDatos.insertarPedido_articulo(numero_pedido, codigo_articulo,
														cantidad);
												break;
											case 2:
												System.out.println("Articulo con codigo " + codigo_articulo
														+ ", del pedido " + numero_pedido + " rechazado");
												break;
											case 3:
												AccesoDatos.eliminarPedido(numero_pedido);
												System.out.println("Pedido eliminado");
												break;
											}
										}
									}
								}
							}
							if (eliminado == true && insertado == true) {
								System.out.println("Peido reemplazado");
							}
							break;
						case 2:
							if (!AccesoDatos.comprobarCliente(numero_cliente)) {
								System.out.println(
										"Cliene " + getNodo("numero-cliente", pedido) + " no existente en la BBDD");
								if (AccesoDatos.insertarCliente(Long.valueOf(getNodo("numero-cliente", pedido)), null, null,
										null)) {
									System.out.println(
											"Cliente " + getNodo("numero-cliente", pedido) + " creado con valores nulos");

								}
							}
							System.out.println("Pedido de la BBDD:");
							AccesoDatos.mostrarPedido(numero_pedido);
							AccesoDatos.mostrarPedido_articulo(numero_pedido);
							int opcion = 0;
							while (opcion == 0) {
								opcion = Leer.pedirEntero(
										"Seleccione 1 si quiere añadir al pedido todos los articulos, o 2 si quiere eliminar por completo",
										"^[1-2]{1}");
								if (opcion == 1) {
									NodeList articulos2 = pedido.getElementsByTagName("articulo");
									for (int j = 0; j < articulos2.getLength(); j++) {
										Node arti = articulos2.item(j);
										if (arti.getNodeType() == Node.ELEMENT_NODE) {
											Element articulo = (Element) arti;
											Integer cantidad = Integer.parseInt(getNodo("cantidad", articulo));
											Long codigo_articulo = Long.valueOf(getNodo("codigo", articulo));
											if (AccesoDatos.comprobarPedido_articulo(numero_pedido,
													codigo_articulo) != 1) {
												AccesoDatos.insertarPedido_articulo(numero_pedido, codigo_articulo,
														cantidad);
											}
											// aqui se podria añadir mas modificaciones, en caso de que el pedido
											// tuviese ya el articulo, añadir o ignorar
										}
									}
								} else if (opcion == 2) {
									AccesoDatos.eliminarPedido(numero_pedido);
									System.out.println("Pedido eliminado");
								}
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
