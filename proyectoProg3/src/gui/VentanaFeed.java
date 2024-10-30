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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Clase que representa la ventana del feed, donde los usuarios podran enviar y leer posts.
 */
public class VentanaFeed extends VentanaBase {

    private static final long serialVersionUID = 1L;

    public VentanaFeed(String title) {
    	super(title);
     

       
        //Panel general de esta ventana, donde iran todos los demas componentes.
        JPanel panelPrincipal = new JPanel();
        //Le cambiamos el Layout a BorderLayout y le añadimos un poco de margen horizontal y vertical.
        panelPrincipal.setLayout(new BorderLayout(10, 10));

        //Panel del centro de la pantalla
        JPanel panelCentral = new JPanel(new BorderLayout());
        
        //Panel Titulo (Header) Aqui va el titulo
        JPanel panelHeader = new JPanel();
        panelHeader.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
        //JLabel para indicar que indicara que estamos en la feed.
        //Fuente Calibri, negrita y tamaño 20
        //Creamos padding de 10px para que se separe un poco del borde del panel
        JLabel feedTitulo = new JLabel("Feed",SwingConstants.CENTER);
        feedTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        feedTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        //Añadimos en el centro
        panelHeader.add(feedTitulo);

        
        //PANEL ENVIO POSTS
        JPanel panelPostTextArea = new JPanel(new BorderLayout());
        //JTextArea con un padding tambien de 10 pixeles
        JTextArea postTextArea = new JTextArea();
        postTextArea.setRows(3);
        //BOTON ENVIAR
        JButton botonEnviar = new JButton("Enviar");
        //29 161 242 es el codigo RGB del azul de Twitter
        botonEnviar.setBackground(new Color(29 ,161, 242));
        botonEnviar.setForeground(Color.white);
        //Padding
        botonEnviar.setBorder(new EmptyBorder(10,10,10,10));
        //Añadimos al panel
        panelPostTextArea.add(postTextArea,BorderLayout.CENTER);
        panelPostTextArea.add(botonEnviar,BorderLayout.SOUTH);
        
       
        
        
        //PANEL DONDE SE VEN LOS POSTS / PUBLICACIONES
        //Eje VERTICAL, ya que la feed va para abajo.
        JPanel panelPosts = new JPanel();
        panelPosts.setLayout(new BoxLayout(panelPosts, BoxLayout.Y_AXIS));
        
    
        
        for(int i = 0 ; i <  5; i++) {
        	JPanel panelPostIndividual = crearPost("Usuario-" + i, "Este es mi post numero " + i );
        	panelPosts.add(panelPostIndividual);
        }
        
        
        JScrollPane scrollPosts = new JScrollPane(panelPosts);
       

        
        
        
        
        
        panelCentral.add(panelHeader,BorderLayout.NORTH);
        panelCentral.add(panelPostTextArea,BorderLayout.CENTER);
        panelCentral.add(scrollPosts,BorderLayout.SOUTH);
        
        //Añadimos al panel Principal todo
        panelPrincipal.add(panelCentral);
        

        //Despues de acabar toda la configuracion, añadimos el panel general a la ventana 
        // y la hacemos visible.
        this.add(panelPrincipal);
        this.setVisible(true);
    }
    
    /**
     * Crear un Post/Publicacion. Esta funcion se encarga de crear un panel y llenar el panel con el texto así como con los botones necesarios.
     * 
     * @param usuario Usuario que ha enviado el post
     * @param mensaje Mensaje a mostrar en el post.
     * @return Un Panel que representa un post
     */
    private JPanel crearPost(String usuario, String mensaje) {
		JPanel post = new JPanel(new BorderLayout(10, 15));
		post.setBorder(new EmptyBorder(10,10, 10,10));
		JLabel usuarioPost = new JLabel(usuario);
		JTextArea mensajePost = new JTextArea(mensaje);
		mensajePost.setBorder(new EmptyBorder(10, 10, 10,10));
		mensajePost.setEditable(false);
		mensajePost.setLineWrap(true);
		mensajePost.setWrapStyleWord(true);
		
		//INTERACCIONES POSIBLES : RESPONDER, LIKE 
		JPanel interacciones = new JPanel();
		interacciones.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton botonResponder = new JButton("Responder");
		JButton botonLike = new JButton("Like");
	
		interacciones.add(botonResponder);
		interacciones.add(botonLike);
		
		post.add(usuarioPost,BorderLayout.NORTH);
		post.add(mensajePost,BorderLayout.CENTER);
		post.add(interacciones,BorderLayout.SOUTH);
		return post;
    	
    
		
	}


	public static void main(String[] args) {
        VentanaFeed v = new VentanaFeed("Aisia Feed");
    }
}
