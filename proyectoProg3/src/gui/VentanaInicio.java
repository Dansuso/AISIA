package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gui.VentanaCatalogo;



public class VentanaInicio extends JFrame{
	protected 	Image fondo;
	
	//Añadimos el coreo y la contreseña a este mapa para luego preguntarle sio eesta 
	protected static HashMap<String, String> mapa;
	
	
	//Cargamos los datos para ver luego si esta en la base de tados 
	public void cargarDatosCSV(){
    	File f = new File("personas.csv");
    	try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] campos =  linea.split(";");
			
				
					
					mapa.put(campos[3], campos[4]);
					
					
					
				}
			
				 	
			sc.close();
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

	

	public  VentanaInicio() {
	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Ventana Inicio");
		setSize(317,300);
		
		
		//Inicializamos el mapa
		 mapa = new HashMap<>();
	     cargarDatosCSV();
	     
		
		
		
		this.setLocationRelativeTo(null);
        
       
        
        
        //Creamos el menu y sus diferentes opciones
        
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
        JPanel cont = new JPanel();
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        JLabel nombre = new JLabel("Correo:");
        JTextField txt1 = new JTextField(16);
        
        
        JLabel contraseña = new JLabel("Contraseña:");
        JPasswordField txt2 = new JPasswordField(16);
        
          
        
        

        
        
     // Crear los botones
    	JButton botonAgregar = new JButton("Iniciar");
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

    	
    	


    	// Agregar los componentes a la ventana de inserción
    	jpanel.add(nombre);
    	jpanel.add(txt1);
    	//jpanel.setLayout(new GridLayout(1,1,5,5));
    	//jpanel.setOpaque(false);    
    	
    	cont.add(contraseña);
    	cont.add(txt2);
    	cont.add(ocultar);
    	//jpanel.setOpaque(false);
    	//cont.setLayout(new GridLayout(1,1,5,5));
    	
    	//Crea una separacion entre panel dde arriba y el central 
    	jpanel.setBorder(new EmptyBorder(30, 0, 30, 0));
    	//cont.setBorder(new EmptyBorder(0, 0, 0, 0));
   
    	
    	
    	mainPanel.add(jpanel);
        mainPanel.add(cont);
    	
        
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        fondo = new ImageIcon(getClass().getResource("fondo.jpg")).getImage();
      

    	
    	
    	panelBotones.add(botonAgregar);
    	panelBotones.add(botonCerrar);
        
    	getContentPane().add(panelBotones, BorderLayout.SOUTH);
    	
    
    	
    	//Inicioamos la ventana REgistro
    	//Para que habra la ventana Registro
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
    	
//    	ImageIcon foto = new ImageIcon();
//    	boton.setIcon();
    	
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
		
		
		//Recorre el mapa y compruba si el usuario que a escrito eCorreo y contrasña y abrir una nueva pensatña
		botonAgregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				String Correo = txt1.getText();
				String contra = new String(txt2.getPassword());
				
				
					if(mapa.containsKey(Correo) && mapa.get(Correo).equals(contra)) {
						dispose();
						
						//Ventana pruba hasta que creemos la principal
						VentanaCatalogo catalo = new VentanaCatalogo();
						catalo.setVisible(true);
						
					}else {
						System.out.println("El usuario no esta regitrado en esta aplicacion");
					}
					
				
				
				
				
				
				
			}
		});
		 
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
        new VentanaInicio();
        System.out.println(mapa);
    }
	

}	
