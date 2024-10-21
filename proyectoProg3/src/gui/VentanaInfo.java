package gui;

import javax.swing.JFrame;

public class VentanaInfo extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Informacion");
		setSize(640,480);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new VentanaInfo();

	}

}
