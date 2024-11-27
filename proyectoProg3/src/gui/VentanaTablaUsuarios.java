package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import domain.Usuario;

public class VentanaTablaUsuarios extends JFrame {
	 private  DefaultTableModel model;
	
    private static  JTable tabla;
    protected JFrame frame;
    protected Usuario perso;
    protected HashMap<String, String> mapa;
    private int filaMouseOver = -1;
	private int callMouseOver = -1;
  
    
	
    

	public VentanaTablaUsuarios(String [] datosUser) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Programa de de tabla de nombre");
		setSize(640,640);
		
		
		
		

		        
		  String[] colubnas = {"Nombre", "Apellido", "Edad","Correo", "Contraseña"};
		  // Agregar algunas columnas a las tablas
		// Crear modelos de tabla
	    
		 
		  DefaultTableModel model = new DefaultTableModel(colubnas, 0);
	        cargarDatosCSV("resources/data/personas.csv", model);
	        
	        if (datosUser != null && datosUser.length == colubnas.length) {
	            model.addRow(datosUser);
	        }
	        
	        
	        // Inicializar la tabla
	        tabla = new JTable(model);
	        
	     // Activar ordenamiento al hacer clic en los encabezados
	        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	        tabla.setRowSorter(sorter);

	        // Listener para detección del mouse
	        tabla.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseExited(MouseEvent e) {
	                filaMouseOver = -1;
	                tabla.repaint();
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

	        // Renderizador de celdas personalizado
	        tabla.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
	            JLabel label = new JLabel(value == null ? "" : value.toString());
	            label.setFont(new Font("Arial", Font.PLAIN, 14));
	            label.setOpaque(true);
	            label.setBackground(row == filaMouseOver ? Color.CYAN : Color.WHITE);
	            return label;
	        });

	        // Listeners para resaltar filas y columnas con el mouse
	        tabla.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseExited(MouseEvent e) {
	                filaMouseOver = -1;
	                callMouseOver = -1;
	                tabla.repaint();
	            }
	        });

	        tabla.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                filaMouseOver = tabla.rowAtPoint(e.getPoint());
	                callMouseOver = tabla.columnAtPoint(e.getPoint());
	                tabla.repaint();
	            }
	        });  

	       
	        
	        guardarEnArchivo(model);
	      	
	      
	       
	       
	        
	        JScrollPane scroll = new JScrollPane(tabla);
	        add(scroll, BorderLayout.CENTER);
	        getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
	        
	      
	        
		
		setVisible(true);
		
	    		
		
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
		 VentanaTablaUsuarios ventana = new VentanaTablaUsuarios(vacio);
		 
    }

	   
}
	