package domain;

import java.util.Objects;

public class Serie extends Contenido {
	
	protected int numTemporadas;
	protected int numCapitulos;
	
	public Serie(String tipo, int contadorContenido, String titulo, Genero genero, double duracion, int calificacion,
			String distribuidora, int edadRecomendada, String premios, String caratula, int numTemporadas,
			int numCapitulos) {
		super(tipo, contadorContenido, titulo, genero, duracion, calificacion, distribuidora, edadRecomendada, premios,
				caratula);
		this.numTemporadas = numTemporadas;
		this.numCapitulos = numCapitulos;
	}
	public int getNumTemporadas() {
		return numTemporadas;
	}
	public void setNumTemporadas(int numTemporadas) {
		this.numTemporadas = numTemporadas;
	}
	public int getNumCapitulos() {
		return numCapitulos;
	}
	public void setNumCapitulos(int numCapitulos) {
		this.numCapitulos = numCapitulos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numCapitulos, numTemporadas);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		return numCapitulos == other.numCapitulos && numTemporadas == other.numTemporadas;
	}
	
	@Override
	public String toString() {
		return "Serie [numTemporadas=" + numTemporadas + ", numCapitulos=" + numCapitulos + "]";
	}

	
	

}
