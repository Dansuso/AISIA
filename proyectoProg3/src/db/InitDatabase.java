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
import java.util.Scanner;

import domain.Contenido.TIPO;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * INICIALIZAR BASE DE DATOS. ESTE SCRIPT NO ESTÁ PENSADO PARA EJECUTARSE VARIAS
 * VECES
 */
public class InitDatabase {
	private String DRIVER_NAME;
	private String DATABASE_FILE;
	public String CONNECTION_STRING;
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

		String sqlTablaUsuario = """

						CREATE TABLE usuario (
					    id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
					    username TEXT NOT NULL UNIQUE,
					    creacionCuenta INT NOT NULL,
					    pais TEXT,
					    foto TEXT,
					    contrasena TEXT NOT NULL
						);
				""";

		String sqlPeliculaFavorito = """
				CREATE TABLE IF NOT EXISTS Pelicula_Favorito (
				    id_pelicula INTEGER,
				    id_usuario INTEGER,
				    PRIMARY KEY(id_pelicula,id_usuario),
				    FOREIGN KEY(id_pelicula) REFERENCES Pelicula(id_pelicula),
				    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
				);
				""";

		String sqlSerieFavorito = """
				CREATE TABLE IF NOT EXISTS Serie_Favorito (
				    id_serie INTEGER,
				    id_usuario INTEGER,
				    PRIMARY KEY(id_serie,id_usuario),
				    FOREIGN KEY(id_serie) REFERENCES Serie(id_serie),
				    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
				);
				""";

		String sqlTablaPelicula = """
				CREATE TABLE IF NOT EXISTS Pelicula (
				    id_pelicula INTEGER PRIMARY KEY,
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
				    id_serie INTEGER PRIMARY KEY,
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

		
		String sqlTablaSeguidores = """
				
				CREATE TABLE IF NOT EXISTS SEGUIDORES(
					ID_SEGUIDOR INTEGER,
					ID_SEGUIDO INTEGER,
					PRIMARY KEY(ID_SEGUIDOR,ID_SEGUIDO),
					FOREIGN KEY(ID_SEGUIDOR) REFERENCES USUARIO(ID_USUARIO),
					FOREIGN KEY(ID_SEGUIDO) REFERENCES USUARIO(ID_USUARIO)
				);
				
				""";
		
	String sqlTablaPost = """
				
				CREATE TABLE IF NOT EXISTS POST(
					ID_POST INTEGER PRIMARY KEY AUTOINCREMENT,
					CONTENIDO TEXT NOT NULL,
					FECHA_POST INTEGER NOT NULL,
					ID_USUARIO_CREADOR INTEGER NOT NULL,
					FOREIGN KEY (ID_USUARIO_CREADOR) REFERENCES USUARIO(ID_USUARIO)
				);
				
