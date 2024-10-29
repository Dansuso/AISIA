package gui;
/*
 * Ventana que representa una instancia de una ventana basica, con los elementos que se repiten en las demas ventanas.
 * 
 */

import javax.swing.JFrame;

public class VentanaBase extends JFrame{
/**
 * Constructor de la ventana base o por defecto. 
 * @param titulo Titulo de la ventana. Es OBLIGATORIO.
 * @throws IllegalArgumentException si no se pasa ningun titulo como parametro
 */
	public VentanaBase(String titulo) {
		
		if(titulo == null) {
			throw new IllegalArgumentException("Es obligatorio que la ventana tenga un titulo");
		}
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle(titulo);
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		
		
		
		
	}
	
	
}
