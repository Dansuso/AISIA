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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Contenido;
import domain.Pelicula;
import domain.Post;
import domain.Serie;
import domain.Usuario;
import domain.Contenido.Genero;
import domain.Contenido.TIPO;
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

		} catch (SQLException e) {
			System.err.println("Error con la consulta de insercion de noticias: " + e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	public List<Noticia> obtenerNoticias(){
		
		List<Noticia> noticias = new ArrayList<Noticia>();
		String sqlNoticias = "SELECT * FROM NOTICIA";
		try(PreparedStatement stmt = con.prepareStatement(sqlNoticias)){
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
	
	
	public void insertarPost(String contenido, long fechaPost, int idCreadorPost) {
		String sqlInsertarPost = "INSERT INTO POST (CONTENIDO,FECHA_POST,ID_USUARIO_CREADOR) VALUES(?,?,?)";
		try(PreparedStatement prepStmt = con.prepareStatement(sqlInsertarPost)){
			prepStmt.setString(1,contenido);
			prepStmt.setLong(2, fechaPost); //Es un unix timestamp
			prepStmt.setInt(3, idCreadorPost);
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			System.err.println("Error al insertar Post ! " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que devuelve los 10 ultimos posts nuestros y de nuestros seguidores, ordenados por fecha.
	 * @param idUsuario Id del Usuario de que se quieren sacar los posts (y de los que sigue)
	 * @return Lista con 10 posts o menos
	 */
	public List<Post> obtenerPostFeed(int idUsuario){
		List<Post> listaPost = new ArrayList<>();
		String sqlObtenerPostFeed = """
				
				SELECT ID_POST,CONTENIDO,FECHA_POST,ID_USUARIO_CREADOR,USERNAME,CREACIONCUENTA,PAIS,FOTO,CONTRASENA 
				FROM POST P
				JOIN USUARIO U ON P.ID_USUARIO_CREADOR =  U.ID_USUARIO
				WHERE ID_USUARIO_CREADOR = ? OR ID_USUARIO_CREADOR IN 
				(SELECT ID_SEGUIDO FROM SEGUIDORES WHERE ID_SEGUIDOR = ? )
				""";
		try(PreparedStatement prepStmt = con.prepareStatement(sqlObtenerPostFeed)){
			prepStmt.setInt(1, idUsuario);
			prepStmt.setInt(2, idUsuario);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				int idPost = rs.getInt("ID_POST"); //ID del Post
				String contenido = rs.getString("CONTENIDO"); //Conenido
				long fechaPost = rs.getLong("FECHA_POST"); //Fecha (TimeStamp!!)
				//Tranformar la fecha del Post a un LocalDateTime
				LocalDateTime fechaFormateada = LocalDateTime.ofInstant(Instant.ofEpochMilli(fechaPost), ZoneId.of("CET"));
				int idUsuarioCreador = rs.getInt("ID_USUARIO_CREADOR");
				String username = rs.getString("USERNAME");
				int creacionCuenta = rs.getInt("CREACIONCUENTA");
				LocalDate fechaFormateadaCreacionCuenta = LocalDate.ofInstant(Instant.ofEpochMilli(creacionCuenta), ZoneId.of("CET"));
				String pais = rs.getString("PAIS");
				String foto = rs.getString("FOTO");
				String contrasena = rs.getString("CONTRASENA");
				Usuario u = new Usuario(idUsuarioCreador, username, fechaFormateadaCreacionCuenta, pais, foto, contrasena);
				Post p = new Post(idPost, contenido, fechaFormateada,u);
				listaPost.add(p);
			}
			
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listaPost;
		
	}
	
	public void borrarPost(int postID) {
		String sqlBorrarPost = "DELETE FROM POST WHERE ID_POST = ? ";
		try(PreparedStatement prepStmt = con.prepareStatement(sqlBorrarPost)){
			prepStmt.setInt(1, postID);
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			System.err.println("Error al borrar Post ! " + e.getMessage());
			e.printStackTrace();
		}
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
	        	
	        	Pelicula pelicula = new Pelicula(rsPelicula.getInt("ID_PELICULA"), 
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
	        	
	        	Serie serie = new Serie(rsSerie.getInt("ID_SERIE"),
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

	        				
	        	
	        	contenidos.add(serie);
	        }
			
	        rsSerie.close();
	        stmtSerie.close();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contenidos;
		
	}
	

	public List<Usuario> obtenerUsuarios() {
	    List<Usuario> usuarios = new ArrayList<>();
	    String sql = "SELECT * FROM usuario";

	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	                
	                Usuario usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("username"), LocalDate.parse(rs.getString("creacionCuenta")),
			                rs.getString("pais"), rs.getString("foto"), rs.getString("contrasena"));
	            
	            usuarios.add(usuario);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener datos de la tabla Usuarios: " + e.getMessage());
	    }
	    return usuarios;
	}
	
	public Usuario loginUsuario(String username, String pass) {
		
		String sqlSelectLogin = "SELECT * FROM USUARIO WHERE USERNAME = ? AND CONTRASENA =?";
		try(PreparedStatement prepStmt = con.prepareStatement(sqlSelectLogin)){
			prepStmt.setString(1, username);
			prepStmt.setString(2, pass);
			ResultSet rsLogin = prepStmt.executeQuery();
			System.out.println(prepStmt);
			while(rsLogin.next()) {
				return new Usuario(rsLogin.getInt("ID_USUARIO"),rsLogin.getString("USERNAME"), 
				null, rsLogin.getString("PAIS"), rsLogin.getString("FOTO"), rsLogin.getString("CONTRASENA"));
			}
			
		} catch (SQLException e) {
			System.err.println("Error con la consulta SQL de Login");
			e.printStackTrace();
		}
		return null;
	}

    // Método para insertar un nuevo usuario
    public void insertarUsuario(int id, String username, long creacioncuenta, String pais, String imagen, String contraseña) {
        String sqlInsertUsuario = """
            INSERT INTO USUARIO (ID_USUARIO, USERNAME, CREACIONCUENTA, PAIS, FOTO, CONTRASENA)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement prepStmt = con.prepareStatement(sqlInsertUsuario)) {
            
            prepStmt.setInt(1, id);
            prepStmt.setString(2, username);
            prepStmt.setLong(3, creacioncuenta);
            prepStmt.setString(4, pais);
            prepStmt.setString(5, imagen);
            prepStmt.setString(6, contraseña);
            prepStmt.executeUpdate();
            System.out.println("Usuario insertado correctamente: " + username);
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    // Método para borrar un usuario por CD
    public void borrarUsuario(int id) {
        String sqlDeleteUsuario = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";

        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement prepStmt = con.prepareStatement(sqlDeleteUsuario)) {
            
            prepStmt.setInt(1, id);
            int rowsAffected = prepStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario con ID " + id + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró un usuario con ID " + id + ".");
            }
        } catch (SQLException e) {
            System.err.println("Error al borrar usuario: " + e.getMessage());
        }
    }
	
	
	
	/**
	 * Metodo que devuelve los  posts nuestros ordenados por fecha.
	 * @param idUsuario Id del Usuario de que se quieren sacar los posts 
	 * @return Lista con posts o menos
	 */
	public List<Post> obtenerPostUsuario(int idUsuario){
		List<Post> listaPost = new ArrayList<>();
	    String sqlObtenerPostUsuario = "SELECT POST.*, usuario.* " +
	            "FROM usuario, POST " +
	            "WHERE POST.ID_USUARIO_CREADOR = usuario.id_usuario " +
	            "AND usuario.id_usuario = ? " +
	            "ORDER BY POST.FECHA_POST DESC";

	    try (PreparedStatement prepStmt = con.prepareStatement(sqlObtenerPostUsuario)) {
	        prepStmt.setInt(1, idUsuario); // Parametrizar el ID del usuario
	        ResultSet rs = prepStmt.executeQuery();

	        while (rs.next()) {
	            // Datos de la tabla POST
	            int idPost = rs.getInt("ID_POST");
	            String contenido = rs.getString("CONTENIDO");
	            long fechaPost = rs.getLong("FECHA_POST");

	            // Transformar la fecha del post a LocalDateTime
	            LocalDateTime fechaFormateada = LocalDateTime.ofInstant(Instant.ofEpochMilli(fechaPost), ZoneId.of("CET"));
	            int idUsuarioCreador = rs.getInt("ID_USUARIO_CREADOR");

	            // Datos de la tabla usuario
	            String usernameDB = rs.getString("USERNAME");
	            long creacionCuenta = rs.getLong("CREACIONCUENTA");

	            // Transformar la fecha de creación de la cuenta a LocalDate
	            LocalDate fechaFormateadaCreacionCuenta = Instant.ofEpochMilli(creacionCuenta).atZone(ZoneId.of("CET")).toLocalDate();
	            String pais = rs.getString("PAIS");
	            String foto = rs.getString("FOTO");
	            String contrasena = rs.getString("CONTRASENA");

	            // Crear objeto Usuario
	            Usuario u = new Usuario(idUsuarioCreador, usernameDB, fechaFormateadaCreacionCuenta, pais, foto, contrasena);

	            // Crear objeto Post
	            Post p = new Post(idPost, contenido, fechaFormateada, u);

	            // Añadir a la lista
	            listaPost.add(p);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaPost;
	}
	
		
	/**
	 * Metodo que devuelve las series favoritas de un usuario
	 * @param idUsuario Id del Usuario de que se quieren sacar las series favoritas
	 * @return Lista con las series favoritas
	 */
	public List<Serie> obtenerSeriesFav(int idUsuario) {
	    List<Serie> series = new ArrayList<>();
	    
	    String sqlObtenerSeriesFav = """
	    		SELECT * FROM Serie WHERE id_serie in 
	    		(SELECT id_serie FROM Serie_Favorito WHERE id_usuario = ?)
	    		""";

	    try(PreparedStatement prepStmt = con.prepareStatement(sqlObtenerSeriesFav)){
			prepStmt.setInt(1, idUsuario);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				
				// int id, TIPO tipo, String titulo, Genero genero, int calificacion, String distribuidora,
				// int edadRecomendada, String caratula, int numTemporadas, int numCapitulos, boolean emmy,LocalDate fecha
				Serie s = new Serie(rs.getInt("id_serie"),TIPO.SERIE, rs.getString("titulo"), Contenido.Genero.fromString(rs.getString("genero")), rs.getInt("calificacion"),
						rs.getString("distribuidora"), rs.getInt("edadrecomendada"),rs.getString("caratula"), rs.getInt("numerotemporadas"), rs.getInt("numeroepisodios"), 
						rs.getBoolean("emmy"), LocalDate.parse(rs.getString("fecha")));
				series.add(s);
			}
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return series;
	}
	
	public List<Pelicula> obtenerPelisFav(int idUsuario) {
	    List<Pelicula> peliculas = new ArrayList<>();
	    
	    String sqlObtenerSeriesFav = """
	    		SELECT * FROM Pelicula WHERE id_pelicula in 
	    		(SELECT id_pelicula FROM Pelicula_Favorito WHERE id_usuario = ?)
	    		""";

	    try(PreparedStatement prepStmt = con.prepareStatement(sqlObtenerSeriesFav)){
			prepStmt.setInt(1, idUsuario);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				
//				int id,TIPO tipo, String titulo, Genero genero, int duracion, int calificacion,
//				String distribuidora, int edadRecomendada, boolean oscar, String caratula,LocalDate fecha
				Pelicula p = new Pelicula(rs.getInt("id_pelicula"),TIPO.PELICULA, rs.getString("titulo"), Contenido.Genero.fromString(rs.getString("genero")), 
						rs.getInt("duracion"), rs.getInt("calificacion"), rs.getString("distribuidora"), rs.getInt("edadrecomendada"),rs.getBoolean("oscar"), 
						rs.getString("caratula"), LocalDate.parse(rs.getString("fecha")));
				peliculas.add(p);
			}
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return peliculas;
	}
	
	public ArrayList<Usuario> obtenerSeguidores(int idUsuario) {
	    ArrayList<Usuario> seguidores = new ArrayList<>();
	    String sqlSeguidores = """
	    		
	    		SELECT * FROM usuario WHERE ID_USUARIO IN (SELECT ID_SEGUIDOR FROM SEGUIDORES
	    		WHERE ID_SEGUIDO = ?) """;
	    
	    try(PreparedStatement prepStmt = con.prepareStatement(sqlSeguidores)){
			prepStmt.setInt(1, idUsuario);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				
				Usuario seguidor = new Usuario(rs.getInt("id_usuario"), rs.getString("username"), LocalDate.parse(rs.getString("creacionCuenta")),
		                rs.getString("pais"), rs.getString("foto"), rs.getString("contrasena"));
            
            seguidores.add(seguidor);
			}

	   
	    } catch (SQLException e) {
	        System.err.println("Error al obtener datos de la SEGUIDORES: " + e.getMessage());
	    }
	    return seguidores;
	}
	
	public ArrayList<Usuario> obtenerSeguidos(int idUsuario) {
	    ArrayList<Usuario> seguidos = new ArrayList<>();
	    String sqlSeguidos = """
	    		
	    		SELECT * FROM usuario WHERE ID_USUARIO IN (SELECT ID_SEGUIDO FROM SEGUIDORES
	    		WHERE ID_SEGUIDOR = ?); """;
	    
	    try(PreparedStatement prepStmt = con.prepareStatement(sqlSeguidos)){
			prepStmt.setInt(1, idUsuario);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next()) {
				
				Usuario seguido = new Usuario(rs.getInt("id_usuario"), rs.getString("username"), LocalDate.parse(rs.getString("creacionCuenta")),
		                rs.getString("pais"), rs.getString("foto"), rs.getString("contrasena"));
            
            seguidos.add(seguido);
			}

	   
	    } catch (SQLException e) {
	        System.err.println("Error al obtener datos de la SEGUIDOS: " + e.getMessage());
	    }
	    return seguidos;
	}
	
	public void anadirFavoritos(int idUsuario, int idContenido, Contenido.TIPO tipoContenido) {
		String sqlInsert = null;

	    if (TIPO.PELICULA.equals(tipoContenido)) {
	        sqlInsert = "INSERT OR IGNORE INTO PELICULA_FAVORITO (id_pelicula, id_usuario) VALUES (?, ?)";
	    } else if (TIPO.SERIE.equals(tipoContenido)) {
	        sqlInsert = "INSERT OR IGNORE INTO SERIE_FAVORITO (id_serie, id_usuario) VALUES (?, ?)";
	    }
	    
	    if (sqlInsert != null) {
	    	try {
				PreparedStatement stmt = con.prepareStatement(sqlInsert);
				stmt.setInt(1, idContenido);
				stmt.setInt(2, idUsuario);
				int rowsAffected = stmt.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Contenido añadido a favoritos.");
	            } else {
	                System.out.println("El contenido ya estaba en favoritos.");
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	public void eliminarDeFavoritos(int idUsuario, int idContenido, Contenido.TIPO tipoContenido) {
	    String sqlDelete = null;

	    if (TIPO.PELICULA.equals(tipoContenido)) {
	        sqlDelete = "DELETE FROM PELICULA_FAVORITO WHERE id_pelicula = ? AND id_usuario = ?";
	    } else if (TIPO.SERIE.equals(tipoContenido)) {
	        sqlDelete = "DELETE FROM SERIE_FAVORITO WHERE id_serie = ? AND id_usuario = ?";
	    }

	    if (sqlDelete != null) {
	        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	             PreparedStatement prepStmt = con.prepareStatement(sqlDelete)) {
	            prepStmt.setInt(1, idContenido);
	            prepStmt.setInt(2, idUsuario);
	            int rowsAffected = prepStmt.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("Contenido eliminado de favoritos.");
	            } else {
	                System.out.println("El contenido no estaba en favoritos.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	

}
