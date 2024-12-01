package gui;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;import javax.swing.JLabel;

import utils.HttpRequestAPI;

public class DialogoInfoTaquilla extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	public DialogoInfoTaquilla(String nombrePelicula) {
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		//JPanel general
		//Devuelve
		String enlaceImagen = HttpRequestAPI.hacerPeticion(nombrePelicula).get("Poster").getAsString();
		
		try {
			URL url = new URL(enlaceImagen);
		    ImageIcon image  = new ImageIcon(url);
		    JLabel labelImagen = new JLabel();
		    labelImagen.setHorizontalAlignment(JLabel.CENTER);
		    labelImagen.setIcon(image);
		    this.add(labelImagen);
		    

		} catch (MalformedURLException e) {
			System.err.println("La URL introducida no es correcta");
			e.printStackTrace();
		}
		
		this.setVisible(true);
			
		
		
	}
	
	
	


}
