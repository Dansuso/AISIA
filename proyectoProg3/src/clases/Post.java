package clases;

import java.time.LocalDate;
import java.util.Objects;

public class Post {
	
	protected int codigoPost;
	protected String contenido;
	protected LocalDate fechaPost;
	protected int numLikes;
	protected int numRepost;
	protected int numRespuestas;
	protected Usuario creadorPost;
	
	public Post(int codigoPost, String contenido, LocalDate fechaPost, int numLikes, int numRepost, int numRespuestas,
			Usuario creadorPost) {
		super();
		this.codigoPost = codigoPost;
		this.contenido = contenido;
		this.fechaPost = fechaPost;
		this.numLikes = numLikes;
		this.numRepost = numRepost;
		this.numRespuestas = numRespuestas;
		this.creadorPost = creadorPost;
	}
	
	public int getCodigoPost() {
		return codigoPost;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public LocalDate getFechaPost() {
		return fechaPost;
	}
	public void setFechaPost(LocalDate fechaPost) {
		this.fechaPost = fechaPost;
	}
	public int getNumLikes() {
		return numLikes;
	}
	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	public int getNumRepost() {
		return numRepost;
	}
	public void setNumRepost(int numRepost) {
		this.numRepost = numRepost;
	}
	public int getNumRespuestas() {
		return numRespuestas;
	}
	public void setNumRespuestas(int numRespuestas) {
		this.numRespuestas = numRespuestas;
	}
	public Usuario getCreadorPost() {
		return creadorPost;
	}
	public void setCreadorPost(Usuario creadorPost) {
		this.creadorPost = creadorPost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoPost, contenido, creadorPost, fechaPost, numLikes, numRepost, numRespuestas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return codigoPost == other.codigoPost && Objects.equals(contenido, other.contenido)
				&& Objects.equals(creadorPost, other.creadorPost) && Objects.equals(fechaPost, other.fechaPost)
				&& numLikes == other.numLikes && numRepost == other.numRepost && numRespuestas == other.numRespuestas;
	}

	@Override
	public String toString() {
		return "Post [codigoPost=" + codigoPost + ", contenido=" + contenido + ", fechaPost=" + fechaPost
				+ ", numLikes=" + numLikes + ", numRepost=" + numRepost + ", numRespuestas=" + numRespuestas
				+ ", creadorPost=" + creadorPost + "]";
	}
	

}
