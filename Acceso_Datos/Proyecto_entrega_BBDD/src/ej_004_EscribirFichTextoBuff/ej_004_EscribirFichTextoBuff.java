package ej_004_EscribirFichTextoBuff;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class ej_004_EscribirFichTextoBuff {
    public static void main(String[] args) {
        try {//controla las excepciones que pueda provocar al crear el BufferedWriter
        	File archivo = new File("src\\ej_003_LeerFichTextoBuff\\archivo.txt");//ruta al archivo donde vamos a escribir, se instancia un objeto File
        	BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));//creamos el BufferedWriter, pasandole un FileWriter y a este el objeto File
            for(int i=1; i<=10; i++) {//bucle for, recorre del 1 al 10
            	bw.write(Integer.toString(i));//en cada iteracion se escribe en el archivo el numero de la iteracion, se pasa a String para que se escriba correctamente
            }
            bw.close();//se cierra el BufferWriter para liberar el buffer del txt, si no se hace no se escribira, o no podremos aabrir el txt de nuevo
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());//mensaje de error en caso de que salte una exepcion
        }
    }
}

