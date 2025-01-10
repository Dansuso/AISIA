package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class CalendarioTransmisionHoras {

    static class Contenido {
        String titulo;
        String tipo; // Película o Serie
        int duracion; // En minutos
        String genero;

        Contenido(String tipo, String titulo, int duracion, String genero) {
            this.tipo = tipo;
            this.titulo = titulo;
            this.duracion = duracion;
            this.genero = genero;
        }

        @Override
        public String toString() {
            return titulo + " (" + tipo + ")";
        }
    }

    public static void main(String[] args) {
    	
        String archivo = "resources/data/contenido.csv";
        List<Contenido> contenidoTotal = new ArrayList<>();

        // Leer CSV y cargar el contenido
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine(); // Saltar la cabecera
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String tipo = datos[1].trim();
                String titulo = datos[2].trim();
                int duracion = tipo.equalsIgnoreCase("Pelicula") ? Integer.parseInt(datos[9].trim()) : 60; // 60 minutos para series
                String genero = datos[3].trim();

                Contenido contenido = new Contenido(tipo, titulo, duracion, genero);
                contenidoTotal.add(contenido);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Separar películas y series
        List<Contenido> peliculas = new ArrayList<>();
        List<Contenido> series = new ArrayList<>();
        for (Contenido contenido : contenidoTotal) {
            if (contenido.duracion == 120) {
                peliculas.add(contenido); // Películas duran 2 horas
            } else {
                series.add(contenido); // Series duran 1 hora
            }
        }

        // Barajar las listas para aleatoriedad
        Collections.shuffle(peliculas);
        Collections.shuffle(series);

        // Crear un calendario de 24 horas con formato: Horas (filas) x Días (columnas)
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        String[] horas = new String[24]; // Definir las horas desde las 00:00 hasta las 23:00
        for (int i = 0; i < 24; i++) {
            horas[i] = String.format("%02d:00-%02d:00", i, (i + 1) % 24);
        }

        String[][] calendario = new String[horas.length][dias.length];

        // Rellenar el calendario con contenido aleatorio
        Random random = new Random();
        for (int hora = 0; hora < horas.length; hora++) {
            for (int dia = 0; dia < dias.length; dia++) {
                if (!peliculas.isEmpty() && random.nextBoolean()) { // Asignar una película aleatoriamente
                    Contenido pelicula = peliculas.remove(0);
                    calendario[hora][dia] = pelicula.toString();
                } else {
                    // Asignar una serie, incluso repetida si se agotaron
                    if (series.isEmpty()) {
                        // Reusar series ya asignadas si la lista está vacía
                        series.addAll(contenidoTotal.stream().filter(c -> c.duracion == 60).toList());
                        Collections.shuffle(series);
                    }
                    Contenido serie = series.remove(0);
                    calendario[hora][dia] = serie.toString();
                }
            }
        }
        
        

        // Mostrar el calendario en una tabla Swing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calendario Diario de Transmisión (24 Horas)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);

            String[] columnas = new String[dias.length + 1];
            columnas[0] = "Horario"; // Primera columna para las horas
            System.arraycopy(dias, 0, columnas, 1, dias.length);

            DefaultTableModel model = new DefaultTableModel(columnas, 0) {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Hacer la tabla no editable
                }
            };
            JTable tabla = new JTable(model);

            for (int i = 0; i < horas.length; i++) {
                Object[] fila = new Object[dias.length + 1];
                fila[0] = horas[i]; // Columna de horarios
                System.arraycopy(calendario[i], 0, fila, 1, dias.length);
                model.addRow(fila);
            }

            // Render personalizado
            tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    LocalDateTime now = LocalDateTime.now();
                    int currentHour = now.getHour();
                    DayOfWeek currentDay = now.getDayOfWeek();
                    int currentDayIndex = currentDay.getValue() - 1; // DayOfWeek value is 1 for Monday to 7 for Sunday

                    // Highlight the current hour and day
                    if (row == currentHour && column - 1 == currentDayIndex) {
                        c.setBackground(Color.RED); // Rojo para la hora y día actuales
                    } else {
                        c.setBackground(Color.WHITE); // Blanco por defecto
                    }

                    return c;
                }
            });

            frame.add(new JScrollPane(tabla));
            frame.setVisible(true);
        });
    }
}


