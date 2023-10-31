package ad3_aplicacion;

import ad3_operaciones.FicheroAccesoAleatorio;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

public class Ejecutable_FicheroAccesoAleatorio {

    public static void main(String[] args) {

        List campos = new ArrayList();
        campos.add(new Pair("DNI", 9));
        campos.add(new Pair("NOMBRE", 32));
        campos.add(new Pair("CP", 5));

        try {
            FicheroAccesoAleatorio faa = new FicheroAccesoAleatorio("fic_acceso_aleat.dat", campos);
            Map reg = new HashMap();
            reg.put("DNI", "56789012B");
            reg.put("NOMBRE", "SAMPER");
            reg.put("CP", "29730");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "89012345E");
            reg.put("NOMBRE", "ROJAS");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "23456789D");
            reg.put("NOMBRE", "DORCE");
            reg.put("CP", "13700");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "78901234X");
            reg.put("NOMBRE", "NADALES");
            reg.put("CP", "44126");
            faa.insertar(reg, 1);
      faa.insertar(reg,25);  // Probarlo, interesante
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
