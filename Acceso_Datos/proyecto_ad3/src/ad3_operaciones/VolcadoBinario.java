package ad3_operaciones;

import java.io.InputStream;
import java.io.IOException;

public class VolcadoBinario {

  static int TAM_FILA = 32;
  static int MAX_BYTES = 2048;
  InputStream is = null;

  public VolcadoBinario(InputStream is) {
    this.is = is;
  }

  public void volcar() throws IOException {
    byte buffer[] = new byte[TAM_FILA];
    int bytesLeidos;
    int offset = 0;
    do {
      bytesLeidos = is.read(buffer);
      System.out.format("[%5d]", offset);
      for (int i = 0; i < bytesLeidos; i++) {
        System.out.format(" %2x", buffer[i]);
      }
      offset += bytesLeidos;
      System.out.println();
    } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
  }
}
