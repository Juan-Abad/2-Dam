package Graphic_game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Semaphore;

public class MiFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JLabel[] labels = new JLabel[9]; // Utilizamos un array para almacenar los JLabels

	private static Integer sizeGame = 3;
	private boolean isPlayerX = true; // Variable para determinar el jugador actual

	private Semaphore semaphore;
	private int filaJugada;
	private int columnaJugada;
	private boolean pause = false;

	/**
	 * Create the frame.
	 */
	public MiFrame(Boolean isPlayerX, Semaphore semaphore) {
		this.isPlayerX = isPlayerX;
		this.semaphore = semaphore;
		
		if(this.isPlayerX) {
			pause = false;
		}else {
			pause = true;
		}
		
		setTitle("" + isPlayerX);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes\\tic-tac-toe-game-icon.jpg"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 0, 139, 22);
		comboBox.addItem("Reiniciar"); // Agrega el ítem "Reiniciar"

		comboBox.setSelectedIndex(-1);

		comboBox.addActionListener(e -> {
			String selectedItem = (String) comboBox.getSelectedItem();
			if (selectedItem != null && selectedItem.equals("Reiniciar")) {
				resetGame(); // Llama al método resetGame() cuando se selecciona "Reiniciar"
			}
		});
		contentPane.add(comboBox);

		JPanel panel = new JPanel();
		panel.setBounds(0, 33, 606, 357);
		contentPane.add(panel);
		panel.setLayout(null);

		int x = 0;
		int y = 0;
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel("");
			labels[i].setFont(new Font("Tahoma", Font.BOLD, 40));
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			labels[i].setBounds(x, y, 195, 126);
			labels[i].addMouseListener(adapter);
			panel.add(labels[i]);
			x += 195;
			if ((i + 1) % 3 == 0) {
				x = 0;
				y += 126;
			}
		}

		JSeparator separator = new JSeparator();
		separator.setOpaque(true);
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(399, 0, 5, 350);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOpaque(true);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(190, 0, 5, 350);
		panel.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setOpaque(true);
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(0, 121, 605, 5);
		panel.add(separator_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setOpaque(true);
		separator_2_1.setForeground(Color.BLACK);
		separator_2_1.setBackground(Color.BLACK);
		separator_2_1.setBounds(0, 242, 605, 5);
		panel.add(separator_2_1);
	}

	MouseAdapter adapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!pause) {
				JLabel label = (JLabel) e.getSource();
				if (label.getText().isEmpty()) {
					String simbolo = "";
					if (isPlayerX) {
						simbolo = "X";
					} else {
						simbolo = "O";
					}

					label.setText(simbolo);

					int index = -1;
					for (int i = 0; i < labels.length; i++) {
						if (labels[i] == label) {
							index = i;
							break;
						}
					}
					// Calcular la fila y la columna
					int fila = index / 3;
					int columna = index - fila * 3;

					setFilaJugada(fila);
					setColumnaJugada(columna);

					// Liberar el semáforo para permitir que el hilo receptor continue
					semaphore.release();
				}
			}
		}
	};

	public void jugada_oponente(int fila, int columna) {
		// labels[(fila+columna)].setText(isPlayerX ? "X" : "O");
		if (isPlayerX) {
			labels[((fila * 3) + columna)].setText("O");
		} else {
			labels[((fila * 3) + columna)].setText("X");
		}
	}

	public void resetGame() {
		for (JLabel label : labels) {
			label.setText(""); // Reinicia todos los labels
		}
		isPlayerX = true; // Vuelve a establecer el primer jugador como X
	}

	public void pauseJuego() {
		this.pause = true;
	}

	public void unpauseJuego() {
		this.pause = false;
	}

	public void desHacerJugadaAnterior() {
		labels[getColumnaJugada() + getFilaJugada()].setText("");
	}

	public int getFilaJugada() {
		return filaJugada;
	}

	public void setFilaJugada(int filaJugada) {
		this.filaJugada = filaJugada;
	}

	public int getColumnaJugada() {
		return columnaJugada;
	}

	public void setColumnaJugada(int columnaJugada) {
		this.columnaJugada = columnaJugada;
	}
}
