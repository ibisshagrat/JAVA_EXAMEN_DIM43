# Examen Fundamentos Java 2 (17-12-2020)

Vamos a crear algunas partes del código de una aplicación parecida a un foro de comentarios.

Para poder realizar el examen es necesario usar el proyecto alojado en este repositorio. Haga un fork de este proyecto, póngalo con **visibilidad privado** y compruebe que está el repositorio correctamente en local. No siga hasta que haya puesto el repositorio en privado.

En el código que de descarga tiene la clase `es.lanyu.forum.test.DatosPrueba` con una lista `List<Comentario> COMENTARIOS` para ahorrar tiempo a la hora de introducir datos de prueba.

**Los puntos evaluables, y ponderados con el valor entre paréntesis, son**:

1. (1 punto) Todo el código que implemente pertenecerá al paquete `es.lanyu.forum` excepto el que contenga el `main` que estará en la clase `com.forum.Application`.
1. (2 puntos) Crear las clases necesarias para que el código de `DatosPrueba` funcione correctamente (`Comentario`, `Tema` y `Usuario`). `Comentario` debe tener también un constructor por defecto.
1. (2 puntos) Compruebe que todo funciona bien imprimiéndo los comentarios por consola. Haga el/los `toString()` necesarios para conseguir la siguiente salida (nótese que el nombre de usuario está en mayúsculas y el texto del comentario se recorta a un tamaño de 20 caracteres).
    > `USER1: Lorem ipsum dolor si... en Tema: Tema 1 a las 2020-12-16T10:00:00Z`  
`USER2: Duis autem vel eum i... en Tema: Tema 2 a las 2020-12-16T11:00:00Z`
   > *NOTA: Las clases creadas anteriormente son implementaciones para pruebas como esta pero se necesitará mejorar el diseño a lo largo del examen para admitir en un futuro librerias para traer recomendaciones de un servidor externo y los usuarios desde otro servidor.*
1. (2 puntos) La aplicación permitirá añadir comentarios a un tema:  
    * Cree un nuevo usuario con nombre `user3`.
    * (1 punto) Implemente un método estático con nombre `comentar` que admita un usuario, un tema y un texto. Comente el "Tema 1" (**recuperar** tema del primer comentario de prueba, no un nuevo tema con nombre repetido) usando en nuevo usuario y con el texto "Comentario añadido".
    * (1 punto) Se debe establecer la hora actual cuando se hace el comentario.
    * Debería generarle la siguiente salida:
    > `USER3: Comentario añadido en Tema: Tema 1 a las 2020-12-17THO:RA:AC.TUALZ`  
  (es decir con la hora en el momento que se ejecute)
    >*NOTA: Si el comentario no supera los 20 caracteres no se añade puntos suspensivos, sólo si se recorta el texto.*
1. (2 puntos) Éste método `comentar` devolverá `true` si el comentario se añade correctamente a la lista de comentarios de la aplicación y en cualquier otro caso devolverá `false`.
1. (1 punto) Al invocar el método saldrá por consola el resultado correspondiente (para diferenciarlo del resto sáquelo por la salida de error *-color rojo en Eclipse-* en vez de la salida estándar):
    * `"El comentario se añadió correctamente"`
    * `"El comentario no se pudo añadir"`
1. (3 puntos) Ahora aprovecharemos un usuario externo de la clase `com.github.auth.User`. Realice los cambios necesarios para instanciar un usuario de esa clase (con nombre de usuario "usuarioExterno") y comentar el "Tema 1" con el texto "Soy un usuario externo". (OJO, no está permitido heredar `User` en la clase `Usuario` actual, la clase `Usuario` actual en realidad tendría ya una herencia que no se detalla en el examen). El resultado debería ser:
    > `USUARIOEXTERNO: Soy un usuario exter... en Tema: Tema 1 a las 2020-12-16T10:32:48.319Z`
1. (2 puntos) Se quiere que el **orden natural** de los comentarios sea por fecha descendente. Deberían quedar ordenados los nuevos comentarios entre los de prueba (primero el del año 3000, luego el resto y cerrando el del 2000). Implemente lo necesario y ejecute una prueba que muestre por pantalla el resultado.
1. (8 puntos) Ahora se añadirá un sistema de recomendación de comentarios:
    * Para recomendar un comentario se usará el método `package com.github.likes.Likes.recomendar`. Es un método genérico que acepta un tipo `T` y un `String` donde se puede asociar el like con un usuario. En nuestro caso se usará el nombre de usuario.
    * (1 punto) Añada una recomendación del `user3` al primer comentario de la aplicación y sendas recomendaciones de `user3` y `usuarioExterno` al segundo comentario.
    >*NOTA: Para conseguir las recomendaciones* (`Like`) *ya hechas se usará el método `getLikesFor(comentario)` de la misma clase. Éste método devolverá un array con los `String`s que correpondan a cuando se hizo la recomendación del comentario pasado como argumento.*
    * (4 puntos) Debe transformar el retorno de los usuarios en formato `String` a los correspondientes objetos del tipo `Usuario`. Hágalo con un método funcional que reciba todos los usuarios de todos los comentarios de la aplicación y que devuelva el usuario que corresponde con el nombre de usuario pasado `mapStringToUsuario(users, username)`. Si no se encuentra coincidencia devolverá `null`.
    * (3 puntos) Imprima los textos de las recomendaciones de los comentarios 1 y 2. Para generar el texto de cada recomendación (líneas después del encabezado) haga un método genérico llamado `textoRecomendacion(T contenido)` que debe quedar así:
   > `Recomendaciones (Likes):`  
`Duis autem vel eum i... 1* [USER3]`  
`Soy un usuario exter... 2* [USER3, USUARIOEXTERNO]`
1. (3 puntos) Haga un comparador que ordene los comentarios por sus recomendaciones:
    * por número de estrellas (de mayor a menor) y después
    * por momento (de mas antigua a mas reciente)  
  Para ello recomiende con dos usuarios distintos el tercer comentario. El resultado debe quedar:
    >`Ordenado por recomendación (likes):`  
`Comentario añadido 2* [USER2, USUARIOEXTERNO]`  
`Soy un usuario exter... 2* [USER3, USUARIOEXTERNO]`  
`Duis autem vel eum i... 1* [USER3]`  
`Lorem ipsum dolor si... 0* []`
1. (4 puntos) En un futuro las recomendaciones no sólo serán de `Comentario` sino que serán de usuarios (tendrán el método `getUsuario`) y en un momento dado (`getFecha`). Como son aspectos que no tienen una relación directa no se quieren unir en un único tipo. Por tanto, refactorice el código para reflejar esto (pista: hará falta crearse un tipo llamado `LikeDatado` por ejemplo).
    * Añada un `LikeDatado` a `Likes.getLikes()` que admita como tipo variable sólo tipos que complan lo dicho. Utilice como datos para el nuevo like el `comentario3` y `user3`.
     >`Usando boundaries:`  
`Comentario añadido 3* [USER2, USUARIOEXTERNO, USER3]`  
`Soy un usuario exter... 2* [USER3, USUARIOEXTERNO]`  
`Duis autem vel eum i... 1* [USER3]`  
`Lorem ipsum dolor si... 0* []`

<hr>

## Entrega de examen
Acuérdese de hacer un **commit con el mensaje `Entrega examen`** con el último estado a guardar. Éste commit debe tener los ficheros necesarios sólo, añadir binarios por ejemplo será considerado una mala práctica penalizable. Compruebe que su commit está subido a su fork.