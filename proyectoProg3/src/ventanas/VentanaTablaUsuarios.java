package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import clases.Usuario;

public class VentanaTablaUsuarios extends JFrame {
	 private DefaultTableModel model;
	
    private  JTable tabla;
    protected JFrame frame;
    protected Usuario perso;
  
    
	
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
				 	

					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

	public VentanaTablaUsuarios() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Programa de de tabla de nombre");
		setSize(640,640);
		
		
		

		        
		  String[] colubnas = {"Nombre", "Apellido", "Edad","Correo", "Contraseña"};
		  // Agregar algunas columnas a las tablas
		// Crear modelos de tabla
	    
		 
		  DefaultTableModel model = new DefaultTableModel(colubnas, 0);
	        cargarDatosCSV("personas.csv", model);

	      	
	      
	        tabla = new JTable(model);
	        
	        JScrollPane scroll = new JScrollPane(tabla);
	        add(scroll, BorderLayout.CENTER);
	        getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
	        
	        
		
		
		setVisible(true);
		
	    		
		
	}
	
	
	  // Método para añadir datos a la tabla manualmente desde un array de strings
    public void agregarDatosATabla(String[] datos) {
        // Añadir la fila con los datos
    	 if (model != null) {
             model.addRow(datos);  // Añadir datos al modelo
         } else {
             System.out.println("Error");
         }
     }

	public static void main(String[] args) {
		 VentanaTablaUsuarios ventana = new VentanaTablaUsuarios();

	       
    }

	   
}
	