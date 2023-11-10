package interfaz_CV;

import java.awt.LayoutManager;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;


public class VentanaTabbedPane {

	public static void main(String[] args) {
		// Crea una ventana
		JFrame ventana = new JFrame("Ventana con pestañas");
		ventana.setSize(418, 305);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Crea un panel de pestañas
		JTabbedPane pestanyas = new JTabbedPane();
		
		// Crea la pestaña de datos personales
		/*JPanel datosPersonales = new JPanel();
		datosPersonales.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel datosPersonalesInfo = new JPanel();
		datosPersonalesInfo.add(new JLabel("Nombre:"));
		datosPersonalesInfo.add(new JTextField("Juan"));
		datosPersonalesInfo.add(new JLabel("Apellidos:"));
		datosPersonalesInfo.add(new JTextField("Abad"));
		datosPersonalesInfo.add(new JLabel("Fecha de nacimiento:"));
		datosPersonalesInfo.add(new JTextField("05/10/2003"));
		datosPersonales.add(datosPersonalesInfo);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("imagenes\\descarga.jpg"));
		datosPersonales.add(lblNewLabel);
		
		pestanyas.addTab("Datos personales", datosPersonales);*/
		
		JPanel datosPersonales = new JPanel();
        datosPersonales.setLayout(new BorderLayout());

        // Panel para la información personal con FlowLayout
        JPanel datosPersonalesInfo = new JPanel();
        datosPersonalesInfo.setLayout(new FlowLayout());

        datosPersonalesInfo.add(new JLabel("Nombre:"));
        datosPersonalesInfo.add(new JTextField("Juan"));
        datosPersonalesInfo.add(new JLabel("Apellidos:"));
        datosPersonalesInfo.add(new JTextField("Abad"));
        datosPersonalesInfo.add(new JLabel("Fecha de nacimiento:"));
        datosPersonalesInfo.add(new JTextField("05/10/2003"));
        datosPersonales.add(datosPersonalesInfo, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setIcon(new ImageIcon("imagenes\\descarga.jpg"));

        datosPersonales.add(lblNewLabel, BorderLayout.CENTER);
        pestanyas.addTab("Datos personales", datosPersonales);
    
		// Crea la pestaña de formación
		JPanel formacion = new JPanel();
		formacion.add(new JLabel("Grado:"));
		formacion.add(new JTextField("Ingenieria Informática"));
		formacion.add(new JLabel("Universidad:"));
		formacion.add(new JTextField("Universidad de Zaragoza"));
		formacion.add(new JLabel("Año de graduación:"));
		formacion.add(new JTextField("2020"));
		pestanyas.addTab("Formación", formacion);

		// Crea la pestaña de experiencia laboral
		JPanel experienciaLaboral = new JPanel();
		experienciaLaboral.add(new JLabel("Empresa:"));
		experienciaLaboral.add(new JTextField("Microsoft"));
		experienciaLaboral.add(new JLabel("Puesto:"));
		experienciaLaboral.add(new JTextField("Full stack developer"));
		experienciaLaboral.add(new JLabel("Fecha de inicio:"));
		experienciaLaboral.add(new JTextField("10/10/2021"));
		experienciaLaboral.add(new JLabel("Fecha de fin:"));
		experienciaLaboral.add(new JTextField("10/11/2023"));
		pestanyas.addTab("Experiencia laboral", experienciaLaboral);
		
		// Añade el panel de pestañas a la ventana
		ventana.getContentPane().add(pestanyas);
		
		
		// Muestra la ventana
		ventana.setVisible(true);

	}

}
