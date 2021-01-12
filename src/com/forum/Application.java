package com.forum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.lanyu.forum.*;
import es.lanyu.forum.test.DatosPrueba;

import com.github.likes.*;

public class Application {
	// Se crea como list para no tener que parsear o copiar a otra lista a la hora de ordenar
	static List<Comentario> comentarios = new ArrayList<Comentario>(DatosPrueba.COMENTARIOS); 
	static final String MENSAJE_INSERCION_OK = "El comentario se annadio correctamente";
	static final String MENSAJE_INSERCION_ERROR = "El comentario no se pudo annadir";

	static List<Usuario> usuarios = new ArrayList<Usuario>();

	public static void main(String[] args) {

		Tema tema1 = new Tema("Tema 1");

		Usuario usuario3 = new UsuarioImpl("user3");
		usuarios.add(usuario3);
		usuarios.add(comentarios.get(0).getUsuario());
		usuarios.add(comentarios.get(1).getUsuario());

		comentar(usuario3, tema1, "Comentario annadido");

		Usuario usuarioExterno = new UsuarioExterno("usuarioExterno");
		comentar(usuarioExterno, tema1, "Soy un usuario externo");

		mostrarComentariosOrdenados();

		Likes.recomendar(comentarios.get(1), usuario3.toString());

		Comparator<Comentario> compararPorRecomendaciones = new Comparator<>() {

			@Override
			public int compare(Comentario c1, Comentario c2) {
				int resultado = ((Integer) Comentario.getLikes(c2)).compareTo((Integer) Comentario.getLikes(c1));
				if (resultado == 0) {
					resultado = c2.getFechaHora().compareTo(c1.getFechaHora());
				}
				return resultado;

			}
		};

		Likes.recomendar(comentarios.get(2), usuarios.get(2).getUserName());
		Likes.recomendar(comentarios.get(2), usuarios.get(2).getUserName());
		Likes.recomendar(comentarios.get(2), usuarios.get(2).getUserName());
		Likes.recomendar(comentarios.get(1), usuarios.get(2).getUserName());
		Likes.recomendar(comentarios.get(0), usuarios.get(2).getUserName());
		Likes.recomendar(comentarios.get(0), usuarios.get(2).getUserName());

		//Con boundaries
		LikeDatado<Comentario> likeDatado = new LikeDatado<Comentario>(comentarios.get(2), usuario3.getUserName());
		recomendarDatado(likeDatado);
		
		Collections.sort(comentarios, compararPorRecomendaciones);
		System.out.println();
		getRecomendaciones(comentarios);
		


	}

	public static boolean comentar(Usuario usuario, Tema tema, String comentario) {
		boolean resultado = true;

		try {
			comentarios.add(new Comentario(usuario, tema, comentario));
		} catch (Exception e) {
			resultado = false;
		}
		System.err.println((resultado) ? MENSAJE_INSERCION_OK : MENSAJE_INSERCION_ERROR);
		return resultado;
	}

	public static void mostrarComentariosOrdenados() {
		Collections.sort(comentarios);
		for (Comentario c : comentarios) {
			System.out.println(c.toString());
		}
	}

	public static <T> Usuario[] getUsuariosRecomendacion(T contenido) {
		int cantidadUsuarios = Comentario.getLikes(contenido);
		boolean hayUsuarios = (cantidadUsuarios != 0);
		Usuario[] usuariosComentario = null;
		if (hayUsuarios) {
			hayUsuarios = false; // Reinicia la varia para ver ver si los usuarios que hay, coinciden con nuestra lista
			usuariosComentario = new Usuario[cantidadUsuarios];
			String[] nombres = new String[cantidadUsuarios];
			for (int i = 0; i < cantidadUsuarios; i++) {
				nombres[i] = Likes.getLikesFor(contenido)[i]; // Recuperamos cada nombre
				for (Usuario usuario : usuarios) {
					if (usuario.getUserName().equals(nombres[i])) {
						usuariosComentario[i] = usuario;
						hayUsuarios = true;
						break;
					}
				}
			}
		}
		return (hayUsuarios) ? usuariosComentario : null;
	}

	public static <T extends Imprimible> void getRecomendaciones(Collection<T> recomendables) {
		String mensaje = "Recomendaciones (Likes):";
		System.out.println(mensaje);
		for (T c : recomendables) {
			System.out.println(textoRecomendacion(c));
		}
	}

	public static <T extends Imprimible> String textoRecomendacion(T contenido) {
		String nombres = "";
		try {
			Usuario[] users = getUsuariosRecomendacion(contenido);
			if (users.length != 0) {
				for (Usuario usuario : users) {
					nombres += usuario.getUserName() + ", ";
				}
				nombres = nombres.substring(0, nombres.length() - 2);
			}
		} catch (Throwable e) {

		}

		return (contenido.comentarioImprimible() + " " + Likes.getLikesFor(contenido).length + "* [" + nombres + "]");
	}
	
	public static <T extends Datable & DeUsuario> boolean recomendarDatado(LikeDatado<T> content) {
		return Likes.getLikes().add(content); 
	}
}
