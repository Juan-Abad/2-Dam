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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class MiFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;

	private Integer turno = 0;

	private static Integer sizeGame = 3;

	private static Integer size_x = 3;
	private static Integer size_y = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiFrame frame = new MiFrame();
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
	public MiFrame() {
		setTitle("El fantástico Juego del 3 en Raya");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"imagenes\\tic-tac-toe-game-icon.jpg"));
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
		comboBox.setRenderer(new TitleComboBoxRenderer("Opciones"));

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedItem = (String) combo.getSelectedItem();
				if (selectedItem != null && selectedItem.equals("Reiniciar")) {
					resetGame(); // Llama al método resetGame() cuando se selecciona "Reiniciar"
				}
			}
		});
		contentPane.add(comboBox);

		JPanel panel = new JPanel();
		panel.setBounds(0, 33, 606, 357);
		contentPane.add(panel);

		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(adapter);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 195, 126);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(adapter);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(194, 0, 206, 126);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(adapter);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(399, 0, 206, 126);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(adapter);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 121, 195, 118);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(adapter);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(194, 121, 206, 126);

		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(adapter);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(399, 121, 206, 118);

		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(adapter);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(0, 242, 195, 110);

		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.addMouseListener(adapter);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(194, 242, 206, 110);

		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.addMouseListener(adapter);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(399, 247, 206, 110);
		panel.setLayout(null);
		panel.add(lblNewLabel);
		panel.add(lblNewLabel_1);
		panel.add(lblNewLabel_2);
		panel.add(lblNewLabel_3);
		panel.add(lblNewLabel_4);
		panel.add(lblNewLabel_5);
		panel.add(lblNewLabel_6);
		panel.add(lblNewLabel_7);
		panel.add(lblNewLabel_8);

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
		lblNewLabel.hashCode();

	}

	MouseAdapter adapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			ArrayList<JLabel> listaLabel = new ArrayList<JLabel>();

			listaLabel.add(lblNewLabel);
			listaLabel.add(lblNewLabel_1);
			listaLabel.add(lblNewLabel_2);
			listaLabel.add(lblNewLabel_3);
			listaLabel.add(lblNewLabel_4);
			listaLabel.add(lblNewLabel_5);
			listaLabel.add(lblNewLabel_6);
			listaLabel.add(lblNewLabel_7);
			listaLabel.add(lblNewLabel_8);

			for (JLabel j : listaLabel) {
				if (e.getSource().equals(j) && j.getText().equals("")) {
					turno++;
					if (turno % 2 == 0) {
						j.setText("O");
					} else {
						j.setText("X");
					}
					Dialog_playAgain play = new Dialog_playAgain(MiFrame.this);
					String ganador = "El ganador es la ";
					switch (comprobarGanador()) {
					case 1:
						play.getLblNewLabel().setText(ganador + "X");
						play.setVisible(true);
						break;
					case 2:
						play.getLblNewLabel().setText(ganador + "O");
						play.setVisible(true);
						break;
					default:
						continue;
					}

				}
			}
		}
	};

	public int comprobarGanador() {
		System.out.println("\n\n");
		TreeMap<Integer, ArrayList<JLabel>> mapa = new TreeMap<Integer, ArrayList<JLabel>>();
		mapa.put(0, new ArrayList<JLabel>());
		mapa.put(1, new ArrayList<JLabel>());
		mapa.put(2, new ArrayList<JLabel>());
		try {
			mapa.get(0).add(lblNewLabel);
			mapa.get(0).add(lblNewLabel_1);
			mapa.get(0).add(lblNewLabel_2);
			mapa.get(1).add(lblNewLabel_3);
			mapa.get(1).add(lblNewLabel_4);
			mapa.get(1).add(lblNewLabel_5);
			mapa.get(2).add(lblNewLabel_6);
			mapa.get(2).add(lblNewLabel_7);
			mapa.get(2).add(lblNewLabel_8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int resultado = 0;
		int lineaCompleta = 0; // variable int, añade 1 si encuentra una x, quita 1 si encuentra un O. luego se
								// comprueba en cada fila si es 3 o -3 para dar un ganador
		int[] filaCompleta = new int[sizeGame];
		String[] diagonal1 = new String[size_x + size_y - 1];
		String[] diagonal2 = new String[size_x + size_y - 1];
		Arrays.fill(diagonal1, "");
		Arrays.fill(diagonal2, "");
		for (Integer i : mapa.keySet()) {
			for (JLabel j : mapa.get(i)) {
				if (!j.getText().equals("")) {
					if (j.getText().equals("X")) {
						filaCompleta[mapa.get(i).indexOf(j)]++;
						lineaCompleta++;
					} else {
						filaCompleta[mapa.get(i).indexOf(j)]--;
						lineaCompleta--;
					}
					char symbol = j.getText().charAt(0);
					diagonal1[mapa.get(i).indexOf(j) + i] += symbol;
					diagonal2[size_y + i - mapa.get(i).indexOf(j) - 1] += symbol;
				} else {
					diagonal1[mapa.get(i).indexOf(j) + i] += " ";
					diagonal2[size_y + i - mapa.get(i).indexOf(j) - 1] += " ";
				}
			}
			if (lineaCompleta == 3) {
				resultado = 1;
			} else if (lineaCompleta == -3) {
				resultado = 2;
			}
			lineaCompleta = 0;
		}
		for (int i : filaCompleta) {
			switch (i) {
			case 3:
				resultado = 1;
				break;
			case -3:
				resultado = 2;
				break;
			}
		}
		for (String diagonal : diagonal1) {
			
			if (diagonal.matches(".*XXX.*")) {
				resultado = 1;
			} else if (diagonal.matches(".*OOO.*")) {
				resultado = 2;
			}
		}
		
		for (String diagonal : diagonal2) {
			
			if (diagonal.matches(".*XXX.*")) {
				resultado = 1;
			} else if (diagonal.matches(".*OOO.*")) {
				resultado = 2;
			}
		}
		return resultado;
	}

	public void resetGame() {
		turno = 0;
		lblNewLabel.setText("");
		lblNewLabel_1.setText("");
		lblNewLabel_2.setText("");
		lblNewLabel_3.setText("");
		lblNewLabel_4.setText("");
		lblNewLabel_5.setText("");
		lblNewLabel_6.setText("");
		lblNewLabel_7.setText("");
		lblNewLabel_8.setText("");
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}

	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}

	public JLabel getLblNewLabel_4() {
		return lblNewLabel_4;
	}

	public JLabel getLblNewLabel_5() {
		return lblNewLabel_5;
	}

	public JLabel getLblNewLabel_6() {
		return lblNewLabel_6;
	}

	public JLabel getLblNewLabel_7() {
		return lblNewLabel_7;
	}

	public JLabel getLblNewLabel_8() {
		return lblNewLabel_8;
	}
}
