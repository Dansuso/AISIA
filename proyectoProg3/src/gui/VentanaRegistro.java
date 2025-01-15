package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import db.GestorDB;




public class VentanaRegistro extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFormattedTextField txtFecha;
	private GestorDB gestorBD;

	
	public VentanaRegistro() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Ventana Inicio");
		setSize(640,450);
		gestorBD = new GestorDB(); 
		
		// Panel para la imagen de fondo
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes entre componentes
        gbc.anchor = GridBagConstraints.WEST; // Alineación a la izquierda
        gbc.fill = GridBagConstraints.HORIZONTAL; // Asegura que los campos de texto ocupen todo el espacio disponible
        
		
       // mainPanel.setLayout(null);
        
		//this.setLocationRelativeTo(null);
        
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
     


        // Configuración de los componentes
        JLabel usuario = new JLabel("username:");
        usuario.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(usuario, gbc);

        JTextField usernameField = new JTextField(16); // Campo de texto para "username"
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(usernameField, gbc);
        
        
      

        JLabel foto = new JLabel("Fecha:");
        foto.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(foto, gbc);


        try {
            // Formato de la fecha (####-##-##)
            MaskFormatter dateFormatter = new MaskFormatter("####-##-##");
            dateFormatter.setPlaceholderCharacter('_');
            txtFecha = new JFormattedTextField(dateFormatter); // Variable global reutilizada

            // Obtener la fecha actual y asignarla al campo de fecha
            LocalDate today = LocalDate.now(); // Fecha actual
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(formatter); // Formatear la fecha
            txtFecha.setText(formattedDate); // Establecer la fecha en el campo de textoç
            txtFecha.setEditable(false);
        } catch (Exception e) {
            txtFecha = new JFormattedTextField(); // Variable global reutilizada
        }
        
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(txtFecha, gbc);

        JLabel pais = new JLabel("País:");
        pais.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(pais, gbc);

        String[] paises = {
                "united states", "canada", "united kingdom", "spain", "mexico",
                "argentina", "chile", "colombia", "venezuela", "peru", "brazil", "uruguay"
        };
        JComboBox<String> comboBoxPais = new JComboBox<>(paises);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(comboBoxPais, gbc);

        JLabel contraseña = new JLabel("Contraseña:");
        contraseña.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(contraseña, gbc);

        JPasswordField txt2 = new JPasswordField(16); // Campo de texto para contraseña
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(txt2, gbc);
        
        JButton btnSeleccionarImagen = new JButton("Seleccionar imagen");
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(btnSeleccionarImagen, gbc);


//        // Botón para mostrar/ocultar contraseña
//        ImageIcon foto1 = new ImageIcon("resources/images/recursos/fotover.png");
//        JButton ocultar = new JButton(foto1);
//        ocultar.setContentAreaFilled(false);
//        ocultar.setBorderPainted(false);
//        gbc.gridx = 3;
//        gbc.gridy = 4;
//        mainPanel.add(ocultar, gbc);

        // Panel para botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton botonAgregar = new JButton("Registrarse");
        JButton botonCerrar = new JButton("Cancelar");
        buttonPanel.add(botonAgregar);
        buttonPanel.add(botonCerrar);
        buttonPanel.setOpaque(false);

       
    	 gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3; // Ocupa tres columnas
        mainPanel.add(buttonPanel, gbc);

