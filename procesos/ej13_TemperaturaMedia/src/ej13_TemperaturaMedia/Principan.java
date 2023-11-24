package ej13_TemperaturaMedia;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principan {

    public static void main(String[] args) {
        File directorioPrincipal = new File(".\\src\\ej13_TemperaturaMedia");
        int temperaturaMedia;
        ProcessBuilder processBuilder;
        for(int i = 0; i < 19; i++) {
            processBuilder = new ProcessBuilder("java", "Principal.java");
            processBuilder.directory(directorioPrincipal);
            try {
                Process proceso = processBuilder.start();
                // Captura la salida del proceso
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream())); // Capturar la salida de error
                String linea;
                while ((linea = reader.readLine()) != null) {
                    // Muestra la salida de Principal
                    System.out.println("Salida de Principal: " + linea);
                    // Puedes asignar el valor a temperaturaMedia si es lo que necesitas
                    // temperaturaMedia = Integer.parseInt(linea);
                }
                // Muestra la salida de error, si la hay
                while ((linea = errorReader.readLine()) != null) {
                    System.err.println("Error de Principal: " + linea);
                }
                // Espera a que el proceso termine
                proceso.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
