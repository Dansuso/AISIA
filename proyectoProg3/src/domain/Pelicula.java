package domain;

import java.time.LocalDate;

public class Pelicula extends Contenido {

	protected boolean oscar;
	protected int duracion;

	public Pelicula(int id,TIPO tipo, String titulo, Genero genero, int duracion, int calificacion,
			String distribuidora, int edadRecomendada, boolean oscar, String caratula,LocalDate fecha) {
		super(id,tipo, titulo, genero, calificacion, distribuidora, edadRecomendada,
		caratula,fecha);
		this.oscar = oscar;
		this.duracion = duracion;
	}

	public boolean tieneOscar() {
		return oscar;
	}

	public void setOscar(boolean oscar) {
		this.oscar = oscar;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	








	
	
	

}
