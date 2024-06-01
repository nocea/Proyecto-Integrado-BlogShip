package proyectoFinalJava.proyectoFinalJava.Repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	/**
	 * Busca un usuario por su email
	 * @param email_usuario
	 * @return
	 */
	public Usuario findFirstByEmailUsuario(String email_usuario);
	/**
	 * Busca un usuario por su teléfono
	 * @param movil_usuario
	 * @return
	 */
	@Query("SELECT u FROM Usuario u WHERE u.movil_usuario = :movil_usuario")
	public Usuario buscarporTelefono(String movil_usuario);
	/**
	 * Comprueba si existe un usuario por su nombre completo.
	 * @param nombreCompletoUsuario
	 * @return
	 */
	public boolean existsByNombreCompletoUsuario(String nombreCompletoUsuario);
	/**
	 * Busca un usuario por su id.
	 * @param idUsuario
	 * @return
	 */
	@Query("SELECT u FROM Usuario u WHERE u.id = :idUsuario")
	public Usuario buscarPorId(Long idUsuario);
	
}
