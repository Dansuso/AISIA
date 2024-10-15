package ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	
	private VentanaTablaUsuarios ventanaTabla;
	
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
    	ocultar.setMinimumSize(new Dimension(20,20));
 
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