//	    	
//	    //Le quita el borde a las imagenes
//	   	 // Quitar el borde del botón
//	   	ocultar.setBorderPainted(false);
//	
//	       // Quitar el relleno del botón
//	   	ocultar.setContentAreaFilled(false);
//	
//	       // Quitar el efecto de enfoque
//	   	ocultar.setFocusPainted(false);
//	   	mainPanel.add(ocultar);
//    	// Agregar los componentes a la ventana
//    	

    
    	
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
    	
    
        // Listeners
        btnSeleccionarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagen();
            }

            
        });
        

    	
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
		 botonAgregar.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        // Validar los datos del formulario
			        String fecha = txtFecha.getText();
			        if (fecha.contains("_")) {
			            JOptionPane.showMessageDialog(null, "Por favor, ingresa una fecha válida en el formato yyyy-MM-dd.");
			            return;
			        }

			        if (!usernameField.getText().isEmpty() && txt2.getPassword().length > 0) {
			            String username = usernameField.getText();

			            // Verificar si el nombre de usuario ya existe
			            while (gestorBD.nombreCompletoExiste(username)) {
			                JOptionPane.showMessageDialog(null, "El nombre completo ya está registrado. Por favor, ingresa otro.");
			                return;
			            }

			            // Continuar con el registro si el nombre no existe
			            String pais = comboBoxPais.getSelectedItem().toString();
			            String contraseña = new String(txt2.getPassword());

			            // Obtener el próximo ID de usuario
			            int nuevoCodigo = gestorBD.obtenerUltimoIdUsuario() +1 ;

			            // Obtener la imagen más reciente de la carpeta
			            String imagen = obtenerUltima();

			            // Registrar el usuario con la imagen más reciente
			            gestorBD.insertarUsuario(nuevoCodigo, username, fecha, pais, imagen, contraseña);
			            
			           

			            // Comprobar que el usuario fue creado correctamente
			           
			                VentanaInicio inicio = new VentanaInicio(); // Pasar el usuario a VentanaFeed
			                inicio.setVisible(true);
			                dispose(); // Cierra la ventana de registro
			                
			           
			        } else {
			            JOptionPane.showMessageDialog(null, "No has escrito Nombre o Contraseña");
			        }
			    }
			});


			

		

