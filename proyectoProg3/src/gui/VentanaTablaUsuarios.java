package gui;

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


import domain.Usuario;

public class VentanaTablaUsuarios extends JFrame {
	 private  DefaultTableModel model;
	
    private static  JTable tabla;
    protected JFrame frame;
    protected Usuario perso;
    protected HashMap<String, String> mapa;
  
    
	
    

	public VentanaTablaUsuarios(String [] datosUser) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Programa de de tabla de nombre");
		setSize(640,640);
		
		
		
		

		        
		  String[] colubnas = {"Nombre", "Apellido", "Edad","Correo", "Contraseña"};
		  // Agregar algunas columnas a las tablas
		// Crear modelos de tabla
	    
		 
		  DefaultTableModel model = new DefaultTableModel(colubnas, 0);
	        cargarDatosCSV("personas.csv", model);
	        
	        if (datosUser != null && datosUser.length == colubnas.length) {
	            model.addRow(datosUser);
	        }
	      	        
	        tabla = new JTable(model);

	        
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
	