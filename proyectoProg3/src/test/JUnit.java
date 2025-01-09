package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import db.GestorDB;
import db.InitDatabase;
import domain.Noticia;
import domain.Post;
import domain.Usuario;

public class JUnit {
/*
 	Pruebas unitarias con JUnit
		Definición de casos de prueba que validen el correcto funcionamiento de:
			· Inserción de datos en la BBDD.
			· Recuperación de datos de la BBDD.

 */
	
	
	public class GestorDBTest {
	    private static InitDatabase initDB;
	    private static GestorDB gestorDB;

	    @BeforeAll
	    static void setup() {
	        // Inicializar la base de datos de prueba
	        initDB = new InitDatabase();
	        initDB.crearTablas();
	        gestorDB = new GestorDB();
	    }

	    @AfterEach
	    void cleanDatabase() throws SQLException {
	        // Limpiar las tablas después de cada prueba
	        try (Connection con = DriverManager.getConnection(initDB.CONNECTION_STRING)) {
	            Statement stmt = con.createStatement();
	            stmt.execute("DELETE FROM NOTICIA");
	            stmt.execute("DELETE FROM POST");
	            stmt.execute("DELETE FROM USUARIO");
	            con.close();
	        }
	    }

	    @Test
	    void testInsertarNoticia() {
	        // Probar la inserción de una noticia
	        String titulo = "Prueba de Noticia";
	        String resumen = "Esta es una prueba.";
	        String url = "http://prueba.com";
	        String fuente = "TestSource";

	        gestorDB.insertarNoticia(titulo, resumen, url, fuente);

	        // Recuperar la noticia insertada
	        List<Noticia> noticias = gestorDB.obtenerNoticias();
	        assertEquals(1, noticias.size());
	        Noticia noticia = noticias.get(0);

	        assertEquals(titulo, noticia.getTitulo());
	        assertEquals(resumen, noticia.getResumen());
	        assertEquals(url, noticia.getUrl());
	        assertEquals(fuente, noticia.getFuente());
	    }

	    @Test
	    void testInsertarYRecuperarPost() {
	        // Insertar y recuperar un post
	        String contenido = "Contenido de prueba";
	        long fechaPost = System.currentTimeMillis();
	        int idUsuario = 1;

	        gestorDB.insertarPost(contenido, fechaPost, idUsuario);

	        List<Post> posts = gestorDB.obtenerPostUsuario(idUsuario);
	        assertEquals(1, posts.size());
	        Post post = posts.get(0);

	        assertEquals(contenido, post.getContenido());
	        assertNotNull(post.getFechaPost());
	        assertEquals(idUsuario, post.getCreadorPost().getCodigo());
	    }

	    @Test
	    void testInsertarUsuarioYRecuperar() {
	        // Insertar un usuario
	        int idUsuario = 1;
	        String username = "user_test";
	        String pais = "Testland";
	        String foto = "foto.jpg";
	        String contrasena = "password";
	        gestorDB.insertarPersona(idUsuario, username, "Prueba", 30, "test@mail.com");

	        // Recuperar el usuario
	        List<Usuario> usuarios = gestorDB.obtenerUsuarios();
	        assertEquals(1, usuarios.size());
	        Usuario usuario = usuarios.get(0);

	        assertEquals(username, usuario.getUsername());
	        assertEquals(pais, usuario.getPais());
	        assertEquals(foto, usuario.getFoto());
	    }
	}

}
