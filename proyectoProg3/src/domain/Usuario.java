package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {
	
	protected int codigo;
	protected String username;
	protected LocalDate creacionCuenta;
	protected String pais;
	protected String foto;
	protected String contrasena;
	
	
	public Usuario(int codigo, String username, LocalDate creacionCuenta, String pais,
			 String foto, String contrasena) {
		super();
		this.codigo = codigo;
		this.username = username;
		this.creacionCuenta = creacionCuenta;
		this.pais = pais;
		this.foto = foto;
		this.contrasena = contrasena;
	}



	public int getCodigo() {
		return codigo;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}






	public LocalDate getCreacionCuenta() {
		return creacionCuenta;
	}


	public void setCreacionCuenta(LocalDate creacionCuenta) {
		this.creacionCuenta = creacionCuenta;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}




	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}




	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return codigo == other.codigo;
	}







	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", username=" + username + ", creacionCuenta=" + creacionCuenta + ", pais="
				+ pais + ", foto=" + foto + "]";
	}







	


	





	
	

}
