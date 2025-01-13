package utils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Clase para hacer Requests a OMBDAPI
 */
public class HttpRequestAPI {
	private static  final Logger LOGGER = Logger.getLogger(HttpRequestAPI.class.getName());

	public static JsonObject hacerPeticion(String nombreContenido) {
		// Hay que codificar el nombre (Por si tiene espacios por ejemplo)
		nombreContenido = URLEncoder.encode(nombreContenido, StandardCharsets.UTF_8);
		HttpClient cliente = HttpClient.newHttpClient();
		//Construimos la peticion , de tipo GET 
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.omdbapi.com/?apikey=623a06b6&t=" + nombreContenido)).GET().build();
		
		FileHandler fh;
		try {
			fh = new FileHandler("log/logOMBDApi.txt",true);
			LOGGER.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			LOGGER.setUseParentHandlers(false);
			LOGGER.info("Se ha hecho una petición a BoxOfficeMojo");
			fh.close();
		} catch (SecurityException | IOException e) {
			System.err.println("Ha habido algun problema con la creacion de Logs de OMBDAPI " + e.getMessage());
		}
		
		// RESPUESTA

		JsonObject json = null;
		try {
			HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
			json = JsonParser.parseString(respuesta.body()).getAsJsonObject();
			
		} catch (IOException | InterruptedException e) {
			System.err.println("Error!");
			e.printStackTrace();
		}
		return json;
	}
}
