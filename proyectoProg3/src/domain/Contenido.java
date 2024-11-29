package domain;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Contenido {
	
	public enum TIPO{
		PELICULA,SERIE;
	}
	
	public enum Genero {
		ACCION, COMEDIA, DRAMA, TERROR, ROMANCE, AVENTURA, FANTASIA
	}
	protected int id;
	protected TIPO tipo;
	protected String titulo;
	protected Genero genero;
	protected int calificacion;
	protected String distribuidora;
	protected int edadRecomendada;
	protected String caratula;
	protected LocalDate fecha;
	
	public Contenido(int id,TIPO tipo, String titulo, Genero genero, int calificacion,
			String distribuidora, int edadRecomendada, String caratula,LocalDate fecha) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.titulo = titulo;
		this.genero = genero;
		this.calificacion = calificacion;
		this.distribuidora = distribuidora;
		this.edadRecomendada = edadRecomendada;
		this.caratula = caratula;
		this.fecha = fecha;
	}
	
	


	public int getId() {
		return id;
	}



	public String getCaratula() {
		return caratula;
	}


	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}


	public TIPO getTipo() {
		return tipo;
	}



	public void setTipo(TIPO tipo) {
		this.tipo = tipo;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
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




	@Override
	public int hashCode() {
		return Objects.hash(calificacion, caratula, distribuidora, edadRecomendada, genero, id, tipo, titulo);
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
		return calificacion == other.calificacion && Objects.equals(caratula, other.caratula)
				&& Objects.equals(distribuidora, other.distribuidora) && edadRecomendada == other.edadRecomendada
				&& genero == other.genero && id == other.id && tipo == other.tipo
				&& Objects.equals(titulo, other.titulo);
	}




	@Override
	public String toString() {
		return "Contenido [id=" + id + ", tipo=" + tipo + ", titulo=" + titulo + ", genero=" + genero
				+ ", calificacion=" + calificacion + ", distribuidora=" + distribuidora + ", edadRecomendada="
				+ edadRecomendada + ", caratula=" + caratula + "]";
	}




	
	
	
	

}
