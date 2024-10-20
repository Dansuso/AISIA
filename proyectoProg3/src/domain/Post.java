package domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clas que representa un post que un usuario puede escribir. 
 */
public class Post {
	
	
	private int codigoPost;
	private String contenido;
	private LocalDate fechaPost;
	private int numLikes;
	private int numRepost;
	private int numRespuestas;
	// Referencia al CREADOR del POST
	private Usuario creadorPost;
	//Referencia al post que responde. Será NULL si es un post nuevo y no una respuesta a uno.
	private Post postRespuesta;
	
	
	public Post(int codigoPost, String contenido, LocalDate fechaPost, int numLikes, int numRepost, int numRespuestas,
			Usuario creadorPost,Post postRespuesta) {
		super();
		this.codigoPost = codigoPost;
		this.contenido = contenido;
		this.fechaPost = fechaPost;
		this.numLikes = numLikes;
		this.numRepost = numRepost;
		this.numRespuestas = numRespuestas;
		this.creadorPost = creadorPost;
		this.postRespuesta = postRespuesta;
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
	public Post getPostRespuesta() {
		return postRespuesta;
	}

	public void setPostRespuesta(Post postRespuesta) {
		this.postRespuesta = postRespuesta;
	}

	public void setCreadorPost(Usuario creadorPost) {
		this.creadorPost = creadorPost;
	}

	
	//TODO : Pensar en el HashCode.
	//Un POST es IGUAL a otro si su identificador es el mismo.
	@Override
	public int hashCode() {
		return Objects.hash(codigoPost);
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
		return codigoPost == other.codigoPost;
	}

	@Override
	public String toString() {
		return "Post [codigoPost=" + codigoPost + ", contenido=" + contenido + ", fechaPost=" + fechaPost
				+ ", numLikes=" + numLikes + ", numRepost=" + numRepost + ", numRespuestas=" + numRespuestas
				+ ", creadorPost=" + creadorPost + "]";
	}
	

}
