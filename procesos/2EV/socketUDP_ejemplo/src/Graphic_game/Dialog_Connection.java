package Graphic_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog_Connection extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel cardPanel;

	public Dialog_Connection(JFrame parent) {
		super(parent, "Tres en Raya", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Configuración del CardLayout
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		// Panel del juego
		JPanel juegoPanel = new JPanel();
		juegoPanel.add(new JLabel("Aquí iría el juego Tres en Raya"));

		// Panel de opciones de conexión
		JPanel opcionesPanel = new JPanel();
		JButton localButton = new JButton("Local");
		JButton onlineButton = new JButton("Online");
		localButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Dialog_Connection.this, "Conexión local seleccionada");
			}
		});
		onlineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Dialog_Connection.this, "Conexión online seleccionada");
			}
		});
		opcionesPanel.add(localButton);
		opcionesPanel.add(onlineButton);

		// Añadir paneles al cardPanel
		cardPanel.add(juegoPanel, "juego");
		cardPanel.add(opcionesPanel, "opciones");

		// Botón para cambiar entre juego y opciones
		JButton jugarButton = new JButton("Jugar");
		jugarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "opciones");
				jugarButton.setText("Regresar");
			}
		});

		// Layout y añadir componentes al diálogo
		setLayout(new BorderLayout());
		add(cardPanel, BorderLayout.CENTER);
		add(jugarButton, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(parent);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JButton abrirDialogButton = new JButton("Abrir diálogo");
				abrirDialogButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Dialog_Connection dialog = new Dialog_Connection(frame);
						dialog.setVisible(true);
					}
				});
				frame.getContentPane().add(abrirDialogButton);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
