package proyectoFinalJava.proyectoFinalJava.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyectoFinalJava.proyectoFinalJava.Modelos.Mensajes;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;
@Repository
public interface MensajeRepositorio extends JpaRepository<Mensajes, Long> {
	@Query("SELECT DISTINCT m.idEmisor " +
	           "FROM Mensajes m " +
	           "WHERE m.idReceptor = :usuario " +
	           "UNION " +
	           "SELECT DISTINCT m.idReceptor " +
	           "FROM Mensajes m " +
	           "WHERE m.idEmisor = :usuario")
	    List<Usuario> findUniqueUsersInConversationsWithUser(@Param("usuario") Usuario usuario);

}
