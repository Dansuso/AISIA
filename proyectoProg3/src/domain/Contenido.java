package domain;

import java.util.Objects;

public abstract class Contenido {
	
	protected String tipo;
	protected int contadorContenido;
	protected String titulo;
	protected String genero;
	protected double duracion;
	protected int calificacion;
	protected String distribuidora;
	protected int edadRecomendada;
	protected String premios;
	protected String caratula;
	
	public Contenido(String tipo, int contadorContenido, String titulo, String genero, double duracion, int calificacion,
			String distribuidora, int edadRecomendada, String premios, String caratula) {
		super();
		this.tipo = tipo;
		this.contadorContenido = contadorContenido;
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.calificacion = calificacion;
		this.distribuidora = distribuidora;
		this.edadRecomendada = edadRecomendada;
		this.premios = premios;
		this.caratula = caratula;
	}
	
	
	public String getCaratula() {
		return caratula;
	}


	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}


	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		return Objects.hash(calificacion, caratula, contadorContenido, distribuidora, duracion, edadRecomendada, genero,
				premios, tipo, titulo);
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
				&& contadorContenido == other.contadorContenido && Objects.equals(distribuidora, other.distribuidora)
				&& Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& edadRecomendada == other.edadRecomendada && Objects.equals(genero, other.genero)
				&& Objects.equals(premios, other.premios) && Objects.equals(tipo, other.tipo)
				&& Objects.equals(titulo, other.titulo);
	}


	@Override
	public String toString() {
		return "Contenido [tipo=" + tipo + ", contadorContenido=" + contadorContenido + ", titulo=" + titulo
				+ ", genero=" + genero + ", duracion=" + duracion + ", calificacion=" + calificacion
				+ ", distribuidora=" + distribuidora + ", edadRecomendada=" + edadRecomendada + ", premios=" + premios
				+ ", caratula=" + caratula + "]";
	}

	
	
	
	

}
