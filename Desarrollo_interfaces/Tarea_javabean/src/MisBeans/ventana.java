package MisBeans;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Rectangle;

public class ventana extends JFrame{
	public ventana() {
		setBounds(new Rectangle(0, 0, 600, 400));
		
		InclusionImagen inclusionImagen = new InclusionImagen();
		inclusionImagen.setLaImagen("C:\\Users\\Juan\\Desktop\\2 Dam\\2-Dam\\Desarrollo_interfaces\\Tarea_javabean\\imagen.jpg");
		inclusionImagen.setVisible(true);
		getContentPane().add(inclusionImagen, BorderLayout.CENTER);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
