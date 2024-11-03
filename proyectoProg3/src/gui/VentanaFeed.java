package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
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

import domain.Noticia;
import domain.Pelicula;
import domain.Post;

/**
 * Clase que representa la ventana del feed, donde los usuarios podran enviar y
 * leer posts.
 */
public class VentanaFeed extends VentanaBase {

	// CLASES INTERNAS

	/**
	 * Clase del Renderer de la Lista de Noticias.
	 */
	private class CellRendererNoticias extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			// Guardamos en c el componente (Es un JLABEL) creado por el renderer.
			Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			// Casteamos el componente a JLabel

			JLabel labelNoticia = (JLabel) c;

			// El value es una instancia de Noticia, asi que casteamos tambien
			Noticia noticia = (Noticia) value;

			labelNoticia.setText(noticia.getTitulo());
			// Cuando el usuario pase el raton por la noticia, mostrara el titulo completo.
			labelNoticia.setToolTipText(noticia.getTitulo());
			// Poner como icono el logo de la fuente de la noticia.
			labelNoticia
					.setIcon(new ImageIcon("resources/images/fuentes/" + noticia.getFuente().toLowerCase() + ".png"));

			return labelNoticia;

		}

	}

	/**
	 * Clase para el TableModel de la Tabla.
	 * 
	 */
	private class PeliculaTableModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] nombreColumnas = { "Posicion", "Nombre", "Recaudacion", "Distribuidora", "Fecha", "Genero" };
		private List<Pelicula> peliculas;

		/**
		 * Constructor del TableModel
		 * 
		 * @param peliculas Una lista de Peliculas para que se muestren en la tabla
		 */
		public PeliculaTableModel(List<Pelicula> peliculas) {
			this.peliculas = peliculas;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return peliculas.size();
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
			// Elegimos la pelicula
			Pelicula pelicula = peliculas.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return rowIndex;
			case 1:
				return pelicula.getTitulo();
			case 2:
				return pelicula.getFacturacionTaquilla();
			case 3:
				return pelicula.getDistribuidora();
			case 4:
				return "2023"; // TODO: Añadir campo de Fecha
			case 5:
				return pelicula.getGenero(); // TODO GENERO DEBERIA SER UN ENUM.

			}
			return null;
		}

	}

	/**
	 * Clase que define el Renderer de la Tabla.
	 * 
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la ventana de la Feed
	 */
	public VentanaFeed() {
		super("Feed");
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
		JPanel panelPostTextArea = new JPanel(new BorderLayout());
		// JTextArea con un padding tambien de 10 pixeles
		JTextArea postTextArea = new JTextArea();
		postTextArea.setRows(3);
		// BOTON ENVIAR
		JButton botonEnviar = new JButton("Enviar");
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

		for (int i = 0; i < 5; i++) {
			JPanel panelPostIndividual = crearPost(
					new Post(i, "Este es mi post numero " + i, null, 0, i, i, null, null));
			panelPosts.add(panelPostIndividual);
		}

		JScrollPane scrollPosts = new JScrollPane(panelPosts);

		panelCentral.add(panelHeader, BorderLayout.NORTH);
		panelCentral.add(panelPostTextArea, BorderLayout.CENTER);
		panelCentral.add(scrollPosts, BorderLayout.SOUTH);

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
		JLabel usuarioPost = new JLabel("Usuario");
		JTextArea mensajePost = new JTextArea(postEscrito.getContenido());
		mensajePost.setBorder(new EmptyBorder(10, 10, 10, 10));
		mensajePost.setEditable(false);
		mensajePost.setLineWrap(true);
		mensajePost.setWrapStyleWord(true);

		// INTERACCIONES POSIBLES : RESPONDER, LIKE
		JPanel interacciones = new JPanel();
		interacciones.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton botonResponder = new JButton("Responder");
		botonResponder.setBackground(new Color(0, 102, 204));
		botonResponder.setForeground(Color.white);
		JButton botonLike = new JButton("Like " + postEscrito.getNumLikes());
		botonLike.setBackground(new Color(36, 160, 237));
		botonLike.setForeground(Color.white);

		interacciones.add(botonResponder);
		interacciones.add(botonLike);

		post.add(usuarioPost, BorderLayout.NORTH);
		post.add(mensajePost, BorderLayout.CENTER);
		post.add(interacciones, BorderLayout.SOUTH);
		return post;

	}

	/**
	 * Clase interna que define el Renderer para la lista de Noticias
	 */

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

		// TODO : Hacer dos paneles , uno para peliculas y otro TV?

		List<Noticia> noticias = List.of(
				new Noticia("Netflix Password Crackdown Delivers Millions of New Customers", " ",
						"https://www.wsj.com/business/earnings/netflix-nflx-q1-earnings-report-2024-78eababf", "wsj"),
				new Noticia("Tom Cruise Eyeing ‘Days of Thunder’ Sequel for Paramount", " ",
						"https://www.hollywoodreporter.com/movies/movie-news/tom-cruise-days-of-thunder-sequel-paramount-1236051723/",
						"thr"),
				new Noticia(
						"Joker’ Box Office Shocker: ‘Folie à Deux’ Bombs With $37.8M Opening After Receiving D CinemaScore",
						" ",
						"https://www.hollywoodreporter.com/movies/movie-news/joker-folie-a-deux-box-office-d-cinemascore-1236025168/",
						"thr"),
				new Noticia("Disney Adds 5 Million Streaming Subscribers", " ",
						"https://www.nytimes.com/2023/11/08/business/disney-5-million-streaming-subscribers.html?searchResultPosition=8",
						"nyt"),
				new Noticia("How Netflix won the streaming wars", " ",
						"https://www.ft.com/content/465a2d0d-8973-4d8d-827d-8729737e6606", "ft"));

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

					//Obtenemos la noticia seleccionada
					Noticia noticiaSeleccionada = listModelNoticias.getElementAt(posicion);
					try {
						//ABRIMOS LA URL EN EL NAVEGADOR
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

		// HEADER DEL PANEL
		JLabel labelTituloTaquilla = new JLabel("Taquilla", SwingConstants.CENTER);
		labelTituloTaquilla.setFont(new Font("Calibri", Font.BOLD, 20));
		labelTituloTaquilla.setBorder(new EmptyBorder(10, 10, 10, 10));
		// panelTaquilla.add(labelTituloTaquilla,BorderLayout.NORTH);

		// TABLA

		List<Pelicula> peliculas = List.of(
				new Pelicula("Cine", 1, "El Señor de los Anillos: La Comunidad del Anillo", "Fantasía", 178.0, 91,
						"New Line Cinema", 13, "Oscar", null, 871.5),
				new Pelicula("Cine", 2, "Titanic", "Romance", 195.0, 89, "20th Century Fox", 13, "Oscar", null, 2200.0),
				new Pelicula("Cine", 3, "Inception", "Ciencia Ficción", 148.0, 87, "Warner Bros.", 13, "Oscar", null,
						829.9),
				new Pelicula("Cine", 4, "The Dark Knight", "Acción", 152.0, 94, "Warner Bros.", 13, "Oscar", null,
						1004.9),
				new Pelicula("Cine", 5, "Toy Story", "Animación", 81.0, 100, "Pixar", 6, "Oscar", null, 373.6),
				new Pelicula("Cine", 6, "Forrest Gump", "Drama", 142.0, 88, "Paramount Pictures", 13, "Oscar", null,
						678.2),
				new Pelicula("Cine", 7, "Avatar", "Ciencia Ficción", 162.0, 82, "20th Century Fox", 13, "Oscar", null,
						2847.2),
				new Pelicula("Cine", 8, "Gladiator", "Histórica", 155.0, 77, "Universal Pictures", 16, "Oscar", null,
						460.5),
				new Pelicula("Cine", 9, "Jurassic Park", "Aventura", 127.0, 91, "Universal Pictures", 13, "Oscar", null,
						1029.2),
				new Pelicula("Cine", 10, "The Matrix", "Ciencia Ficción", 136.0, 87, "Warner Bros.", 16, "Oscar", null,
						466.3));

		JTable tablaTaquilla = new JTable(new PeliculaTableModel(peliculas));

		// Renderer para las Columnas

		JTableHeader headerTabla = tablaTaquilla.getTableHeader();
		/**
		 * Renderer para los Headers
		 */
		headerTabla.setDefaultRenderer(new DefaultTableCellRenderer() {

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
				return headerLabel;
			}

		});

		/**
		 * Renderer para la columna "Recaudacion. Mostrara en color dorado las peliculas
		 * que superan 1000 millones en recaudacion
		 */

		tablaTaquilla.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				JLabel recaudacionLabel = (JLabel) c;

				// Sabemos que el value es un numero

				if (((Double) value) > 1000.0) {
					recaudacionLabel.setForeground(new Color(218, 165, 32)); //COLOR DORADO
				}
				// IMPORTANTE! Hay que tener una clausula ELSE, si no todos los colores seran
				// Dorados
				// Al parecer, TableCellRender REUSA El LABEL, no es un LABEL distinto.
				//Asi que hay que RESETEAR el valor

				else {
					recaudacionLabel.setForeground(Color.BLACK); // Reset to black for lower values
				}
				
				
				//Por defecto nuestros renderer van  a alternar el color cada fila.
				//Pero como la columna 2 tiene su propio renderer, no le afecta.
				//Por eso añadimos explicitamente
				
				if (row % 2 == 0) {
		            recaudacionLabel.setBackground(Color.WHITE);
		        } else {
		            recaudacionLabel.setBackground(new Color(240, 240, 240)); //Gris CLARO
		        }
				

				return recaudacionLabel;
			}

		});
		
		/**
		 * Renderer para cada fila vaya alternando de color
		 */
		
		tablaTaquilla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				Component c =	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				//Casteamos el componente a un JLabel
				
				JLabel label = (JLabel) c;
				//Si es par...
				if(row % 2 == 0) {
					label.setBackground(Color.white);
					
				}
				//Sino
				
				else {
					label.setBackground(new Color(240,240,240));
				}
				
				
				
				return label;
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VentanaFeed());
	}
}
