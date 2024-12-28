package utils;

import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;

public class cargarFotoDePerfil {
	
	    public static ImageIcon cargarImagenDesdeURL(String url) {
	        if (url == null || url.isEmpty()) {
	            System.err.println("La URL proporcionada es nula o está vacía.");
	            return null;
	        }

	        try {

	            URI uri = new URI(url);
	            URL urlFinal = uri.toURL();

	            // Crear el ImageIcon a partir de la URL
	            return new ImageIcon(urlFinal);
	        } catch (Exception e) {
	            System.err.println("Error al cargar la imagen desde la URI: " + e.getMessage());
	            e.printStackTrace();
	            return null;
	        
	    }
	}
}
