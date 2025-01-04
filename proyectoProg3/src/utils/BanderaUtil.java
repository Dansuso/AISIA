package utils;

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
        	JLabel fin = new JLabel(pais);
    		fin.setFont(new Font("Monospaced", Font.BOLD, 20));
            return fin;
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

            Image imagenEscalada = imagen.getScaledInstance(80, 55, Image.SCALE_SMOOTH);
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
        case "united-states": return "us";
        case "brazil": return "br";
        case "france": return "fr";
        case "germany": return "de";
        case "italy": return "it";
        case "canada": return "ca";
        case "united-kingdom": return "gb";
        case "japan": return "jp";
        case "china": return "cn";
        case "australia": return "au";
        case "india": return "in";
        case "mexico": return "mx";
        case "south-africa": return "za";
        case "russia": return "ru";
        case "south-korea": return "kr";
        case "portugal": return "pt";
        case "netherlands": return "nl";
        case "sweden": return "se";
        case "norway": return "no";
        case "denmark": return "dk";
        case "switzerland": return "ch";
        case "belgium": return "be";
        case "austria": return "at";
        case "new-zealand": return "nz";
        case "ireland": return "ie";
        case "greece": return "gr";
        case "poland": return "pl";
        case "czech-republic": return "cz";
        case "hungary": return "hu";
        case "turkey": return "tr";
        case "finland": return "fi";
        case "iceland": return "is";
        case "thailand": return "th";
        case "vietnam": return "vn";
        case "philippines": return "ph";
        case "malaysia": return "my";
        case "singapore": return "sg";
        case "indonesia": return "id";
        case "saudi-arabia": return "sa";
        case "egypt": return "eg";
        case "nigeria": return "ng";
        case "colombia": return "co";
        case "chile": return "cl";
        case "peru": return "pe";
        case "venezuela": return "ve";
        case "pakistan": return "pk";
        case "bangladesh": return "bd";
        case "iran": return "ir";
        case "iraq": return "iq";
        case "israel": return "il";
        case "argelia": return "dz";
        case "morocco": return "ma";
        default: return null;
        }
    }

}