package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GestorDB {
	private static  String DRIVER_NAME;
	private static  String DATABASE_FILE;
	private static  String CONNECTION_STRING;
	
	/**
	 * Constructor de la clase GestorDB que se encargara de toda la gestion relacionada con la base de datos
	 */
	public GestorDB() {
		
		//Leer propiedades del fichero ; seran utilizadas para la conexion a la Base de Datos.
		Properties properties = new Properties();
		try(FileInputStream ficheroProperties = new FileInputStream(new File("conf/propiedades.properties"))){
			properties.load(ficheroProperties);
			DRIVER_NAME = properties.getProperty("DRIVER_NAME");
			DATABASE_FILE = properties.getProperty("DATABASE_FILE");
			CONNECTION_STRING = properties.getProperty("CONNECTION_STRING") + DATABASE_FILE;
			
		} catch (FileNotFoundException e) {
			System.err.println("Fichero de propiedades no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Problema de Entrada/Salida ");
			e.printStackTrace();
		}
		
		//Cargar el driver
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar la libreria de JDBC");
			e.printStackTrace();
		}
			
		}
		
	}


