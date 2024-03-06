import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlSocketClient {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Cambia la direcci�n del servidor seg�n tu configuraci�n
        int serverPort = 12345; // Cambia el puerto seg�n tu configuraci�n

        try {
            // Conectamos al servidor
            Socket socket = new Socket(serverAddress, serverPort);

            // Creamos un BufferedReader para leer desde el InputStream del socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Leemos y almacenamos el XML desde el servidor
            StringBuilder xmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlContent.append(line).append("\n");
            }
            //Imprimo el xml
            System.out.println(xmlContent.toString());

            // Cerramos la conexi�n
            socket.close();

            // Procesamos el XML utilizando DOM para reconstruir la lista de coches
            ArrayList<Coche> lista = parseXmlContent(xmlContent.toString());
            // Ahora 'lista' contiene la lista de coches reconstruida a partir del XML

            // Imprimimos la lista reconstruida
            for (Coche coche : lista) {
                System.out.println(coche.getMarca() + " - " + coche.getColor());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Coche> parseXmlContent(String xmlContent) {
        ArrayList<Coche> lista = new ArrayList<>();

        try {
            // Creamos un DocumentBuilder para parsear el XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xmlContent)));
            doc.getDocumentElement().normalize();

            // Obtenemos la lista de nodos 'coche'
            NodeList cocheNodes = doc.getElementsByTagName("coche");

            // Iteramos sobre los nodos 'coche' y construimos objetos Coche
            for (int i = 0; i < cocheNodes.getLength(); i++) {
                Element cocheElement = (Element) cocheNodes.item(i);
                String marca = cocheElement.getElementsByTagName("marca").item(0).getTextContent();
                String color = cocheElement.getElementsByTagName("color").item(0).getTextContent();
                lista.add(new Coche(marca, color));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}