package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;

import java.util.Scanner;



import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
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
		
		  String[] colubnas = {"Nombre", "Apellido", "Edad","Correo", "Contraseña"};
		
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
	        
	
		
	        cargarDatosCSV("resources/data/personas.csv", model);
	        
	        // Inicializar la tabla
	        tabla = new JTable(model);
	        tabla.setFillsViewportHeight(true);
	       
	        
	    
			if (vacio != null && vacio.length == colubnas.length) {
	            model.addRow(vacio);
	        }
	        
	    
	        
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
	                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 0)); // "(?i)" hace que la búsqueda no distinga entre mayúsculas/minúsculas
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
	        tabla.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
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
	                if (column == 4) { // Contraseña
	                    label.setForeground(Color.RED);
	                    label.setText("******");
	                }

	                // Personalizar según la edad (columna 2)
	                if (column == 2) {
	                    try {
	                        int edad = Integer.parseInt(value.toString());
	                        if (edad > 65) {
	                            label.setBackground(filaMouseOver == row ? Color.CYAN : Color.YELLOW); // Amarillo para mayores de 65
	                        } else if (edad < 25) {
	                            label.setBackground(filaMouseOver == row ? Color.CYAN : Color.GREEN); // Verde para menores de 25
	                        } else {
	                            label.setBackground(filaMouseOver == row ? Color.CYAN : Color.LIGHT_GRAY); // Gris para edades intermedias
	                        }
	                    } catch (NumberFormatException e) {
	                        // Si no se puede convertir a número, no aplicar colores
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
					// TODO Auto-generated method stub
	    			
	    		
					
					
					int indice = tabla.getSelectedRow();
	    			
	    			String nombre = model.getValueAt(indice, 0).toString();
	    			String apellidos = model.getValueAt(indice, 1).toString();
	    			String edad = model.getValueAt(indice, 2).toString();
	    			String Correo = model.getValueAt(indice, 3).toString();
	    			String contraseña = model.getValueAt(indice, 4).toString();
	    			
	    			VentanaDatos modificar = new VentanaDatos(nombre,apellidos,edad,Correo,contraseña);
					modificar.setVisible(true);
					
					
	    		}
	    	});

	       
	        
	        guardarEnArchivo(model);
	      	
	
	
		
	    		
		
	}
	
	
	

	public void cargarDatosCSV(String n, DefaultTableModel datos){
    	File f = new File(n);
    	try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
			
				String linea = sc.nextLine();
				
					String[] campos =  linea.split(";");
					
					datos.addRow(campos);
					
					HashMap<String,String> mapa = new HashMap<String, String>();
	
						mapa.put(campos[0], campos[1]  +campos[2] + campos[3] + campos[4]);
				
					
					
				}			
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }	
		

	 public void guardarEnArchivo(DefaultTableModel model) {

	        try (PrintWriter pw = new PrintWriter(new FileWriter("personas.csv",false))) {


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
	