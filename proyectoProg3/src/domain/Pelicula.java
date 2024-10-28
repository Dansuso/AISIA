package domain;

import java.util.Objects;

public class Pelicula extends Contenido {

	protected double facturacionTaquilla;


	public Pelicula(String tipo, int contadorContenido, String titulo, String genero, double duracion, int calificacion,
			String distribuidora, int edadRecomendada, String premios, double facturacionTaquilla) {
		super(tipo, contadorContenido, titulo, genero, duracion, calificacion, distribuidora, edadRecomendada, premios);
		this.facturacionTaquilla = facturacionTaquilla;
	}

	public double getFacturacionTaquilla() {
		return facturacionTaquilla;
	}

	public void setFacturacionTaquilla(double facturacionTaquilla) {
		this.facturacionTaquilla = facturacionTaquilla;
	}

	@Override
	public String toString() {
		return "Pelicula [facturacionTaquilla=" + facturacionTaquilla + ", contadorContenido=" + contadorContenido
				+ ", titulo=" + titulo + ", genero=" + genero + ", duracion=" + duracion + ", calificacion="
				+ calificacion + ", distribuidora=" + distribuidora + ", edadRecomendada=" + edadRecomendada
				+ ", premios=" + premios + "]";
	}
	
	

}
