package proyectoFinalJava.proyectoFinalJava.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoFinalJava.proyectoFinalJava.Modelos.Token;

@Repository
public interface TokenRepositorio extends JpaRepository<Token, Long> {
	/**
	 * Método que devuelve el token según su cadena.
	 * @param cadenaToken
	 * @return
	 */
	Token findByCadenaToken(String cadenaToken);
}
