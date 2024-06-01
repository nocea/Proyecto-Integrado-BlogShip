package proyectoFinalJava.proyectoFinalJava.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyectoFinalJava.proyectoFinalJava.Modelos.Post;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;


@Repository
public interface PostRepositorio extends JpaRepository<Post, Long> {
	/**
	 * Método que busca un post por su id
	 * @param idPost
	 * @return
	 */
	@Query("SELECT p FROM Post p WHERE p.id = :idPost")
	public Post buscarPorId(Long idPost);
	/**
	 * Método que busca los post de un usuario en concreto.
	 * @param usuario
	 * @return
	 */
	@Query("SELECT p FROM Post p WHERE p.usuario = :usuario")
    List<Post> findPostsByUsuario(@Param("usuario") Usuario usuario);
}
