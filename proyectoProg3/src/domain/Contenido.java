package domain;

import java.util.Objects;

public abstract class Contenido {
	
	protected int contadorContenido;
	protected String titulo;
	protected String genero;
	protected double duracion;
	protected int calificacion;
	protected String distribuidora;
	protected int edadRecomendada;
	protected String premios;
	
	public Contenido(int contadorContenido, String titulo, String genero, double duracion, int calificacion,
			String distribuidora, int edadRecomendada, String premios) {
		super();
		this.contadorContenido = contadorContenido;
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.calificacion = calificacion;
		this.distribuidora = distribuidora;
		this.edadRecomendada = edadRecomendada;
		this.premios = premios;
	}

	public int getContadorContenido() {
		return contadorContenido;
	}

	public void setContadorContenido(int contadorContenido) {
		this.contadorContenido = contadorContenido;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}

	public int getEdadRecomendada() {
		return edadRecomendada;
	}

	public void setEdadRecomendada(int edadRecomendada) {
		this.edadRecomendada = edadRecomendada;
	}

	public String getPremios() {
		return premios;
	}

	public void setPremios(String premios) {
		this.premios = premios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(calificacion, contadorContenido, distribuidora, duracion, edadRecomendada, genero, premios,
				titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contenido other = (Contenido) obj;
		return calificacion == other.calificacion && contadorContenido == other.contadorContenido
				&& Objects.equals(distribuidora, other.distribuidora)
				&& Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& edadRecomendada == other.edadRecomendada && Objects.equals(genero, other.genero)
				&& Objects.equals(premios, other.premios) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Contenido [contadorContenido=" + contadorContenido + ", titulo=" + titulo + ", genero=" + genero
				+ ", duracion=" + duracion + ", calificacion=" + calificacion + ", distribuidora=" + distribuidora
				+ ", edadRecomendada=" + edadRecomendada + ", premios=" + premios + "]";
	}
	
	
	

}
