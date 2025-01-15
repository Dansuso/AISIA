package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import db.GestorDB;
import db.InitDatabase;
import domain.Noticia;
import domain.Post;
import domain.Usuario;

import org.junit.jupiter.api.*;

import java.time.Instant;


/*
 	Pruebas unitarias con JUnit
		Definición de casos de prueba que validen el correcto funcionamiento de:
			· Inserción de datos en la BBDD.
			· Recuperación de datos de la BBDD.

 */
	


	import static org.junit.jupiter.api.Assertions.*;


	public class TestAisia {

		private GestorDB gestorDB;

	    @BeforeAll
	    public void setupDatabase() {
	        // Inicializar la base de datos antes de todas las pruebas
	        InitDatabase initDb = new InitDatabase();
	        initDb.crearTablas();
	        gestorDB = new GestorDB();
	    }

	    @BeforeEach
	    public void setUp() {
	        
	    }

	    @Test
	    public void testInsertarYObtenerNoticias() {
	    	setupDatabase();
	    	
	        String titulo = "Noticia Test";
	        String resumen = "Resumen de prueba";
	        String url = "http://example.com/noticia-test";
	        String fuente = "Fuente Test";

	        gestorDB.insertarNoticia(titulo, resumen, url, fuente);

	        List<Noticia> noticias = gestorDB.obtenerNoticias();
	        
	        Noticia noticiaInsertada = noticias.get(noticias.size() - 1);
	        
//	        for (Noticia noticia : noticias) {
//	        	if (noticia.getTitulo().equals(titulo)) {
//	        		noticiaInsertada = noticia;
//	        	}
//	        }
	        
	        System.out.println(noticiaInsertada.getTitulo());
	        assertNotNull(noticiaInsertada, "La noticia insertada debería existir en la base de datos");
	        
	        assertEquals("El título de la noticia no coincide", titulo, noticiaInsertada.getTitulo());
	        
	        assertEquals("La URL de la noticia no coincide", url, noticiaInsertada.getUrl());

	    }

	    @Test
	    public void testEliminarPost() {
	    	setupDatabase();
	    	
	        String contenido = "Post a eliminar";
	        long fechaPost = Instant.now().toEpochMilli();
	        int idUsuario = 1;

	        int idPost = gestorDB.insertarPost(contenido, fechaPost, idUsuario);
	        assertTrue(idPost > 0, "El ID del post debería ser mayor que 0");

	        gestorDB.borrarPost(idPost);

	        List<Post> posts = gestorDB.obtenerPostUsuario(idUsuario);
	        
	        
	        // Método realizado con la ayuda de ChatGPT 4o
	        
	        /* 
	         * -.stream(): Convierte la lista de post en un flujo de datos stream para poder trabajar de una forma más funcional
	         * 
	         * -.filter(): filtra los elementos de la lista. En este caso selecciona sólo los elementos con el mismo id. Como el id
	         * 				es único, arrojará solo un valor.
	         * 
	         * -.findFirst(): aunque en este caso innecesario, se utiliza para seleccionar el primer elemento de todos con esas características.
	         * 
	         * -.orElse(): si no encuentra ningún elemento con estas caracterísitcas, devuelve null.
	         * 
	         */
	        
	        Post postEliminado = posts.stream()
	                                  .filter(p -> p.getCodigoPost() == idPost)
	                                  .findFirst()
	                                  .orElse(null);
	        

	        assertNull(postEliminado, "El post eliminado no debería existir en la base de datos");
	    }

	    @Test
	    public void testInsertarYObtenerUsuarios() {
	    	setupDatabase();
	    	
	        int id = 100;
	        String username = "usuarioTest";
	        String fecha = "2025-01-01";
	        String pais = "España";
	        String foto = "foto.png";
	        String contrasena = "password123";

	        gestorDB.insertarUsuario(id, username, fecha, pais, foto, contrasena);

	        List<Usuario> usuarios = gestorDB.obtenerUsuarios();
	        Usuario usuarioInsertado = usuarios.stream()
	                                           .filter(u -> u.getCodigo() == id)
	                                           .findFirst()
	                                           .orElse(null);

	        assertNotNull(usuarioInsertado, "El usuario insertado debería existir en la base de datos");
	        assertEquals("El nombre de usuario no coincide", username, usuarioInsertado.getUsername());

	    }

	    @Test
	    public void testEliminarUsuario() {
	    	setupDatabase();
	    	
	        int id = 101;
	        String username = "usuarioAEliminar";
	        String fecha = "2025-01-01";
	        String pais = "España";
	        String foto = "foto.png";
	        String contrasena = "password123";

	        gestorDB.insertarUsuario(id, username, fecha, pais, foto, contrasena);
	        gestorDB.borrarUsuario(id);

	        List<Usuario> usuarios = gestorDB.obtenerUsuarios();
	        Usuario usuarioEliminado = usuarios.stream()
	                                           .filter(u -> u.getCodigo() == id)
	                                           .findFirst()
	                                           .orElse(null);

	        assertNull(usuarioEliminado, "El usuario eliminado no debería existir en la base de datos");

	    }




}
