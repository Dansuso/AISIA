package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import db.GestorDB;
import db.InitDatabase;
import domain.Contenido;
import domain.Pelicula;
import domain.Serie;
import domain.Contenido.Genero;
import domain.Contenido.TIPO;

public class VentanaCatalogoVistaAlterna extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static HashMap<String, ArrayList<Contenido>> mapaContenido;
	protected List<Contenido> contenidosBD;
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
		
		
//		mapaContenido = VentanaCatalogo.cargarContenido();
//		System.out.println(mapaContenido);
		
		GestorDB db = new GestorDB();
        contenidosBD = db.obtenerContenidos();
		
		
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
		
		
		
		
		modeloLista = new DefaultListModel<Contenido>();
		modeloLista.addAll(contenidosBD);
		
		//Ej 2.1
		listaContenidos = new JList<Contenido>(modeloLista);
		listaContenidos.setFixedCellWidth(200);
		JScrollPane panelScroll = new JScrollPane(listaContenidos);
		listaContenidos.setCellRenderer(new RendererListaContenidos());
		
		JTextField buscador = new JTextField();
		
		JButton favoritos = new JButton("Anadir favs");
		JButton eliminarFavs = new JButton("Eliminar favs");
		
		JPanel panelIzqBotones = new JPanel();
		panelIzqBotones.setLayout(new FlowLayout());
		panelIzqBotones.add(favoritos);
		panelIzqBotones.add(eliminarFavs);
		
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setLayout(new BorderLayout());
		panelIzquierda.add(buscador, BorderLayout.NORTH);
		panelIzquierda.add(panelScroll, BorderLayout.CENTER);
		panelIzquierda.add(panelIzqBotones, BorderLayout.SOUTH);
		
		JPanel panelFavoritos = new JPanel();
        panelFavoritos.setLayout(new BorderLayout());
        
        JEditorPane panelDescripcion = new JEditorPane();
        panelDescripcion.setContentType("text/html");
        panelDescripcion.setEditable(false);
        panelDescripcion.setText("<html><i>Selecciona un genero para ver su descripción aquí.</i></html>");
        JScrollPane scrollDescripcion = new JScrollPane(panelDescripcion);
        
        String[] columnas = { "Título", "Género", "Calificacion", "Distribuidora" }; // Columnas de la tabla
        modeloTabla = new DefaultTableModel(columnas, 0) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        	
        };
        tablaFavoritos = new JTable(modeloTabla);
        JScrollPane scrollTablaFavoritos = new JScrollPane(tablaFavoritos);
        panelFavoritos.add(scrollTablaFavoritos, BorderLayout.CENTER);
        panelFavoritos.add(scrollDescripcion, BorderLayout.SOUTH);
		
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
		
		eliminarFavs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaFavoritos.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Eliminar la fila seleccionada del modelo
                    modeloTabla.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(
                            VentanaCatalogoVistaAlterna.this,
                            "Fila eliminada correctamente",
                            "Fila eliminada",
                            JOptionPane.PLAIN_MESSAGE
                        );
                    
                } else {
                    JOptionPane.showMessageDialog(
                        VentanaCatalogoVistaAlterna.this,
                        "Por favor, seleccione una fila para eliminar.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
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
		
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaInicio();
				
			}
			
		});
		
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		
		
		JTableHeader headerTablaFavoritos = tablaFavoritos.getTableHeader();
		TableCellRenderer headerRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel resultado = new JLabel(value.toString());
			resultado.setFont(new Font("Arial", Font.BOLD, 12));
			resultado.setHorizontalAlignment(SwingConstants.CENTER);
			resultado.setOpaque(true);
			return resultado;
			
		};
		
		headerTablaFavoritos.setDefaultRenderer(headerRenderer);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel resultado = new JLabel (value.toString());
			
			if(value instanceof Genero) {
				if(value.equals(Genero.TERROR)) {
					String rutaTerror = "resources/images/recursos/contenido/terror.png";
					resultado.setIcon(new ImageIcon(rutaTerror));
					resultado.setBackground(Color.RED);
				}
				else if(value.equals(Genero.ACCION)) {
					String rutaAccion = "resources/images/recursos/contenido/accion.png";
					resultado.setIcon(new ImageIcon(rutaAccion));
					resultado.setBackground(Color.YELLOW);
				}
				else if(value.equals(Genero.COMEDIA)) {
					String rutaComedia = "resources/images/recursos/contenido/comedia.png";
					resultado.setIcon(new ImageIcon(rutaComedia));
					resultado.setBackground(Color.GREEN);
				}
				else if(value.equals(Genero.DRAMA)) {
					String rutaDrama = "resources/images/recursos/contenido/drama.png";
					resultado.setIcon(new ImageIcon(rutaDrama));
					resultado.setBackground(Color.CYAN);
				}
				else if(value.equals(Genero.AVENTURA)) {
					String rutaAventura = "resources/images/recursos/contenido/aventura.png";
					resultado.setIcon(new ImageIcon(rutaAventura));
					resultado.setBackground(Color.ORANGE);
				}
				else if(value.equals(Genero.FANTASIA)) {
					String rutaFantasia = "resources/images/recursos/contenido/fantasia.png";
					resultado.setIcon(new ImageIcon(rutaFantasia));
					resultado.setBackground(Color.BLUE);
				}
				else if(value.equals(Genero.ROMANCE)) {
					String rutaRomance = "resources/images/recursos/contenido/romance.png";
					resultado.setIcon(new ImageIcon(rutaRomance));
					resultado.setBackground(Color.PINK);
				}
			}
			else if(value instanceof Number) {
				resultado.setHorizontalAlignment(SwingConstants.CENTER);
				double numericValue = ((Number) value).doubleValue(); // Convertir a double para comparación
		        if (numericValue < 5) {
		            resultado.setBackground(Color.RED);
		        }
		        else if(numericValue <= 7) {
		        	resultado.setBackground(Color.YELLOW);
		        }
		        else {
		            resultado.setBackground(Color.GREEN);
		        }
			}
			
			
			resultado.setOpaque(true);
			
			return resultado;
		};
		
		tablaFavoritos.setDefaultRenderer(Object.class, cellRenderer);
		tablaFavoritos.setRowHeight(30);
		tablaFavoritos.getColumnModel().getColumn(2).setPreferredWidth(30);
		tablaFavoritos.getColumnModel().getColumn(3).setPreferredWidth(26);
		
		tablaFavoritos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaFavoritos.getSelectedRow();
                int columnaSeleccionada = tablaFavoritos.getSelectedColumn();
                
                if (filaSeleccionada != -1 && columnaSeleccionada == 1) { // Verificar que la columna es la de "Género"
                    Genero genero = (Genero) modeloTabla.getValueAt(filaSeleccionada, columnaSeleccionada);

                    // Lanzar un hilo para obtener la descripción del género
                    new Thread(() -> {
                        String descripcion = obtenerDescripcionGenero(genero);
                        SwingUtilities.invokeLater(() -> panelDescripcion.setText(descripcion));
                    }).start();
                }
            }
			
			
		});
		
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(panelIzquierda, BorderLayout.WEST);
		
		
		
		
		setVisible(true);
	}
	
	private void filtroContenidos(String text) {
    	modelo = new DefaultListModel<>();
    	
		for (Contenido contenido : contenidosBD) {
			if(contenido.getTitulo().toLowerCase().contains(text.toLowerCase())) {
				modelo.addElement(contenido);
			}
		}
		
		listaContenidos.setModel(modelo);
		
	}
	
	private String obtenerDescripcionGenero(Genero genero) {
        try {
            // Simular un retardo de red
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (genero) {
            case TERROR:
                return "El género de terror se centra en provocar miedo e inquietud en el espectador.";
            case ACCION:
                return "El género de acción se caracteriza por escenas intensas y emocionantes con héroes y villanos.";
            case COMEDIA:
                return "El género de comedia busca entretener al público a través del humor.";
            case DRAMA:
                return "El drama aborda historias profundas y emocionales que exploran conflictos humanos.";
            case AVENTURA:
                return "El género de aventura se centra en viajes épicos y desafíos heroicos.";
            case FANTASIA:
                return "La fantasía transporta a los espectadores a mundos imaginarios llenos de magia.";
            case ROMANCE:
                return "El romance explora historias de amor y relaciones sentimentales.";
            default:
                return "Descripción no disponible para este género.";
        	}
        }
	
	public void mostrarContenidos() {
        // Recorremos y mostramos los contenidos en la consola, o los cargamos en la interfaz
        for (Contenido contenido : contenidosBD) {
            System.out.println(contenido);
        }
    }

	public static void main(String[] args) {
		VentanaCatalogoVistaAlterna ventana = new VentanaCatalogoVistaAlterna();
        ventana.mostrarContenidos();

	}

}
