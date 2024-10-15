package ventanas;

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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

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
import javax.swing.table.DefaultTableModel;



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
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ventana Inicio");
		setSize(312,300);
		
		
		//Inicializamos el mapa
		 mapa = new HashMap<>();
	     cargarDatosCSV();
	     
		
		
		
		this.setLocationRelativeTo(null);
        
        fondo = new ImageIcon("imagen1.jpeg").getImage();
        
        
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
        
        JLabel nombre = new JLabel("Correo:");
        JTextField txt1 = new JTextField(16);
        
        JLabel contraseña = new JLabel("Contraseña:");
        JPasswordField txt2 = new JPasswordField(16);
        
          
        
        

        
        
     // Crear los botones
    	JButton botonAgregar = new JButton("Iniciar");
    	JButton botonCerrar = new JButton("Cerrar");
    	JButton ocultar = new JButton("Mostrar contraseña");
    	ocultar.setMinimumSize(new Dimension(20,20));


    	// Agregar los componentes a la ventana de inserción
    	jpanel.add(nombre);
    	jpanel.add(txt1);
    	
    	jpanel.add(contraseña);
    	jpanel.add(txt2);
    	jpanel.add(ocultar);
    	jpanel.setLayout(new GridLayout(4,4,10,10));
    	
    	//Crea una separacion entre panel dde arriba y el central 
    	jpanel.setBorder(new EmptyBorder(20, 0, 20, 0));
    	
    	
    
    	
    	getContentPane().add(jpanel, BorderLayout.CENTER);
    	
    	
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
    	
    	
    	
    	// tocas el boton 1 muestra la contraseña que hay hay en el txt2 
    	char valor = txt2.getEchoChar();
    	ocultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Comprobamos en que modo de codificacion esta para cambiarlo de un a otro
				
				if(txt2.echoCharIsSet()) {
					//Con este codigo pasamos de * a lo que ha escrito el usuario para que sepa la contraseña
					 txt2.setEchoChar((char) 0);
					 ocultar.setText("Ocultar contraseña");
					
				}else {
					//Y aqui al reves 
					txt2.setEchoChar(valor);
					ocultar.setText("Mostrar contraseña");
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
    	
        
		
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
        new VentanaInicio();
        System.out.println(mapa);
    }
	

}	
