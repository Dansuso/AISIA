package gui;
/*
 * Ventana que representa una instancia de una ventana basica, con los elementos que se repiten en las demas ventanas.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class VentanaBase extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	protected final static String NOMBRE_FUENTE = "Calibri";

	/**
	 * Constructor de la ventana base o por defecto.
	 * 
	 * @param titulo Titulo de la ventana. Es OBLIGATORIO.
	 * @throws IllegalArgumentException si no se pasa ningun titulo como parametro
	 */
	public VentanaBase(String titulo) {

		if (titulo == "") {
			throw new IllegalArgumentException("Es obligatorio que la ventana tenga un titulo");
		}

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle(titulo);
		this.setSize(1920, 1080);
		this.setLocationRelativeTo(null);
		
		
		//Header
		JPanel header = new JPanel(new BorderLayout());
		JLabel icono = new JLabel(new ImageIcon("resources/images/aisia/aisia1.png"));
		header.add(icono,BorderLayout.CENTER);
		this.add(header,BorderLayout.NORTH);
		icono.setBorder(new EmptyBorder(10,10,10,10));

	

		// Cuando el usuario haga click en el bootn de salir, se ejecutara la funcion
		// cerrarVentanaConfirmacion que sacará una ventana
		// para confirmar que el usuario relamente quiere salir de la ventana.
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaConfirmacion();
			}
		});

	}

	/**
	 * Metodo que muestrau una pequeña ventana con dos opciones 'SI' 'NO'. En el
	 * caso de que el usuario eliga 'SI' la ventana se cerrara. IMPORTANTE: Se usa
	 * dispose() y no System.exit porque no queremos que la JVM se cierre, solamente
	 * la ventana.
	 * 
	 */
	private void cerrarVentanaConfirmacion() {

		// Preguntar por confirmacion
		int quiereCerrarVentana = JOptionPane.showConfirmDialog(null, "Quiere cerrar la ventana?", "Salir",
				JOptionPane.YES_NO_OPTION);
		if (quiereCerrarVentana == JOptionPane.YES_OPTION) {
			dispose();
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater( ()-> new VentanaBase("Prueba"));

	}

}
