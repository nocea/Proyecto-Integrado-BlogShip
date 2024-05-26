package proyectoFinalJava.proyectoFinalJava.DTO;

import java.util.List;

import proyectoFinalJava.proyectoFinalJava.Modelos.Like;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;

public class PostDTO {
		//atributos
	    private Long id_post;
	    private String titulo_post;
	    private byte[] imagen_post; 
	    private String string_imagen_post;
	    private String pieDeFoto_post;
	    private String usuario_alias_post;
	    private Usuario usuario;
	    private String ubicacion;
	    private int cantidad_comentarios;
	    private List<Like> likes;
	    private int cantidad_likes;
	    private boolean usuarioHaDadoLike;
	    public boolean getUsuarioHaDadoLike() {
	        return usuarioHaDadoLike;
	    }

	    public void setUsuarioHaDadoLike(boolean usuarioHaDadoLike) {
	        this.usuarioHaDadoLike = usuarioHaDadoLike;
	    }
	    //gets y sets
	    
	    public int getCantidad_likes() {
	        return cantidad_likes;
	    }

	    public String getUbicacion() {
			return ubicacion;
		}

		public void setUbicacion(String ubicacion) {
			this.ubicacion = ubicacion;
		}

		public void setCantidad_likes(int cantidad_likes) {
	        this.cantidad_likes = cantidad_likes;
	    }
	    public int getCantidad_comentarios() {
			return cantidad_comentarios;
		}

	    public List<Like> getLikes() {
	        return likes;
	    }

	    public void setLikes(List<Like> likes) {
	        this.likes = likes;
	    }
		public void setCantidad_comentarios(int cantidad_comentarios) {
			this.cantidad_comentarios = cantidad_comentarios;
		}
	    public String getUsuario_alias_post() {
			return usuario_alias_post;
		}


		public void setUsuario_alias_post(String usuario_alias_post) {
			this.usuario_alias_post = usuario_alias_post;
		}
		public Long getId_post() {
			return id_post;
		}


		public void setId_post(Long id_post) {
			this.id_post = id_post;
		}


		public String getTitulo_post() {
			return titulo_post;
		}


		public void setTitulo_post(String titulo_post) {
			this.titulo_post = titulo_post;
		}


		public byte[] getImagen_post() {
			return imagen_post;
		}


		public void setImagen_post(byte[] imagen_post) {
			this.imagen_post = imagen_post;
		}


		public String getString_imagen_post() {
			return string_imagen_post;
		}


		public void setString_imagen_post(String string_imagen_post) {
			this.string_imagen_post = string_imagen_post;
		}


		public String getPieDeFoto_post() {
			return pieDeFoto_post;
		}


		public void setPieDeFoto_post(String pieDeFoto_post) {
			this.pieDeFoto_post = pieDeFoto_post;
		}


		public Usuario getUsuario() {
			return usuario;
		}


		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		//contructores
		


		public PostDTO() {
			super();
		}

		public PostDTO(Long id_post,String ubicacion, String titulo_post, byte[] imagen_post, String string_imagen_post,
	            String pieDeFoto_post, String usuario_alias_post, Usuario usuario, int cantidad_comentarios,
	            List<Like> likes, int cantidad_likes) {
	        super();
	        this.id_post = id_post;
	        this.titulo_post = titulo_post;
	        this.imagen_post = imagen_post;
	        this.string_imagen_post = string_imagen_post;
	        this.pieDeFoto_post = pieDeFoto_post;
	        this.usuario_alias_post = usuario_alias_post;
	        this.usuario = usuario;
	        this.cantidad_comentarios = cantidad_comentarios;
	        this.likes = likes;
	        this.ubicacion=ubicacion;
	        this.cantidad_likes = cantidad_likes;
	    }

		
}

