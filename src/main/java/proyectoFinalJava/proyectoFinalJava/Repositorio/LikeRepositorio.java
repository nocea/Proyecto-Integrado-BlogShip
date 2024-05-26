package proyectoFinalJava.proyectoFinalJava.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoFinalJava.proyectoFinalJava.Modelos.Like;
import proyectoFinalJava.proyectoFinalJava.Modelos.Post;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;

@Repository
public interface LikeRepositorio extends JpaRepository<Like, Long>{
	Like findByUsuarioAndPost(Usuario usuario, Post post);
	boolean existsByUsuarioAndPost(Usuario usuario, Post post);
}
