package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VentanaSeleccionPersona extends JFrame {

    private static final long serialVersionUID = 1L;

    // Tabla para mostrar los datos
    private JTable tablaPersonas;
    private DefaultTableModel modeloTabla;

    // Botones
    private JButton botonLanzar;
    private JButton botonAgregar;
    private JButton botonEliminar;

    public VentanaSeleccionPersona() {
        // Configuración de la ventana
        setTitle("Gestión de Personas y Hilos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Crear la tabla con columnas y modelo
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Apellido", "Estado"}, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacemos que las celdas no sean editables
            }
        };
        tablaPersonas = new JTable(modeloTabla);
        tablaPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPersonas.setRowHeight(25);

        // Cargar datos desde el archivo CSV
        cargarPersonasDesdeCSV("resources/data/personas.csv");

        // Scroll para la tabla
        JScrollPane scrollTabla = new JScrollPane(tablaPersonas);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botonLanzar = new JButton("Lanzar Tarea");
        botonAgregar = new JButton("Agregar Persona");
        botonEliminar = new JButton("Eliminar Persona");

        panelBotones.add(botonLanzar);
        panelBotones.add(botonAgregar);
        panelBotones.add(botonEliminar);

        // Panel de salida
        JTextArea areaSalida = new JTextArea(5, 40);
        areaSalida.setEditable(false);
        areaSalida.setBorder(BorderFactory.createTitledBorder("Salida de Procesos"));
        JScrollPane scrollSalida = new JScrollPane(areaSalida);

        // Añadir componentes al panel principal
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        panelPrincipal.add(scrollSalida, BorderLayout.EAST);

        add(panelPrincipal);

        // Acción de botón "Lanzar Tarea"
        botonLanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPersonas.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una persona.");
                } else {
                    String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
                    String apellido = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                    modeloTabla.setValueAt("Procesando...", filaSeleccionada, 2);

                    // Crear y lanzar el hilo
                    new Thread(new PersonaTask(nombre, apellido, filaSeleccionada, areaSalida)).start();
                }
            }
        });

        // Acción de botón "Agregar Persona" (Simulación)
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
                String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
                if (nombre != null && apellido != null) {
                    modeloTabla.addRow(new Object[]{nombre, apellido, "Pendiente"});
                }
            }
        });

        // Acción de botón "Eliminar Persona"
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPersonas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    modeloTabla.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una persona para eliminar.");
                }
            }
        });

        setVisible(true);
    }

    /**
     * Cargar las personas desde un archivo CSV
     */
    private void cargarPersonasDesdeCSV(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 2) {
                    modeloTabla.addRow(new Object[]{datos[0], datos[1], "Pendiente"});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clase interna que representa la tarea de procesar a una persona
     */
    private class PersonaTask implements Runnable {
        private String nombre;
        private String apellido;
        private int fila;
        private JTextArea salida;

        public PersonaTask(String nombre, String apellido, int fila, JTextArea salida) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.fila = fila;
            this.salida = salida;
        }

        @Override
        public void run() {
            try {
                salida.append("Revisando a " + nombre + " " + apellido + "...\n");
                Thread.sleep(3000); // Simula el tiempo de procesamiento
                salida.append(nombre + " " + apellido + " ha sido revisada.\n");
                modeloTabla.setValueAt("Completado", fila, 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
                modeloTabla.setValueAt("Error", fila, 2);
            }
        }
    }

    public static void main(String[] args) {
        new VentanaSeleccionPersona();
    }
}