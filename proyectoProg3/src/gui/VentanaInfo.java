package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VentanaInfo extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaInfo(String tituloPasado, String generoPasado, String calificacionPasado, String distribuidoraPasado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Informacion");
		setSize(640,480);
		setLocationRelativeTo(null);
		
		JButton volver = new JButton("Volver");
		this.add(volver,BorderLayout.NORTH);
		
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaCatalogo();
				
			}
			
		});
		
		JPanel panelPrincipal = new JPanel();
		
		Border bordePantalla = BorderFactory.createTitledBorder("Informacion");
        panelPrincipal.setBorder(bordePantalla);
        
        JPanel panelTitulo = new JPanel();
        JLabel titulo = new JLabel("Titulo:");
        JLabel infoTitulo = new JLabel(tituloPasado);
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.add(titulo);
        panelTitulo.add(infoTitulo);
        
        JPanel panelGenero = new JPanel();
        JLabel genero = new JLabel("Genero: ");
        JLabel infoGenero = new JLabel(generoPasado);
        panelGenero.setLayout(new FlowLayout());
        panelGenero.add(genero);
        panelGenero.add(infoGenero);
        
        JPanel panelDuracion = new JPanel();
        JLabel duracion = new JLabel("Duracion: ");
        JLabel infoDuracion = new JLabel("DatosDuracion");
        panelDuracion.setLayout(new FlowLayout());
        panelDuracion.add(duracion);
        panelDuracion.add(infoDuracion);
        
        JPanel panelCalificacion = new JPanel();
        JLabel calificacion = new JLabel("Calificacion: ");
        JLabel infocalificacion = new JLabel(calificacionPasado);
        panelCalificacion.setLayout(new FlowLayout());
        panelCalificacion.add(calificacion);
        panelCalificacion.add(infocalificacion);
        
        JPanel panelDistribuidora = new JPanel();
        JLabel distribuidora = new JLabel("Distribuidora: ");
        JLabel infoDistribuidora = new JLabel(distribuidoraPasado);
        panelDistribuidora.setLayout(new FlowLayout());
        panelDistribuidora.add(distribuidora);
        panelDistribuidora.add(infoDistribuidora);
        
        JPanel panelPremios = new JPanel();
        JLabel premios = new JLabel("Premios: ");
        JLabel infoPremios = new JLabel("Nada");
        panelPremios.setLayout(new FlowLayout());
        panelPremios.add(premios);
        panelPremios.add(infoPremios);
        
        
        
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(panelTitulo);
        panelPrincipal.add(panelGenero);
        panelPrincipal.add(panelDuracion);
        panelPrincipal.add(panelCalificacion);
        panelPrincipal.add(panelDistribuidora);
        panelPrincipal.add(panelPremios);
        
        this.add(panelPrincipal, BorderLayout.CENTER);
		
		setVisible(true);
		
	}

	

}
