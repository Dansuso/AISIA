package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		
		JButton volver = new JButton("Volver");
		this.add(volver,BorderLayout.NORTH);
		
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaCatalogo();
				
			}
			
		});
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new VentanaInfo();

	}

}
