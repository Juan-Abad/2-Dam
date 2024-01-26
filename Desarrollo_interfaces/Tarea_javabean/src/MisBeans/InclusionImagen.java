package MisBeans;

import java.awt.Dimension;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class InclusionImagen extends JLabel {

	private File laImagen = null;

	public InclusionImagen() {
		setBorder(BorderFactory.createEtchedBorder());
	}

	public Dimension getPreferredSize() {
		return new Dimension(600, 400);
	}

	public String getLaImagen() {
		if(laImagen==null) return null;
		else return laImagen.getPath();
	}

	public void setLaImagen(File laImagen) {
		this.laImagen = laImagen;
	}
	
	
}
