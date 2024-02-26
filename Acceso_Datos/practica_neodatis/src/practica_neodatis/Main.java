package practica_neodatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Informes.Informes;

public class Main {
	
	private static ODB odb;

    public static void main(String[] args) {
    	Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtener la ruta del archivo XML desde el archivo de configuración
        String ruta = properties.getProperty("ruta"); //obtiene la ruta del archivo de configuración
        if (ruta == null) { //comprueba que la ruta del archivo de configuración no es nula, si lo es se termina el programa
            System.err.println("La propiedad 'ruta' no está definida en el archivo de configuración.");
            System.exit(0);
        }
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("El archivo no existe, base de datos creada");
        }
        odb = ODBFactory.open(ruta);
        //insertarBBDD();
        Informes informes = new Informes(odb);
        //informes.Num_pedidos_procesados();
        //informes.Num_lineas_pedido_procesadas();
        //informes.List_articulos_unicos();
        //informes.List_clientes_con_pedidos();
        //informes.List_cantidad_pedida_de_cada_articulo();
        //informes.List_unidades_pedidas_por_pedido();
        //informes.Media_articulos_por_pedido();
        informes.Listado_pedidos();
        odb.close();
    }

    public static void insertarBBDD() {
        Map<String, ArrayList<Object>> mapa_objetos = new LinkedHashMap<String, ArrayList<Object>>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("xml_pedidosDB.xml"));
            document.getDocumentElement().normalize();
            NodeList tablas = document.getElementsByTagName("table");

            ArrayList<Object> lista_articulo = new ArrayList<Object>();
            ArrayList<Object> lista_clientes = new ArrayList<Object>();
            ArrayList<Object> lista_pedidoArticulo = new ArrayList<Object>();
            ArrayList<Object> lista_pedidos = new ArrayList<Object>();
            for (int i = 0; i < tablas.getLength(); i++) {
                Node tablaNode = tablas.item(i);
                if (tablaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tabla = (Element) tablaNode;
                    String nombreTabla = getNodo("name", tabla);
                    NodeList filas = tabla.getElementsByTagName("row");
                    for (int k = 0; k < filas.getLength(); k++) {
                        Element fila = (Element) filas.item(k);
                        NodeList valores = fila.getElementsByTagName("value");
                        if (nombreTabla.equals("articulos")) {
                            Long codigo_articulo = null;
                            String descripcion = null;
                            String familia = null;
                            Long stock = null;
                            for (int j = 0; j < valores.getLength(); j++) {
                                Node val = valores.item(j);
                                if (val.getNodeType() == Node.ELEMENT_NODE) {
                                    Element valor = (Element) val;
                                    Long valorColumna = Long.valueOf(valor.getAttribute("column"));
                                    if (valorColumna == 0) {
                                        codigo_articulo = Long.valueOf(valor.getTextContent());
                                    } else if (valorColumna == 1) {
                                        descripcion = valor.getTextContent();
                                    } else if (valorColumna == 2) {
                                        familia = valor.getTextContent();
                                    } else if (valorColumna == 3) {
                                        stock = Long.valueOf(valor.getTextContent());
                                    }
                                }
                            }
                            lista_articulo.add(new Articulo(codigo_articulo, descripcion, familia, stock));
                        } else if (nombreTabla.equals("clientes")) {
                            Long numero_cliente = null;
                            String nombre = null;
                            String direccion = null;
                            Long telefono = null;
                            for (int j = 0; j < valores.getLength(); j++) {
                                Node val = valores.item(j);
                                if (val.getNodeType() == Node.ELEMENT_NODE) {
                                    Element valor = (Element) val;
                                    Long valorColumna = Long.valueOf(valor.getAttribute("column"));
                                    if (valorColumna == 0) {
                                        numero_cliente = Long.valueOf(valor.getTextContent());
                                    } else if (valorColumna == 1) {
                                        nombre = valor.getTextContent();
                                    } else if (valorColumna == 2) {
                                        direccion = valor.getTextContent();
                                    } else if (valorColumna == 3) {
                                        telefono = Long.valueOf(valor.getTextContent());
                                    }
                                }
                            }
                            lista_clientes.add(new Cliente(numero_cliente, nombre, direccion, telefono));
                        } else if (nombreTabla.equals("pedido_articulo")) {
                            Long numero_pedido = null;
                            Long codigo_articulo2 = null;
                            Integer cantidad = null;
                            for (int j = 0; j < valores.getLength(); j++) {
                                Node val = valores.item(j);
                                if (val.getNodeType() == Node.ELEMENT_NODE) {
                                    Element valor = (Element) val;
                                    Long valorColumna = Long.valueOf(valor.getAttribute("column"));
                                    if (valorColumna == 0) {
                                        numero_pedido = Long.valueOf(valor.getTextContent());
                                    } else if (valorColumna == 1) {
                                        codigo_articulo2 = Long.valueOf(valor.getTextContent());
                                    } else if (valorColumna == 2) {
                                        cantidad = Integer.valueOf(valor.getTextContent());
                                    }
                                }
                            }
                            lista_pedidoArticulo.add(new PedidoArticulo(numero_pedido, codigo_articulo2, cantidad));
                        } else if (nombreTabla.equals("pedidos")) {
                            Long numero_pedido2 = null;
                            Long numero_cliente2 = null;
                            String fecha = null;
                            for (int j = 0; j < valores.getLength(); j++) {
                                Node val = valores.item(j);
                                if (val.getNodeType() == Node.ELEMENT_NODE) {
                                    Element valor = (Element) val;
                                    Long valorColumna = Long.valueOf(valor.getAttribute("column"));
                                    if (valorColumna == 0) {
                                        numero_pedido2 = Long.valueOf(valor.getTextContent());
                                    } else if (valorColumna == 1) {
                                        numero_cliente2 = Long.valueOf(valor.getTextContent());
                                    } else if (valorColumna == 2) {
                                        fecha = valor.getTextContent();
                                    }
                                }
                            }
                            lista_pedidos.add(new Pedido(numero_pedido2, numero_cliente2, fecha));
                        }
                    }
                }
            }
            mapa_objetos.put("articulos", lista_articulo);
            mapa_objetos.put("clientes", lista_clientes);
            mapa_objetos.put("pedido_articulo", lista_pedidoArticulo);
            mapa_objetos.put("pedidos", lista_pedidos);
            
            for(String e: mapa_objetos.keySet()) {
            	for(Object o: mapa_objetos.get(e)) {
            		odb.store(o);
            	}
            }
        } catch (Exception e) {
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
}
