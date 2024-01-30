package MisBeans;

import java.awt.Dimension;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class InclusionImagen extends JLabel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File laImagen = null;

	public InclusionImagen() {
		setBorder(BorderFactory.createEtchedBorder());
	}

	public Dimension getPreferredSize() {
		return new Dimension(600, 400);
	}

	public String getLaImagen() {
		if (laImagen == null)
			return null;
		else
			return laImagen.getPath();
	}

	public void setLaImagen(String escogeImagen) {
		try {
			laImagen = new File(escogeImagen);
			setIcon(new ImageIcon(ImageIO.read(laImagen)));
		} catch (Exception IO) {
			laImagen = null;
			setIcon(null);
		}
	}

}
