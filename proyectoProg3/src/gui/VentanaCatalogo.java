package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import db.GestorDB;
import domain.Contenido;
import domain.Contenido.TIPO;
import domain.Serie;

public class VentanaCatalogo extends JFrame {
	
	protected static HashMap<String, ArrayList<Contenido>> mapaContenido;
	private List<JButton> botonesBusc;
	private JPanel panelGrid;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//TODO 
	
//	public static HashMap<String, ArrayList<Contenido>> cargarContenido() {
//		HashMap<String, ArrayList<Contenido>> mapaPrueba = new HashMap<>();
//		File f = new File("resources/data/contenido.csv");
//		ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
//		
//		try {
//			Scanner sc = new Scanner(f);
//			while(sc.hasNextLine()) {
//				String linea = sc.nextLine();
//				String[] campos = linea.split(";");
//				String tipo = campos[0];
//				int codigo = Integer.parseInt(campos[1]);
//				String nombre = campos[2];
//				Genero genero = Genero.valueOf(campos[3]);
//				double duracion = Double.parseDouble(campos[4]);
//				int calificacion = Integer.parseInt(campos[5]);
//				String distribuidora = campos[6];
//				int edad = Integer.parseInt(campos[7]);
//				String premios = campos[8];
//			//	Abría que añadir un campos[9] que sea la ubicación de la imagen poster
//				
//				Contenido nuevo;
//				
//				if(tipo.equals("Pelicula")) {
//					double facturacion = Double.parseDouble(campos[9]);
//					nuevo = new Pelicula(tipo, codigo, nombre, genero, duracion, calificacion, distribuidora, edad, premios, null, facturacion);
//				}
//				else {
//					int numTemporadas = Integer.parseInt(campos[9]);
//					int numCapitulos = Integer.parseInt(campos[10]);
//					nuevo = new Serie(tipo, codigo, nombre, genero, duracion, calificacion, distribuidora, edad, premios, null, numTemporadas, numCapitulos);
//				}
//				
//				contenidos.add(nuevo);
//				
//				
//				
//			}
//			sc.close();
//			
//			crearMapa(contenidos, mapaPrueba);
			
			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(contenidos);
//		mapaContenido = crearMapa(contenidos, mapaPrueba);
//		System.out.println(mapaContenido);
//		return mapaPrueba;
//	}
	
	public static HashMap<String, ArrayList<Contenido>> crearMapa(ArrayList<Contenido> contenidos, HashMap<String, ArrayList<Contenido>> mapa) {
		for (Contenido contenido : contenidos) {
			String nombre = contenido.getTitulo();
			if(!mapa.containsKey(nombre)) {
				mapa.put(nombre, new ArrayList<Contenido>());
			}
			mapa.get(nombre).add(contenido);
		}
		
		return mapa;
		
	}

//	List<Contenido> contenidos = List.of(
//			new Pelicula("Peli", 1, "Pelicula1", Genero.TERROR, 90.5, 6, "Marvel", 13, "Ninguno", null, 10000.40),
//			new Pelicula("Peli", 2, "Pelicula2", Genero.ACCION, 80, 7, "Marvel", 18, "Ninguno", null, 20000.40),
//			new Serie("Serie", 1, "Serie1", Genero.AVENTURA, 40, 8, "Fox", 16, "Ninguno", null, 3, 8)
//			
//			);

	public VentanaCatalogo() {
		
		
		
		
		//Creacion de las caracteristicas de la ventana
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Catalogo");
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
	
		
		mapaContenido = new HashMap<>();
		
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
		
		//Creacion del menu de opciones arriba a la izquierda
		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout());
		
		JMenuBar barraCatalogo = new JMenuBar();
		setJMenuBar(barraCatalogo);
		
		JMenu menuCatalogo = new JMenu("Opciones");
		barraCatalogo.add(menuCatalogo);
		
		JMenuItem vista = new JMenuItem("Cambiar de vista");
		menuCatalogo.add(vista);
		
		menuCatalogo.addSeparator();
		
		JMenuItem pelicula = new JMenuItem("Pelicula");
		menuCatalogo.add(pelicula);
		
		JMenuItem serie = new JMenuItem("Serie");
		menuCatalogo.add(serie);
		
		menuCatalogo.addSeparator();
		
		JMenuItem volver = new JMenuItem("Cerrar sesion");
		menuCatalogo.add(volver);
		
		menuCatalogo.addSeparator();
		
		JMenuItem salir = new JMenuItem("Salir");
		menuCatalogo.add(salir);
		
		panelSuperior.add(barraCatalogo, BorderLayout.WEST);
		
		
		
