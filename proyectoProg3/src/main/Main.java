package main;

import javax.swing.SwingUtilities;

import gui.VentanaCarga;
import gui.VentanaInicio;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VentanaCarga());

	}

}
