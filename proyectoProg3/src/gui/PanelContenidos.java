package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import domain.Contenido;


public class PanelContenidos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField textoTitulo;
	private JTextField textoGenero;
	private JTextField textoNota;
	private JTextField textoDistribuidora;
	private JRadioButton radioButtonPeli;
    private JRadioButton radioButtonSerie;
    
    private boolean editable = true;
    
    public void setContenido(Contenido c) {
    	textoTitulo.setText(c.getTitulo());
    	textoGenero.setText(c.getGenero().toString());
    	textoNota.setText(String.valueOf(c.getCalificacion()));
    	textoDistribuidora.setText(c.getDistribuidora());
    	
    	if ("Peli".equals(c.getTipo())) {
            radioButtonPeli.setSelected(true);
        } else if ("Serie".equals(c.getTipo())) {
            radioButtonSerie.setSelected(true);
        }
    }
    
    
    public void setEditable(boolean editable) {
        this.editable = editable;
        
        // Campos de texto y nacimiento
        textoTitulo.setEditable(editable);
        textoGenero.setEditable(editable);
        textoNota.setEditable(editable);
        textoDistribuidora.setEditable(editable);
        
        
        
        // Botones de radio para género
        radioButtonPeli.setEnabled(editable);
        radioButtonSerie.setEnabled(editable);
    }

    // Método para verificar si el formulario está en modo editable
    public boolean isEditable() {
        return this.editable;
    }
	
	public PanelContenidos() {
		
		setSize(400, 300);
		
		setLayout(new BorderLayout());
		
		Border checkButtonsBorder = BorderFactory.createTitledBorder("Informacion");
        this.setBorder(checkButtonsBorder);
        
        JLabel titulo = new JLabel("Titulo");
        textoTitulo = new JTextField(20);
        
        JLabel genero = new JLabel("Genero");
        textoGenero = new JTextField(20);
        
        JLabel nota = new JLabel("Calificacion");
        textoNota = new JTextField(20);
        
        JLabel distribuidora = new JLabel("Distribuidora");
        textoDistribuidora = new JTextField(20);
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.add(titulo);
        panelTitulo.add(textoTitulo);
        
        JPanel panelGenero = new JPanel();
        panelGenero.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelGenero.add(genero);
        panelGenero.add(textoGenero);
        
        JPanel panelNota = new JPanel();
        panelNota.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelNota.add(nota);
        panelNota.add(textoNota);
        
        JPanel panelDistribuidora = new JPanel();
        panelDistribuidora.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelDistribuidora.add(distribuidora);
        panelDistribuidora.add(textoDistribuidora);
        
        radioButtonPeli = new JRadioButton("Pelicula");
        radioButtonSerie = new JRadioButton("Serie");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radioButtonPeli);
        radioButtonGroup.add(radioButtonSerie);
        
        JPanel panelRadioBotones = new JPanel();
        panelRadioBotones.setLayout(new FlowLayout());
        panelRadioBotones.add(radioButtonPeli);
        panelRadioBotones.add(radioButtonSerie);
        
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        JButton botonObtener = new JButton("Obtener Datos");
        botonObtener.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
        	
        });
        JButton botonHabilitar = new JButton("Habilitar/Desabilitar");
        botonHabilitar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEditable(!editable);
				
			}
        	
        });
        panelInferior.add(botonObtener);
        panelInferior.add(botonHabilitar);
        
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panelTitulo);
        this.add(panelGenero);
        this.add(panelNota);
        this.add(panelDistribuidora);
        this.add(panelRadioBotones);
        this.add(panelInferior);
		
		
		setVisible(true);
	}

}
