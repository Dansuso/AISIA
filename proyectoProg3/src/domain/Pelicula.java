package domain;

import java.util.Objects;

public class Pelicula extends Contenido {

	protected double facturacionTaquilla;

	public Pelicula(String tipo, int contadorContenido, String titulo, Genero genero, double duracion, int calificacion,
			String distribuidora, int edadRecomendada, String premios, String caratula, double facturacionTaquilla) {
		super(tipo, contadorContenido, titulo, genero, duracion, calificacion, distribuidora, edadRecomendada, premios,
				caratula);
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
		return "Pelicula [facturacionTaquilla=" + facturacionTaquilla + ", tipo=" + tipo + ", contadorContenido="
				+ contadorContenido + ", titulo=" + titulo + ", genero=" + genero + ", duracion=" + duracion
				+ ", calificacion=" + calificacion + ", distribuidora=" + distribuidora + ", edadRecomendada="
				+ edadRecomendada + ", premios=" + premios + ", caratula=" + caratula + "]";
	}

	
	
	

}