		//Creacion de la barra de buscar
		JTextField buscador = new JTextField("Buscador",10);
		//Escuchador para la barra de buscar 
		buscador.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
                buscador.setText(""); // Borrar el contenido de la barra
            }
		});
		panelSuperior.add(buscador, BorderLayout.EAST);
		
		
		
		JLabel titulo = new JLabel("Catalogo");
		titulo.setHorizontalAlignment(JTextField.CENTER);
		titulo.setBackground(Color.BLUE);
		Font fuente = new Font("Arial", Font.BOLD, 30);
		titulo.setFont(fuente);
		panelSuperior.add(titulo, BorderLayout.NORTH);
		
		
		
		//Sacamos el contenido
		GestorDB db = new GestorDB();
		List<Contenido> contenidos = db.obtenerContenidos();
		

		panelGrid = new JPanel();
		panelGrid.setLayout((new GridLayout(contenidos.size() / 2,2,10,10)));
		add(panelGrid, BorderLayout.CENTER);
		
		
		
		//Añadir los botones al panel con el nombre de cada contenido
		botonesBusc = new ArrayList<>(); //Uso del array para tener los botones guardados y usarlos en el filtro
        for (Contenido c : contenidos) {
        	
            JButton botones = new JButton(c.getTitulo());  // Asignar el contenido directamente
            botones.setBackground(Color.LIGHT_GRAY);
            botones.setPreferredSize(new Dimension(100,100));
            botonesBusc.add(botones);

            // Añadir el ActionListener al botón
            botones.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Contenido contenidoSeleccionado = c; // Asignar el contenido actual
                	dispose();
                    new VentanaInfo(contenidoSeleccionado.getTitulo(),
                            contenidoSeleccionado.getGenero().toString(),
                            String.valueOf(contenidoSeleccionado.getCalificacion()),
                            contenidoSeleccionado.getDistribuidora());  // Llama a la nueva ventana
                }
            });

            panelGrid.add(botones);
            
        }
        
        vista.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaCatalogoVistaAlterna(null);
				
			}
        	
        });
        
        pelicula.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelGrid.removeAll();
				
				for (String conts : mapaContenido.keySet()) {
					ArrayList<Contenido> contents = mapaContenido.get(conts);
					
					for (Contenido contenido : contents) {
						if (contenido.getTipo().equals(TIPO.PELICULA)) {
		                    // Buscamos el botón asociado a este contenido y lo añadimos al panel
		                    for (JButton boton : botonesBusc) {
		                        if (boton.getText().equals(contenido.getTitulo())) {
		                            panelGrid.add(boton);
		                        }
		                    }
		                }
					}
				}
				panelGrid.revalidate();
		        panelGrid.repaint();
				
			}

			
			
		});
        
        serie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelGrid.removeAll();
				
				for (String conts : mapaContenido.keySet()) {
					ArrayList<Contenido> contents = mapaContenido.get(conts);
					
					for (Contenido contenido : contents) {
						if (contenido.getTipo().equals(TIPO.SERIE)) {
		                    // Buscamos el botón asociado a este contenido y lo añadimos al panel
		                    for (JButton boton : botonesBusc) {
		                        if (boton.getText().equals(contenido.getTitulo())) {
		                            panelGrid.add(boton);
		                        }
		                    }
		                }
					}
				}
				panelGrid.revalidate();
		        panelGrid.repaint();
				
			}
        	
        });
        
        //Listener del JTextField del buscador que llama a una funcion para filtrar por titulo
        buscador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filtrarElementos(buscador.getText());
				
				String textoBusqueda = buscador.getText();

		        // Llamamos al método recursivo con la lista de contenidos
		        int totalResultados = contarResultados(db.obtenerContenidos(), textoBusqueda, 0);

		        // Mostrar un mensaje con el número de resultados encontrados
		        String mensaje = totalResultados == 0 
		            ? "No se encontraron resultados para: " + textoBusqueda 
		            : "Se encontraron " + totalResultados + " resultados para: " + textoBusqueda;

		        JOptionPane.showMessageDialog(
		            VentanaCatalogo.this, 
		            mensaje, 
		            "Resultados de la búsqueda", 
		            JOptionPane.INFORMATION_MESSAGE
		        );
				
			}
        	
        });
        
		
		//Creacion del JScrollPane
		JScrollPane panelScrollCatalogo = new JScrollPane(panelGrid);
		panelScrollCatalogo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelScrollCatalogo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(panelScrollCatalogo, BorderLayout.CENTER);
		
		//Escuchador del boton de volver. Para volver a la anterior ventana
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				VentanaInicio ventana = new VentanaInicio();
				ventana.setVisible(true);
				
			}
			
		});
		
		//Para que el boton de opcion salir funcione
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel labelSalir = new JLabel("Desea salir de la aplicacion?");
				
				Object[] mensaje = {labelSalir};
				
				int resultado = JOptionPane.showConfirmDialog(null, 
						mensaje, 
						"Salir", 
						JOptionPane.YES_NO_OPTION
					);
				
				if(resultado == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

				
			}
			
		});
		
		
		setVisible(true);
		
	}
	
	//Funcion para filtar los titulos de los contenidos para el listener del buscador
	protected void filtrarElementos(String texto) {
		panelGrid.removeAll();  // Limpiar el panel

        for (JButton boton : botonesBusc) {
            if (boton.getText().toLowerCase().contains(texto.toLowerCase())) {
                panelGrid.add(boton);  // Añadir solo los botones que coincidan con la búsqueda
            }
            
        }
        
     // Refrescar el panel después de añadir o quitar elementos
        panelGrid.revalidate();
        panelGrid.repaint();
		
	}
	
	private int contarResultados(List<Contenido> lista, String palabra, int indice) {
	    // Caso base: si hemos llegado al final de la lista
	    if (indice == lista.size()) {
	        return 0;
	    }

	    // Verificar si el título contiene la palabra buscada
	    Contenido contenido = lista.get(indice);
	    int coincidencia = contenido.getTitulo().toLowerCase().contains(palabra.toLowerCase()) ? 1 : 0;

	    // Llamada recursiva para el resto de la lista
	    return coincidencia + contarResultados(lista, palabra, indice + 1);
	}

	public static void main(String[] args) {
        new VentanaCatalogo();
    }

}
