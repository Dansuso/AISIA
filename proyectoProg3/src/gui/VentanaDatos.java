package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaDatos extends JFrame {
    private JTextField campoNombre;
    private JTextField campoApellidos;
    private JTextField campoEdad;
    private JTextField campoCorreo;
    private JTextField campoContraseña;

    public VentanaDatos(String nombre, String apellidos, String edad, String correo, String contraseña) {
        setTitle("Insertar Persona");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        // Crear los campos y los componentes
        JLabel etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField();
        JLabel etiquetaApellidos = new JLabel("Apellidos:");
        campoApellidos = new JTextField();
        JLabel etiquetaEdad = new JLabel("Edad:");
        campoEdad = new JTextField();
        JLabel etiquetaCorreo = new JLabel("Correo:");
        campoCorreo = new JTextField();
        JLabel etiquetaContra = new JLabel("Contraseña:");
        campoContraseña = new JTextField();

        // Crear los botones
        JButton botonAgregar = new JButton("Modificar");
        JButton botonCerrar = new JButton("Cerrar");

        add(etiquetaNombre);
        add(campoNombre);
        add(etiquetaApellidos);
        add(campoApellidos);
        add(etiquetaEdad);
        add(campoEdad);
        add(etiquetaCorreo);
        add(campoCorreo);
        add(etiquetaContra);
        add(campoContraseña);

        add(botonAgregar);
        add(botonCerrar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaDatos();
    }
}                          

	
	
  
                            
