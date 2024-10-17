package ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class VentanaRegistro extends JFrame{
	
	private VentanaTablaUsuarios ventanaTabla;
	
	public VentanaRegistro() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        JPanel jpanelnombre = new JPanel();
        JPanel jpanelapellido = new JPanel();
        JPanel jpaneledad = new JPanel();
        JPanel jpanelcorreo = new JPanel();
        JPanel jpanelcontra = new JPanel();
        JPanel panelBotones = new JPanel();
        
        JPanel general = new JPanel();
        
        
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
    	ImageIcon foto1 = new ImageIcon("fotover.png");
    	JButton ocultar = new JButton(foto1);
    	
	    	
	    //Le quita el borde a las imagenes
	   	 // Quitar el borde del botón
	   	ocultar.setBorderPainted(false);
	
	       // Quitar el relleno del botón
	   	ocultar.setContentAreaFilled(false);
	
	       // Quitar el efecto de enfoque
	   	ocultar.setFocusPainted(false);
	 
    	// Agregar los componentes a la ventana
    	
    	
    	jpanelnombre.add(usuario);
    	jpanelnombre.add(txt3);
    	
    	jpanelapellido.add(apellidos);
    	jpanelapellido.add(txt4);
    	
    	jpaneledad.add(edad);
    	jpaneledad.add(txt5);
    	
    	jpanelcorreo.add(nombre);
    	jpanelcorreo.add(txt1);
    	
    	jpanelcontra.add(contraseña);
    	jpanelcontra.add(txt2);
    	jpanelcontra.add(ocultar);
    	
    	//jpanel.setLayout(new GridLayout(6,6,10,10));
    	
    	//Crea una separacion entre panel dde arriba y el central 
    	general.setBorder(new EmptyBorder(10, 0, 10, 0));
    	
    	general.add(jpanelnombre);
    	general.add(jpanelapellido);
    	general.add(jpaneledad);
    	general.add(jpanelcorreo);
    	general.add(jpanelcontra);
    	
    	getContentPane().add(general, BorderLayout.CENTER);
    	
    	
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
						JLabel etiqueta1 = new JLabel("Quieres salir de la aplicación");
		        		Object[] message = { etiqueta1};
		        		// Mostrar el JOptionPane con los componentes en un array
		        		int resultado = JOptionPane.showConfirmDialog(null, 
		        			message, 
		        			"Introduce los datos", 
		        			JOptionPane.YES_NO_OPTION
		        			
		        			
		        	
		        		);
		        		if (resultado == JOptionPane.YES_OPTION) {
		        			System.exit(0);
		        		}
		        		
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
		 
		 
		 
		 //ventanaTabla = new VentanaTablaUsuarios();
		 botonAgregar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					   // Verificar si los campos obligatorios no están vacíos
	                if (!txt1.getText().isEmpty() && txt2.getPassword().length > 0) {
	                    // Concatenar los datos en el formato adecuado
	                    String correo = txt1.getText();
	                    String contraseña = new String(txt2.getPassword());
	                    String nombre = txt3.getText();
	                    String apellidos = txt4.getText();
	                    String edad = txt5.getText();

	                    // Crear un variable de los datos para añadirlos a la tabla
	                    String[] datosUsuario = { nombre, apellidos, edad, correo, contraseña };

	                    // Crear la ventana de la tabla y pasarle los datos
	                    if (ventanaTabla == null) {
	                        ventanaTabla = new VentanaTablaUsuarios(datosUsuario);
	                    }

	                   

	                    // Mostrar la ventana de la tabla
	                    ventanaTabla.setVisible(true);

	                    // Ocultar la ventana actual de registro
	                    dispose();
	                    
	                    
					
					
	                }else {
	                	System.out.println("No has escrito Correo o COntraseña");
	                }
					
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
						 ImageIcon foto1 = new ImageIcon("fotnover.png");
						 ocultar.setIcon(foto1);
						
					}else {
						//Y aqui al reves 
						txt2.setEchoChar(valor);
						ImageIcon foto2 = new ImageIcon("fotover.png");
						ocultar.setIcon(foto2);
					}
	                 
					
				}
			});;
			 
	        addWindowListener(new WindowAdapter() {
	        	@Override
	        	public void windowClosing(WindowEvent e) {
	        	// se llama cuando el usuario intenta cerrar la ventana
	        		JLabel etiqueta1 = new JLabel("Quieres salir de la aplicación");
	        		Object[] message = { etiqueta1};
	        		// Mostrar el JOptionPane con los componentes en un array
	        		int resultado = JOptionPane.showConfirmDialog(null, 
	        			message, 
	        			"Introduce los datos", 
	        			JOptionPane.YES_NO_OPTION
	        			
	        			
	        	
	        		);
	        		if (resultado == JOptionPane.YES_OPTION) {
	        			System.exit(0);
	        		}
	        		
	        	}
	        	});
	    	
	        
		
	}
	
	public static void main(String[] args) {
        new VentanaRegistro();
        
    }
}
