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

public class VentanaTablaUsuarios extends JFrame{
	private JTable tabla;
    private JComboBox<String> comboBox;
    private JList<String> listaPersonas;
    private JList<String> persona = new JList<String>() ;
    private DefaultListModel<String> modeloLista;
    private  DefaultTableModel model;
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

					mapa.put(campos[2], campos[0] + "" +campos[1] + campos[3] + campos[4]);
					
					
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

	      
	        JTable tabla = new JTable(model);
	        getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
	        
	        
	        
	      

	    	// Crear los botones
	    	JButton botonInsertar = new JButton("Insertar");
	    	JButton botonEliminar = new JButton("Eliminar");
	    	JButton botonGuardar = new JButton("Guardar");
	    	JButton botonModificar = new JButton("Modificcar");

	    	JPanel panelBotones = new JPanel();
	    	panelBotones.add(botonInsertar);
	    	panelBotones.add(botonEliminar);
	    	panelBotones.add(botonGuardar);
	    	panelBotones.add(botonModificar);
	    	getContentPane().add(panelBotones, BorderLayout.SOUTH);
	   	 
	    	
	    	
	    	
	    	
	    	
	    	
		
		setVisible(true);
	    		
		
	}
	
	public void guardarEnArchivo() {
		// TODO Auto-generated method stub
		try {
			PrintWriter pw = new PrintWriter("tabla.csv");
			for (int j = 0; j < model.getColumnCount(); j++) {
				pw.print(model.getColumnName(j));
				
				if(j < model.getColumnCount() - 1) {
					pw.print(";");
				}
				
			}
			pw.println();
			
			
			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					
					
					pw.println(model.getValueAt(i, j));
					if(j < model.getColumnCount()- 1) {
						pw.print(";");
					}
					
				}
				pw.println();
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	    	

	public static void main(String[] args) {
        new VentanaTablaUsuarios();
        
    }
	   
}
	