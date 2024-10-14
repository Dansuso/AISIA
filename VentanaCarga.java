package proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;



public class VentanaCarga extends JFrame{
	protected javax.swing.Timer t;
	protected ActionListener al;
	protected int cont =0;
	protected JProgressBar jProgressBar ;
	protected JLabel jlabel;
	public VentanaCarga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cargar Pantalla");
		setSize(240,55);
		
		//Proyecto en el centro de la pantalla
		this.setLocationRelativeTo(null);
		
		// Inicializamos el JProgressBar y lo agregamos a la ventana
        jProgressBar = new JProgressBar(0, 100);  // Barra de progreso de 0 a 100
        jProgressBar.setValue(cont);  // Valor inicial
        jProgressBar.setStringPainted(true);  // Mostrar el valor en forma de texto
        //jProgressBar.setBackground(Color.GREEN);
        // Añadir la barra de progreso al JFrame
        add(jProgressBar, BorderLayout.NORTH);
        
        
		//Llamamos a la instancia de actrion Lisener
		al = new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cont = cont +1;
				
				jProgressBar.setValue(cont);
				 // Detener el temporizador cuando llega a 100
               
				 // Detener el temporizador cuando llega a 100
                if (cont >= 100) {
                	//Se cierra el programa 
                	
                	VentanaInicio inicio = new VentanaInicio();
                	setVisible(true);
                	dispose();
                    t.stop();
                }
			}
		};
		
		t = new javax.swing.Timer(50, al);
		t.start();
		//pack();
		setVisible(true);
		
		
		
		
		
		
	}

	public static void main(String[] args) {
        new VentanaCarga();
        
    }

}
