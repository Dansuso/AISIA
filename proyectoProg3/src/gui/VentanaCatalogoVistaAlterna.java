package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import domain.Contenido;
import domain.Pelicula;
import domain.Serie;
import domain.Contenido.Genero;

public class VentanaCatalogoVistaAlterna extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static HashMap<String, ArrayList<Contenido>> mapaContenido;
	protected List<Contenido> contenidos;
	protected DefaultListModel<Contenido> modeloLista;
	protected JList<Contenido> listaContenidos;
	protected DefaultListModel<Contenido> modelo;
	protected JTable tablaFavoritos;
    protected DefaultTableModel modeloTabla;

	public VentanaCatalogoVistaAlterna() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setTitle("Ventana Catalogo");
		setLocationRelativeTo(null);
		
		
		mapaContenido = VentanaCatalogo.cargarContenido();
		System.out.println(mapaContenido);
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JLabel label1 = new JLabel("¿Seguro que desea salir?");
				
				Object[] message = { label1 };
				
				int resultado = JOptionPane.showConfirmDialog(null, 
						message, 
						"Salir", 
						JOptionPane.YES_NO_OPTION
					);
				
				if (resultado == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				// se llama cuando el usuario intenta cerrar la ventana
				}
		});
		
		
		
		JLabel titulo = new JLabel("Catalogo");
		titulo.setHorizontalAlignment(JTextField.CENTER);
		Font fuente = new Font("Arial", Font.BOLD, 30);
		titulo.setFont(fuente);
		
		JMenuBar barraCatalogo = new JMenuBar();
		setJMenuBar(barraCatalogo);
		
		JMenu menuCatalogo = new JMenu("Opciones");
		barraCatalogo.add(menuCatalogo);
		
		JMenuItem vista = new JMenuItem("Cambiar de vista");
		menuCatalogo.add(vista);
		
		menuCatalogo.addSeparator();
		
		JMenuItem volver = new JMenuItem("Cerrar sesion");
		menuCatalogo.add(volver);
		
		menuCatalogo.addSeparator();
		
		JMenuItem salir = new JMenuItem("Salir");
		menuCatalogo.add(salir);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		panelSuperior.add(titulo, BorderLayout.NORTH);
		panelSuperior.add(barraCatalogo, BorderLayout.WEST);
		
		contenidos = List.of(
				new Pelicula("Peli", 1, "Pelicula1", Genero.TERROR, 90.5, 6, "Marvel", 13, "Ninguno", null, 10000.40),
				new Pelicula("Peli", 2, "Pelicula2", Genero.ACCION, 80, 7, "Marvel", 18, "Ninguno", null, 20000.40),
				new Pelicula("Peli", 2, "Pelicula2", Genero.ACCION, 80, 7, "Marvel", 18, "Ninguno", null, 20000.40),
				new Pelicula("Peli", 3, "Pelicula3", Genero.ACCION, 80, 7, "Marvel", 18, "Ninguno", null, 20000.40),
				new Pelicula("Peli", 4, "Pelicula4", Genero.ACCION, 80, 7, "Marvel", 18, "Ninguno", null, 20000.40),
				new Pelicula("Peli", 2, "Pelicula2", Genero.ACCION, 80, 7, "Marvel", 18, "Ninguno", null, 20000.40),
				new Pelicula("Peli", 2, "Pelicula2", Genero.ACCION, 80, 7, "Marvel", 18, "Ninguno", null, 20000.40),
				new Serie("Serie", 1, "Serie1", Genero.AVENTURA, 40, 8, "Fox", 16, "Ninguno", null, 3, 8),
				new Pelicula("Peli", 2, "Pelicula2", Genero.ACCION, 80, 7, "Marvel", 18, "Ninguno", null, 20000.40),
				new Serie("Serie", 1, "Serie2", Genero.AVENTURA, 40, 8, "Fox", 16, "Ninguno", null, 3, 8)
				
				);
		
		modeloLista = new DefaultListModel<Contenido>();
		modeloLista.addAll(contenidos);
		
		//Ej 2.1
		listaContenidos = new JList<Contenido>(modeloLista);
		listaContenidos.setFixedCellWidth(200);
		JScrollPane panelScroll = new JScrollPane(listaContenidos);
		listaContenidos.setCellRenderer(new RendererListaContenidos());
		
		JTextField buscador = new JTextField();
		
		JButton favoritos = new JButton("Anadir favoritos");
		
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setLayout(new BorderLayout());
		panelIzquierda.add(buscador, BorderLayout.NORTH);
		panelIzquierda.add(panelScroll, BorderLayout.CENTER);
		panelIzquierda.add(favoritos, BorderLayout.SOUTH);
		
		JPanel panelFavoritos = new JPanel();
        panelFavoritos.setLayout(new BorderLayout());
        
        String[] columnas = { "Título", "Género", "Calificacion", "Distribuidora" }; // Columnas de la tabla
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaFavoritos = new JTable(modeloTabla);
        JScrollPane scrollTablaFavoritos = new JScrollPane(tablaFavoritos);
        panelFavoritos.add(scrollTablaFavoritos, BorderLayout.CENTER);
		
		JTabbedPane panelTab = new JTabbedPane();
		PanelContenidos panelContenido = new PanelContenidos();
		panelTab.add("Datos", panelContenido);
		panelTab.add("Favoritos", panelFavoritos);
		this.add(panelTab, BorderLayout.CENTER);
		
		
		
		listaContenidos.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					Contenido contenidoElegido = listaContenidos.getSelectedValue();
					System.out.println(contenidoElegido);
					if(contenidoElegido != null) {
						panelContenido.setContenido(contenidoElegido);
						panelContenido.setEditable(false);
					}
				}
				
			}
			
		});
		
		buscador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				filtroContenidos(buscador.getText());
				
			}
			
		});
		
		favoritos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Contenido contenidoSeleccionado = listaContenidos.getSelectedValue();
                if (contenidoSeleccionado != null) {
                	int resultado = JOptionPane.showConfirmDialog(null, "Favorito anadido", "Favoritos", JOptionPane.PLAIN_MESSAGE);
                    // Añadir contenido seleccionado a la tabla
                    modeloTabla.addRow(new Object[] {
                            contenidoSeleccionado.getTitulo(),
                            contenidoSeleccionado.getGenero(),
                            contenidoSeleccionado.getCalificacion(),
                            contenidoSeleccionado.getDistribuidora()
                    });
                }
				
			}
			
		});
		
		
		vista.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaCatalogo();
				
			}
			
		});
		
		
		
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelIzquierda, BorderLayout.WEST);
		
		
		
		
		setVisible(true);
	}
	
	private void filtroContenidos(String text) {
    	modelo = new DefaultListModel<>();
    	
		for (Contenido contenido : contenidos) {
			if(contenido.getTitulo().contains(text)) {
				modelo.addElement(contenido);
			}
		}
		
		listaContenidos.setModel(modelo);
		
	}

	public static void main(String[] args) {
		new VentanaCatalogoVistaAlterna();

	}

}
