package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import domain.Contenido;
import domain.Contenido.TIPO;

public class RendererListaContenidos extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		JLabel label =  (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Contenido c = (Contenido) value;
		
		
		
		if(c.getTipo().equals(TIPO.PELICULA)) {
			String rutaPeli = "resources/images/recursos/contenido/pelicula.png";
			label.setIcon(new ImageIcon(rutaPeli));
		}
		else if(c.getTipo().equals(TIPO.SERIE)) {
			String rutaSerie = "resources/images/recursos/contenido/serie.png";
			label.setIcon(new ImageIcon(rutaSerie));
		}
		
		label.setText(c.getTitulo());
		
		return label;
	}
	
	

}
