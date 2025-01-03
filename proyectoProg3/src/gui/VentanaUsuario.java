package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import db.GestorDB;
import domain.Usuario;
import utils.BanderaUtil;
import utils.cargarFotoDePerfil;
import domain.Contenido;
import domain.Contenido.Genero;
import domain.Contenido.TIPO;
import domain.Pelicula;
import domain.Post;

public class VentanaUsuario extends JFrame{
	public Color colorAisia = new Color(184, 232, 229);
	public Color colorAisia2 = new Color(120, 142, 227);
	public Color colorAisia3 = new Color(235, 155, 195);
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	Contenido contenido = new Pelicula(1, TIPO.PELICULA, "Gladiator", Genero.AVENTURA, 143, 5, "Sony", 12, true, "resources/images/recursos/contenido/gladiator.jpg", LocalDate.of(2010, 2, 2));
	
	public VentanaUsuario(Usuario user) {
		setLayout(new GridLayout(3, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Usuario");
		setSize(640, 900);
		
		// Creo un panel para añadir la foto de perfil y el nombre de usuario debajo de esa foto de perfil.
		JPanel panelFotoNombre = new JPanel(new BorderLayout());
		
		/*
		 * setBounds Prohibido
		 * 
		 * panelFotoNombre.setBounds(0, 0, 630, 200);
		 */
		
		
		// Establecer color y tomaño del panel.
		
		panelFotoNombre.setBackground(colorAisia);
//		panelFotoNombre.setPreferredSize(new Dimension(125, 125));
		
		String direccionPredeterminada = "resources/images/recursos/perfil/";
		
		
		// Qué hacer en caso de que el usuario no quiera tener foto de perfil.
		String dirFoto = "";
		if (user.getFoto() == null) {
			dirFoto = "defautUsuario.png";
		} else {
			dirFoto = user.getFoto();
		}
		
		ImageIcon fotoPerfilDefecto = new ImageIcon(direccionPredeterminada + dirFoto);
		// Reescalamos la imagen
		Image scaledImage = fotoPerfilDefecto.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon fotoPerfil = new ImageIcon(scaledImage);
        
		JLabel etiquetaFotoPerfil = new JLabel(fotoPerfil);
		JButton botonEtiquetaFotoPerfil = new JButton();
		
		botonEtiquetaFotoPerfil.addActionListener(e -> {
			JDialog nuevaVentana = new JDialog(VentanaUsuario.this, "Foto Usuario", Dialog.ModalityType.APPLICATION_MODAL);
			nuevaVentana.setSize(600, 600);
		    nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana al cerrar
		    
		    // Reascalamos para que sea más grande
		    
		    Image scaledImageG = fotoPerfilDefecto.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
	        ImageIcon fotoPerfilG = new ImageIcon(scaledImageG);
			JPanel nuevaVentanaPanel = new JPanel();
			nuevaVentanaPanel.setBackground(colorAisia);
			nuevaVentana.add(nuevaVentanaPanel);
		    JLabel fotoPerfilDefectoLabel = new JLabel(fotoPerfilG);
		    nuevaVentanaPanel.add(fotoPerfilDefectoLabel);
		    
		    nuevaVentana.setLocationRelativeTo(VentanaUsuario.this);
		    nuevaVentana.setVisible(true);
		});
		

		botonEtiquetaFotoPerfil.add(etiquetaFotoPerfil);
		botonEtiquetaFotoPerfil.setBackground(colorAisia);
		
		panelFotoNombre.add(botonEtiquetaFotoPerfil, BorderLayout.WEST);
		
		ImageIcon fotoAisia = new ImageIcon("resources/images/aisia/aisia1.png");
		
		JLabel etiquetaFotoAisia = new JLabel(fotoAisia);
		panelFotoNombre.add(etiquetaFotoAisia, BorderLayout.EAST);
		
	
		JLabel nombreUsuario = new JLabel("@" + user.getUsername());
		panelFotoNombre.add(nombreUsuario);
		nombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		/* Prohibido
		nombreRealUsuario.setBounds(178, 75, 100, 100);
		VentanaUsuario.this.add(nombreRealUsuario);
		*/
		
		/*
		 * Cración del panel con la información básica y la película favorita. A continuacion viene la parte de información.
		 */
		
		
		JPanel panelPeliculas = new JPanel(new GridLayout(1, 2));
		JPanel panelInformacion = new JPanel(new GridLayout(1, 3));
		
		JPanel panelSeg = new JPanel(new BorderLayout());
		panelSeg.setBackground(colorAisia2);
		
		
		// TODO
		
		/*
		JLabel seguidores = new JLabel(String.valueOf(usuario.getNumSeguidores()));
		seguidores.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelSeg.add(seguidores);
		*/
		
		JLabel seguidoresStr = new JLabel("Seguidores");
		seguidoresStr.setFont(new Font("Arial", Font.BOLD, 16));
		panelSeg.add(seguidoresStr, BorderLayout.NORTH);
		
		JPanel panelSeguidos = new JPanel(new BorderLayout());
		panelSeguidos.setBackground(colorAisia2);
		
		
		// TODO
		
		/*
		JLabel seguidos = new JLabel(String.valueOf(usuario.getNumSeguidos()));
		seguidos.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelSeguidos.add(seguidos);
		*/
		
		JLabel seguidosStr = new JLabel("Seguidos");
		seguidosStr.setFont(new Font("Arial", Font.BOLD, 16));
		panelSeguidos.add(seguidosStr, BorderLayout.NORTH);
		
		
		JPanel panelPais = new JPanel(new BorderLayout());
		panelPais.setBackground(colorAisia2);
		
		
		// TODO
		
		/*
		JLabel pais = new JLabel(String.valueOf(usuario.getPais()));
		pais.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelPais.add(pais, BorderLayout.SOUTH);
		panelPais.add(BanderaUtil.obtenerBandera(usuario.getPais()));
		*/
		
		JLabel paisStr = new JLabel("País");
		paisStr.setFont(new Font("Arial", Font.BOLD, 16));
		panelPais.add(paisStr, BorderLayout.NORTH);
		
		
		panelInformacion.add(panelSeg);
		panelInformacion.add(panelSeguidos);
		panelInformacion.add(panelPais);
		
		
		
		/*
		 * Ahora vienen la parte de la película favorita
		 * 
		 */
		
		// Cogemas el contenido favorito del usuario, cogemos la ubicacion de la caratula de ese contenido y
		// lo añadimos reescalado para que sea del tamaño ideal.
		JPanel panelPelis = new JPanel(new BorderLayout());
		panelPelis.setBackground(colorAisia2);
		
		
		 // TODO
//		ImageIcon caratula = new ImageIcon(usuario.getFavorito().getCaratula());
//      Image escaladoCaratula = caratula.getImage().getScaledInstance(195, 280, Image.SCALE_SMOOTH);
//      ImageIcon escaladoCaratulaFin = new ImageIcon(escaladoCaratula);
//      JLabel etiquetaCaratula = new JLabel(escaladoCaratulaFin);
//      JLabel etiquetaCaratula2 = new JLabel(escaladoCaratulaFin);
//		panelPelis.add(etiquetaCaratula, BorderLayout.WEST);
//		panelPelis.add(etiquetaCaratula2, BorderLayout.EAST);
		
		// Creamos un JLabel con "Contenido Favorito".  
		JLabel contenidoFavorito = new JLabel("Contenidos favoritos");
		contenidoFavorito.setFont(new Font("Arial", Font.BOLD, 15));
		panelPelis.add(contenidoFavorito, BorderLayout.NORTH);
		
		// Añadimos ambos paneles
		panelPeliculas.add(panelPelis);
		panelPeliculas.add(panelInformacion);
		

		
		JPanel panelUltiComentario = new JPanel(new BorderLayout());
		panelUltiComentario.setBackground(colorAisia3);
		
		
		JLabel ultimoComentario = new JLabel("<html>Últimos <br> comentarios.<html>");
		ultimoComentario.setFont(new Font("Agency FB", Font.BOLD, 25));
		
		GestorDB bd = new GestorDB();
		List<Post> comentarios = bd.obtenerPostUsuario(user.getCodigo());
		System.out.println(comentarios);
		
		JPanel panelConComentarios = new JPanel(new GridLayout(3, 1));
		
		for (int i = 0; i < 3; i++) {
			try {
				JButton botonAbreComentario = botonFormatoComentario(comentarios.get(i));
				botonAbreComentario.setBackground(colorAisia3);
				panelConComentarios.add(botonAbreComentario);
				botonAbreComentario.addActionListener(VentanaComentario.VentanaComent(comentarios.get(i)));
			} catch (IndexOutOfBoundsException e) {
				JLabel EtiquetaAbreComentario = new JLabel("Sin comentario.");
				JPanel panelSinComentario = new JPanel(new BorderLayout());
				panelSinComentario.add(EtiquetaAbreComentario, BorderLayout.WEST);
				panelSinComentario.setBackground(colorAisia3);
				panelConComentarios.add(panelSinComentario);
			}
		}
		
		
		
		// TODO
//		botonAbreComentario1.addActionListener(VentanaComentario.VentanaComent(comentarios.get(0)));

		

		/*
		JButton botonAbreComentario2 = botonFormatoComentario(comentarios.get(1));
		botonAbreComentario2.setBackground(colorAisia3);
		
		JButton botonAbreComentario3 = botonFormatoComentario(comentarios.get(2));
		botonAbreComentario3.setBackground(colorAisia3);

		
		panelConComentarios.add(botonAbreComentario2, BorderLayout.CENTER);
		panelConComentarios.add(botonAbreComentario3, BorderLayout.SOUTH);
		*/
		
		panelUltiComentario.add(panelConComentarios, BorderLayout.CENTER);
		
		panelUltiComentario.add(ultimoComentario, BorderLayout.WEST);

		
		
		add(panelFotoNombre, BorderLayout.NORTH);
		add(panelUltiComentario, BorderLayout.CENTER);
		add(panelPeliculas, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}

	private JButton botonFormatoComentario(Post post) {
		String contenido = acortarComentario(post.getContenido());
		JLabel contenidoEtiqueta = new JLabel(contenido);
		JButton boton = new JButton();
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(formatearFecha(post.getFechaPost()), BorderLayout.NORTH);
		panel.add(contenidoEtiqueta);
		panel.setBackground(colorAisia3);
		boton.add(panel);
		return boton;
	}

	private String acortarComentario(String comentario) {
		
		// Dividir el texto en palabras. Uso  de \\s+ para dividir con cualquier cantidad de espacios.
        String[] palabras = comentario.split("\\s+");
        if (palabras.length <= 10) {
            return comentario;
            
        } else {
        	// StringBuilder es una clase que se utiliza para crear y manipular cadenas de texto de una forma más eficiente que la clase String.
        	StringBuilder resultado = new StringBuilder();
        	for (int i = 0; i < 10; i++) {
        		resultado.append(palabras[i]).append(" ");
        	}
        
        resultado.append("...");
        // trim() para que no queden espacios
        return resultado.toString().trim();
        }
        
        
	}
	public static JLabel formatearFecha(LocalDateTime fecha) {
        // Crear un formato con el patrón deseado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy");
        JLabel etiqueta = new JLabel(fecha.format(formato));
        etiqueta.setFont(new Font("Serif", Font.BOLD, 17));
        return etiqueta;
    }
	
	public static void main(String[] args) {
		Usuario user = new Usuario(2, "johndoe", LocalDate.parse("2024-01-01"), "united-states", "1.jpg", "12345");
		new VentanaUsuario(user);
	}
	
}
