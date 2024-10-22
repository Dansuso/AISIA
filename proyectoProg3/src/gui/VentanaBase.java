package gui;
/*
 * Ventana que representa una instancia de una ventana basica, con los elementos que se repiten en las demas ventanas.
 * 
 */

import javax.swing.JFrame;

public class VentanaBase extends JFrame{

	public VentanaBase() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Catalogo");
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
}
