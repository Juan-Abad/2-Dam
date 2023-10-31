package ad3_aplicacion;

import ad3_operaciones.VolcadoBinario;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Ejecutable_VolcadoBinario {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("No se ha indicado ningún fichero");
      return;
    }

    String nomFich = args[0];

    try (FileInputStream fis = new FileInputStream(nomFich)) {
      VolcadoBinario vb = new VolcadoBinario(fis);
      System.out.println("Volcado binario de "+nomFich);
      vb.volcar();
    } catch (FileNotFoundException e) {
      System.err.println("ERROR: no existe fichero " + nomFich);
    } catch (IOException e) {
      System.err.println("ERROR de E/S: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
