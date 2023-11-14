import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class CalculadoraGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	private JButton btnNewButton_9;
	private JButton btnNewButton_10;
	private JButton btnNewButton_11;
	private JButton btnNewButton_12;
	private JButton btnNewButton_13;
	private JButton btnNewButton_14;
	private JButton btnNewButton_15;
	private JButton btnNewButton_16;
	private JButton btnNewButton_17;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraGUI frame = new CalculadoraGUI();
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
	public CalculadoraGUI() {
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Serif", Font.BOLD, 33));
		textField.setText("0");
		textField.setMargin(new Insets(0,10,0,0));
		textField.setBounds(0, 0, 445, 62);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("7");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton.setBounds(0, 62, 110, 95);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("8");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_1.setBounds(110, 62, 110, 95);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("9");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_2.setBounds(220, 62, 110, 95);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("/");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_3.setBounds(330, 62, 110, 95);
		contentPane.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("4");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_4.setBounds(0, 157, 110, 95);
		contentPane.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("5");
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_5.setBounds(110, 157, 110, 95);
		contentPane.add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("6");
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_6.setBounds(220, 157, 110, 95);
		contentPane.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("*");
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_7.setBounds(330, 157, 110, 95);
		contentPane.add(btnNewButton_7);
		
		btnNewButton_8 = new JButton("1");
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_8.setBounds(0, 252, 110, 95);
		contentPane.add(btnNewButton_8);
		
		btnNewButton_9 = new JButton("2");
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_9.setBounds(110, 252, 110, 95);
		contentPane.add(btnNewButton_9);
		
		btnNewButton_10 = new JButton("3");
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_10.setBounds(220, 252, 110, 95);
		contentPane.add(btnNewButton_10);
		
		btnNewButton_11 = new JButton("-");
		btnNewButton_11.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_11.setBounds(330, 252, 110, 95);
		contentPane.add(btnNewButton_11);
		
		btnNewButton_12 = new JButton("0");
		btnNewButton_12.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_12.setBounds(0, 347, 110, 95);
		contentPane.add(btnNewButton_12);
		
		btnNewButton_13 = new JButton(".");
		btnNewButton_13.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_13.setBounds(110, 347, 110, 95);
		contentPane.add(btnNewButton_13);
		
		btnNewButton_14 = new JButton("=");
		btnNewButton_14.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_14.setBounds(220, 347, 110, 95);
		contentPane.add(btnNewButton_14);
		
		btnNewButton_15 = new JButton("+");
		btnNewButton_15.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_15.setBounds(330, 347, 110, 95);
		contentPane.add(btnNewButton_15);
		
		btnNewButton_16 = new JButton("Borrar");
		btnNewButton_16.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_16.setBounds(0, 442, 110, 95);
		btnNewButton_16.addActionListener((ActionEvent e) -> {
			textField.setText("0");
		});
		contentPane.add(btnNewButton_16);
		
		btnNewButton_17 = new JButton("Salir");
		btnNewButton_17.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_17.setBounds(110, 442, 110, 95);
		contentPane.add(btnNewButton_17);
	}
}
