import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class EscribirDom {
	

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		
		ArrayList <Coche> lista=new ArrayList <>();
		lista.add(new Coche("seat","verde"));
		lista.add(new Coche("Volkswagen","gris"));
		lista.add(new Coche("JhonDeere","verde"));
		//Voy a crear unarchivo xml con los coches del Array
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		
		// definimos el elemento ra�z del documento
		Element eRaiz = doc.createElement("concesionario");
		doc.appendChild(eRaiz);
		Element eCoche,eMarca,eColor;
		
		for(Coche c:lista) {
			eCoche = doc.createElement("coche");
			eRaiz.appendChild(eCoche);
			eMarca = doc.createElement("marca");
			eMarca.appendChild(doc.createTextNode(c.getMarca()));
			eCoche.appendChild(eMarca);
			eColor = doc.createElement("color");
			eColor.appendChild(doc.createTextNode(c.getColor()));
			eCoche.appendChild(eColor);		
		}
		// clases necesarias finalizar la creaci�n del archivo XML
		  TransformerFactory transformerFactory = TransformerFactory.newInstance();
		  Transformer transformer = transformerFactory.newTransformer();
		  DOMSource source = new DOMSource(doc);
		  StreamResult result = new StreamResult(new File("cochesCreado.xml"));
		  transformer.transform(source, result);
       
	}

}
