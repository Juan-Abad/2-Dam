import javax.help.*;
import java.net.URL;

public class MiAyuda {
    public static void main(String[] args) {
        try {
            ClassLoader cl = MiAyuda.class.getClassLoader();
            HelpSet hs = new HelpSet(null, new URL("jar:file:MiAyuda.jar!/helpset.hs"));
            HelpBroker hb = hs.createHelpBroker();

            hb.setDisplayed(true);
        } catch (Exception e) {
            System.out.println("Error al cargar la ayuda: " + e);
        }
    }
}