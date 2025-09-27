package utils;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BanderaUtil {

    public static JLabel obtenerBandera(String pais) {

        String codigoPais = obtenerCodigoPais(pais.toLowerCase());
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
    	
    	// Noombres de los paises generados con ChatGPT
    	
        switch (pais.toLowerCase()) {
        case "argentina": return "ar";
        case "spain": case "españa": return "es";
        case "united states": case "estados unidos": return "us";
        case "brazil": case "brasil": return "br";
        case "france": case "francia": return "fr";
        case "germany": case "alemania": return "de";
        case "italy": case "italia": return "it";
        case "canada": return "ca";
        case "united kingdom": case "reino unido": return "gb";
        case "japan": case "japon": return "jp";
        case "china": return "cn";
        case "australia": return "au";
        case "india": return "in";
        case "mexico": return "mx";
        case "south africa": case "sudáfrica": return "za";
        case "russia": case "rusia": return "ru";
        case "south korea": case "corea del sur": return "kr";
        case "portugal": return "pt";
        case "netherlands": case "países bajos": return "nl";
        case "sweden": case "suecia": return "se";
        case "norway": case "noruega": return "no";
        case "denmark": case "dinamarca": return "dk";
        case "switzerland": case "suiza": return "ch";
        case "belgium": case "bélgica": return "be";
        case "austria": return "at";
        case "new-zealand": case "nueva zelanda": return "nz";
        case "ireland": case "irlanda": return "ie";
        case "greece": case "grecia": return "gr";
        case "poland": case "polonia": return "pl";
        case "czech republic": case "república checa": return "cz";
        case "hungary": case "hungría": return "hu";
        case "turkey": case "turquía": return "tr";
        case "finland": case "finlandia": return "fi";
        case "iceland": case "islandia": return "is";
        case "thailand": case "tailandia": return "th";
        case "vietnam": case "viet nam": return "vn";
        case "philippines": case "filipinas": return "ph";
        case "malaysia": case "malasia": return "my";
        case "singapore": case "singapur": return "sg";
        case "indonesia": return "id";
        case "saudi arabia": case "arabia saudita": return "sa";
        case "egypt": case "egipto": return "eg";
        case "nigeria": return "ng";
        case "colombia": return "co";
        case "chile": return "cl";
        case "peru": case "perú": return "pe";
        case "venezuela": return "ve";
        case "pakistan": case "pakistán": return "pk";
        case "bangladesh": return "bd";
        case "iran": return "ir";
        case "iraq": return "iq";
        case "israel": return "il";
        case "argelia": return "dz";
        case "morocco": case "marruecos": return "ma";
        case "uruguay": return "uy";
        default: return null;
        }
    }

}