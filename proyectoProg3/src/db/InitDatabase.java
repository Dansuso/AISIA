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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import domain.Contenido;
import domain.Contenido.Genero;
import domain.Contenido.TIPO;
import domain.Pelicula;
import domain.Serie;

import java.sql.Statement;
import java.time.LocalDate;

/**
 * INICIALIZAR BASE DE DATOS. ESTE SCRIPT NO ESTÁ PENSADO PARA EJECUTARSE VARIAS
 * VECES
 */
public class InitDatabase {
	private String DRIVER_NAME;
	private String DATABASE_FILE;
	private String CONNECTION_STRING;
	private Connection con;

	public InitDatabase() {

		// CARGAR PROPIEDADES
		Properties propiedades = new Properties();
		try (FileInputStream fis = new FileInputStream(new File("conf/propiedades.properties"))) {
			propiedades.load(fis);
			DRIVER_NAME = propiedades.getProperty("DRIVER_NAME");
			DATABASE_FILE = propiedades.getProperty("DATABASE_FILE");
			CONNECTION_STRING = propiedades.getProperty("CONNECTION_STRING") + DATABASE_FILE;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// CARGAR DRIVER

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar la libreria de JDBC");
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que crea las tablas necesarias para el proyecto. Antes de nada, borra
	 * las que hay.
	 */

	public void crearTablas() {

		String sqlTablaPelicula = """
				CREATE TABLE IF NOT EXISTS Pelicula (
				    id INTEGER PRIMARY KEY,
				    titulo TEXT NOT NULL,
				    genero TEXT,
				    duracion INTEGER,
				    calificacion INTEGER,
				    distribuidora TEXT,
				    edadrecomendada INTEGER,
				    oscar BOOLEAN,
				    caratula TEXT,
				    fecha TEXT
				);
				""";

		String sqlTablaSerie = """
				CREATE TABLE IF NOT EXISTS Serie (
				    id INTEGER PRIMARY KEY,
				      titulo TEXT NOT NULL,
				    genero TEXT,
				    calificacion INTEGER,
				    distribuidora TEXT,
				    edadrecomendada INTEGER,
				    numeroepisodios INTEGER,
				    numerotemporadas INTEGER,
				    emmy BOOLEAN,
				    caratula TEXT,
				    fecha TEXT
				);
				""";
		
		String sqlTablaNoticia = """
				CREATE TABLE IF NOT EXISTS NOTICIA (
				    id INTEGER PRIMARY KEY AUTOINCREMENT,
				    titulo TEXT NOT NULL,
				    resumen TEXT,
				    url TEXT NOT NULL,
				    fuente TEXT NOT NULL	 
				);
				""";
	
		// Nos conectamos a la base de datos

		try {
			con = DriverManager.getConnection(CONNECTION_STRING);
			Statement stmt = con.createStatement();
			stmt.execute("DROP TABLE IF EXISTS PELICULA");
			stmt.execute("DROP TABLE IF EXISTS SERIE");
			stmt.execute(sqlTablaPelicula);
			stmt.execute(sqlTablaSerie);
			stmt.execute(sqlTablaNoticia);
			stmt.close();
			con.close();
			System.out.println("TABLAS CREADAS");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo para insertar las Series y Peliculas que tenemos en el CSV.
	 */
	public void insertarSeriesYPeliculasDefault() {
		// Leer el archivo

		try (Scanner sc = new Scanner(new File("resources/data/contenido.csv"))) {

			// Abro Conexion con la base de datos
			con = DriverManager.getConnection(CONNECTION_STRING);
			// Nos saltamos la Primera linea ( es la cabecera)
			sc.nextLine();
			while (sc.hasNext()) {
				// Nos saltamos la primera linea
				String linea = sc.nextLine();
				// Delimitador ,
				String[] campos = linea.split(",");
				int id = Integer.parseInt(campos[0]);
				TIPO tipo = TIPO.valueOf(campos[1].toUpperCase());
				String titulo = campos[2];
				String genero = campos[3];
				double calificacion = Double.parseDouble(campos[4]);
				String distribuidora = campos[5];
				int edadRecomendada = Integer.parseInt(campos[6]);
				String fechaLanzamiento = campos[7];

				if (tipo == TIPO.PELICULA) {
					boolean oscar = Boolean.parseBoolean(campos[8]);
					int duracion = Integer.parseInt(campos[9]);

					String sqlInsertPelicula = """
							INSERT INTO PELICULA (ID,TITULO,GENERO,DURACION,CALIFICACION,DISTRIBUIDORA,EDADRECOMENDADA,OSCAR,CARATULA,FECHA)
							VALUES(?,?,?,?,?,?,?,?,NULL,?);
							""";

					PreparedStatement prepStmt = con.prepareStatement(sqlInsertPelicula);
					prepStmt.setInt(1, id);
					prepStmt.setString(2, titulo);
					prepStmt.setString(3, genero);
					prepStmt.setDouble(4, duracion);
					prepStmt.setDouble(5, calificacion);
					prepStmt.setString(6, distribuidora);
					prepStmt.setInt(7, edadRecomendada);
					prepStmt.setBoolean(8, oscar);
					prepStmt.setString(9, fechaLanzamiento);
					prepStmt.executeUpdate();
					prepStmt.close();

					// Ya tenemos todos los datos, creamos la pelicula.

				}
				// Es una serie
				else {
					int temporadas = Integer.parseInt(campos[11]);
					int capitulos = Integer.parseInt(campos[12]);
					boolean emmy = Boolean.parseBoolean(campos[13]);

					String sqlInsertSerie = """
							INSERT INTO SERIE(ID,TITULO,GENERO,CALIFICACION,DISTRIBUIDORA,EDADRECOMENDADA,NUMEROEPISODIOS,NUMEROTEMPORADAS,EMMY,FECHA)
							VALUES(?,?,?,?,?,?,?,?,?,?);

							""";

					PreparedStatement prepStmt = con.prepareStatement(sqlInsertSerie);
					prepStmt.setInt(1, id);
					prepStmt.setString(2, titulo);
					prepStmt.setString(3, genero);
					prepStmt.setDouble(4, calificacion);
					prepStmt.setString(5, distribuidora);
					prepStmt.setInt(6, edadRecomendada);
					prepStmt.setInt(7, capitulos);
					prepStmt.setInt(8, temporadas);
					prepStmt.setBoolean(9, emmy);
					prepStmt.setString(10, fechaLanzamiento);
					prepStmt.executeUpdate();
					prepStmt.close();

					// Ya tenemos todos los datos, creamos la serie .

				}

			}

			// Cerramos la conexion
			con.close();
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		InitDatabase db = new InitDatabase();	
		db.crearTablas();
		db.insertarSeriesYPeliculasDefault();
	    

	}
}
