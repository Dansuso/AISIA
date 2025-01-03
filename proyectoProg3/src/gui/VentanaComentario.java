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

import domain.Post;

public class VentanaComentario {
	public static ActionListener VentanaComent(Post post) {
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

                
                
                // Comentario en cuestión
                JTextArea areaComentario = new JTextArea(post.getContenido());
                areaComentario.setBackground(colorAisia3);
                areaComentario.setLineWrap(true);       // Ajustar texto
                areaComentario.setWrapStyleWord(true);	// Que no se partan las palabras
                areaComentario.setEditable(false);
        		areaComentario.setFont(new Font("Arial", Font.BOLD, 15));
        		
        		JScrollPane deslizanteComentario = new JScrollPane(areaComentario);
                panelVentanaComentario.add(deslizanteComentario);
                
                
                // Panel con la fecha en la que se ha publicado el post
                JPanel panelNombreCalif = new JPanel(new BorderLayout());
                panelNombreCalif.setBackground(colorAisia3);
                
                
                JLabel areaFecha = VentanaUsuario.formatearFecha(post.getFechaPost());
                areaFecha.setBackground(colorAisia3);
        		areaFecha.setFont(new Font("Tahoma", Font.BOLD, 20));
        		areaFecha.setForeground(Color.WHITE);
        		
        		JLabel areaNombreUser = new JLabel(post.getCreadorPost().getUsername());
        		areaNombreUser.setForeground(Color.RED);
                areaNombreUser.setBackground(colorAisia3);
        		areaNombreUser.setFont(new Font("Tahoma", Font.BOLD, 40));
        		
                panelNombreCalif.add(areaFecha, BorderLayout.NORTH);
                panelNombreCalif.add(areaNombreUser, BorderLayout.CENTER);
                
                panelArriba.add(panelNombreCalif);
                panelArriba.add(panelVentanaComentario);
                
                
                JPanel fotoPanel = new JPanel(new BorderLayout());
                fotoPanel.setBackground(colorAisia3);
                String direccionPredeterminada = "resources/images/recursos/perfil/";
                String dirFinal = direccionPredeterminada + post.getCreadorPost().getFoto();
                
                ImageIcon caratula = new ImageIcon(dirFinal);
                Image escaladoCaratula = caratula.getImage().getScaledInstance(265, 265, Image.SCALE_SMOOTH);
                
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
