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
	/**
	 * Método para obtener la lista de usuarios con los que se ha intercambiado algún mensaje.
	 * @param usuario
	 * @return
	 */
	@Query("SELECT DISTINCT m.idEmisor " +
	           "FROM Mensajes m " +
	           "WHERE m.idReceptor = :usuario " +
	           "UNION " +
	           "SELECT DISTINCT m.idReceptor " +
	           "FROM Mensajes m " +
	           "WHERE m.idEmisor = :usuario")
	    List<Usuario> findUniqueUsersInConversationsWithUser(@Param("usuario") Usuario usuario);
	/**
	 * Método para obenter los mensajes de una conversación
	 * @param idUsuario1
	 * @param idUsuario2
	 * @return
	 */
	@Query("SELECT m FROM Mensajes m WHERE (m.idEmisor.id_usuario = :idUsuario1 AND m.idReceptor.id_usuario = :idUsuario2) OR (m.idEmisor.id_usuario = :idUsuario2 AND m.idReceptor.id_usuario = :idUsuario1) ORDER BY m.fechaEnvio")
    List<Mensajes> findMensajesEntreUsuarios(@Param("idUsuario1") Long idUsuario1, @Param("idUsuario2") Long idUsuario2);
	/**
	 * Método para obtener el número de mensajes que ha enviado un usuario.
	 * @param idUsuario
	 * @return
	 */
	@Query("SELECT COUNT(m) FROM Mensajes m WHERE m.idEmisor.id_usuario = :idUsuario")
    int numeroMensajesEnviadosPorUsuario(@Param("idUsuario") Long idUsuario);
	/**
	 * Método para obtener el número de mensajes que ha recibido un usuario.
	 * @param idUsuario
	 * @return
	 */
	@Query("SELECT COUNT(m) FROM Mensajes m WHERE m.idReceptor.id_usuario = :idUsuario")
    int numeroMensajesRecibidosPorUsuario(@Param("idUsuario") Long idUsuario);
	
}
