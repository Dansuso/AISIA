package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
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
		
		panelFotoNombre.setBackground(colorAisia);
		panelFotoNombre.setPreferredSize(new Dimension(125, 125));
		
		ImageIcon fotoPerfilDefecto = new ImageIcon("img/defautUsuario.png");
		
		JLabel etiquetaFotoPerfil = new JLabel(fotoPerfilDefecto);
		panelFotoNombre.add(etiquetaFotoPerfil, BorderLayout.WEST);
		
		ImageIcon fotoAisia = new ImageIcon("img/aisia1.png");
		
		JLabel etiquetaFotoAisia = new JLabel(fotoAisia);
		panelFotoNombre.add(etiquetaFotoAisia, BorderLayout.EAST);
	
		JLabel nombreUsuario = new JLabel("@Nombre Usuario");
		panelFotoNombre.add(nombreUsuario);
		nombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel nombreRealUsuario = new JLabel("Nombre Real");
		nombreRealUsuario.setBounds(21, 120, 100, 100);
		VentanaUsuario.this.add(nombreRealUsuario);
		
		
		VentanaUsuario.this.add(panelFotoNombre, BorderLayout.NORTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		VentanaUsuario vu = new VentanaUsuario();
	}
	
}
