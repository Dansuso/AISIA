package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import domain.Noticia;

public class CellRendererNoticias extends DefaultListCellRenderer {
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
