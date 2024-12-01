package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Usuario;
import utils.BanderaUtil;
import domain.Contenido;
import domain.Contenido.Genero;
import domain.Contenido.TIPO;
import domain.Pelicula;

public class VentanaUsuario extends JFrame{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	Contenido contenido = new Pelicula(1, TIPO.PELICULA, "Gladiator", Genero.AVENTURA, 143, 5, "Sony", 12, true, "resources/images/recursos/contenido/gladiator.jpg", LocalDate.of(2010, 2, 2));
	Usuario usuario = new Usuario(111, "Nombre real", "Nombre Usuario", LocalDate.of(2024, 10, 31), "spain",143, 100, "resources/images/recursos/defautUsuario.png", "1234", contenido);
	
	public VentanaUsuario() {
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
		
		Color colorAisia = new Color(184, 232, 229);
		Color colorAisia2 = new Color(120, 142, 227);
		Color colorAisia3 = new Color(235, 155, 195);
		
		// Establecer color y tomaño del panel.
		
		panelFotoNombre.setBackground(colorAisia);
//		panelFotoNombre.setPreferredSize(new Dimension(125, 125));
		
		ImageIcon fotoPerfilDefecto = new ImageIcon(usuario.getFoto());
		Image scaledImage = fotoPerfilDefecto.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        ImageIcon fotoPerfil = new ImageIcon(scaledImage);
        
		JLabel etiquetaFotoPerfil = new JLabel(fotoPerfil);
		JButton botonEtiquetaFotoPerfil = new JButton();
		
		botonEtiquetaFotoPerfil.addActionListener(e -> {
			JDialog nuevaVentana = new JDialog(VentanaUsuario.this, "Foto Usuario", Dialog.ModalityType.APPLICATION_MODAL);
			nuevaVentana.setSize(600, 600);
		    nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana al cerrar
		    
			JPanel nuevaVentanaPanel = new JPanel();
			nuevaVentanaPanel.setBackground(colorAisia);
			nuevaVentana.add(nuevaVentanaPanel);
		    JLabel fotoPerfilDefectoLabel = new JLabel(fotoPerfilDefecto);
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
	
		JLabel nombreUsuario = new JLabel("@" + usuario.getDisplayname());
		panelFotoNombre.add(nombreUsuario);
		nombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		

		JLabel nombreRealUsuario = new JLabel("           " + usuario.getUsername());
		panelFotoNombre.add(nombreRealUsuario, BorderLayout.AFTER_LAST_LINE);
		
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
		
		JLabel seguidores = new JLabel(String.valueOf(usuario.getNumSeguidores()));
		seguidores.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelSeg.add(seguidores);
		
		JLabel seguidoresStr = new JLabel("Seguidores");
		seguidoresStr.setFont(new Font("Arial", Font.BOLD, 16));
		panelSeg.add(seguidoresStr, BorderLayout.NORTH);
		
		JPanel panelSeguidos = new JPanel(new BorderLayout());
		panelSeguidos.setBackground(colorAisia2);
		
		JLabel seguidos = new JLabel(String.valueOf(usuario.getNumSeguidos()));
		seguidos.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelSeguidos.add(seguidos);
		
		JLabel seguidosStr = new JLabel("Seguidos");
		seguidosStr.setFont(new Font("Arial", Font.BOLD, 16));
		panelSeguidos.add(seguidosStr, BorderLayout.NORTH);
		
		
		JPanel panelPais = new JPanel(new BorderLayout());
		panelPais.setBackground(colorAisia2);
		
		JLabel pais = new JLabel(String.valueOf(usuario.getPais()));
		pais.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelPais.add(pais, BorderLayout.SOUTH);
		panelPais.add(BanderaUtil.obtenerBandera(usuario.getPais()));
		
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
		ImageIcon caratula = new ImageIcon(usuario.getFavorito().getCaratula());
        Image escaladoCaratula = caratula.getImage().getScaledInstance(195, 280, Image.SCALE_SMOOTH);
        ImageIcon escaladoCaratulaFin = new ImageIcon(escaladoCaratula);
        JLabel etiquetaCaratula = new JLabel(escaladoCaratulaFin);
        JLabel etiquetaCaratula2 = new JLabel(escaladoCaratulaFin);
		panelPelis.add(etiquetaCaratula, BorderLayout.WEST);
		panelPelis.add(etiquetaCaratula2, BorderLayout.EAST);
		
		// Creamos un JLabel con "Contenido Favorito".  
		JLabel contenidoFavorito = new JLabel("Contenido favorito");
		contenidoFavorito.setFont(new Font("Arial", Font.BOLD, 15));
		panelPelis.add(contenidoFavorito, BorderLayout.NORTH);
		
		// Añadimos ambos paneles
		panelPeliculas.add(panelPelis);
		panelPeliculas.add(panelInformacion);
		

		
		JPanel panelUltiComentario = new JPanel(new BorderLayout());
		panelUltiComentario.setBackground(colorAisia3);
		
		JPanel panelConComentarios = new JPanel(new GridLayout(3, 1));
		String emogiPeli = "🎥 ";
		String emogiJuego = "🎮 ";
		
		JLabel ultimoComentario = new JLabel("<html>Últimos <br> comentarios.<html>");
		ultimoComentario.setFont(new Font("Agency FB", Font.BOLD, 25));
		
		String comentario1 = "Horror, no perdaís el tiempo en esta película porque de verdad no merece nada la pena. Es incrible ver a la gente abandonado la sala. Las canciones insoportables, me pitaban los oidos.";
		String nombre1 = "Joker: Folie à Deux";
		String calif1 = "✰";
		String foto1 = "resources/images/recursos/contenido/joker2.jpg";
		
		JLabel ejemploComentario1 = new JLabel(emogiPeli + " " + nombre1 + ": " + calif1 +  " " + acortarComentario(comentario1));
		
		JButton botonAbreComentario1 = new JButton();
		botonAbreComentario1.addActionListener(VentanaComentario.VentanaComent(comentario1, nombre1, calif1, foto1));
		botonAbreComentario1.setBackground(colorAisia3);
		botonAbreComentario1.add(ejemploComentario1);
		
		// TODO
		
		JLabel ejemploComentario2 = new JLabel(emogiPeli + "Titanic: " +  "✰✰✰✰✰" + " 'Me ha encantado, no he podido evitar llorar con el final'");
		JButton botonAbreComentario2 = new JButton();
		botonAbreComentario2.setBackground(colorAisia3);
		botonAbreComentario2.add(ejemploComentario2);
		
		JLabel ejemploComentario3 = new JLabel(emogiJuego + "Grand Theft Auto: Vice City: " +  "✰✰✰✰✰" + " 'Que decir de un juego clasico que nos ha...'");
		JButton botonAbreComentario3 = new JButton();
		botonAbreComentario3.setBackground(colorAisia3);
		botonAbreComentario3.add(ejemploComentario3);
		
		panelConComentarios.add(botonAbreComentario1, BorderLayout.NORTH);
		panelConComentarios.add(botonAbreComentario2, BorderLayout.CENTER);
		panelConComentarios.add(botonAbreComentario3, BorderLayout.SOUTH);
		
		panelUltiComentario.add(ultimoComentario, BorderLayout.WEST);
		panelUltiComentario.add(panelConComentarios, BorderLayout.EAST);
		
		
		add(panelFotoNombre, BorderLayout.NORTH);
		add(panelUltiComentario, BorderLayout.CENTER);
		add(panelPeliculas, BorderLayout.SOUTH);
		
		
		setVisible(true);
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
	public static void main(String[] args) {
		VentanaUsuario vu = new VentanaUsuario();
	}
	
}
