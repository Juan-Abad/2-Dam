package ad2_aplicacion;

import java.io.File;
import java.io.IOException;

import ad2_excepciones.ExcepcionesConThrows;

public class ConThrows {

  public static void main(String[] args) {

    try {
      File ft = new ExcepcionesConThrows().creaFicheroTempConCar("AAAA_", 'A', 20);
      System.out.println("Creado fichero: " + ft.getAbsolutePath());
      ft.delete();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

}
