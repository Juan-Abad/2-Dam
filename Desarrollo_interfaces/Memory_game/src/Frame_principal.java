import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class Frame_principal extends JFrame {

	private TreeMap<Integer, JLabel> mapaImagenes = new TreeMap<Integer, JLabel>();
	private TreeMap<Integer, ArrayList<Integer>> mapa_generado = new TreeMap<Integer, ArrayList<Integer>>();
	private Integer turno = 0;
	private Integer pos_imagen_seleccionada;
	private Dialog_playAgain play_again;
	private Dialog_solution solution_dialog;

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
		
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = (screenWidth - getWidth()) / 2;
		int y = (screenHeight - screenHeight/2 - getHeight()*2) / 2;
		
		setBounds(x, y, 917, 837);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 0, 139, 22);
		comboBox.addItem("Reiniciar"); // Agrega el ítem "Reiniciar"
		comboBox.addItem("Jugar nueva partida");
		comboBox.addItem("Mostrar Solución");
		comboBox.addItem("Quitar Solución");
		comboBox.addItem("Salir");

		comboBox.setSelectedIndex(-1);
		comboBox.setRenderer(new TitleComboBoxRenderer("Opciones"));
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedItem = (String) combo.getSelectedItem();
				if (selectedItem != null) {
					switch (selectedItem) {
					case "Reiniciar":
						resetGame();
						break;
					case "Jugar nueva partida":
						resetGame();
						mapa_generado.clear();
						generarJuego();
						solution_dialog.crear_solucion();
						break;
					case "Mostrar Solución":
						solution_dialog.setVisible(true);
						break;
					case "Quitar Solución":
						if (solution_dialog.isVisible())
							solution_dialog.setVisible(false);
						break;
					case "Salir":
						if (solution_dialog.isVisible())
							solution_dialog.setVisible(false);
						play_again.dispose();
						dispose();
						break;
					}
				}
			}
		});
		contentPane.add(comboBox);

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

		play_again = new Dialog_playAgain(Frame_principal.this);
		solution_dialog = new Dialog_solution(Frame_principal.this);
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
									mapaImagenes.get(index).setIcon(redimensionar_imagen("imagenes\\" + i + ".jpeg"));
									pos_imagen_seleccionada = mapa_generado.get(i).get(0);
									turno = 1;
								} else if (mapa_generado.get(i).get(1).equals(index)) {
									mapaImagenes.get(index).setIcon(redimensionar_imagen("imagenes\\" + i + ".jpeg"));
									pos_imagen_seleccionada = mapa_generado.get(i).get(1);
									turno = 1;
								}
							}
						} else {
							for (Integer i : mapa_generado.keySet()) {
								if (mapa_generado.get(i).contains(index)) {
									mapaImagenes.get(index).setIcon(redimensionar_imagen("imagenes\\" + i + ".jpeg"));
									turno = 0;
								}
							}
							SwingUtilities.invokeLater(() -> {
								boolean imagenes_correspondientes = true;
								for (Integer i : mapa_generado.keySet()) {
									if (mapa_generado.get(i).contains(index)
											&& mapa_generado.get(i).contains(pos_imagen_seleccionada)) {
										imagenes_correspondientes = true;
										break;
									} else {
										imagenes_correspondientes = false;
									}
								}
								if (!imagenes_correspondientes) {
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
									// Acción a realizar después de un pequeño retraso
									mapaImagenes.get(pos_imagen_seleccionada).setIcon(null);
									mapaImagenes.get(index).setIcon(null);
									turno = 0;
									pos_imagen_seleccionada = null;
								}
							});
						}
					}
				}
				if (comprobar_partida_ganada())
					play_again.setVisible(true);
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

	public void resetGame() {
		for (Integer index : mapaImagenes.keySet())
			mapaImagenes.get(index).setIcon(null);
	}

	public boolean comprobar_partida_ganada() {
		boolean partida_ganada = true;
		for (Integer index : mapaImagenes.keySet()) {
			if (mapaImagenes.get(index).getIcon() == null)
				partida_ganada = false;
		}
		return partida_ganada;
	}
	
	public static ImageIcon redimensionar_imagen(String ruta) {
		ImageIcon icon = new ImageIcon(ruta); // Carga la imagen
		Image image = icon.getImage(); // Obtén la imagen del ImageIcon

		// Redimensiona la imagen a un tamaño específico
		int anchoDeseado = 150; // Ancho deseado en píxeles
		int altoDeseado = 170;  // Alto deseado en píxeles
		Image imagenRedimensionada = image.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);

		// Crea un nuevo ImageIcon con la imagen redimensionada
		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		return iconoRedimensionado;
	}

	public TreeMap<Integer, JLabel> getMapaImagenes() {
		return mapaImagenes;
	}

	public TreeMap<Integer, ArrayList<Integer>> getMapa_generado() {
		return mapa_generado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public MouseAdapter getAdapter() {
		return adapter;
	}

	public Dialog_playAgain getPlay_again() {
		return play_again;
	}

	public Dialog_solution getSolution_dialog() {
		return solution_dialog;
	}

}
