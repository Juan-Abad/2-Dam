package Graphic_game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import socketUDP_ejemplo.Mensajes;
import socketUDP_ejemplo.MessageSender;
import socketUDP_ejemplo.UDPcliente;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;

public class Dialog_playAgain extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;

	/**
	 * Create the dialog.
	 */
	public Dialog_playAgain(JFrame framePrincipal) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		int x = framePrincipal.getX() + (framePrincipal.getWidth() - getWidth()) / 2;
		int y = framePrincipal.getY() + (framePrincipal.getHeight() - getHeight()) / 2;
		setLocation(x, y);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 67);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("¿Quiere volver a jugar?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 114, 414, 42);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 167, 434, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("SI");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						((MiFrame) framePrincipal).resetGame();
						dispose();
					}
				});
				okButton.setBounds(148, 5, 57, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("NO");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Mensajes.MensajeDesconectar mensajeDesconectar = new Mensajes.MensajeDesconectar(((MiFrame) framePrincipal).getIdJugador());
						try {
							((MiFrame) framePrincipal).getSender().sendMessage(mensajeDesconectar, ((MiFrame) framePrincipal).getSender().getHostAddress(), MessageSender.getPort());
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						framePrincipal.dispose();
						System.exit(0);
					}
				});
				cancelButton.setBounds(230, 5, 57, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
}
