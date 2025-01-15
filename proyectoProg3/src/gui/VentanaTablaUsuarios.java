package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;


import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javax.swing.table.TableRowSorter;

import db.GestorDB;
import domain.Usuario;

public class VentanaTablaUsuarios extends JFrame{
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private GestorDB db;
	protected JFrame frame;
	protected Usuario perso;
	protected HashMap<String, String> mapa;
	// private int callMouseOver = -1;
	private TableRowSorter<DefaultTableModel> sorter;
	private JTextField searchField;
	private Usuario usuario;

	private static class ModeloTablaUsuarios extends AbstractTableModel {
		
	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<Usuario> usuarios;
		private String[] columnas = { "Nombre de Usuario", "Foto", "Pais", "Fecha de Creacion", "Seguidores" };
		private GestorDB db = new GestorDB();

		public ModeloTablaUsuarios(List<Usuario> usuarios) {
			this.usuarios = usuarios;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return usuarios.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnas.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return columnas[columnIndex];

		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Usuario u = usuarios.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return u.getUsername();
			case 1:
				return u.getFoto();
			case 2:
				return u.getPais();
			case 3:
				return u.getCreacionCuenta();
			case 4:
				return db.obtenerSeguidores(u.getCodigo()).size();
			}

			return null;
		}

	}

	public VentanaTablaUsuarios(Usuario user) {
		this.usuario = user;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Programa de de tabla de nombre");
		setSize(1100, 900);
		setLocationRelativeTo(null);
		db = new GestorDB();
		List<Usuario> usuarios = db.obtenerUsuarios();
		
		tabla = new JTable(new ModeloTablaUsuarios(usuarios));
		tabla.setRowHeight(50);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//IAG : Claude Sonnet @3.5. Adaptado (Ya he hecho muchos Renderers manualmente)
		tabla.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JLabel label = new JLabel(value.toString());
		        label.setHorizontalAlignment(JLabel.CENTER);
		        label.setBackground(new Color(51, 51, 51));  // Gris oscuro
		        label.setForeground(Color.WHITE);
		        label.setFont(getFont().deriveFont(Font.BOLD, 14f));
		        label.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		        label.setOpaque(true);

				return label;
			}
		});

		//Render de la columna del Pais
		tabla.getColumnModel().getColumn(2).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				String pais = (String) value;

			    JLabel label =  utils.BanderaUtil.obtenerBandera(pais);
				label.setOpaque(true);
				


			    if(db.estaSiguiendo(user, usuarios.get(row))) {
					label.setBackground(new Color(29,161,242));

				}

				// Estilo de la celda: centrar la imagen
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setVerticalAlignment(JLabel.CENTER);

				// Estilos adicionales
				if (isSelected) {
					label.setBackground(table.getSelectionBackground());
					label.setForeground(table.getSelectionForeground());
				}

				return label;
			}
		});
		// Configurar la columna de "Foto" para mostrar imágenes
	

		// Crear un campo de texto para la búsqueda
		searchField = new JTextField(20);
		searchField.setToolTipText("Buscar por nombre...");
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = searchField.getText().toLowerCase();

				// Si la consulta está vacía, no aplicamos el filtro
				if (query.trim().isEmpty()) {
					sorter.setRowFilter(null); // Mostrar todo si no hay filtro
				} else {
					// Filtro usando la expresión regular
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 1)); // "(?i)" hace que la búsqueda no
																					// distinga entre
																					// mayúsculas/minúsculas
				}
			}
		});

		/*
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		searchPanel.add(new JLabel("Buscar por Nombre:"));
		searchPanel.add(searchField);
		*/
		// Panel para mostrar la tabla
		JScrollPane scroll = new JScrollPane(tabla); // Esto solo debe aparecer una vez
	//	add(searchPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER); // Aquí se agrega el JScrollPane a la ventana

		JPanel panelTabla = new JPanel(new BorderLayout());
	//	panelTabla.add(searchPanel, BorderLayout.NORTH);
		panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

		add(panelTabla, BorderLayout.CENTER);

	
		
		tabla.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() { 
			@Override 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
					boolean hasFocus, int row, int column) { 
				JLabel label = new JLabel(); 
				String imageName = value.toString(); 
				label.setOpaque(true);

				
				if(db.estaSiguiendo(user, usuarios.get(row))) {
					label.setBackground(new Color(29,161,242));

				}
 
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
				
				if (isSelected) {
					label.setBackground(table.getSelectionBackground());
					label.setForeground(table.getSelectionForeground());
				}
 
				return label; 
			} 
		}); 
 

		
		
		tabla.setDefaultRenderer(Object.class, new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
					JLabel labelGeneral = new JLabel(value.toString());
					labelGeneral.setOpaque(true);

					if(db.estaSiguiendo(user, usuarios.get(row))) {
						labelGeneral.setBackground(new Color(29,161,242));

					}
					
					if (isSelected) {
						labelGeneral.setBackground(table.getSelectionBackground());
						labelGeneral.setForeground(table.getSelectionForeground());
					}


					
					
					
					return labelGeneral;
				
				
				
			}
		});
		
		
		
		
		JButton perfil = new JButton("Perfil");
		perfil.addActionListener((e) -> new VentanaUsuario(usuarios.get(tabla.getSelectedRow())));

		JPanel panelBotones = new JPanel();
		JButton botonSeguir = new JButton("Seguir");
		JButton dejarSeguir = new JButton("Dejar de Seguir");


		panelBotones.add(perfil);
		panelBotones.add(perfil);
		panelBotones.add(botonSeguir);
		
		
		
		
		
		panelBotones.add(dejarSeguir);

		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		this.setVisible(true);

		
		tabla.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabla.getSelectedRow() != -1 ) {
					System.out.println("e");
					
					if(db.estaSiguiendo(usuario,usuarios.get(tabla.getSelectedRow()))) {
						botonSeguir.setEnabled(false);
						dejarSeguir.setEnabled(true);

					}
					else {
						dejarSeguir.setEnabled(false);
						botonSeguir.setEnabled(true);

					}
					repaint();
					revalidate();

				}
			}
		});
	
		botonSeguir.addActionListener(( e) -> {
			if(tabla.getSelectedRow() != -1) {
				db.insertarSeguidor(user, usuarios.get(tabla.getSelectedRow()));
				botonSeguir.setEnabled(false);
				dejarSeguir.setEnabled(true);
				repaint();
			}
			
		});
		
		dejarSeguir.addActionListener(( e) -> {
			if(tabla.getSelectedRow() != -1) {
				db.eliminarSeguidor(user, usuarios.get(tabla.getSelectedRow()));
				dejarSeguir.setEnabled(false);
				botonSeguir.setEnabled(true);
				repaint();
			}
			
		});

	}


	
}
