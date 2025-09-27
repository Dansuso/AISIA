package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class VentanaCarga extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int cont = 0; // Contador del progreso
    protected JProgressBar jProgressBar; // Barra de progreso

    public VentanaCarga() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cargar Pantalla");
        setSize(240, 55);

        // Centrar la ventana
        this.setLocationRelativeTo(null);

        // Inicializamos el JProgressBar y lo agregamos a la ventana
        jProgressBar = new JProgressBar(0, 100); // Barra de progreso de 0 a 100
        jProgressBar.setValue(cont); // Valor inicial
        jProgressBar.setStringPainted(true); // Mostrar el valor como texto

        // Añadir la barra de progreso al JFrame
        add(jProgressBar, BorderLayout.NORTH);

        // Crear y lanzar el hilo
        Thread hiloCarga = new Thread(new Runnable() {
            @Override
            public void run() {
                while (cont <= 100) {
                    try {
                        Thread.sleep(50); // Espera 50 ms entre incrementos
                        
                        jProgressBar.setValue(cont); // Actualizar barra

                        // Mensajes en consola según el progreso
                        if (cont == 0) {
                            System.out.println("Programa iniciado...");
                            cont++;
                        } else if (cont == 50) {
                            System.out.println("En proceso...");
                            cont++;
                        } else if (cont == 100) {
                            System.out.println("Carga completa. Programa iniciado.");
                            cont++;
                        }else {
                        	cont++;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Una vez completado, mostrar una nueva ventana
                abrirVentanaInicio();
            }
        });

        hiloCarga.start(); // Iniciar el hilo
        setVisible(true); // Mostrar la ventana
    }

    /**
     * Método para abrir la ventana inicial una vez completada la carga.
     */
    private void abrirVentanaInicio() {
        // Simular la transición a una nueva ventana
        System.out.println("Transición a la ventana de inicio...");
        dispose(); // Cerrar la ventana actual
        new VentanaInicio(); // Crear la nueva ventana (suponiendo que existe)
    }

    public static void main(String[] args) {
        new VentanaCarga(); // Crear y mostrar la ventana de carga
    }
}