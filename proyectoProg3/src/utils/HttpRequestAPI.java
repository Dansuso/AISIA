package utils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Clase para hacer Requests a OMBDAPI
 */
public class HttpRequestAPI {

	public static JsonObject hacerPeticion(String nombreContenido) {
		// Hay que codificar el nombre (Por si tiene espacios por ejemplo)
		nombreContenido = URLEncoder.encode(nombreContenido, StandardCharsets.UTF_8);
		HttpClient cliente = HttpClient.newHttpClient();
		//Construimos la peticion , de tipo GET 
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.omdbapi.com/?apikey=623a06b6&t=" + nombreContenido)).GET().build();

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
