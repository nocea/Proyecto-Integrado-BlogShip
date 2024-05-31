package proyectoFinalJava.proyectoFinalJava.Repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	public Usuario findFirstByEmailUsuario(String email_usuario);
	@Query("SELECT u FROM Usuario u WHERE u.movil_usuario = :movil_usuario")
	public Usuario buscarporTelefono(String movil_usuario);
	public boolean existsByNombreCompletoUsuario(String nombreCompletoUsuario);
	@Query("SELECT u FROM Usuario u WHERE u.id = :idUsuario")
	public Usuario buscarPorId(Long idUsuario);
	
}
