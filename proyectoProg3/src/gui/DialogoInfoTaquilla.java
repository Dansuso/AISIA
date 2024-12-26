package gui;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import utils.HttpRequestAPI;

public class DialogoInfoTaquilla extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	public DialogoInfoTaquilla(String nombrePelicula) {
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle(nombrePelicula);
		
		JLabel labelImagen = new JLabel("Cargando poster..");
		this.add(labelImagen);
	
	    labelImagen.setHorizontalAlignment(JLabel.CENTER);

		
		
		/**
		 * Hilo para hacer la peticion de manera asincrona
		 * De esta forma si la peticion le cuesta mucho o no carga la ventana se sigue abriendo sin problema
		 */
		Thread hiloRequest =  new Thread(() -> {
			String enlaceImagen = HttpRequestAPI.hacerPeticion(nombrePelicula).get("Poster").getAsString();
			
			try {
				URL url = new URL(enlaceImagen);
			    ImageIcon image  = new ImageIcon(url);
			    SwingUtilities.invokeLater(() -> labelImagen.setText(""));
			    SwingUtilities.invokeLater(() -> labelImagen.setIcon(image));

			    this.add(labelImagen);
			  
			} catch (MalformedURLException e) {
				System.err.println("La URL (Enlace a la imagen) introducida no es correcta");
				e.printStackTrace();
			}
			
		});
		
		
		hiloRequest.start();
		
		this.setVisible(true);
			
		
		
	}
	
	
	


}
