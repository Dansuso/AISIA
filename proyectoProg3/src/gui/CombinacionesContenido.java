package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import db.GestorDB;
import domain.Contenido;
import domain.Contenido.Genero;
import domain.Contenido.TIPO;

public class CombinacionesContenido extends VentanaBase {
	
	public CombinacionesContenido (){
		super("Combinaciones de Serie");
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		
		GestorDB db = new GestorDB();
		
		JPanel panelCentral = new JPanel(new BorderLayout());
		JLabel labelTop = new JLabel("Recomiendame peliculas para ver!");
		labelTop.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel panelesBotonesCentrales = new JPanel();
		JComboBox<Contenido.Genero> generos = new JComboBox<Contenido.Genero>(Contenido.Genero.values());
		panelesBotonesCentrales.add(generos);
		
		JButton botonRecomendar = new JButton("Recomiendame!");
		panelesBotonesCentrales.add(botonRecomendar);
		ArrayList<Contenido> combinaciones = new ArrayList<Contenido>();
		ArrayList<List<Contenido>> combinacionesFinales = new ArrayList<List<Contenido>>();
		botonRecomendar.addActionListener((e) -> { 
			List<Contenido> contenidos = db.obtenerContenidos();
		
			List<List<Contenido>> lista = combinacionesPeliculas(contenidos, 0, (Genero) generos.getSelectedItem(), combinaciones,combinacionesFinales);	
			for (List<Contenido> list : lista) {
				System.out.println(list.get(0).getTitulo() + "|" + list.get(1).getTitulo() + "|" + list.get(2).getTitulo());
				
			}
				
			
		});
		
		
	    panelCentral.add(panelesBotonesCentrales);
		panelCentral.add(labelTop,BorderLayout.NORTH);
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
