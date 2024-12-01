package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VentanaSeleccionPersona extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tablaPersonas;
    private DefaultTableModel modeloTabla;

    private JButton botonLanzar;
    private JButton botonAgregar;
    private JButton botonEliminar;
    private JButton botonGuardar;

    public VentanaSeleccionPersona() {
        // Configuración de la ventana
        setTitle("Gestión de Personas y Hilos");
        setSize(700, 400);
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
                return false;
            }
        };

        tablaPersonas = new JTable(modeloTabla);
        tablaPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPersonas.setRowHeight(25);

        // Personalizar las celdas para cambiar el color de la fila según el estado
        tablaPersonas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String estado = (String) table.getValueAt(row, 2);
                if ("Revisada".equals(estado)) {
                    c.setBackground(new Color(144, 238, 144)); // Verde claro
                } else if ("Procesando...".equals(estado)) {
                    c.setBackground(new Color(255, 228, 181)); // Amarillo claro
                } else {
                    c.setBackground(Color.WHITE); // Blanco por defecto
                }
                if (isSelected) {
                    c.setBackground(new Color(173, 216, 230)); // Azul claro al seleccionar
                }
                return c;
            }
        });

        // Cargar datos desde un archivo CSV
        cargarPersonasDesdeCSV("personas.csv");

        // Scroll para la tabla
        JScrollPane scrollTabla = new JScrollPane(tablaPersonas);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botonLanzar = new JButton("Lanzar Revisión");
        botonAgregar = new JButton("Agregar Persona");
        botonEliminar = new JButton("Eliminar Persona");
        botonGuardar = new JButton("Guardar Cambios");

        panelBotones.add(botonLanzar);
        panelBotones.add(botonAgregar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonGuardar);

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

        // Acción de botón "Lanzar Revisión"
        botonLanzar.addActionListener(e -> {
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
        });

        // Acción de botón "Agregar Persona"
        botonAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
            if (nombre != null && apellido != null) {
                modeloTabla.addRow(new Object[]{nombre, apellido, "Pendiente"});
            }
        });

        // Acción de botón "Eliminar Persona"
        botonEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaPersonas.getSelectedRow();
            if (filaSeleccionada != -1) {
                modeloTabla.removeRow(filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una persona para eliminar.");
            }
        });

        // Acción de botón "Guardar Cambios"
        botonGuardar.addActionListener(e -> guardarPersonasEnCSV("personas.csv"));

        setVisible(true);
    }

    /**
     * Cargar las personas desde un archivo CSV usando Scanner
     */
    private void cargarPersonasDesdeCSV(String archivo) {
        try (Scanner scanner = new Scanner(new File(archivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
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
     * Guardar las personas en un archivo CSV usando PrintWriter
     */
    private void guardarPersonasEnCSV(String archivo) {
        try (PrintWriter writer = new PrintWriter(new File(archivo))) {
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String nombre = (String) modeloTabla.getValueAt(i, 0);
                String apellido = (String) modeloTabla.getValueAt(i, 1);
                String estado = (String) modeloTabla.getValueAt(i, 2);
                writer.println(nombre + ";" + apellido + ";" + estado);
            }
            JOptionPane.showMessageDialog(null, "Cambios guardados exitosamente.");
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
                salida.append("Procesando a " + nombre + " " + apellido + "...\n");
                Thread.sleep(3000); // Simula el tiempo de procesamiento
                salida.append(nombre + " " + apellido + " ha sido procesado.\n");
                modeloTabla.setValueAt("Revisada", fila, 2); // Cambiar estado a "Revisada"
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
