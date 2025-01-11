package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import db.GestorDB;
import domain.Noticia;
import domain.Post;
import domain.Usuario;

/**
 * Clase que representa la ventana del feed, donde los usuarios podran enviar y
 * leer posts.
 */
public class VentanaFeed extends VentanaBase {
	
	
	private static final long serialVersionUID = 1L;
	private JTable tablaTaquilla;
	private LinkedHashMap<String, String> recaudacionPorPelicula = new LinkedHashMap<String, String>();
	private JComboBox<String> comboBoxAnos;
	private JLabel labelTaquilla;
	private GestorDB db;
	private final Usuario usuario;
    private static final Logger LOGGER = Logger.getLogger(VentanaFeed.class.getName());


	// CLASES INTERNAS

	/**
	 * Clase para el TableModel de la Tabla.
	 * 
	 */
	private class PeliculaTableModel extends AbstractTableModel {
		/**
		 * 
		 */

		private static final long serialVersionUID = 1L;
		private String[] nombreColumnas = { "Posicion", "Nombre", "Recaudacion" };
		private LinkedHashMap<String, String> recaudacionPorPelicula;
		private ArrayList<String> peliculasModelLista = new ArrayList<String>();

		/**
		 * Constructor del TableModel
		 * 
		 * @param <T>
		 * 
		 * @param peliculas Una lista de Peliculas para que se muestren en la tabla
		 */
		public PeliculaTableModel(LinkedHashMap<String, String> recaudacionPorPelicula) {
			this.recaudacionPorPelicula = recaudacionPorPelicula;
			peliculasModelLista.addAll(recaudacionPorPelicula.keySet());
		}
		
		

		public List<String> getModelLista(){
			return peliculasModelLista;
		}
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return recaudacionPorPelicula.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return nombreColumnas.length;
		}

