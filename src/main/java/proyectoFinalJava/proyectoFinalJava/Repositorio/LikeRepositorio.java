package proyectoFinalJava.proyectoFinalJava.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyectoFinalJava.proyectoFinalJava.Modelos.Like;
import proyectoFinalJava.proyectoFinalJava.Modelos.Post;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;

@Repository
public interface LikeRepositorio extends JpaRepository<Like, Long>{
	/**
	 * Método para buscar los likes que ha dado un usuario a un post
	 * @param usuario
	 * @param post
	 * @return
	 */
	Like findByUsuarioAndPost(Usuario usuario, Post post);
	/**
	 * Método para comprobar qque existe un like en un post de un usuario
	 * @param usuario
	 * @param post
	 * @return
	 */
	boolean existsByUsuarioAndPost(Usuario usuario, Post post);
	/**
	 * Método para guardar los likes que ha dado un usuario
	 * @param usuario
	 * @return
	 */
	@Query("SELECT l FROM Like l WHERE l.usuario = :usuario")
    List<Like> findLikesByUsuario(@Param("usuario") Usuario usuario);
}
