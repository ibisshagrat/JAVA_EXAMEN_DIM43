package com.forum.Application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.lanyu.forum.Comentario;
import es.lanyu.forum.Recomendacion;
import es.lanyu.forum.Tema;
import es.lanyu.forum.Usuario;
import es.lanyu.forum.UsuarioExterno;
import es.lanyu.forum.UsuarioImpl;
import es.lanyu.forum.test.DatosPrueba;

public class Main {

	static List<Comentario> comentarios = new ArrayList<Comentario>(DatosPrueba.COMENTARIOS);  //Se crea como list para no tener que parsear o copiar a otra lista a la hora de ordenar
	static final String MENSAJE_INSERCION_OK = "El comentario se añadió correctamente";
	static final String MENSAJE_INSERCION_ERROR = "El comentario no se pudo añadir";

	static List<Usuario> usuarios = new ArrayList();

	public static void main(String[] args) {

		Tema tema1 = new Tema("Tema 1");
		
		Usuario usuario3 = new UsuarioImpl("user3");
		usuarios.add(usuario3);
		usuarios.add(comentarios.get(0).getUsuario());
		usuarios.add(comentarios.get(1).getUsuario());
		
		comentar(usuario3, tema1, "Comentario añadido");
	
		Usuario usuarioExterno = new UsuarioExterno("usuarioExterno");
		comentar(usuarioExterno, tema1, "Soy un usuario externo");
		
		mostrarComentariosOrdenados();
		
		Recomendacion.recomendar(comentarios.get(0), usuario3.toString());
		Recomendacion.recomendar(comentarios.get(0), usuarios.get(1).getUser());

		getUsuariosRecomendacion(comentarios.get(0));
		
	}
	
	
	public static boolean comentar(Usuario usuario, Tema tema, String comentario) {
		boolean resultado = true;

		try {
			comentarios.add(new Comentario(usuario, tema, comentario));
		} catch (Exception e) {
			resultado = false;
			}
		System.err.println((resultado)? MENSAJE_INSERCION_OK:MENSAJE_INSERCION_ERROR);
		return resultado;
	}
	
	public static void mostrarComentariosOrdenados() {
		Collections.sort(comentarios);
		for (Comentario c : comentarios) {
			System.out.println(c.toString());
			}
	}
	
	public static Usuario[] getUsuariosRecomendacion(Comentario comentario) {
		int cantidadUsuarios = Recomendacion.getLikesFor(comentario).length;
		boolean hayUsuarios = (cantidadUsuarios != 0);
		Usuario[] usuariosComentario = null;
		if (hayUsuarios) {
			hayUsuarios = false;				//Reinicia la varia para ver ver si los usuarios que hay, coinciden con nuestra lista
			usuariosComentario =  new Usuario[cantidadUsuarios];
			String[] nombres = new String[cantidadUsuarios];
			for (int i = 0; i < cantidadUsuarios; i++) {
				nombres[i] = Recomendacion.getLikesFor(comentario)[i];	//Recuperamos cada nombre
				for (Usuario usuario : usuarios) {
					if (usuario.getUser().equals(nombres[i])) {
						usuariosComentario[i] = usuario;
						System.out.println(usuariosComentario[i].getUser());
						hayUsuarios = true;
						break;
					}
				}
			}
		}
		return (hayUsuarios)? usuariosComentario:null;
	}
}
