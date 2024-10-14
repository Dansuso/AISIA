package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import clases.Contenido;
import clases.Pelicula;
import clases.Serie;

public class VentanaCatalogo extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Contenido> contenidos = List.of(
			new Pelicula(1, "Pelicula1", "Terror", 90.5, 6, "Marvel", 13, "Ninguno", 10000.40),
			new Pelicula(2, "Pelicula2", "Terror", 80, 7, "Marvel", 18, "Ninguno", 20000.40),
			new Serie(1, "Serie1", "Accion", 40, 8, "Fox", 16, "Ninguno", 3, 8)
			
			);

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
		
		JMenuItem serie = new JMenuItem("Serie");
		menuCatalogo.add(serie);
		
		menuCatalogo.addSeparator();
		
		JMenuItem salir = new JMenuItem("Salir");
		menuCatalogo.add(salir);
		
		panelSuperior.add(barraCatalogo, BorderLayout.WEST);
		
		JTextField buscador = new JTextField("Buscador",10);
		panelSuperior.add(buscador, BorderLayout.EAST);
		
		
		JPanel panelCentral = new JPanel(new GridLayout(5,3,10,10));
		add(panelCentral, BorderLayout.CENTER);
		
		for (int i = 1; i <= 20; i++) {
            panelCentral.add(new JButton("Contenido " + i));
        }
		
		JScrollPane panelScrollCatalogo = new JScrollPane(panelCentral);
		add(panelScrollCatalogo, BorderLayout.CENTER);	
		
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
        new VentanaCatalogo();
    }

}
