package gui;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BanderaUtil {

    public static JLabel obtenerBandera(String pais) {

        String codigoPais = obtenerCodigoPais(pais);
        if (codigoPais == null) {
            return new JLabel("País no encontrado");
        }

        String urlBase = "https://flagcdn.com/w40/"; // Ruta del servicio de banderas
        String rutaImagen = codigoPais + ".png";

        try {
            URI uri = new URI(urlBase + rutaImagen);
            URL url = uri.toURL();

            Image imagen = ImageIO.read(url);

            if (imagen == null) {
                throw new IOException("La imagen descargada es nula. Verifica la URL: " + url);
            }

            Image imagenEscalada = imagen.getScaledInstance(70, 45, Image.SCALE_SMOOTH);
            ImageIcon iconoBandera = new ImageIcon(imagenEscalada);
            return new JLabel(iconoBandera);

        } catch (Exception e) {
            e.printStackTrace();
            return new JLabel("Bandera no disponible");
        }
    }

    private static String obtenerCodigoPais(String pais) {
        // Conversión básica de países a códigos ISO (agrega más según necesidad)
        switch (pais.toLowerCase()) {
            case "argentina": return "ar";
            case "spain": return "es";
            case "united states": return "us";
            case "brazil": return "br";
            case "france": return "fr";
            case "germany": return "de";
            case "italy": return "it";
            default: return null; 
        }
    }

}