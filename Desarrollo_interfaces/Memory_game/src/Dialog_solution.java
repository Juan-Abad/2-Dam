import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class Dialog_solution extends JDialog {

	private TreeMap<Integer, JLabel> mapaImagenes = new TreeMap<Integer, JLabel>();
	private TreeMap<Integer, ArrayList<Integer>> mapa_generado = new TreeMap<Integer, ArrayList<Integer>>();
	private JFrame frame_principal;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public Dialog_solution(JFrame frame_principal) {
		this.frame_principal = frame_principal;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = (screenWidth) / 2;
		int y = (screenHeight - getHeight()) / 2;
		setLocation(x, y);

		int posX = -150;
		int posY = 27;

		for (int i = 0; i < 36; i++) {
			if (i % 6 == 0 && i != 0) {
				posY += 125;
				posX = -150;
			}
			posX += 150;
			JLabel label = new JLabel("");
			label.setBounds(posX, posY, 150, 125);
			mapaImagenes.put(i, label);
			contentPanel.add(label);
		}
		crear_solucion();
	}

	public void crear_solucion() {
		this.mapa_generado = ((Frame_principal) this.frame_principal).getMapa_generado();
		for (Integer index : mapa_generado.keySet()) {
			mapaImagenes.get(mapa_generado.get(index).get(0))
					.setIcon(((Frame_principal) frame_principal).redimensionar_imagen("imagenes\\" + index + ".jpeg"));
			mapaImagenes.get(mapa_generado.get(index).get(1))
					.setIcon(((Frame_principal) frame_principal).redimensionar_imagen("imagenes\\" + index + ".jpeg"));
		}
	}

}
