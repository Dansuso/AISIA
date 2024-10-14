package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaCatalogo extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaCatalogo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Catalogo");
		setSize(640, 480);
		setLocationRelativeTo(null);
		
		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout());
		
		JMenuBar barraCatalogo = new JMenuBar();
		setJMenuBar(barraCatalogo);
		
		JMenu menuCatalogo = new JMenu("Opciones");
		barraCatalogo.add(menuCatalogo);
		
		JMenuItem pelicula = new JMenuItem("Pelicula");
		menuCatalogo.add(pelicula);
		
		menuCatalogo.addSeparator();
		
		JMenuItem serie = new JMenuItem("Serie");
		menuCatalogo.add(serie);
		
		panelSuperior.add(barraCatalogo, BorderLayout.WEST);
		
		JTextField buscador = new JTextField("Buscador");
		panelSuperior.add(buscador, BorderLayout.EAST);
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
        new VentanaCatalogo();
    }

}
