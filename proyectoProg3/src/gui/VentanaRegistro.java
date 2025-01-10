package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;



public class VentanaRegistro extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VentanaTablaUsuarios ventanaTabla;
	
	public VentanaRegistro() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Ventana Inicio");
		setSize(340,450);
		
		// Panel para la imagen de fondo
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
        
		
        mainPanel.setLayout(null);
        
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
     

        JPanel panelBotones = new JPanel();
    
        JLabel usuario = new JLabel("Nombre:");
        usuario.setBounds(30, 10 , 80, 20);
        usuario.setForeground(Color.WHITE);
        mainPanel.add(usuario);
        
        JTextField txt3 = new JTextField(16);
        txt3.setBounds(100, 10, 150, 20);
     	mainPanel.add(txt3);
        
        JLabel apellidos = new JLabel("Apellidos:");
        apellidos.setForeground(Color.white);
        apellidos.setBounds(30, 60, 80, 20);
    	mainPanel.add(apellidos);
    	
        JTextField txt4 = new JTextField(16);
        txt4.setBounds(100, 60, 150, 20);
    	mainPanel.add(txt4);
    	
    	 JLabel fechaNacimiento = new JLabel("Fecha:");
         fechaNacimiento.setForeground(Color.WHITE);
         fechaNacimiento.setBounds(30, 100, 150, 20);
         mainPanel.add(fechaNacimiento);

         JFormattedTextField txtFecha;
         try {
             MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
             dateFormatter.setPlaceholderCharacter('_');
             txtFecha = new JFormattedTextField(dateFormatter);
         } catch (Exception e) {
             txtFecha = new JFormattedTextField();
         }
         txtFecha.setBounds(100, 100, 100, 20);
         mainPanel.add(txtFecha);
         

     
        
  
     	 JLabel pais = new JLabel("Pais:");
         pais.setForeground(Color.WHITE);
         pais.setBounds(30, 160, 80, 20);
         mainPanel.add(pais);

         String[] paises = {
             "United States", "Canada", "United Kingdom", "Spain", "Mexico",
             "Argentina", "Chile", "Colombia", "Venezuela", "Peru", "Brazil", "Uruguay"
         };
         JComboBox<String> comboBoxPais = new JComboBox<>(paises);
         comboBoxPais.setBounds(100, 160, 150, 20);
         mainPanel.add(comboBoxPais);
        
        JLabel contraseña = new JLabel("Contraseña:");
        contraseña.setForeground(Color.WHITE);
        contraseña.setBounds(30, 210, 80, 20);
        mainPanel.add(contraseña);
        
        JPasswordField txt2 = new JPasswordField(16);
        txt2.setBounds(100,210,150,20);
        mainPanel.add(txt2);

        ImageIcon foto1 = new ImageIcon("fotover.png");
        JButton ocultar = new JButton(foto1);
        ocultar.setBounds(210, 210, 120, 30); 
    	
        
        
        
     // Crear los botones
    	JButton botonAgregar = new JButton("Registrarme");
    	JButton botonCerrar = new JButton("Cerrar");
    	
    	
	    	
	    //Le quita el borde a las imagenes
	   	 // Quitar el borde del botón
	   	ocultar.setBorderPainted(false);
	
	       // Quitar el relleno del botón
	   	ocultar.setContentAreaFilled(false);
	
	       // Quitar el efecto de enfoque
	   	ocultar.setFocusPainted(false);
	   	mainPanel.add(ocultar);
    	// Agregar los componentes a la ventana
    	

    
    	
    	//jpanel.setLayout(new GridLayout(6,6,10,10));
    	
    	
    	// Añado logo de aisia.
	   	ImageIcon aisiactimg = new ImageIcon("resources/images/aisia/aisiact.png");
	   	Image imagenEscalada = aisiactimg.getImage().getScaledInstance(200, 110, java.awt.Image.SCALE_SMOOTH);
	   	ImageIcon aisiactimgEscalado = new ImageIcon(imagenEscalada);
		JLabel aisiact = new JLabel(aisiactimgEscalado);
		aisiact.setBounds(-15, 210, aisiactimg.getIconWidth(), aisiactimg.getIconHeight());
		mainPanel.add(aisiact);
    	
		//Crea una separacion entre panel dde arriba y el central 
		
    	getContentPane().add(mainPanel, BorderLayout.CENTER);
    	
    	
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
	                if (!txt3.getText().isEmpty() && txt2.getPassword().length > 0) {
	                    String nombre = txt3.getText();
	                    String apellidos = txt4.getText();
	                    //String edad = txt5.getText();
	                    //String fecha = txtFecha.getText();
	                    String pais = comboBoxPais.getSelectedItem().toString();
	                    String contraseña = new String(txt2.getPassword());

	                    String[] datosUsuario = { nombre, apellidos, pais, contraseña };

	                    if (ventanaTabla == null) {
	                        ventanaTabla = new VentanaTablaUsuarios(datosUsuario);
	                    }

	                    ventanaTabla.setVisible(true);
	                    dispose();
	                } else {
	                    System.out.println("No has escrito Nombre o Contraseña");
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
	        
	    	setVisible(true);
	        
		
	}
	
	public static void main(String[] args) {
        new VentanaRegistro();
        
    }
}

