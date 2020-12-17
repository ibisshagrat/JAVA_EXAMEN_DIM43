package com.forum.Application;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.lanyu.forum.Comentario;
import es.lanyu.forum.Tema;
import es.lanyu.forum.Usuario;
import es.lanyu.forum.UsuarioExterno;
import es.lanyu.forum.UsuarioImpl;
import es.lanyu.forum.test.DatosPrueba;

public class Main {

	static List<Comentario> comentarios = new ArrayList(DatosPrueba.COMENTARIOS);  //Se crea como list para no tener que parsear o copiar a otra lista a la hora de ordenar
	static final String MENSAJE_INSERCION_OK = "El comentario se añadió correctamente";
	static final String MENSAJE_INSERCION_ERROR = "El comentario no se pudo añadir";
	

	public static void main(String[] args) {

		Tema tema1 = new Tema("Tema 1");
		Usuario usuario3 = new UsuarioImpl("user3");
		comentar(usuario3, tema1, "Comentario añadido");
	
		Usuario usuarioExterno = new UsuarioExterno("usuarioExterno");
		comentar(usuarioExterno, tema1, "Soy un usuario externo");
		
		mostrarComentariosOrdenados();

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

}
