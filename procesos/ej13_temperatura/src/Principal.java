

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Principal {

	public static void main(String[] args) {
		 int suma=0;
		    final int NUMEROCIUDADES=20;
		    
		    for(int i=0; i<NUMEROCIUDADES; i++) {
		    	suma+=lanzarProceso(String.valueOf((int)(Math.random()*50)+50));
		    }
		    System.out.println("La media de las ciudades es: "+(suma/NUMEROCIUDADES));
	}

	public static int lanzarProceso(String ciudad) {
	    	
	    	File f = new File(".\\bin");
	    	System.out.println(f.getAbsolutePath());
	    	ProcessBuilder b = new ProcessBuilder("java","Temperatura",ciudad);
	    	b.directory(f);
	    	
	    	String resultado = "";
	    	
	    	Process p;
			try {
				p = b.start();
				InputStream salida = p.getInputStream();
		    	
		    	int letra = salida.read();
		    	while(letra !=-1){
		    		resultado += (char)(letra);
		    		letra = salida.read();
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	System.out.println("La ciudad "+ciudad+" tiene una temperatura de "+resultado);
	    	
	    	return 1;
	    }

}
