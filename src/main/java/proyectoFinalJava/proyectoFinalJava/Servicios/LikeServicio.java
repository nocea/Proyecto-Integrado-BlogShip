package proyectoFinalJava.proyectoFinalJava.Servicios;

public interface LikeServicio {
	/**
	 * Método para comprobar que un usuario ha dado like a un post
	 * @param username
	 * @param postId
	 * @return
	 */
	public boolean usuarioHaDadoLike(String username, Long postId);
}
