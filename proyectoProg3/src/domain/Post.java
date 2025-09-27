package domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clas que representa un post que un usuario puede escribir. 
 */
public class Post {
	


	private int codigoPost;
	private String contenido;
	private LocalDateTime fechaPost;
	// Referencia al CREADOR del POST
	private Usuario creadorPost;
	//Referencia al post que responde. Será NULL si es un post nuevo y no una respuesta a uno.
	
	
	public Post(int codigoPost, String contenido, LocalDateTime fechaPost,Usuario creadorPost) {
		super();
		this.codigoPost = codigoPost;
		this.contenido = contenido;
		this.fechaPost = fechaPost;
		this.creadorPost = creadorPost;
	}
	
	public void setCodigoPost(int codigoPost) {
		this.codigoPost = codigoPost;
	}
	
	public Usuario getCreadorPost() {
		return creadorPost;
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
	public LocalDateTime getFechaPost() {
		return fechaPost;
	}
	public void setFechaPost(LocalDateTime fechaPost) {
		this.fechaPost = fechaPost;
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
				+ ", creadorPost=" + creadorPost + "]";
	}

	
	

}
