import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Dialog_solution extends JDialog {

	private TreeMap<Integer, JLabel> mapaImagenes = new TreeMap<Integer, JLabel>();
	private TreeMap<Integer, ArrayList<Integer>> mapa_generado = new TreeMap<Integer, ArrayList<Integer>>();
	private JFrame frame_principal;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Dialog_solution(JFrame frame_principal) {
		this.frame_principal = frame_principal;
		setBounds(100, 100, 917, 791);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);

		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = (screenWidth) / 2;
		int y = (screenHeight - getHeight()) / 2;
		setLocation(x, y);

		int posX = -150;
		int posY = 0;
		int separadorAncho = 5; // Ancho del separador

		for (int i = 0; i < 36; i++) {
			if (i % 6 == 0 && i != 0) {
				posY += 125;
				posX = -150;
			}
			posX += 150;
			JLabel label = new JLabel();
			label.setBounds(posX, posY, 150, 125);
			contentPanel.add(label);
			mapaImagenes.put(i, label);

			// Agregar separadores horizontales
			if (i < 6) {
				JSeparator separatorVertical = new JSeparator(SwingConstants.VERTICAL);
				separatorVertical.setBounds(posX + 150, posY, separadorAncho, screenHeight);
				separatorVertical.setForeground(Color.BLACK);
				separatorVertical.setBackground(Color.BLACK);
				contentPanel.add(separatorVertical);
			}

			// Agregar separadores verticales
			if (i % 6 == 0) {
				JSeparator separatorHorizontal = new JSeparator(SwingConstants.HORIZONTAL);
				separatorHorizontal.setBounds(posX, posY + 125, screenWidth, separadorAncho);
				separatorHorizontal.setForeground(Color.BLACK);
				separatorHorizontal.setBackground(Color.BLACK);
				contentPanel.add(separatorHorizontal);
				if (i == 0) {
					separatorHorizontal = new JSeparator(SwingConstants.HORIZONTAL);
					separatorHorizontal.setBounds(posX, posY, screenWidth, separadorAncho);
					separatorHorizontal.setForeground(Color.BLACK);
					separatorHorizontal.setBackground(Color.BLACK);
					contentPanel.add(separatorHorizontal);
				}
			}
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
