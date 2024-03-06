import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EscribirDomSocket {

    public static void main(String[] args) {
        int port = 12345; // Puedes cambiar el puerto seg�n tus necesidades

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Esperando conexi�n en el puerto " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + socket.getInetAddress());

                // Llamamos a la funci�n para escribir el XML en el OutputStream del socket
                writeToSocket(socket.getOutputStream());

                // Cerramos la conexi�n despu�s de escribir
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToSocket(OutputStream outputStream) {
        try {
            // Creamos un PrintWriter para escribir en el OutputStream del socket
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));

            // Creamos el documento XML y lo escribimos en el PrintWriter
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            
            // definimos el elemento ra�z del documento
            Element eRaiz = doc.createElement("concesionario");
            doc.appendChild(eRaiz);
            Element eCoche, eMarca, eColor;
            
            // Tu c�digo para agregar coches al XML
            ArrayList<Coche> lista = new ArrayList<>();
            lista.add(new Coche("seat", "verde"));
            lista.add(new Coche("Volkswagen", "gris"));
            lista.add(new Coche("JhonDeere", "verde"));
            
            for (Coche c : lista) {
                eCoche = doc.createElement("coche");
                eRaiz.appendChild(eCoche);
                eMarca = doc.createElement("marca");
                eMarca.appendChild(doc.createTextNode(c.getMarca()));
                eCoche.appendChild(eMarca);
                eColor = doc.createElement("color");
                eColor.appendChild(doc.createTextNode(c.getColor()));
                eCoche.appendChild(eColor);
            }

            // Transformamos el documento XML y lo escribimos en el PrintWriter
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);

            // Limpiamos el buffer y cerramos el PrintWriter
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}