import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JLabel;

public class Frame_principal extends JFrame {

	static TreeMap<Integer, JLabel> mapaImagenes = new TreeMap<Integer, JLabel>();
	static TreeMap<Integer, ArrayList<Integer>> mapa_generado = new TreeMap<Integer, ArrayList<Integer>>();
	static Integer turno = 0;
	static Integer pos_imagen_seleccionada;

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
			label.addMouseListener(adapter);
			contentPane.add(label);
			mapaImagenes.put(i, label);
		}
		generarJuego();
	}

	MouseAdapter adapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (((JLabel) (e.getSource())).getIcon() == null) {
				for (Integer index : mapaImagenes.keySet()) {
					if (mapaImagenes.get(index).equals((JLabel) (e.getSource()))) {
						if (turno == 0) {
							for (Integer i : mapa_generado.keySet()) {
								if (mapa_generado.get(i).get(0).equals(index)) {
									System.out.println("ac");
									mapaImagenes.get(index).setIcon(new ImageIcon("imagenes\\" + i + ".jpeg"));
									pos_imagen_seleccionada = mapa_generado.get(i).get(0);
									turno = 1;
								} else if (mapa_generado.get(i).get(1).equals(index)) {
									System.out.println("al");
									mapaImagenes.get(index).setIcon(new ImageIcon("imagenes\\" + i + ".jpeg"));
									pos_imagen_seleccionada = mapa_generado.get(i).get(1);
									turno = 1;
								}
							}
						} else {
							for (Integer i : mapa_generado.keySet()) {
								System.out.println("..");
								if (mapa_generado.get(i).get(0) == index) {
									System.out.println("a");
									mapaImagenes.get(index).setIcon(new ImageIcon("imagenes\\" + i + ".jpeg"));
									pos_imagen_seleccionada = index;
									turno = 0;
								} else if (mapa_generado.get(i).get(1) == index) {
									System.out.println("b");
									mapaImagenes.get(index).setIcon(new ImageIcon("imagenes\\" + i + ".jpeg"));
									pos_imagen_seleccionada = index;
									turno = 0;
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							mapaImagenes.get(index).setIcon("imagenes\\" + 19 + ".jpeg");
							mapaImagenes.get(pos_imagen_seleccionada).setIcon(null);
							for (Integer i : mapa_generado.keySet()) {
								if (mapa_generado.get(i).get(0) == pos_imagen_seleccionada
										&& mapa_generado.get(i).get(1) != pos_imagen_seleccionada) {
									mapaImagenes.get(index).setIcon(null);
									mapaImagenes.get(pos_imagen_seleccionada).setIcon(null);
									turno = 0;
									pos_imagen_seleccionada = null;
								} else if (mapa_generado.get(i).get(1) == pos_imagen_seleccionada
										&& mapa_generado.get(i).get(0) != pos_imagen_seleccionada) {
									mapaImagenes.get(index).setIcon(new ImageIcon(""));
									mapaImagenes.get(pos_imagen_seleccionada).setIcon(new ImageIcon(""));
									turno = 0;
									pos_imagen_seleccionada = null;
								}
							}
						}
					}
				}
			}
		}
	};

	public void generarJuego() {
		int posicion_aleatoria_1, posicion_aleatoria_2;
		boolean pos_ocupada = false;
		for (int i = 0; i < mapaImagenes.size() / 2; i++) {
			do {
				posicion_aleatoria_1 = (int) (Math.random() * mapaImagenes.size());
				for (Integer index : mapa_generado.keySet()) {
					if (mapa_generado.get(index).contains(posicion_aleatoria_1)) {
						pos_ocupada = true;
						break;
					} else {
						pos_ocupada = false;
					}
				}
			} while (pos_ocupada);
			pos_ocupada = false;
			do {
				posicion_aleatoria_2 = (int) (Math.random() * mapaImagenes.size());
				for (Integer index : mapa_generado.keySet()) {
					if (mapa_generado.get(index).contains(posicion_aleatoria_2)) {
						pos_ocupada = true;
						break;
					} else {
						pos_ocupada = false;
					}
				}
			} while (posicion_aleatoria_2 == posicion_aleatoria_1 || pos_ocupada);
			pos_ocupada = false;
			mapa_generado.put(i, new ArrayList<Integer>());
			mapa_generado.get(i).add(posicion_aleatoria_1);
			mapa_generado.get(i).add(posicion_aleatoria_2);
		}
	}
}
