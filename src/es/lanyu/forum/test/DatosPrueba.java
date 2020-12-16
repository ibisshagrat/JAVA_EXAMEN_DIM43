package es.lanyu.forum.test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import es.lanyu.forum.Comentario;
import es.lanyu.forum.Tema;
import es.lanyu.forum.Usuario;
import es.lanyu.forum.UsuarioImpl;

public class DatosPrueba {

    public final static List<Comentario> COMENTARIOS;
    
    static {
        Tema tema1 = new Tema("Tema 1");
        Tema tema2 = new Tema("Tema 2");
        
        Usuario usuario1 = new UsuarioImpl("user1");
        Usuario usuario2 = new UsuarioImpl("user2");
        
        COMENTARIOS = Arrays.asList(
                new Comentario(usuario1, "Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.",
                        tema1, Instant.parse("2000-01-01T00:00:00.00Z")),
                new Comentario(usuario2, "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat",
                        tema2, Instant.parse("3000-12-12T23:59:59.99Z"))
                );
    }
}
