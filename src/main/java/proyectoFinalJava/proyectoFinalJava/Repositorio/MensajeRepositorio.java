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
	@Query("SELECT m FROM Mensajes m WHERE (m.idEmisor.id_usuario = :idUsuario1 AND m.idReceptor.id_usuario = :idUsuario2) OR (m.idEmisor.id_usuario = :idUsuario2 AND m.idReceptor.id_usuario = :idUsuario1) ORDER BY m.fechaEnvio")
    List<Mensajes> findMensajesEntreUsuarios(@Param("idUsuario1") Long idUsuario1, @Param("idUsuario2") Long idUsuario2);
	@Query("SELECT COUNT(m) FROM Mensajes m WHERE m.idEmisor.id_usuario = :idUsuario")
    int numeroMensajesEnviadosPorUsuario(@Param("idUsuario") Long idUsuario);
	@Query("SELECT COUNT(m) FROM Mensajes m WHERE m.idReceptor.id_usuario = :idUsuario")
    int numeroMensajesRecibidosPorUsuario(@Param("idUsuario") Long idUsuario);
	
}
