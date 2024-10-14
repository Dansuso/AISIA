package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class VentanaRegistro extends JFrame{
	
	public VentanaRegistro() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Inicio");
		setSize(312,300);
		
		
		
		this.setLocationRelativeTo(null);
        
        //Creamos el menu superior izquierda
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
     
        //Cremos el Jpanel 
        JPanel jpanel = new JPanel();
        JPanel panelBotones = new JPanel();
        
        
        
        JLabel nombre = new JLabel("Correo:");
        JTextField txt1 = new JTextField(16);
        
        JLabel contraseña = new JLabel("Contraseña:");
        JPasswordField txt2 = new JPasswordField(16);
        
        JLabel usuario = new JLabel("Nombre:");
        JTextField txt3 = new JTextField(16);
        
        JLabel apellidos = new JLabel("Apellidos:");
        JTextField txt4 = new JTextField(16);
        
        JLabel edad = new JLabel("Edad:");
        JTextField txt5 = new JTextField(16);
        
        
        
        
     // Crear los botones
    	JButton botonAgregar = new JButton("Registrarme");
    	JButton botonCerrar = new JButton("Cerrar");
    	JButton ocultar = new JButton("Mostrar Contraseña");
 
    	// Agregar los componentes a la ventana
    	
    	
    	jpanel.add(usuario);
    	jpanel.add(txt3);
    	
    	jpanel.add(apellidos);
    	jpanel.add(txt4);
    	
    	jpanel.add(edad);
    	jpanel.add(txt5);
    	
    	jpanel.add(nombre);
    	jpanel.add(txt1);
    	
    	jpanel.add(contraseña);
    	jpanel.add(txt2);
    	jpanel.add(ocultar);
    	
    	jpanel.setLayout(new GridLayout(6,6,10,10));
    	
    	//Crea una separacion entre panel dde arriba y el central 
    	jpanel.setBorder(new EmptyBorder(10, 0, 10, 0));
    	
    	getContentPane().add(jpanel, BorderLayout.CENTER);
    	
    	
    	panelBotones.add(botonAgregar);
    	panelBotones.add(botonCerrar);
        
    	getContentPane().add(panelBotones, BorderLayout.SOUTH);
    	
    	
    	
    	//Iniciamos la ventantana Inicio
		inicio.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					VentanaInicio vinicio = new VentanaInicio();
					vinicio.setVisible(true);
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
		 //Si tocas el boton 1 muestra la contraseña que hay hay en el txt2 
		 //Boton ocultar
		 char valor = txt2.getEchoChar();
	    	ocultar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					// Comprobamos en que modo de codificacion esta para cambiarlo de un a otro
					if(txt2.echoCharIsSet()) {
						//Con este codigo pasamos de * a lo que ha escrito el usuario para que sepa la contraseña
						 txt2.setEchoChar((char)0);
						 ocultar.setText("Ocultar contraseña");
						
					}else {
						//Y aqui al reves 
						txt2.setEchoChar(valor);
						ocultar.setText("Mostrar contraseña");
					}
	                 
					
				}
			});;
	    	
	        
		
	}
	
	public static void main(String[] args) {
        new VentanaRegistro();
        
    }
}