//			    private boolean nombreCompletoExiste(String nombreCompleto) {
//			        File archivoCSV = new File("resources/data/usuario.csv");
//			        
//			        // Verificar si el archivo existe
//			        if (!archivoCSV.exists()) {
//			            JOptionPane.showMessageDialog(null, "El archivo CSV no existe en la ruta especificada.");
//			            return false;
//			        }
//
//			        try (Scanner sc = new Scanner(archivoCSV)) {
//			            while (sc.hasNextLine()) {
//			                String linea = sc.nextLine().trim(); // Eliminar espacios en blanco al inicio y final
//			                if (linea.isEmpty()) {
//			                    continue; // Saltar las líneas vacías
//			                }
//			                String[] campos = linea.split(";");
//			                
//			                // Asegurarse de que la línea tenga suficientes campos
//			                if (campos.length > 1 && campos[1].equalsIgnoreCase(nombreCompleto)) {
//			                    return true; // Si el nombre completo ya existe
//			                }
//			            }
//			        } catch (IOException e) {
//			            e.printStackTrace();
//			            JOptionPane.showMessageDialog(null, "Error al leer el archivo CSV: " + e.getMessage());
//			        }
//			        return false; // Si no se encontró el nombre completo
//			    }
//
//
//				private int obtenerUltimoCodigo() {
//			        int ultimoCodigo = 0;
//			        File file = new File("resources/data/usuario.csv");
//			        try (Scanner scanner = new Scanner(file)) {
//			            while (scanner.hasNextLine()) {
//			                String linea = scanner.nextLine();
//			                String[] campos = linea.split(";");
//			                if (campos.length > 0 && !campos[0].isEmpty()) {
//			                    try {
//			                        int codigo = Integer.parseInt(campos[0]);
//			                        if (codigo > ultimoCodigo) {
//			                            ultimoCodigo = codigo;
//			                        }
//			                    } catch (NumberFormatException ex) {
//			                        // Manejo del caso donde no se pueda convertir el código
//			                        System.err.println("Error al parsear el código: " + campos[0]);
//			                    }
//			                }
//			            }
//			        } catch (IOException ex) {
//			            ex.printStackTrace();
//			        }
//			        return ultimoCodigo;
//			    }
//	
			

			
//		 //Si tocas el boton 1 muestra la contraseña que hay hay en el txt2 
//		 //Boton ocultar
//		 char valor = txt2.getEchoChar();
//	    	ocultar.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					
//					// Comprobamos en que modo de codificacion esta para cambiarlo de un a otro
//					if(txt2.echoCharIsSet()) {
//						//Con este codigo pasamos de * a lo que ha escrito el usuario para que sepa la contraseña
//						 txt2.setEchoChar((char)0);
//						 ImageIcon foto1 = new ImageIcon("resources/images/recursos/fotnover.png");
//						 ocultar.setIcon(foto1);
//						
//					}else {
//						//Y aqui al reves 
//						txt2.setEchoChar(valor);
//						ImageIcon foto2 = new ImageIcon("resources/images/recursos/fotover.png");
//						ocultar.setIcon(foto2);
//					}
//	                 
//					
//				}
//			});;
			 
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
	        
	        
	        // Configuración final de la ventana
	        mainPanel.setBackground(Color.DARK_GRAY); // Fondo oscuro
	        setContentPane(mainPanel);
	        setLocationRelativeTo(null); // Centrar la ventana
	    	setVisible(true);
	    	
	        
		
	}
	private String obtenerUltima() {
    // Ruta de la carpeta de destino
    File directory = new File("resources/images/recursos/perfil");

    // Crear la carpeta de destino si no existe
    if (!directory.exists()) {
        directory.mkdirs();
    }

    // Obtener los archivos existentes en la carpeta
    File[] files = directory.listFiles();
    int ultimoNumero = 0;
    String extension = "";

    // Si hay archivos, buscar el último número
    if (files != null) {
        for (File f : files) {
            String nombreArchivo = f.getName();
            // Buscar archivos con el formato numérico seguido de la extensión
            if (nombreArchivo.matches("\\d+\\.\\w+")) { // Detectar cualquier extensión
                int numero = Integer.parseInt(nombreArchivo.split("\\.")[0]); // Obtener solo el número
                extension = nombreArchivo.substring(nombreArchivo.lastIndexOf('.')); // Obtener la extensión
                ultimoNumero = Math.max(ultimoNumero, numero); // Guardar el último número
            }
        }
    }

    // Asignar el siguiente número disponible y usar la extensión detectada
    int nuevoNumero = ultimoNumero ;
    return nuevoNumero + extension; // Ejemplo: 13.png, 13.jpg, etc.
}


	private void seleccionarImagen() {
	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "gif");
	    fileChooser.setFileFilter(filter);
	    int returnValue = fileChooser.showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        copiarImagenARecursos(selectedFile);
	    }
	}

	private void copiarImagenARecursos(File file) {
	    // Obtener la extensión del archivo original
	    String extension = "";
	    String fileName = file.getName();
	    int dotIndex = fileName.lastIndexOf('.');
	    if (dotIndex > 0) {
	        extension = fileName.substring(dotIndex); // Obtiene la extensión (ej. .jpg, .png)
	    }
	    
	    // Ruta de la carpeta de destino
	    File directory = new File("resources/images/recursos/perfil");

	    // Crear la carpeta de destino si no existe
	    if (!directory.exists()) {
	        directory.mkdirs();
	    }

	    // Obtener los archivos existentes en la carpeta
	    File[] files = directory.listFiles();
	    int ultimoNumero = 0;

	    // Si hay archivos, buscar el último número
	    if (files != null) {
	        for (File f : files) {
	            String nombreArchivo = f.getName();
	            // Buscar archivos con el formato numérico (sin la extensión)
	            if (nombreArchivo.matches("\\d+\\.\\w+")) {
	                int numero = Integer.parseInt(nombreArchivo.split("\\.")[0]);
	                ultimoNumero = Math.max(ultimoNumero, numero);
	            }
	        }
	    }

	    // Asignar el siguiente número disponible
	    int nuevoNumero = ultimoNumero + 1;
	    String nuevoNombre = nuevoNumero + extension;

	    // Definir la ruta de destino con el nuevo nombre
	    Path sourcePath = file.toPath();
	    Path destinationPath = Paths.get("resources/images/recursos/perfil", nuevoNombre);

	    try {
	        System.out.println("Copiando desde: " + sourcePath);
	        System.out.println("Copiando hacia: " + destinationPath);
	        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
	        JOptionPane.showMessageDialog(null, "Imagen copiada a: " + destinationPath.toString());
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Error al copiar la imagen: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


    
    
	public static void main(String[] args) {
        new VentanaRegistro();
        
    }
}