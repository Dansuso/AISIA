package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {
	
	protected int codigo;
	protected String username;
	protected String displayname;
	protected LocalDate creacionCuenta;
	protected String pais;
	protected int numSeguidores;
	protected int numSeguidos;
	protected String foto;
	protected String contrasena;
	protected Contenido favorito;
	
	
	public Usuario(int codigo, String username, String displayname, LocalDate creacionCuenta, String pais,
			int numSeguidores, int numSeguidos, String foto, String contrasena, Contenido favorito) {
		super();
		this.codigo = codigo;
		this.username = username;
		this.displayname = displayname;
		this.creacionCuenta = creacionCuenta;
		this.pais = pais;
		this.numSeguidores = numSeguidores;
		this.numSeguidos = numSeguidos;
		this.foto = foto;
		this.contrasena = contrasena;
		this.favorito = favorito;
	}


	public Contenido getFavorito() {
		return favorito;
	}


	public void setFavorito(Contenido favorito) {
		this.favorito = favorito;
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


	public String getDisplayname() {
		return displayname;
	}


	public void setDisplayname(String displayname) {
		this.displayname = displayname;
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


	public int getNumSeguidores() {
		return numSeguidores;
	}


	public void setNumSeguidores(int numSeguidores) {
		this.numSeguidores = numSeguidores;
	}


	public int getNumSeguidos() {
		return numSeguidos;
	}


	public void setNumSeguidos(int numSeguidos) {
		this.numSeguidos = numSeguidos;
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
		return Objects.hash(codigo, contrasena, creacionCuenta, displayname, favorito, foto, numSeguidores, numSeguidos,
				pais, username);
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
		return codigo == other.codigo && Objects.equals(contrasena, other.contrasena)
				&& Objects.equals(creacionCuenta, other.creacionCuenta)
				&& Objects.equals(displayname, other.displayname) && Objects.equals(favorito, other.favorito)
				&& Objects.equals(foto, other.foto) && numSeguidores == other.numSeguidores
				&& numSeguidos == other.numSeguidos && Objects.equals(pais, other.pais)
				&& Objects.equals(username, other.username);
	}


	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", username=" + username + ", displayname=" + displayname
				+ ", creacionCuenta=" + creacionCuenta + ", pais=" + pais + ", numSeguidores=" + numSeguidores
				+ ", numSeguidos=" + numSeguidos + ", foto=" + foto + ", contrasena=" + contrasena + ", contenido favorito"+ favorito +"]";
	}
	
	

}
