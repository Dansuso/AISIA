package proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class VentanaInicio extends JFrame{
	protected 	Image fondo;
	

	public  VentanaInicio() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Inicio");
		setSize(300,300);
		
		//setIconImage(new ImageIcon(getClass().getResource("imagen2.png")).getImage());
		
		
		this.setLocationRelativeTo(null);
        
        fondo = new ImageIcon("imagen1.jpeg").getImage();
        
        
		JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Opciones de inicio");
        menuBar.add(fileMenu);
        

        JMenuItem inicio = new JMenuItem("Iniciar sesión");
        fileMenu.add(inicio);
        

        JMenuItem registro = new JMenuItem("Registarse");
        fileMenu.add(registro);
        
        
        fileMenu.addSeparator();
        
        JMenuItem cerrar = new JMenuItem("Cerrar sesión");
        fileMenu.add(cerrar);
        

        fileMenu.addSeparator();

        JMenuItem salir = new JMenuItem("Salir");
        fileMenu.add(salir);
        // Crear el panel personalizado para mostrar la imagen de fondo
       

        
        JPanel jpanel = new JPanel();
        JPanel panelBotones = new JPanel();
        
        JLabel nombre = new JLabel("Correo:");
        JTextField txt1 = new JTextField(16);
        
        JLabel contraseña = new JLabel("Contraseña:");
        JPasswordField txt2 = new JPasswordField(16);

        
        
     // Crear los botones
    	JButton botonAgregar = new JButton("Iniciar");
    	JButton botonCerrar = new JButton("Cerrar");

    	// Agregar los componentes a la ventana de inserción
    	jpanel.add(nombre);
    	jpanel.add(txt1);
    	
    	jpanel.add(contraseña);
    	jpanel.add(txt2);
    	jpanel.setLayout(new GridLayout(6,6,15,15));
    	
    	//Crea una separacion entre panel dde arriba y el central 
    	jpanel.setBorder(new EmptyBorder(10, 0, 10, 0));
    	
    
    	
    	getContentPane().add(jpanel, BorderLayout.CENTER);
    	
    	
    	panelBotones.add(botonAgregar);
    	panelBotones.add(botonCerrar);
        
    	getContentPane().add(panelBotones, BorderLayout.SOUTH);
    	
    
    	
    	//Inicioamos la ventana REgistro
    	registro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				VentanaRegistro ventana = new VentanaRegistro();
				ventana.setVisible(true);
			}
		});
    	//Boton salir
    	salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
    	//Boton cerrar
    	botonCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
    	
        
		
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
        new VentanaInicio();
    }
	

}	
