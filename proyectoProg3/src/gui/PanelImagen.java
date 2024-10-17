package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImagen extends JPanel{
	private Image imagen;

	public PanelImagen(String fondo) {
		// TODO Auto-generated constructor stub
		imagen = new ImageIcon(fondo).getImage();
			


}
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
		
		setOpaque(false);
		
	}
}