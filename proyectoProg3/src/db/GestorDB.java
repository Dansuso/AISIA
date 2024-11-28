package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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

}
