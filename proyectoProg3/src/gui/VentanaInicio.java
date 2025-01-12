package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BorderFactory;
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





public class VentanaInicio extends JFrame {
    

	private JPasswordField txt2;
	private JTextField txt1;
	protected static HashMap<String, String> mapa;

    public VentanaInicio() {
        setTitle("Iniciar Sesion");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //Que aparezca en el MEDIO
        
      //Inicializamos el mapa
		 mapa = new HashMap<>();
	     cargarDatosCSV();
        
        
        //MENU
        
    	JMenuBar menuBar = new JMenuBar(); 
        setJMenuBar(menuBar); 
 
        JMenu fileMenu = new JMenu("Opciones de inicio"); 
        menuBar.add(fileMenu); 
         
 
        JMenuItem inicio = new JMenuItem("Iniciar sesión"); 
        fileMenu.add(inicio); 
         
 
        JMenuItem registro = new JMenuItem("Registarse"); 
        fileMenu.add(registro); 
         
         
        fileMenu.addSeparator(); 
         
        
        JMenuItem salir = new JMenuItem("Salir"); 
        fileMenu.add(salir); 
        
        salir.addActionListener(new ActionListener() { 
			 
			@Override 
			public void actionPerformed(ActionEvent e) { 
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
         
        
        JPanel mainPanel = new JPanel() { 
            /** 
			 *  
			 */ 
			private static final long serialVersionUID = 1L; 
 
			@Override 
            protected void paintComponent(Graphics g) { 
                super.paintComponent(g); 
                ImageIcon fondo = new ImageIcon("resources/images/recursos/fondo.jpg"); // Ruta de tu imagen 
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this); 
            } 
        }; 
         
        mainPanel.setLayout(new BorderLayout(10, 10)); 
         
        
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerPanel.setOpaque(false);
        
        // Username
        JPanel nombreUsuarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nombreUsuarioPanel.setOpaque(false); // Transparente para la imagen de fondo
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
        nombreUsuarioPanel.add(usernameLabel);
        txt1 = new JTextField(15);
        nombreUsuarioPanel.add(txt1);
        centerPanel.add(nombreUsuarioPanel);
        
        //Contrasena 
        JPanel panelContrasena = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelContrasena.setOpaque(false); // Transparente para la imagen de fondo
        JLabel passwordLabel = new JLabel("Password:  ");
        passwordLabel.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
        panelContrasena.add(passwordLabel);
        txt2 = new JPasswordField(15);
        panelContrasena.add(txt2);
        
        
     
        
        // Botón para mostrar/ocultar contraseña
        ImageIcon foto1 = new ImageIcon("resources/images/recursos/fotover.png");
        JButton ocultar = new JButton(foto1);
       
        
     	
     	//Le quita el borde a las imagenes
     	 // Quitar el borde del botón
     	ocultar.setBorderPainted(false);

         // Quitar el relleno del botón
     	ocultar.setContentAreaFilled(false);

         // Quitar el efecto de enfoque
     	ocultar.setFocusPainted(false);

        panelContrasena.add(ocultar);

        centerPanel.add(panelContrasena);
      
    
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel();
        // Crear los botones
    	JButton botonAgregar = new JButton("Iniciar");
    	JButton botonCerrar = new JButton("Cerrar");
    	
    	

    	
    
    	
    	panelBotones.add(botonAgregar);
    	panelBotones.add(botonCerrar);
        
    	getContentPane().add(panelBotones, BorderLayout.SOUTH);
    	
		registro.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
						VentanaRegistro ventana = new VentanaRegistro();
						ventana.setVisible(true);
					}
				});
    	
    	
        //CERRAR
        this.addWindowListener(new WindowAdapter() { 
       	 
        	@Override 
        	public void windowClosing(WindowEvent e) { 
        	// se llama cuando el usuario intenta cerrar la ventana 
        		JLabel etiqueta1 = new JLabel("Quieres salir de la aplicación"); 
        		Object[] message = { etiqueta1}; 
        		// Mostrar el JOptionPane con los componentes en un array 
        		int resultado = JOptionPane.showConfirmDialog(null,  
        			message,  
        			"Salir",  
        			JOptionPane.YES_NO_OPTION 
        		
        		); 
        		if (resultado == JOptionPane.YES_OPTION) { 
        			System.exit(0); 
        		} 
        		 
        	} 
        	}); 

    	//Boton salir
    	salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
    	
    	
    	// tocas el boton 1 muestra la contraseña que hay hay en el txt2 
    	char valor = txt2.getEchoChar();
    	ocultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Comprobamos en que modo de codificacion esta para cambiarlo de un a otro
				//Ponemos la foto en depende de que modo este el formato del txt
				if(txt2.echoCharIsSet()) {
					//Con este codigo pasamos de * a lo que ha escrito el usuario para que sepa la contraseña
					 txt2.setEchoChar((char) 0);
					 ImageIcon foto1 = new ImageIcon("resources/images/recursos/fotnover.png");
					 ocultar.setIcon(foto1);
					
				}else {
					//Y aqui al reves 
					txt2.setEchoChar(valor);
					ImageIcon foto2 = new ImageIcon("resources/images/recursos/fotover.png");
					ocultar.setIcon(foto2);
				}
				
				
                 
				
			}
		});;
		
	botonAgregar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					
					String Correo = txt1.getText();
					String contra = new String(txt2.getPassword());
					
					
						if(mapa.containsKey(Correo) && mapa.get(Correo).equals(contra)) {
							dispose();
							
							//Ventana pruba hasta que creemos la principal
						
							
							VentanaFeed f = new VentanaFeed(null);
							f.setVisible(true);
							
						}else {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
						}
						
					
					
					
					
					
					
				}
			});
         
        
        
        // Añadir panel principal a la ventana
       this.add(mainPanel);
       this.setVisible(true);
        
    }
    
    

   


 
	//Cargamos los datos para ver luego si esta en la base de tados 
	public void cargarDatosCSV(){
    	File f = new File("resources/data/usuario.csv");
    	try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] campos =  linea.split(";");
			
				
					
					mapa.put(campos[1], campos[5]);
					
					
					
				}
			
				 	
			sc.close();
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }



    


	public static void main(String[] args) {
       new VentanaInicio();
       System.out.println(mapa);

    }
} 