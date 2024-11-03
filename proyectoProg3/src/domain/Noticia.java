package domain;

public class Noticia {
	private String titulo;
	private String resumen; // Este ATRIBUTO puede que se quite  en el futuruo. Decision no tomada.
	
	private String url;
	private String fuente;
	private String tipo;
	
	
	public Noticia(String titulo, String resumen, String url, String fuente) {
		super();
		this.titulo = titulo;
		this.resumen = resumen;
		this.url = url;
		this.fuente = fuente;
		
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	@Override
	public String toString() {
		return "Noticia [titulo=" + titulo + ", resumen=" + resumen + ", url=" + url + ", fuente=" + fuente + ", tipo="
				+ tipo + "]";
	}
	
	
	

}
