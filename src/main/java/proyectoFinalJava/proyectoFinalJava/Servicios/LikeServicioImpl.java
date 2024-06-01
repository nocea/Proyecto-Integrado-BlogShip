package proyectoFinalJava.proyectoFinalJava.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectoFinalJava.proyectoFinalJava.Modelos.Post;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;
import proyectoFinalJava.proyectoFinalJava.Repositorio.LikeRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.PostRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.UsuarioRepositorio;

@Service
public class LikeServicioImpl implements LikeServicio{
	 @Autowired
	    private LikeRepositorio likeRepositorio;

	    @Autowired
	    private UsuarioRepositorio usuarioRepositorio;

	    @Autowired
	    private PostRepositorio postRepositorio;
	@Override
	public boolean usuarioHaDadoLike(String username, Long postId) {
		Usuario usuario = usuarioRepositorio.findFirstByEmailUsuario(username);//busco el usuario
        if (usuario == null) {
            return false; // usuario no econtrado
        }

        Post post = postRepositorio.findById(postId).orElse(null);//busco el post
        if (post == null) {
            return false; // post no encontrado
        }

        return likeRepositorio.existsByUsuarioAndPost(usuario, post);

	}

}
