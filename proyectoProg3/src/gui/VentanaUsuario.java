package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaUsuario extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaUsuario() {
		VentanaUsuario.this.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Usuario");
		setSize(640,640);
		
		// Creo un panel para añadir la foto de perfil y el nombre de usuario debajo de esa foto de perfil.
		JPanel panelFotoNombre = new JPanel(new BorderLayout());
		panelFotoNombre.setBounds(0, 0, 630, 200);
		
		Color colorAisia = new Color(184, 232, 229);
		Color colorAisia2 = new Color(120, 142, 227);
		Color colorAisia3 = new Color(235, 155, 195);
		
		// Establecer color y tomaño del panel.
		panelFotoNombre.setBackground(colorAisia);
//		panelFotoNombre.setPreferredSize(new Dimension(125, 125));
		
		ImageIcon fotoPerfilDefecto = new ImageIcon("resources/images/recursos/defautUsuario.png");
		
		JLabel etiquetaFotoPerfil = new JLabel(fotoPerfilDefecto);
		JButton botonEtiquetaFotoPerfil = new JButton();
		botonEtiquetaFotoPerfil.addActionListener(e -> {
			JFrame nuevaVentana = new JFrame("Foto Usuario");
		    nuevaVentana.setSize(500, 500);
		    nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana al cerrar
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
		
		JLabel nombreRealUsuario = new JLabel("Nombre Real");
		nombreRealUsuario.setBounds(80, 50, 100, 100);
		VentanaUsuario.this.add(nombreRealUsuario);
		VentanaUsuario.this.add(panelFotoNombre, BorderLayout.NORTH);
		
		JPanel panelPeliculas = new JPanel(new BorderLayout());
		panelPeliculas.setBackground(colorAisia2);
		panelPeliculas.setBounds(0, 300, 630, 310);;
		VentanaUsuario.this.add(panelPeliculas);
		
		
		
		JPanel panelUltiComentario = new JPanel(new BorderLayout());
		panelUltiComentario.setBackground(colorAisia3);
		panelUltiComentario.setBounds(0, 200, 630, 100);
		add(panelUltiComentario);
		
		JLabel ultimoComentario = new JLabel("Último comentario.");
		ultimoComentario.setFont(new Font("Agency FB", Font.BOLD, 20));
		
		JLabel ejemploComentario = new JLabel("🎥 " + "Joker: Folie à Deux: ✰ 'Horror, no perdaís el tiempo en esta película porque...'");
		JButton botonAbreComentario = new JButton();
		botonAbreComentario.setBackground(colorAisia3);
		botonAbreComentario.add(ejemploComentario);
		
		panelUltiComentario.add(ultimoComentario, BorderLayout.WEST);
		panelUltiComentario.add(botonAbreComentario, BorderLayout.EAST);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		VentanaUsuario vu = new VentanaUsuario();
	}
	
}
