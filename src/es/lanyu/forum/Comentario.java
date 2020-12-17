package es.lanyu.forum;

import java.time.Instant;

public class Comentario implements Comparable<Comentario>{
	
	private final int LONGITUD_COMENTARIO = 20;
	
	private Usuario usuario;
	private String comentario;
	private Tema tema;
	private Instant fechaHora;
	
	public Instant getFechaHora() {
		return fechaHora;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Comentario(Usuario usuario, String comentario, Tema tema, Instant fechaHora) {
		super();
		this.usuario = usuario;
		this.comentario = comentario;
		this.tema = tema;
		this.fechaHora = fechaHora;
	}
	
	public Comentario(Usuario usuario, Tema tema, String comentario) {
		this(usuario, comentario, tema, Instant.now());
	}
	
	public String comentarioImprible() {
		String mensaje = "";
		if (comentario.length() > LONGITUD_COMENTARIO) {
			mensaje = comentario.substring(0, LONGITUD_COMENTARIO) + "...";
		} else {
			mensaje = comentario;
		}
		return mensaje;
	}
	
	@Override
	public String toString() {
		return usuario.getUser().toUpperCase()  + ": " + comentarioImprible() + " en Tema: " + tema.toString() + " a las "
				+ fechaHora;
	}

	@Override
	public int compareTo(Comentario arg0) {
		return arg0.getFechaHora().compareTo(getFechaHora());
	}
	
	
}
