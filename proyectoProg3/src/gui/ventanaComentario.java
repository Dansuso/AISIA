package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ventanaComentario {
	public static ActionListener ventanacomentario(String comentario, String nombre, String calif, String foto) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Color colorAisia3 = new Color(235, 155, 195);
                JFrame ventanaComentario = new JFrame();
                ventanaComentario.setTitle("Comentario");
                ventanaComentario.setSize(640, 640);
                ventanaComentario.setLayout(new GridLayout(2, 1));
                
                JPanel panelArriba = new JPanel(new GridLayout(1,2));
                
                JPanel panelVentanaComentario = new JPanel();
                panelVentanaComentario.setLayout(new BorderLayout());
                panelVentanaComentario.setBackground(colorAisia3);

                
                JTextArea areaComentario = new JTextArea(comentario);
                areaComentario.setBackground(colorAisia3);
                areaComentario.setLineWrap(true);       // Ajustar texto
                areaComentario.setWrapStyleWord(true);	// Que no se partan las palabras
                areaComentario.setEditable(false);
        		areaComentario.setFont(new Font("Arial", Font.BOLD, 15));
        		
        		JScrollPane deslizanteComentario = new JScrollPane(areaComentario);
                panelVentanaComentario.add(deslizanteComentario);
                
                JPanel panelNombreCalif = new JPanel(new BorderLayout());
                panelNombreCalif.setBackground(colorAisia3);
                
                JTextArea areaNombre = new JTextArea(" " + nombre);
                areaNombre.setBackground(colorAisia3);
                areaNombre.setLineWrap(true);       // Ajustar texto
                areaNombre.setWrapStyleWord(true);	// Que no se partan las palabras
                areaNombre.setEditable(false);
        		areaNombre.setFont(new Font("Tahoma", Font.BOLD, 45));
        		
                panelNombreCalif.add(areaNombre, BorderLayout.NORTH);
                
                JTextArea areaCalif = new JTextArea(" " + calif);
                areaCalif.setBackground(colorAisia3);
                areaCalif.setLineWrap(true);       // Ajustar texto
                areaCalif.setWrapStyleWord(true);	// Que no se partan las palabras
                areaCalif.setEditable(false);
        		areaCalif.setFont(new Font("emojiFont", Font.BOLD, 70));
        		
        		panelNombreCalif.add(areaCalif, BorderLayout.SOUTH);
                
                panelArriba.add(panelNombreCalif);
                panelArriba.add(panelVentanaComentario);

                JPanel fotoPanel = new JPanel(new BorderLayout());
                fotoPanel.setBackground(colorAisia3);
                ImageIcon caratula = new ImageIcon(foto);
                Image escaladoCaratula = caratula.getImage().getScaledInstance(350, 450, Image.SCALE_SMOOTH);
                ImageIcon escaladoCaratulaFin = new ImageIcon(escaladoCaratula);
                JLabel etiquetaCaratula = new JLabel(escaladoCaratulaFin);
                fotoPanel.add(etiquetaCaratula, BorderLayout.CENTER);
                
                ventanaComentario.add(panelArriba, BorderLayout.NORTH);
                ventanaComentario.add(fotoPanel, BorderLayout.SOUTH);
                ventanaComentario.setVisible(true);
            }
        };
    }

}
