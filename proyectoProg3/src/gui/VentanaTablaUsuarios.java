package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;

import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javax.swing.table.TableRowSorter;


import domain.Usuario;

public class VentanaTablaUsuarios extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private static  JTable tabla;
    protected JFrame frame;
    protected Usuario perso;
    protected HashMap<String, String> mapa;
    private int filaMouseOver = -1;
	//private int callMouseOver = -1;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTextField searchField;


    
	
    

	public VentanaTablaUsuarios(String[] vacio) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Programa de de tabla de nombre");
		setSize(1100,900);
		setLocationRelativeTo(null);
		
		  String[] colubnas = {"Codigo", "Usuario", "Fecha","Pais",
		  		 "Foto", "Contraseña"};
		
		  DefaultTableModel model = new DefaultTableModel(colubnas, 0) {
		        

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Ninguna celda es editable
	            }
	        };
	      
	        
	        
	        
	       // guardarEnArchivo(model);
	        cargarDatosCSV("resources/data/usuario.csv", model);
	        
	        // Inicializar la tabla
	        tabla = new JTable(model);
	        tabla.setFillsViewportHeight(true);
	       
	        tabla.setRowHeight(50);  // Ajusta la altura de las filas para que las imágenes se vean mejor
	        tabla.getColumnModel().getColumn(4).setPreferredWidth(50); 
	    
			if (vacio != null && vacio.length == colubnas.length) {
	            model.addRow(vacio);
	        }
			File archivo = new File("resources/data/usuario.csv");
	        if (archivo.exists()) {
	            System.out.println("Archivo encontrado: " + archivo.getAbsolutePath());
	        } else {
	            System.out.println("El archivo no existe.");
	        }
		     
	        
	        tabla.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                JLabel label = new JLabel();

	                // Obtener el nombre del país que está en la celda y convertirlo a minúsculas
	                String pais = value.toString().toLowerCase().replace(" ", "_"); // Reemplazar espacios por guiones bajos

	                // Imprimir el valor del país para depuración
	                System.out.println("Pais: " + pais);

	                // Crear la ruta de la imagen
	                String imagePath = "resources/images/recursos/Pais/" + pais + ".png"; // Ruta de la imagen
	                System.out.println("Ruta de la imagen: " + imagePath); // Imprimir la ruta completa para ver si está correcta

	                // Intentar cargar la imagen
	                File imageFile = new File(imagePath);
	                if (imageFile.exists()) {
	                    // Si la imagen existe, se carga en el JLabel
	                    ImageIcon icon = new ImageIcon(imagePath);

	                    // Redimensionar la imagen al tamaño de la celda
	                    Image img = icon.getImage(); // Obtener la imagen
	                    Image resizedImage = img.getScaledInstance(50, 30, Image.SCALE_SMOOTH); // Redimensionar con un tamaño adecuado
	                    label.setIcon(new ImageIcon(resizedImage));
	                } else {
	                    // Si no existe la imagen, mostrar un texto predeterminado o una imagen por defecto
	                    label.setText("No disponible");
	                    System.out.println("La imagen no fue encontrada en la ruta: " + imagePath); // Depuración si la imagen no se encuentra
	                }

	                // Estilo de la celda: centrar la imagen
	                label.setHorizontalAlignment(JLabel.CENTER);
	                label.setVerticalAlignment(JLabel.CENTER);

	                // Estilos adicionales
	                if (isSelected) {
	                    label.setBackground(table.getSelectionBackground());
	                    label.setForeground(table.getSelectionForeground());
	                } else {
	                    label.setBackground(table.getBackground());
	                    label.setForeground(table.getForeground());
	                }

	                return label;
	            }
	        });

	        
	  

	        // Configurar la columna de "Foto" para mostrar imágenes
	        tabla.getColumnModel().getColumn(4).setCellRenderer(new TableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                JLabel label = new JLabel();
	                String imageName = value.toString();

	                // Ruta de las imágenes
	                String imagePath = "resources/images/recursos/perfil/" + imageName;
	                ImageIcon icon = new ImageIcon(imagePath);

	                // Obtener la imagen original
	                Image originalImage = icon.getImage();

	                // Ajustar el tamaño de la imagen a 25x25 píxeles
	                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

	                // Crear un nuevo ImageIcon con la imagen redimensionada
	                ImageIcon scaledIcon = new ImageIcon(scaledImage);

	                // Asignar la imagen redimensionada al JLabel
	                label.setIcon(scaledIcon);

	                // Centrar la imagen en la celda
	                label.setHorizontalAlignment(SwingConstants.CENTER);
	                label.setVerticalAlignment(SwingConstants.CENTER);

	                return label;
	            }
	        });



	        
	        sorter = new TableRowSorter<>(model);
	        tabla.setRowSorter(sorter);

	        // Crear un campo de texto para la búsqueda
	        searchField = new JTextField(20);
	        searchField.setToolTipText("Buscar por nombre...");
	        searchField.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyReleased(KeyEvent e) {
	                String query = searchField.getText().toLowerCase();
	                
	                // Si la consulta está vacía, no aplicamos el filtro
	                if (query.trim().isEmpty()) {
	                    sorter.setRowFilter(null);  // Mostrar todo si no hay filtro
	                } else {
	                    // Filtro usando la expresión regular
	                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 1)); // "(?i)" hace que la búsqueda no distinga entre mayúsculas/minúsculas
	                }
	            }
	        });
	        
	        
	        //Buscador
	        JPanel searchPanel = new JPanel();
	        searchPanel.setLayout(new FlowLayout());
	        searchPanel.add(new JLabel("Buscar por Nombre:"));
	        searchPanel.add(searchField);

	        // Panel para mostrar la tabla
	        JScrollPane scroll = new JScrollPane(tabla);  // Esto solo debe aparecer una vez
	        add(searchPanel, BorderLayout.NORTH);
	        add(scroll, BorderLayout.CENTER);  // Aquí se agrega el JScrollPane a la ventana
	        
	        JPanel panelTabla = new JPanel(new BorderLayout());
	        panelTabla.add(searchPanel, BorderLayout.NORTH);
	        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);
	        
	        add(panelTabla , BorderLayout.CENTER);        
	        
	        

	        // Agregar el campo de texto para búsqueda al panel superior
	       
	      
	     // Crear los botones
	    	JButton botonInsertar = new JButton("Insertar");
	    	JButton botonEliminar = new JButton("Eliminar");
	    	JButton botonGuardar = new JButton("Guardar");
	    	JButton botonazar = new JButton("Revisar");

	    	JPanel panelBotones = new JPanel();
	    	panelBotones.add(botonInsertar);
	    	panelBotones.add(botonEliminar);
	    	panelBotones.add(botonGuardar);
	    	panelBotones.add(botonazar);
	    	getContentPane().add(panelBotones, BorderLayout.SOUTH);
	   	 
	    
	        
	    	setVisible(true);
	    	
	    	
	    	
	    	//Interecccione con el raton
	     
	        botonazar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					VentanaSeleccionPersona selec = new VentanaSeleccionPersona();
					selec.setVisible(true);
				}
			});
	       
	       

	        tabla.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                filaMouseOver = tabla.rowAtPoint(e.getPoint());
	                tabla.repaint();
	            }
	        });
	        
	        
	     // Ocultar contraseñas
	        tabla.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                // Máscara para la contraseña
	                String maskedValue = (value != null) ? "*".repeat(value.toString().length()) : "";

	                JLabel label = new JLabel(maskedValue);
	                label.setOpaque(true);

	                // Aplicar estilos dinámicos
	                if (row == filaMouseOver) { // Resaltado al pasar el mouse
	                    label.setBackground(Color.CYAN);
	                } else if (isSelected) { // Estilo para selección
	                    label.setBackground(table.getSelectionBackground());
	                    label.setForeground(table.getSelectionForeground());
	                } else { // Estilo normal
	                    label.setBackground(table.getBackground());
	                    label.setForeground(table.getForeground());
	                }

	                return label;
	            }
	        });

	        tabla.setDefaultRenderer(Object.class, new TableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                JLabel label = new JLabel(value + "");
	                label.setFont(new Font("Arial", Font.PLAIN, 14));
	                label.setOpaque(true);

	                // Cambiar el color de fondo cuando el ratón pasa por encima
	                if (filaMouseOver == row) {
	                    label.setBackground(Color.CYAN);
	                } else {
	                    label.setBackground(Color.WHITE); // Fondo por defecto
	                }

	                // Personalizar columna de contraseña
	                if (column == 5) { // Contraseña
	                    label.setForeground(Color.RED);
	                    label.setText("******");
	                }

	             // Personalizar según el trimestre (columna 2)
	             // Personalizar según el trimestre (columna 2)
	                if (column == 2) {
	                    try {
	                        // Parsear la fecha que está en la columna
	                        String fechaStr = value.toString();
	                        // Suponiendo que la fecha está en formato "yyyy-MM-dd"
	                        LocalDate fecha = LocalDate.parse(fechaStr);

	                        // Obtener el año de la fecha
	                        int year = fecha.getYear();

	                        // Asignar colores según el año
	                        if (year == 2024) {
	                            label.setBackground(filaMouseOver == row ? Color.CYAN : Color.RED); // Color para 2024
	                        } else if (year == 2025) {
	                            label.setBackground(filaMouseOver == row ? Color.CYAN : Color.YELLOW); // Color para 2025
	                        } else if (year == 2026) {
	                            label.setBackground(filaMouseOver == row ? Color.CYAN : Color.GREEN); // Color para 2026
	                        } else if (year > 2026) {
	                            label.setBackground(filaMouseOver == row ? Color.CYAN : Color.LIGHT_GRAY); // Color para 2027 en adelante
	                        } else {
	                            label.setBackground(filaMouseOver == row ? Color.CYAN : Color.PINK); // Color para 2023 y años anteriores
	                        }
	                    } catch (Exception e) {
	                        // Si la fecha no se puede parsear, no aplicar colores
	                    }
	                }

	                // Si la celda es seleccionada, sobrescribe el color
	                if (isSelected) {
	                    label.setBackground(Color.LIGHT_GRAY);
	                }

	                return label;



	          
	            }
	        });
	        
	        
	        // Listeners para resaltar filas y columnas con el mouse
	        tabla.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseExited(MouseEvent e) {
	                filaMouseOver = -1;
	                //callMouseOver = -1;
	                tabla.repaint();
	            }
	        });

	        tabla.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                filaMouseOver = tabla.rowAtPoint(e.getPoint());
	               // callMouseOver = tabla.columnAtPoint(e.getPoint());
	                tabla.repaint();
	            }
	        });  
	        

	        tabla.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Obtener la fila seleccionada
	                int indice = tabla.getSelectedRow();

	                // Obtener los valores de las celdas de la fila
	                String Codigo = model.getValueAt(indice, 0).toString();
	                String Usuario = model.getValueAt(indice, 1).toString();
	                String edad = model.getValueAt(indice, 2).toString();
	                String Correo = model.getValueAt(indice, 3).toString();
	                String contraseña = model.getValueAt(indice, 5).toString();

	                // Mostrar un cuadro de diálogo pidiendo la contraseña
	                String passwordInput = JOptionPane.showInputDialog(
	                    tabla,
	                    "Ingrese la contraseña para acceder:",
	                    "Acceso a la ventana de detalles",
	                    JOptionPane.PLAIN_MESSAGE
	                );

	                // Validar la contraseña
	                if (passwordInput != null && passwordInput.equals("1234")) {
	                    // Si la contraseña es correcta, abrir la ventana de detalles
	                    VentanaDatos modificar = new VentanaDatos(Codigo, Usuario, edad, Correo, contraseña);
	                    modificar.setVisible(true);
	                } else {
	                    // Si la contraseña es incorrecta, mostrar un mensaje de error
	                    JOptionPane.showMessageDialog(
	                        tabla,
	                        "Contraseña incorrecta. No tienes acceso.",
	                        "Error",
	                        JOptionPane.ERROR_MESSAGE
	                    );
	                }
	            }
	        });

	       
	        
	        guardarEnArchivo(model);
	      	
	
	
		
	    		
		
	}
	
	
	

	public void cargarDatosCSV(String n, DefaultTableModel datos) {
	    File f = new File(n);
	    try {
	        // Abrir el archivo CSV
	        Scanner sc = new Scanner(f);

	        // Recorremos las líneas del archivo CSV
	        while (sc.hasNextLine()) {
	            String linea = sc.nextLine().trim(); // Eliminar espacios en blanco al principio y al final

	            // Asegurarnos de que la línea no esté vacía
	            if (!linea.isEmpty()) {
	                // Dividir la línea en campos usando el delimitador ';'
	                String[] campos = linea.split(";");
	                
	                // Comprobamos si el número de campos es el esperado (6 en este caso)
	                if (campos.length == 6) {
	                    // Si la línea tiene el número correcto de campos, agregar la fila
	                    datos.addRow(campos);
	                } else {
	                    // Si no tiene el número esperado de campos, mostrar un mensaje de advertencia
	                    System.err.println("Advertencia: Línea con formato incorrecto, no tiene 6 campos: " + linea);
	                }
	            }
	        }
	        sc.close();
	    } catch (IOException e) {
	        e.printStackTrace(); // Capturar y mostrar cualquier error al abrir o leer el archivo
	    }
	}

		
	public void guardarEnArchivo(DefaultTableModel model) {
	    // Usando try-with-resources para garantizar que el archivo se cierre correctamente
	    try (PrintWriter pw = new PrintWriter(new FileWriter("resources/data/usuario.csv", false))) {
	        for (int i = 0; i < model.getRowCount(); i++) {
	            for (int j = 0; j < model.getColumnCount(); j++) {
	                pw.print(model.getValueAt(i, j));
	                if (j < model.getColumnCount() - 1) {
	                    pw.print(";");
	                }
	            }
	            pw.println();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	 public static void main(String[] args) {
			String[] vacio = null;
			new VentanaTablaUsuarios(vacio);
	 }
	 

	   
}

	