package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Clase que representa la ventana del feed, donde los usuarios podran enviar y leer posts.
 */
public class VentanaFeed extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaFeed() {
        this.setSize(640, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tweet");

       

        //Panel general de esta ventana, donde iran todos los demas componentes.
        JPanel panelPrincipal = new JPanel();
        //Le cambiamos el Layout a BorderLayout y le añadimos un poco de margen horizontal y veritcal.
        panelPrincipal.setLayout(new BorderLayout(10, 10));

        //Panel del centro de la pantalla
        JPanel panelCentral = new JPanel(new BorderLayout());
        
        
        //Panel Titulo (Header) Aqui va el titulo
        
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,false));

        
        //JLabel para indicar que indicara que estamos en la feed.
        
        JLabel feedHeader = new JLabel("Aisia");
             
        feedHeader.setFont(new Font("Calibri", Font.BOLD, 20));
        //Creamos padding de 10px para que se separe un poco del borde del panel
        feedHeader.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelHeader.add(feedHeader,BorderLayout.CENTER);
        
        //PANEL DE POSTS (postTextArea)
        
        
        JPanel panelPostTextArea = new JPanel(new BorderLayout());
        JTextArea postTextArea = new JTextArea(5,20);
        postTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        //BOTON ENVIAR
        JButton botonEnviar = new JButton("Enviar");
        //29 161 242 es el codigo RGB del azul de Twitter
        botonEnviar.setBackground(new Color(29 ,161, 242));
        botonEnviar.setForeground(Color.white);

        botonEnviar.setBorder(new EmptyBorder(10,10,10,10));
        
        panelPostTextArea.add(postTextArea,BorderLayout.CENTER);
        panelPostTextArea.add(botonEnviar,BorderLayout.SOUTH);
        
        //FEED ( PRINCIPAL -> CENTRAL -> panelFeed)
        
       
        //Eje VERTICAL, ya que la feed va para abajo.
        JPanel panelFeed = new JPanel();
        panelFeed.setLayout(new BoxLayout(panelFeed, BoxLayout.Y_AXIS));
        
        //Un panel -> Un Post 
        
        JPanel panelPost = new JPanel();
        
        
        
        
        
        
        
        panelCentral.add(panelHeader,BorderLayout.NORTH);
        panelCentral.add(panelPostTextArea,BorderLayout.CENTER);
        panelCentral.add(panelFeed,BorderLayout.SOUTH);
        panelPrincipal.add(panelCentral);
        

        //Despues de acabar toda la configuracion, añadimos el panel general a la ventana 
        // y la hacemos visible.
        this.add(panelPrincipal);
        this.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        VentanaFeed v = new VentanaFeed();
    }
}
