package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import db.GestorDB;
import domain.Contenido;
import domain.Contenido.Genero;
import domain.Contenido.TIPO;

public class CombinacionesContenido extends JDialog {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelArbol;
	    
	    public CombinacionesContenido() {
	        this.setSize(800, 600);
	        this.setMinimumSize(new Dimension(800,600));
	        this.setLocationRelativeTo(null);
	        
	        GestorDB db = new GestorDB();
	        
	        JPanel panelCentral = new JPanel(new BorderLayout());
	        
	        // Panel superior para los controles
	        JPanel panelControles = new JPanel(new BorderLayout());
	        
	        JLabel labelTop = new JLabel("Recomiéndame películas para ver!");
	        labelTop.setHorizontalAlignment(JLabel.CENTER);
	        panelControles.add(labelTop, BorderLayout.NORTH);
	        
	        // Panel para el combo y el boton
	        JPanel panelBotones = new JPanel();
	        JComboBox<Contenido.Genero> generos = new JComboBox<Contenido.Genero>(Contenido.Genero.values());
	        JButton botonRecomendar = new JButton("¡Recomiéndame!");
	        
	        panelBotones.add(generos);
	        panelBotones.add(botonRecomendar);
	        panelControles.add(panelBotones, BorderLayout.CENTER);
	        
	        // Añadir el panel de controles arriba
	        panelCentral.add(panelControles, BorderLayout.NORTH);
	        
	        // Panel para el JTree
	        panelArbol = new JPanel(new BorderLayout());
	        panelArbol.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
	        panelCentral.add(panelArbol, BorderLayout.CENTER);
	        
	        ArrayList<Contenido> combinaciones = new ArrayList<Contenido>();
	        ArrayList<List<Contenido>> combinacionesFinales = new ArrayList<List<Contenido>>();
	        
	        botonRecomendar.addActionListener((e) -> { 
	            List<Contenido> contenidos = db.obtenerContenidos();
	            combinacionesFinales.clear();
	            List<List<Contenido>> peliculasCombinacion = combinacionesPeliculas(
	                contenidos, 0, (Genero) generos.getSelectedItem(), 
	                combinaciones, combinacionesFinales
	            );    
	            //Cada vez que le damos al boton, que se borre todo
	            panelArbol.removeAll();
	            //Nodo raiz (De aqui sale todo )
	            DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Combinaciones disponibles");
	            
	            for (int i = 0; i < peliculasCombinacion.size(); i++) {
	                List<Contenido> lista = peliculasCombinacion.get(i);
	                //Creamos un nodo por cada grupo de 3 peliculas (Son listas de 3 peliculas).
	                DefaultMutableTreeNode nodoPadre = new DefaultMutableTreeNode(
	                   lista.get(0).getTitulo() +   "- " + lista.get(1).getTitulo() +  " - " +  lista.get(2).getTitulo()
	                );
	                
	                for (Contenido elemento : lista) {
	                	//A cada nodo , le añadimos las  3 peliculas
	                    nodoPadre.add(new DefaultMutableTreeNode(elemento.getTitulo()));
	                }
	                
	                raiz.add(nodoPadre);
	            }
	            
	            //MODELO
	            DefaultTreeModel modelo = new DefaultTreeModel(raiz);
	            //JTREE
	            JTree arbol = new JTree(modelo);
	            
	            
	            JScrollPane scrollPane = new JScrollPane(arbol);
	            
	            panelArbol.add(scrollPane, BorderLayout.CENTER);
	            panelArbol.revalidate();
	            panelArbol.repaint();
	        });
	        
	        this.add(panelCentral);
	        this.setVisible(true);
	    }	
	/**
	 * Metodo para generar combinaciones de 3 peliculas y el genero elegido
	 * @param peliculas Lista de Contenido, se saca de la BBDD
	 * @param indice    Indice para que en la llamada recursiva sepa por donde seguir
	 * @param genero	Genero elegido 
	 * @param combinacion
	 * @param combinacionesFinales
	 * @return
	 */
	private List<List<Contenido>> combinacionesPeliculas(List<Contenido> peliculas, int indice, Genero genero, List<Contenido> combinacion, List<List<Contenido>> combinacionesFinales) {
	    // Caso Base: Si la combinación tiene tamaño 3, añadir a combinacionesFinales
	    if (combinacion.size() == 3) {
	        combinacionesFinales.add(new ArrayList<Contenido>(combinacion)); 
	        return combinacionesFinales;
	    }

	    // Caso Recursivo: Iterar sobre las películas restantes
	    for (int i = indice; i < peliculas.size(); i++) {
	        if (peliculas.get(i).getGenero() == genero && peliculas.get(i).getTipo() == TIPO.PELICULA) { 
	            combinacion.add(peliculas.get(i)); // Añadir película a la combinación
	            combinacionesPeliculas(peliculas, i + 1, genero, combinacion, combinacionesFinales); // Llamada recursiva
	            combinacion.remove(combinacion.size() - 1); // Backtracking 
	        }
	    }

	    return combinacionesFinales;
	}

	
	public static void main(String[] args) {
		new CombinacionesContenido();
		
	}
	

}
