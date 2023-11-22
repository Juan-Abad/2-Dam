import java.awt.Color;
import java.awt.EventQueue;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JLabel;

public class Frame_principal extends JFrame {

	static TreeMap<Integer, JLabel> mapaImagenes = new TreeMap<Integer, JLabel>();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_principal frame = new Frame_principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame_principal() {
		setTitle("Juego de memoria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 837);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 87, 16);
		contentPane.add(toolBar);

		int posX = -150;
		int posY = 27;
		for (int i = 0; i < 36; i++) {
			if (i % 6 == 0 && i != 0) {
				posY += 125;
				posX = -150;
			}
			posX += 150;
			JLabel label = new JLabel(Integer.toString(i));
			label.setBounds(posX, posY, 150, 125);
			contentPane.add(label);
			mapaImagenes.put(i, label);
		}

	}

}
