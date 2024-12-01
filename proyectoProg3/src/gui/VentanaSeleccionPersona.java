package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSeleccionPersona extends JFrame {
    
    // Lista de personas
    private String[] personas = {"Ana", "Carlos", "Juan", "Laura", "Miguel"};
    
    // Componentes de la ventana
    private JComboBox<String> comboBox;
    private JButton lanzarBoton;
    private JTextArea outputArea;

    public VentanaSeleccionPersona() {
        // Configuración de la ventana
        setTitle("Seleccionar Persona y Lanzar Hilo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes
        comboBox = new JComboBox<>(personas);
        lanzarBoton = new JButton("Lanzar");
        outputArea = new JTextArea(5, 20);
        outputArea.setEditable(false);

        // Añadir los componentes a la ventana
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(comboBox, BorderLayout.NORTH);
        panel.add(lanzarBoton, BorderLayout.CENTER);
        panel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        add(panel);
        
        // Acción del botón
        lanzarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la persona seleccionada
                String personaSeleccionada = (String) comboBox.getSelectedItem();
                // Crear y lanzar el hilo
                Thread hiloPersona = new Thread(new PersonaTask(personaSeleccionada));
                hiloPersona.start();
            }
        });

        setVisible(true);
    }

    /**
     * Clase interna que representa la tarea que realiza el hilo
     */
    private class PersonaTask implements Runnable {
        private String persona;

        public PersonaTask(String persona) {
            this.persona = persona;
        }

        @Override
        public void run() {
            try {
                // Mostrar en la consola que se ha lanzado la persona
                System.out.println("Lanzando hilo para: " + persona);
                outputArea.append("Lanzando hilo para: " + persona + "\n");

                // Simular una acción del hilo (esperar 2 segundos)
                Thread.sleep(2000);

                // Mostrar en consola cuando termine el hilo
                System.out.println(persona + " ha sido procesada.");
                outputArea.append(persona + " ha sido procesada.\n");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new VentanaSeleccionPersona(); // Crear y mostrar la ventana
    }
}