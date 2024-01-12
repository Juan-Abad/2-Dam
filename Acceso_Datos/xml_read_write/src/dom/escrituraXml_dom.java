package dom;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class escrituraXml_dom {

	public static void main(String[] args) throws IOException{
		File fichero = new File("AleatorioEmple.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int id, dep, posicion = 0;
		Double salario;
		char apellido[] = new char[10], aux;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Empleados", null);
			document.setXmlVersion("1.0");
			
			for(;;) {
				file.seek(posicion);
				id=file.readInt();
				for(int i=0; i<apellido.length; i++) {
					aux=file.readChar();
					apellido[i]=aux;
				}
				String apellidos = new String(apellido);
				dep=file.readInt();
				salario=file.readDouble();
				
				if(id>0) {
					Element raiz = document.createElement("empleado");
					document.getDocumentElement().appendChild(raiz);
					CrearElemento("id", Integer.toString(id), raiz, document);
					CrearElemento("apellido", apellidos.trim(), raiz, document);
					CrearElemento("dep", Integer.toString(dep), raiz, document);
					CrearElemento("salario", Double.toString(salario), raiz, document);
				}
				posicion = posicion + 36;
				if(file.getFilePointer() == file.length()) break;
			}
		}catch(Exception e) {
			System.err.println("Error: "+e);
			file.close();
		}

	}
	static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		raiz.appendChild(text);
	}

}
