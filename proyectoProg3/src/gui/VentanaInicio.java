package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import db.GestorDB;
import domain.Usuario;

public class VentanaInicio extends JFrame {
    private Image fondo;
	private JPasswordField passwordField;
	private JTextField usuarioField;

    public VentanaInicio() {
        setTitle("Iniciar Sesion");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //Que aparezca en el MEDIO
        
        
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
        
        // Username
        JPanel nombreUsuarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nombreUsuarioPanel.add(new JLabel("Username: "));
        usuarioField = new JTextField(15);
        nombreUsuarioPanel.add(usuarioField);
        centerPanel.add(nombreUsuarioPanel);
        
        //Contrasena 
        JPanel panelContrasena = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelContrasena.add(new JLabel("Password:  "));
        passwordField = new JPasswordField(15);
        panelContrasena.add(passwordField);
        centerPanel.add(panelContrasena);
      
    
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferior para el botón de login
        JPanel panelLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botonLogin = new JButton("Iniciar Sesion");
        panelLogin.add(botonLogin);
        mainPanel.add(panelLogin, BorderLayout.SOUTH);
        
        
        botonLogin.addActionListener((e) -> {
        	Usuario user =  comprobarUsuarioExiste();
        	if(comprobarUsuarioExiste() != null) {
        		

            	SwingUtilities.invokeLater(() -> {
            		new VentanaFeed(user);
            	});
            	
        		
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
         
        
        
        // Añadir panel principal a la ventana
       this.add(mainPanel);
       this.setVisible(true);
        
    }

   


    private Usuario comprobarUsuarioExiste() {
		GestorDB db = new GestorDB();
		String username = usuarioField.getText();
		String contrasena = String.valueOf(passwordField.getPassword());
	
		Usuario u = db.loginUsuario(username, contrasena);
		
		return u;
		
	}




	public static void main(String[] args) {
       new VentanaInicio();
    }
} 