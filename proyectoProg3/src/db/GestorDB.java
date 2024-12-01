package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Contenido;
import domain.Pelicula;
import domain.Serie;
import domain.Contenido.Genero;
import domain.Noticia;

public class GestorDB {
	private static String DRIVER_NAME;
	private static String DATABASE_FILE;
	private static String CONNECTION_STRING;
	private Connection con;

	/**
	 * Constructor de la clase GestorDB que se encargara de toda la gestion
	 * relacionada con la base de datos
	 */
	public GestorDB() {

		// Leer propiedades del fichero ; seran utilizadas para la conexion a la Base de
		// Datos.
		Properties properties = new Properties();
		try (FileInputStream ficheroProperties = new FileInputStream(new File("conf/propiedades.properties"))) {
			properties.load(ficheroProperties);
			DRIVER_NAME = properties.getProperty("DRIVER_NAME");
			DATABASE_FILE = properties.getProperty("DATABASE_FILE");
			CONNECTION_STRING = properties.getProperty("CONNECTION_STRING") + DATABASE_FILE;
			
			// Cargar el driver
			Class.forName(DRIVER_NAME);
			//Establecer Conexion
			con = DriverManager.getConnection(CONNECTION_STRING);

		} catch (FileNotFoundException e) {
			System.err.println("Fichero de propiedades no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Problema de Entrada/Salida ");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			System.err.println("Error al cargar la libreria de JDBC");
			e.printStackTrace();	
		} catch (SQLException e) {
			System.err.println("Error al establecer conexion con la base de datos");
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param titulo
	 * @param resumen
	 * @param url
	 * @param fuente
	 */
	public void insertarNoticia(String titulo, String resumen, String url, String fuente) {
		String sql = "INSERT INTO NOTICIA (TITULO,RESUMEN,URL,FUENTE) VALUES (?,?,?,?)";
		try(PreparedStatement prepStmt = con.prepareStatement(sql)){
			prepStmt.setString(1, titulo);
			prepStmt.setString(2, resumen);
			prepStmt.setString(3, url);
			prepStmt.setString(4, fuente);
			prepStmt.executeUpdate();
			con.close();

		} catch (SQLException e) {
			System.err.println("Error con la consulta de insercion de noticias: " + e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	public List<Noticia> obtenerNoticias(){
		
		List<Noticia> noticias = new ArrayList<Noticia>();
		try(Connection con = DriverManager.getConnection(CONNECTION_STRING)){
			String sqlNoticias = "SELECT * FROM NOTICIA";
			PreparedStatement stmt = con.prepareStatement(sqlNoticias);
			ResultSet rsNoticias = stmt.executeQuery();
			while(rsNoticias.next()) {
				noticias.add(new Noticia(
						rsNoticias.getInt("ID"),
						rsNoticias.getString("TITULO"), 
						rsNoticias.getString("RESUMEN"), 
						rsNoticias.getString("URL"),
						rsNoticias.getString("FUENTE")
						));
			}
			
				
		} catch (SQLException e) {
			System.err.println("Ha habido algun problema con la consulta SQL");
			e.printStackTrace();
		}
		
		
		
		return noticias;
	}
	
	public List<Contenido> obtenerContenidos() {
		List<Contenido> contenidos = new ArrayList<>();
		
		try {
			Connection con = DriverManager.getConnection(CONNECTION_STRING);
			// Consultar películas
	        String sqlPelicula = "SELECT * FROM PELICULA";
	        PreparedStatement stmtPelicula = con.prepareStatement(sqlPelicula);
	        ResultSet rsPelicula = stmtPelicula.executeQuery();
	        
	        
	        while (rsPelicula.next()) {
	        	Genero generoPeli = Genero.valueOf(rsPelicula.getString("GENERO").toUpperCase());
	        	
	        	Pelicula pelicula = new Pelicula(rsPelicula.getInt("ID"), 
	        										Contenido.TIPO.PELICULA, 
	        										rsPelicula.getString("TITULO"), 
	        										generoPeli, 
	        										rsPelicula.getInt("DURACION"), 
	        										rsPelicula.getInt("CALIFICACION"), 
	        										rsPelicula.getString("DISTRIBUIDORA"), 
	        										rsPelicula.getInt("EDADRECOMENDADA"), 
	        										rsPelicula.getBoolean("OSCAR"),
	        										rsPelicula.getString("CARATULA"),
	        										LocalDate.parse(rsPelicula.getString("FECHA"))
	        										);
	        	
	        	
	        	System.out.println(pelicula);
	        	contenidos.add(pelicula);
	        }
	        
	        rsPelicula.close();
	        stmtPelicula.close();
	        
	     // Consultar series
	        String sqlSerie = "SELECT * FROM SERIE";
	        PreparedStatement stmtSerie = con.prepareStatement(sqlSerie);
	        ResultSet rsSerie = stmtSerie.executeQuery();
	        
	        
	        while(rsSerie.next()) {
	        	Genero generoSerie = Genero.valueOf(rsSerie.getString("GENERO").toUpperCase());
	        	
	        	Serie serie = new Serie(rsSerie.getInt("ID"),
	                    				Contenido.TIPO.SERIE, 
	        							rsSerie.getString("TITULO"), 
	        							generoSerie, 
	        							rsSerie.getInt("CALIFICACION"),
	        							rsSerie.getString("DISTRIBUIDORA"), 
	        							rsSerie.getInt("EDADRECOMENDADA"), 
	        							rsSerie.getString("CARATULA"), 
	        							rsSerie.getInt("NUMEROTEMPORADAS"), 
	        							rsSerie.getInt("NUMEROEPISODIOS"), 
	        							rsSerie.getBoolean("EMMY"),
										LocalDate.parse(rsSerie.getString("FECHA")));

	        				
	        	
	        	System.out.println(serie);
	        	contenidos.add(serie);
	        }
			
	        rsSerie.close();
	        stmtSerie.close();
	        con.close();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contenidos;
		
	}
	
	public List<String> obtenerPersonas() {
	    List<String> personas = new ArrayList<>();
	    String sql = "SELECT * FROM PERSONAS";

	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            String persona = String.format(
	                "ID: %d, Nombre: %s, Apellido: %s, Edad: %d, Email: %s",
	                rs.getInt("ID"),
	                rs.getString("NOMBRE"),
	                rs.getString("APELLIDO"),
	                rs.getInt("EDAD"),
	                rs.getString("EMAIL")
	            );
	            personas.add(persona);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener datos de la tabla PERSONAS: " + e.getMessage());
	    }
	    return personas;
	}
	
	public void insertarPersona(int id, String nombre, String apellido, int edad, String email) {
	    String sqlInsertPersona = """
	        INSERT INTO PERSONAS (ID, NOMBRE, APELLIDO, EDAD, EMAIL)
	        VALUES (?, ?, ?, ?, ?);
	    """;

	    try (PreparedStatement prepStmt = con.prepareStatement(sqlInsertPersona)) {
	        prepStmt.setInt(1, id);
	        prepStmt.setString(2, nombre);
	        prepStmt.setString(3, apellido);
	        prepStmt.setInt(4, edad);
	        prepStmt.setString(5, email);
	        prepStmt.executeUpdate();
	        System.out.println("Persona insertada correctamente: " + nombre + " " + apellido);
	    } catch (SQLException e) {
	        System.err.println("Error al insertar persona: " + e.getMessage());
	    }
	}
	
	public void borrarPersona(int id) {
	    String sqlDeletePersona = "DELETE FROM PERSONAS WHERE ID = ?";

	    try (PreparedStatement prepStmt = con.prepareStatement(sqlDeletePersona)) {
	        prepStmt.setInt(1, id);
	        int rowsAffected = prepStmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Persona con ID " + id + " eliminada correctamente.");
	        } else {
	            System.out.println("No se encontró una persona con ID " + id + ".");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al borrar persona: " + e.getMessage());
	    }
	}
	
	
	

}