		@Override
		public String getColumnName(int column) {
			return nombreColumnas[column];

		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			switch (columnIndex) {
			case 0:
				return rowIndex + 1;
			case 1:
				return peliculasModelLista.get(rowIndex);
			case 2:
				return recaudacionPorPelicula.get(peliculasModelLista.get(rowIndex));

			}
			return null;
		}

	}

	


	/**
	 * Constructor de la ventana de la Feed
	 * @param idUsuario idUsuario que ha abierto esta ventana. Para simular las "cookies" de un navegador.
	 */
	public VentanaFeed(Usuario usuario) {
		super("Feed",usuario);
		this.usuario = usuario;

		
		db = new GestorDB();
	
		// this.setMinimumSize(new Dimension(400,800));

		// Panel general de esta ventana, donde iran todos los demas componentes.
		JPanel panelPrincipal = new JPanel();
		// Le cambiamos el Layout a BorderLayout y le añadimos un poco de margen
		// horizontal y vertical.
		panelPrincipal.setLayout(new BorderLayout(10, 10));

		// Panel del centro de la pantalla
		JPanel panelCentral = new JPanel(new BorderLayout());

		// Panel Titulo (Header) Aqui va el titulo
		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		// JLabel para indicar que indicara que estamos en la feed.
		// Fuente Calibri, negrita y tamaño 20
		// Creamos padding de 10px para que se separe un poco del borde del panel
		JLabel feedTitulo = new JLabel("Feed", SwingConstants.CENTER);
		feedTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		feedTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
		// Añadimos en el centro
		panelHeader.add(feedTitulo, BorderLayout.CENTER);

		// PANEL ENVIO POSTS
		
	
		JButton botonEnviar = new JButton("Enviar");
		
		JPanel panelPostTextArea = new JPanel(new BorderLayout());
		JTextArea postTextArea = new JTextArea();
		postTextArea.setRows(6);  
		postTextArea.setLineWrap(true);
		postTextArea.setWrapStyleWord(true);

		panelPostTextArea.add(new JScrollPane(postTextArea), BorderLayout.CENTER);
	
		panelPostTextArea.add(botonEnviar, BorderLayout.SOUTH);
		
		// 29 161 242 es el codigo RGB del azul de Twitter
		botonEnviar.setBackground(new Color(29, 161, 242));
		botonEnviar.setForeground(Color.white);
		// Padding
		botonEnviar.setBorder(new EmptyBorder(10, 10, 10, 10));
		// Añadimos al panel
		panelPostTextArea.add(postTextArea, BorderLayout.CENTER);
		panelPostTextArea.add(botonEnviar, BorderLayout.SOUTH);

		// PANEL DONDE SE VEN LOS POSTS / PUBLICACIONES
		// Eje VERTICAL, ya que la feed va para abajo.
		JPanel panelPosts = new JPanel();
		panelPosts.setLayout(new BoxLayout(panelPosts, BoxLayout.Y_AXIS));
		/*
		for (int i = 0; i < 3; i++) {
			JPanel panelPostIndividual = crearPost(
					new Post(i, "Este es mi post numero " + i, LocalDateTime.now(),1));
			panelPosts.add(panelPostIndividual);
		}
		*/
		
		List<Post> postsFeed = db.obtenerPostFeed(usuario.getCodigo());
		for (Post post : postsFeed) {
			JPanel panelPostIndividual = crearPost(post);
			panelPosts.add(panelPostIndividual);
			
		}

		JScrollPane scrollPosts = new JScrollPane(panelPosts);
		
		botonEnviar.addActionListener((e -> {
			
			//SACAR HORA DEL POST (En Unix epoch)
		
			// IAG Claude Sonnet 3.5
			//ADAPTADO : Estaba hecho para LocalDate y UTC, cambiado a CET y LocalDateTime
	
			//Convertir a una fecha (LocalDateTime), a CET!!! 
			long horaPost = e.getWhen();
			LocalDateTime fechaPost = Instant.ofEpochMilli(horaPost).atZone(ZoneId.of("CET")).toLocalDateTime();
		    if (!postTextArea.getText().trim().isEmpty()) {
		    	 
		        Post p = new Post(0, postTextArea.getText(),fechaPost,usuario);
				db.insertarPost(p.getContenido(),horaPost, p.getCreadorPost().getCodigo());

		        JPanel p1 = crearPost(p);
		        if (panelPosts.getComponentCount() >= 10) {
		            panelPosts.remove(0);
		        }
		        
		        panelPosts.add(p1);
		        
		        //Limpiar el JTextAREA despues de añadir dicho evento
		        postTextArea.setText("");
		        //Hacer que la ScrollBar este siempre abajo (Para ver el ultimo post)
		        SwingUtilities.invokeLater(() -> {
		            JScrollBar barraVertical = scrollPosts.getVerticalScrollBar();
		            barraVertical.setValue(barraVertical.getMaximum());
		        });

		        // Revalidar y repintar 
		        panelPosts.revalidate();
		        panelPosts.repaint();

		    }
		}));
		


		panelCentral.add(panelPostTextArea, BorderLayout.NORTH);
		panelCentral.add(scrollPosts, BorderLayout.CENTER);

		// Añadimos al panel Principal todo
		panelPrincipal.add(setupPanelNoticias(), BorderLayout.EAST);
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		

		// Despues de acabar toda la configuracion, añadimos el panel general a la
		// ventana
		// y la hacemos visible.
		this.add(panelPrincipal);

		this.setMinimumSize(new Dimension(600, 300));
		this.setVisible(true);

	}

	/**
	 * Crear un Post/Publicacion. Esta funcion se encarga de crear un panel y llenar
	 * el panel con el texto así como con los botones necesarios.
	 * 
	 * @param usuario Usuario que ha enviado el post
	 * @param mensaje Mensaje a mostrar en el post.
	 * @return Un Panel que representa un post
	 */
	private JPanel crearPost(Post postEscrito) {
		
		JPanel post = new JPanel(new BorderLayout(10, 15));
		post.setBorder(new EmptyBorder(10, 10, 10, 10));
		DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
		JLabel usuarioPost = new JLabel(postEscrito.getCreadorPost().getUsername() + " - " + postEscrito.getFechaPost().format(fechaFormato));
		JTextArea mensajePost = new JTextArea(postEscrito.getContenido());
		mensajePost.setBorder(new EmptyBorder(10, 10, 10, 10));
		mensajePost.setEditable(false);
		mensajePost.setLineWrap(true);
		mensajePost.setWrapStyleWord(true);

		// INTERACCIONES POSIBLES : RESPONDER, LIKE
		JPanel interacciones = new JPanel();
		interacciones.setLayout(new FlowLayout(FlowLayout.LEFT));
		
	    // Boton de eliminar
	    JButton botonEliminar = new JButton("Eliminar");
	    botonEliminar.setBackground(Color.RED);
	    botonEliminar.setForeground(Color.WHITE);
	    botonEliminar.setBorder(new EmptyBorder(5, 10, 5, 10));
	    
	    //Boton de Perfil (Abre el perfil)
	    JButton botonPerfil = new JButton("Perfil");
	    botonPerfil.setBackground(new Color(29,161,242));
	    botonPerfil.setForeground(Color.white);
	    botonPerfil.setBorder(new EmptyBorder(5,10,5,10));
	    botonPerfil.addActionListener( (e) -> {
	    	SwingUtilities.invokeLater(( ) -> new VentanaUsuario(postEscrito.getCreadorPost()));
	    });
	    
	    
	    //Solamente aparecera el boton de Eliminar para los posts escritos por nosotros.
	    if(postEscrito.getCreadorPost().getCodigo() == usuario.getCodigo())
	    {
	    interacciones.add(botonEliminar);
	    }
	    interacciones.add(botonPerfil);

	    // Acción del botón de eliminar
	    botonEliminar.addActionListener(e -> {
	        // Obtenemos el panel padre del panel de post
	        Container parent = post.getParent();
	        //Lo eliminamos y revalidamos y repintamos
	        if (parent != null) {
	            parent.remove(post);
	            parent.revalidate();
	            parent.repaint();
	            db.borrarPost(postEscrito.getCodigoPost());
	        }
	    });

		
		post.add(usuarioPost, BorderLayout.NORTH);
		post.add(mensajePost, BorderLayout.CENTER);
		post.add(interacciones, BorderLayout.SOUTH);

		return post;

	}

	

	/**
	 * Funcion para crear el panel de Noticias que saldra a la derecha en la Feed.
	 *
	 */

	private JPanel setupPanelNoticias() {

		JPanel panelNoticias = new JPanel(new BorderLayout());
		panelNoticias.setBorder(new EmptyBorder(0, 10, 0, 10));

		// HEADER del Panel Lateral
		JPanel panelHeaderNoticias = new JPanel();
		JLabel LabelHeaderNoticias = new JLabel("ACTUALIDAD", SwingConstants.CENTER);

		LabelHeaderNoticias.setFont(new Font(VentanaBase.NOMBRE_FUENTE, Font.BOLD, 20));
		LabelHeaderNoticias.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelHeaderNoticias.add(LabelHeaderNoticias);
		panelHeaderNoticias.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panelNoticias.add(panelHeaderNoticias, BorderLayout.NORTH);

		// Dos JTabbedPane uno para las noticias de
		JTabbedPane paneNoticias = new JTabbedPane();

		//TODO Substituir por lo que hay en la BD
		List<Noticia> noticias = db.obtenerNoticias();	

		DefaultListModel<Noticia> listModelNoticias = new DefaultListModel<Noticia>();
		listModelNoticias.addAll(noticias);
		JList<Noticia> listaNoticias = new JList<Noticia>(listModelNoticias);
		listaNoticias.setFixedCellWidth(400);
		// Para que solo se pueda elegir una a la vez.
		listaNoticias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// TODO : TOOLTIP

		listaNoticias.setCellRenderer(new CellRendererNoticias());

		// En vez de usar MouseListener que es una interfaz con metodos que no nos
		// interesan
		// Instanciamos una clase anonima de MouseAdapter y hacemos Override al metodo
		// mouseClicked

		listaNoticias.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Solamente si es un DOBLE CLICK
				if (e.getClickCount() == 2) {
					// Sacamos la posicion en la que hemos clickado
					int posicion = listaNoticias.locationToIndex(e.getPoint());

					// Obtenemos la noticia seleccionada
					Noticia noticiaSeleccionada = listModelNoticias.getElementAt(posicion);
					try {
						// ABRIMOS LA URL EN EL NAVEGADOR
						Desktop.getDesktop().browse(new URI(noticiaSeleccionada.getUrl()));
					} catch (IOException ex) {
						//
						ex.printStackTrace();
					} catch (URISyntaxException ex) {
						System.err.println("La cadena de texto no puede ser interpretada como una URI");
						ex.printStackTrace();
					}

				}
			}

		});

		paneNoticias.add("Noticias", listaNoticias);

		// PANEL DE TAQUILLA
		JPanel panelTaquilla = new JPanel(new BorderLayout());
		labelTaquilla = new JLabel("");
		labelTaquilla.setFont(new Font("Calibri", Font.BOLD, 32));
		labelTaquilla.setHorizontalAlignment(JLabel.CENTER);
		panelTaquilla.add(labelTaquilla, BorderLayout.SOUTH);
		String[] anos = { "2019", "2020", "2021", "2022", "2023", "2024" };
		comboBoxAnos = new JComboBox<String>(anos);

		panelTaquilla.add(comboBoxAnos, BorderLayout.NORTH);

		//Por defecto 
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				peliculasTaquillaScrapping(comboBoxAnos.getSelectedItem().toString());
			}
		});
		t1.start();
		
		comboBoxAnos.addItemListener((e) -> {
			comboBoxAnos.setEnabled(false);
			Thread t2 = new Thread(() -> {
					peliculasTaquillaScrapping(comboBoxAnos.getSelectedItem().toString());					
			});

			t2.start();

		});

		// HEADER DEL PANEL
		JLabel labelTituloTaquilla = new JLabel("Taquilla", SwingConstants.CENTER);
		labelTituloTaquilla.setFont(new Font("Calibri", Font.BOLD, 20));
		labelTituloTaquilla.setBorder(new EmptyBorder(10, 10, 10, 10));
		// panelTaquilla.add(labelTituloTaquilla,BorderLayout.NORTH);

		// TABLA

		tablaTaquilla = new JTable(new PeliculaTableModel(recaudacionPorPelicula));
		tablaTaquilla.setRowHeight(35);

		// Renderer para las Columnas

		JTableHeader headerTabla = tablaTaquilla.getTableHeader();
		// NO permitir que se puedan reordenar las columnas.
		headerTabla.setReorderingAllowed(false);
		/**
		 * Renderer para los Headers	
		 */
		headerTabla.setDefaultRenderer(new DefaultTableCellRenderer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// Obtenemos el Componente el cual es un JLabel
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				// Castemaos el componente a un JLabel
				JLabel headerLabel = (JLabel) c;

				headerLabel.setFont(new Font(VentanaBase.NOMBRE_FUENTE, Font.BOLD, 14)); // Cambia la fuente
				headerLabel.setBackground(new Color(173, 216, 230)); // Fondo azul claro
				setForeground(Color.BLACK); // Texto
				headerLabel.setHorizontalAlignment(JLabel.CENTER);
				return headerLabel;
			}

		});

		/**
		 * Renderer para cada fila vaya alternando de color
		 */
	

		tablaTaquilla.setDefaultRenderer(Object.class, new TableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				JLabel label = new JLabel(value.toString());
				label.setOpaque(true);
				label.setHorizontalAlignment(JLabel.CENTER);
				// Fuente por defecto
				label.setFont(table.getFont());
				//FILA PAR, color Gris claro.
				if(row % 2 == 0) {
					label.setBackground(new Color(211,211,211));
				}
			
				//Mostrar como tooltip el nombre de la pelicula
				if(column == 1) {
					label.setToolTipText(value.toString());
					//Añadir un poco de padding
					label.setBorder(new EmptyBorder(0, 20, 0, 20));
				}
				

				if (column == 2) {
					// Convertimos el value en un Double
					// Sabemos que es un STRING, pero primero le quitamos
					String valueCadena = (String) value;
					String valueSinDolar = valueCadena.replace("$", "").replace(",", "");

					Double recuadacionValor = Double.parseDouble(valueSinDolar);
					// Si la recaudacion es mayor a $1.000.0000.000 (Mil millones)
					if (recuadacionValor > 1000000000) {
						//COLOR DORADO+
						label.setFont(new Font(label.getFont().getName(),Font.BOLD, label.getFont().getSize()));
						label.setForeground(Color.black);
					}
					
				}

				return label;
			}
		});
		
		// Al hacer doble click en una fila , abrira un Dialog con el poster de la pelicula
		tablaTaquilla.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//Cuando hagamos doble click
				if(e.getClickCount() == 2) {
					//Hay que obtener que valor hemos obtenido
					PeliculaTableModel ptm = (PeliculaTableModel) tablaTaquilla.getModel();
					if(tablaTaquilla.getSelectedRow() !=-1) {
						String peliculaClickada = ptm.getModelLista().get(tablaTaquilla.getSelectedRow());
						new DialogoInfoTaquilla(peliculaClickada);
						
					}
					
					
				
				
				}
			}
			
			
		});
		
		
		
		JScrollPane taquillaScroll = new JScrollPane(tablaTaquilla);
		panelTaquilla.add(taquillaScroll);
		// panelTaquilla.add(tablaTaquilla);

		// Añadimos al JTabbedPane
		paneNoticias.add("Taquilla", panelTaquilla);
		// Añadimos el TabbedPane al Centro del Panel lateral.
		panelNoticias.add(paneNoticias, BorderLayout.CENTER);

		return panelNoticias;

	}

	/**
	 * Metodo que descarga las 10 peliculas mas taquilleras del momento 
	 * El scrapping lo hace desde boxofficemojo que suele actualizar la informacion
	 * de taquilla basante frecuentemente
	 * 
	 * @return Lista de las 10 peliculas mas taquilleras del Mundo este año.
	 */

	
	private void peliculasTaquillaScrapping(String ano) {

		SwingUtilities.invokeLater(() -> labelTaquilla.setText("Cargando Taquilla..."));
		// BORRAMOS EL MAPA POR DEFECTO
		recaudacionPorPelicula.clear();

		try {
			// Devuelve el HTML de la lista de peliculas más taquilleras.
			// Despues de scrappearlo, hay que parsearlo para obtener los datos que nos
			// interesan.
			Document doc = Jsoup.connect("https://www.boxofficemojo.com/year/world/" + ano).get();
			//FUENTE-EXTERNA
			//URL: https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
			//ADAPTADO : Añadido cerrado del fichero, si no se cierra no se guarda bien
			FileHandler fh = new FileHandler("log/logBoxOfficeMojo.txt",true);
			LOGGER.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			LOGGER.setUseParentHandlers(false);
			LOGGER.info("Se ha hecho una petición a BoxOfficeMojo para el año " + ano);
			fh.close();
			
			// Vamos a obtener SOLO LOS NOMBRES
			Elements filas = doc.select("#table tbody tr");
			// Vamos a sacar los trs del 1-10 que son el TOP 10
			for (int i = 1; i <= 10; i++) {
				// Por cada fila obtener el NOMBRE de la pelicula, y el dinero recaudado (En
				// todo el mundo
				String nombrePelicula = filas.get(i).select("td.a-text-left.mojo-field-type-release_group a").text();
				String recaudacion = filas.get(i).selectFirst("td.a-text-right.mojo-field-type-money").text();
				recaudacionPorPelicula.put(nombrePelicula, recaudacion);

			}
			
			SwingUtilities.invokeLater(() ->tablaTaquilla.setModel(new PeliculaTableModel(recaudacionPorPelicula)));
			SwingUtilities.invokeLater(() -> labelTaquilla.setText(""));
			SwingUtilities.invokeLater(() -> comboBoxAnos.setEnabled(true));


		} catch (IOException e) {
			
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
	}
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VentanaFeed(new Usuario(3, "Dani", LocalDate.now(), "España", "jpg", "12345")));
	}
}