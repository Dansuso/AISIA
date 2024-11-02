package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaUsuario extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaUsuario() {
		setLayout(new GridLayout(3, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Usuario");
		setSize(640,640);
		
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
		
		ImageIcon fotoPerfilDefecto = new ImageIcon("resources/images/recursos/defautUsuario.png");
		Image scaledImage = fotoPerfilDefecto.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        ImageIcon fotoPerfil = new ImageIcon(scaledImage);
        
		JLabel etiquetaFotoPerfil = new JLabel(fotoPerfil);
		JButton botonEtiquetaFotoPerfil = new JButton();
		
		botonEtiquetaFotoPerfil.addActionListener(e -> {
			JFrame nuevaVentana = new JFrame("Foto Usuario");
			nuevaVentana.setSize(600, 600);
		    nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana al cerrar
		    
			JPanel nuevaVentanaPanel = new JPanel();
			nuevaVentanaPanel.setBackground(colorAisia);
			nuevaVentana.add(nuevaVentanaPanel);
		    JLabel fotoPerfilDefectoLabel = new JLabel(fotoPerfilDefecto);
		    nuevaVentanaPanel.add(fotoPerfilDefectoLabel);
		    
		    nuevaVentana.setVisible(true);
		});
		
		
		botonEtiquetaFotoPerfil.add(etiquetaFotoPerfil);
		botonEtiquetaFotoPerfil.setBackground(colorAisia);
		
		panelFotoNombre.add(botonEtiquetaFotoPerfil, BorderLayout.WEST);
		
		ImageIcon fotoAisia = new ImageIcon("resources/images/aisia/aisia1.png");
		
		JLabel etiquetaFotoAisia = new JLabel(fotoAisia);
		panelFotoNombre.add(etiquetaFotoAisia, BorderLayout.EAST);
	
		JLabel nombreUsuario = new JLabel("@" + "Nombre Usuario");
		panelFotoNombre.add(nombreUsuario);
		nombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		

		JLabel nombreRealUsuario = new JLabel("           " + "Nombre Real");
		panelFotoNombre.add(nombreRealUsuario, BorderLayout.AFTER_LAST_LINE);
		
		/* Prohibido
		nombreRealUsuario.setBounds(178, 75, 100, 100);
		VentanaUsuario.this.add(nombreRealUsuario);
		*/
		
		
		JPanel panelPeliculas = new JPanel(new BorderLayout());
		panelPeliculas.setBackground(colorAisia2);

		
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
		botonAbreComentario1.addActionListener(ventanaComentario(comentario1, nombre1, calif1, foto1));
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
	
	
	public static ActionListener ventanaComentario(String comentario, String nombre, String calif, String foto) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Color colorAisia3 = new Color(235, 155, 195);
                JFrame ventanaComentario = new JFrame();
                ventanaComentario.setTitle("Comentario");
                ventanaComentario.setSize(640, 640);
                ventanaComentario.setLayout(new GridLayout(2, 1));
                
                JPanel panelArriba = new JPanel(new GridLayout(1,2));
                
                JPanel panelVentanaComentario = new JPanel();
                panelVentanaComentario.setLayout(new BorderLayout());
                panelVentanaComentario.setBackground(colorAisia3);

                
                JTextArea areaComentario = new JTextArea(comentario);
                areaComentario.setBackground(colorAisia3);
                areaComentario.setLineWrap(true);       // Ajustar texto
                areaComentario.setWrapStyleWord(true);	// Que no se partan las palabras
                areaComentario.setEditable(false);
        		areaComentario.setFont(new Font("Arial", Font.BOLD, 15));
        		
        		JScrollPane deslizanteComentario = new JScrollPane(areaComentario);
                panelVentanaComentario.add(deslizanteComentario);
                
                JPanel panelNombreCalif = new JPanel(new BorderLayout());
                panelNombreCalif.setBackground(colorAisia3);
                
                JTextArea areaNombre = new JTextArea(" " + nombre);
                areaNombre.setBackground(colorAisia3);
                areaNombre.setLineWrap(true);       // Ajustar texto
                areaNombre.setWrapStyleWord(true);	// Que no se partan las palabras
                areaNombre.setEditable(false);
        		areaNombre.setFont(new Font("Tahoma", Font.BOLD, 45));
        		
                panelNombreCalif.add(areaNombre, BorderLayout.NORTH);
                
                JTextArea areaCalif = new JTextArea(" " + calif);
                areaCalif.setBackground(colorAisia3);
                areaCalif.setLineWrap(true);       // Ajustar texto
                areaCalif.setWrapStyleWord(true);	// Que no se partan las palabras
                areaCalif.setEditable(false);
        		areaCalif.setFont(new Font("emojiFont", Font.BOLD, 70));
        		
        		panelNombreCalif.add(areaCalif, BorderLayout.SOUTH);
                
                panelArriba.add(panelNombreCalif);
                panelArriba.add(panelVentanaComentario);

                JPanel fotoPanel = new JPanel(new BorderLayout());
                fotoPanel.setBackground(colorAisia3);
                ImageIcon caratula = new ImageIcon(foto);
                Image escaladoCaratula = caratula.getImage().getScaledInstance(350, 450, Image.SCALE_SMOOTH);
                ImageIcon escaladoCaratulaFin = new ImageIcon(escaladoCaratula);
                JLabel etiquetaCaratula = new JLabel(escaladoCaratulaFin);
                fotoPanel.add(etiquetaCaratula, BorderLayout.CENTER);
                
                ventanaComentario.add(panelArriba, BorderLayout.NORTH);
                ventanaComentario.add(fotoPanel, BorderLayout.SOUTH);
                ventanaComentario.setVisible(true);
            }
        };
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
