package proyectoFinalJava.proyectoFinalJava.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyectoFinalJava.proyectoFinalJava.Modelos.Comentario;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {

	@Query("SELECT c FROM Comentario c WHERE c.post.id = :postId")
    List<Comentario> findByPostId(@Param("postId") Long postId);
	@Query("SELECT c FROM Comentario c WHERE c.usuario = :usuario")
    List<Comentario> findComentariosByUsuario(@Param("usuario") Usuario usuario);

}