				""";

		// Nos conectamos a la base de datos

		try {
			con = DriverManager.getConnection(CONNECTION_STRING);
			Statement stmt = con.createStatement();
			stmt.execute("DROP TABLE IF EXISTS USUARIO");
			stmt.execute("DROP TABLE IF EXISTS PELICULA");
			stmt.execute("DROP TABLE IF EXISTS SERIE");
			stmt.execute("DROP TABLE IF EXISTS PERSONAS");
			stmt.execute("DROP TABLE IF EXISTS NOTICIA");
			stmt.execute("DROP TABLE IF EXISTS USUARIO");
			stmt.execute("DROP TABLE IF EXISTS POST");
			stmt.execute(sqlTablaUsuario);
			stmt.execute(sqlTablaPelicula);
			stmt.execute(sqlTablaSerie);
			stmt.execute(sqlTablaNoticia);
			stmt.execute(sqlSerieFavorito);
			stmt.execute(sqlPeliculaFavorito);
			stmt.execute(sqlTablaSeguidores);
			stmt.execute(sqlTablaPost);
			
			stmt.close();
			con.close();
			System.out.println("TABLAS CREADAS");
		} catch (SQLException e) {
			System.err.println("Ha habido algun problema al crear las tablas! " + e.getMessage());
			e.printStackTrace();
		}

	}

	
	
	public void insertarNoticiasDefault() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
			String insertNoticia = """
					INSERT INTO NOTICIA(TITULO,RESUMEN,URL,FUENTE)
					VALUES(?,?,?,?);
					""";

			PreparedStatement prepStmt = con.prepareStatement(insertNoticia);
			prepStmt.setString(1, "Netflix Password Crackdown Delivers Millions of New Customers");
			prepStmt.setString(2, "Streaming giant says it will continue to diversify its slate and product features");
			prepStmt.setString(3,
					"https://www.wsj.com/business/earnings/netflix-nflx-q1-earnings-report-2024-78eababf");
			prepStmt.setString(4, "WSJ");
			prepStmt.executeUpdate();

			prepStmt.setString(1, "Tom Cruise Eyeing ‘Days of Thunder’ Sequel for Paramount");
			prepStmt.setString(2,
					"The actor-producer is exploring a follow-up to his 1990 NASCAR racing film while also developing a ‘Top Gun: Maverick’ sequel and figuring out the future of the ‘Mission: Impossible' franchise");
			prepStmt.setString(3,
					"https://www.hollywoodreporter.com/movies/movie-news/tom-cruise-days-of-thunder-sequel-paramount-1236051723/");
			prepStmt.setString(4, "THR");
			prepStmt.executeUpdate();

			prepStmt.setString(1,
					"Joker’ Box Office Shocker: ‘Folie à Deux’ Bombs With $37.8M Opening After Receiving D CinemaScore");
			prepStmt.setString(2, "Streaming giant says it will continue to diversify its slate and product features");
			prepStmt.setString(3,
					"https://www.hollywoodreporter.com/movies/movie-news/joker-folie-a-deux-box-office-d-cinemascore-1236025168/");
			prepStmt.setString(4, "THR");
			prepStmt.executeUpdate();

			prepStmt.setString(1, "How Netflix won the streaming wars");
			prepStmt.setString(2,
					"The company has staged a remarkable recovery since the ‘great correction’ of 2022 and now has the edge over Hollywood rivals");
			prepStmt.setString(3, "https://www.ft.com/content/465a2d0d-8973-4d8d-827d-8729737e6606");
			prepStmt.setString(4, "FT");
			prepStmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error al insertar las noticias! " + e.getMessage());
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
							INSERT INTO PELICULA (id_pelicula,TITULO,GENERO,DURACION,CALIFICACION,DISTRIBUIDORA,EDADRECOMENDADA,OSCAR,CARATULA,FECHA)
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
							INSERT INTO SERIE(id_serie,TITULO,GENERO,CALIFICACION,DISTRIBUIDORA,EDADRECOMENDADA,NUMEROEPISODIOS,NUMEROTEMPORADAS,EMMY,FECHA)
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
		} catch (FileNotFoundException e) {
			System.err.println("Archivo CSV no encontrado: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error al insertar datos en la base de datos: " + e.getMessage());
		}
	}

	public void insertarPersonasDesdeCSV() {
		try (Scanner sc = new Scanner(new File("resources/data/personas.csv"))) {
			// Abro conexión con la base de datos
			con = DriverManager.getConnection(CONNECTION_STRING);

			// Nos saltamos la primera línea (cabecera)
			sc.nextLine();
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				String nombre = campos[0];
				String apellido = campos[1];
				int edad = Integer.parseInt(campos[2]);
				String email = campos[3];
				int id = Integer.parseInt(campos[4]);

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
				}
			}
			con.close();
			// Mostrar mensaje con JDialog al finalizar
			//ESTO ES HORRIBLE
			//mostrarMensaje("Éxito", "Datos del CSV insertados correctamente en la tabla PERSONAS.",
			//	JOptionPane.INFORMATION_MESSAGE);

		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado");
		//	mostrarMensaje("Error", "Archivo CSV no encontrado: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			System.err.println("Error al insertar datos en la base de datos " + e.getMessage());

		//	mostrarMensaje("Error", "Error al insertar datos en la base de datos: " + e.getMessage(),
			//		JOptionPane.ERROR_MESSAGE);
		}
	}

	//private void mostrarMensaje(String titulo, String mensaje, int tipoMensaje) {
	//	JOptionPane.showMessageDialog(null, mensaje, titulo, tipoMensaje);
	//}
	public void insertarUsuarioDesdeCSV() {
		try (Scanner sc = new Scanner(new File("resources/data/usuario.csv"))) {
			// Abro conexión con la base de datos
			con = DriverManager.getConnection(CONNECTION_STRING);

			while (sc.hasNext()) {
				/*
				 * 	protected int codigo;
					protected String username;
					protected LocalDate creacionCuenta;
					protected String pais;
					protected String foto;
					protected String contrasena;
				 */
				
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				
				int codigo = Integer.parseInt(campos[0]);
				String username = campos[1];
				String fechaInic = campos[2];
				String pais = campos[3];
				String foto = campos[4];
				String contraseña = campos[5];

				String sqlInsertPersona = """
						    INSERT INTO usuario (id_usuario, username, creacionCuenta, pais, foto, contrasena)
						    VALUES (?, ?, ?, ?, ?, ?);
						""";

				try (PreparedStatement prepStmt = con.prepareStatement(sqlInsertPersona)) {
					prepStmt.setInt(1, codigo);
					prepStmt.setString(2, username);
					prepStmt.setString(3, fechaInic);
					prepStmt.setString(4, pais);
					prepStmt.setString(5, foto);
					prepStmt.setString(6, contraseña);
					prepStmt.executeUpdate();
					prepStmt.close();
				}
				
			}
			con.close();
			// Mostrar mensaje con JDialog al finalizar
			//ESTO ES HORRIBLE
			//mostrarMensaje("Éxito", "Datos del CSV insertados correctamente en la tabla PERSONAS.",
			//	JOptionPane.INFORMATION_MESSAGE);

		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado");
		//	mostrarMensaje("Error", "Archivo CSV no encontrado: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			System.err.println("Error al insertar datos en la base de datos " + e.getMessage());

		//	mostrarMensaje("Error", "Error al insertar datos en la base de datos: " + e.getMessage(),
			//		JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	// Generado por ChatGPT
    public void insertarPostDefault() {
        String insertNoticia = """
                INSERT INTO POST (ID_POST, CONTENIDO, FECHA_POST, ID_USUARIO_CREADOR)
                VALUES (?, ?, ?, ?);
                """;

        // Datos para los comentarios
        Object[][] posts = {
                // Usuario 1: 4 comentarios
                {1, "¡El Señor de los Anillos es mi trilogía favorita!", LocalDate.of(2024, 1, 1), 1},
                {2, "Recientemente volví a ver 'Matrix' y sigue siendo impresionante.", LocalDate.of(2024, 1, 2), 1},
                {3, "¿Alguien más ama las películas de Studio Ghibli?", LocalDate.of(2024, 1, 4), 1},
                {4, "La dirección de Peter Jackson en 'El Hobbit' es fenomenal.", LocalDate.of(2024, 1, 3), 1},

                // Usuario 2: 2 comentarios
                {5, "'Inception' me dejó pensando en cómo funcionan los sueños.", LocalDate.of(2024, 1, 4), 2},
                {6, "¡No puedo esperar a la próxima película de Christopher Nolan!", LocalDate.of(2024, 1, 5), 2},

                // Otros usuarios
                {7, "'Parasite' merece todos los premios que recibió. ¡Espectacular!", LocalDate.of(2024, 1, 6), 3}, // Usuario 3
                {8, "'The Dark Knight' es mi película favorita de superhéroes.", LocalDate.of(2024, 1, 14), 6}, // Usuario 4
                {9, "La animación en 'Spider-Man: Into the Spider-Verse' es increíble.", LocalDate.of(2024, 1, 6), 5}, // Usuario 5
                {10, "'Pulp Fiction' tiene los mejores diálogos del cine.", LocalDate.of(2024, 1, 11), 6}, // Usuario 6
                
             // Usuario 13: 4 comentarios
                {11, "La la land es un homenaje moderno a los clásicos musicales, con una historia conmovedora sobre sueños y sacrificios. "
                		+ "La química entre Emma Stone y Ryan Gosling brilla tanto como su mágica banda sonora.", LocalDate.of(2024, 1, 7), 13},
                {12, "El juego del calamar es impactante y brutal, aborda temas como desigualdad y desesperación humana en un contexto de juegos mortales. "
                		+ "Cada episodio aumenta la tensión con giros inesperados y un mensaje social profundo.", LocalDate.of(2024, 1, 8), 13},
                {13, "Peaky Blinders es un drama oscuro y elegante lleno de intrigas familiares y crímenes. La actuación de Cillian"
                		+ " Murphy como Tommy Shelby es magistral, acompañado por una banda sonora moderna que sorprende gratamente.", LocalDate.of(2024, 1, 9), 13},
            };

        try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
            try (PreparedStatement prepStmt = con.prepareStatement(insertNoticia)) {
                for (Object[] post : posts) {
                    prepStmt.setInt(1, (int) post[0]); // ID_POST
                    prepStmt.setString(2, (String) post[1]); // CONTENIDO
                    prepStmt.setDate(3, java.sql.Date.valueOf((LocalDate) post[2])); // FECHA_POST
                    prepStmt.setInt(4, (int) post[3]); // ID_USUARIO_CREADOR
                    prepStmt.executeUpdate();
                }
                System.out.println("10 publicaciones insertadas correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertarSeriesFavoritas() {
    	String insertSerieFav = """
    	        INSERT OR IGNORE INTO SERIE_FAVORITO(ID_SERIE, ID_USUARIO)
    	        VALUES(?, ?);
    	    """;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
		
			PreparedStatement prepStmt = con.prepareStatement(insertSerieFav);
			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 8);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 10);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 43);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 2);
			prepStmt.setInt(1, 24);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 2);
			prepStmt.setInt(1, 20);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 3);
			prepStmt.setInt(1, 34);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 4);
			prepStmt.setInt(1, 11);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 5);
			prepStmt.setInt(1, 21);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 6);
			prepStmt.setInt(1, 49);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 7);
			prepStmt.setInt(1, 45);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 23);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 24);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 34);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 49);
			prepStmt.executeUpdate();
			
			con.close();

		} catch (SQLException e) {
			System.err.println("Error al insertar las series favoritas! " + e.getMessage());
			e.printStackTrace();
		}

	}

    public void insertarPelisFavoritas() {
    	String insertSerieFav = """
    	        INSERT OR IGNORE INTO PELICULA_FAVORITO(ID_PELICULA, ID_USUARIO)
    	        VALUES(?, ?);
    	    """;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
		
			PreparedStatement prepStmt = con.prepareStatement(insertSerieFav);
			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 6);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 2);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 43);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 2);
			prepStmt.setInt(1, 24);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 2);
			prepStmt.setInt(1, 25);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 3);
			prepStmt.setInt(1, 34);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 4);
			prepStmt.setInt(1, 14);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 5);
			prepStmt.setInt(1, 21);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 6);
			prepStmt.setInt(1, 49);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 7);
			prepStmt.setInt(1, 45);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 2);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 16);
			prepStmt.executeUpdate();
			
			con.close();

		} catch (SQLException e) {
			System.err.println("Error al insertar las peliculas favoritas! " + e.getMessage());
			e.printStackTrace();
		}

	}
    
    public void insertarSeguidores() {
    	String insertSerieFav = """
    	        INSERT OR IGNORE INTO SEGUIDORES(ID_SEGUIDOR, ID_SEGUIDO)
    	        VALUES(?, ?);
    	    """;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
		
			PreparedStatement prepStmt = con.prepareStatement(insertSerieFav);
			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 6);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 2);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 1);
			prepStmt.setInt(1, 11);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 6);
			prepStmt.setInt(1, 1);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 8);
			prepStmt.setInt(1, 1);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 7);
			prepStmt.setInt(1, 1);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 2);
			prepStmt.setInt(1, 3);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 2);
			prepStmt.setInt(1, 8);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 3);
			prepStmt.setInt(1, 9);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 4);
			prepStmt.setInt(1, 2);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 5);
			prepStmt.setInt(1, 1);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 6);
			prepStmt.setInt(1, 7);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 7);
			prepStmt.setInt(1, 9);
			prepStmt.executeUpdate();
			
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 1);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 2);
			prepStmt.executeUpdate();


			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 3);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 4);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 5);
			prepStmt.executeUpdate();

			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 6);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 7);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 8);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 13);
			prepStmt.setInt(1, 9);
			prepStmt.executeUpdate();
			
			prepStmt.setInt(2, 9);
			prepStmt.setInt(1, 13);
			prepStmt.executeUpdate();
			
			con.close();

		} catch (SQLException e) {
			System.err.println("Error al insertar laos seguidores/seguidos! " + e.getMessage());
			e.printStackTrace();
		}

	}
    
	public static void main(String[] args) {
		InitDatabase db = new InitDatabase();
		db.crearTablas();
		db.insertarSeriesYPeliculasDefault();
	//	db.insertarPersonasDesdeCSV();
		db.insertarNoticiasDefault();
		db.insertarUsuarioDesdeCSV();
		db.insertarPostDefault();
		db.insertarSeriesFavoritas();
		db.insertarPelisFavoritas();
		db.insertarSeguidores();
	}
}