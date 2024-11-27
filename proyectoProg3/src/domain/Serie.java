package domain;

import java.util.Objects;

public class Serie extends Contenido {
	
	protected int numTemporadas;
	protected int numCapitulos;
	protected boolean emmy;
	

	public boolean isEmmy() {
		return emmy;
	}
	public void setEmmy(boolean emmy) {
		this.emmy = emmy;
	}
	public Serie(int id, TIPO tipo, String titulo, Genero genero, int calificacion, String distribuidora,
			int edadRecomendada, String caratula, int numTemporadas, int numCapitulos, boolean emmy) {
		super(id, tipo, titulo, genero, calificacion, distribuidora, edadRecomendada, caratula);
		this.numTemporadas = numTemporadas;
		this.numCapitulos = numCapitulos;
		this.emmy = emmy;
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
		result = prime * result + Objects.hash(emmy, numCapitulos, numTemporadas);
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
		return emmy == other.emmy && numCapitulos == other.numCapitulos && numTemporadas == other.numTemporadas;
	}
	@Override
	public String toString() {
		return "Serie [numTemporadas=" + numTemporadas + ", numCapitulos=" + numCapitulos + ", emmy=" + emmy + "]";
	}
	

	
	

	
	

}
