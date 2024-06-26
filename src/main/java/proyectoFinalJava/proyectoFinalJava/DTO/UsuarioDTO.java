package proyectoFinalJava.proyectoFinalJava.DTO;



public class UsuarioDTO {
	//atributos
	private Long id_usuario;
	private String nombreCompleto_usuario;
	private String email_usuario;
	private String alias_usuario;
	private String movil_usuario;
	private String passwd_usuario;
	private String rol;
	private String string_imagen_usuario;
	private int numeroConversacionesAbiertas;
	private int numeroMensajesEnviados;
	private int numeroMensajesRecibidos;
	private int numeroPostsAsociados;
	private int numeroTotalLikes;
	private int numeroTotalComentarios;
	public int getNumeroTotalLikes() {
		return numeroTotalLikes;
	}
	public void setNumeroTotalLikes(int numeroTotalLikes) {
		this.numeroTotalLikes = numeroTotalLikes;
	}
	public int getNumeroTotalComentarios() {
		return numeroTotalComentarios;
	}
	public void setNumeroTotalComentarios(int numeroTotalComentarios) {
		this.numeroTotalComentarios = numeroTotalComentarios;
	}
	public int getNumeroPostsAsociados() {
		return numeroPostsAsociados;
	}
	public void setNumeroPostsAsociados(int numeroPostsAsociados) {
		this.numeroPostsAsociados = numeroPostsAsociados;
	}
	public int getNumeroMensajesEnviados() {
		return numeroMensajesEnviados;
	}
	public void setNumeroMensajesEnviados(int numeroMensajesEnviados) {
		this.numeroMensajesEnviados = numeroMensajesEnviados;
	}
	public int getNumeroMensajesRecibidos() {
		return numeroMensajesRecibidos;
	}
	public void setNumeroMensajesRecibidos(int numeroMensajesRecibidos) {
		this.numeroMensajesRecibidos = numeroMensajesRecibidos;
	}
	public int getNumeroConversacionesAbiertas() {
		return numeroConversacionesAbiertas;
	}
	public void setNumeroConversacionesAbiertas(int numeroConversacionesAbiertas) {
		this.numeroConversacionesAbiertas = numeroConversacionesAbiertas;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getString_imagen_usuario() {
		return string_imagen_usuario;
	}
	public void setString_imagen_usuario(String string_imagen_usuario) {
		this.string_imagen_usuario = string_imagen_usuario;
	}
	private byte[] imagen_usuario;
	//gets y sets
	public byte[] getImagen_usuario() {
		return imagen_usuario;
	}
	public void setImagen_usuario(byte[] imagen_usuario) {
		this.imagen_usuario = imagen_usuario;
	}
	public Long getId_usuario() {
		return id_usuario;
	}
	public String getNombreCompleto_usuario() {
		return nombreCompleto_usuario;
	}
	public String getEmail_usuario() {
		return email_usuario;
	}
	public String getAlias_usuario() {
		return alias_usuario;
	}
	public String getMovil_usuario() {
		return movil_usuario;
	}
	public String getPasswd_usuario() {
		return passwd_usuario;
	}
	public String getRol_usuario() {
		return rol;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public void setNombreCompleto_usuario(String nombreCompleto_usuario) {
		this.nombreCompleto_usuario = nombreCompleto_usuario;
	}
	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}
	public void setAlias_usuario(String alias_usuario) {
		this.alias_usuario = alias_usuario;
	}
	public void setMovil_usuario(String movil_usuario) {
		this.movil_usuario = movil_usuario;
	}
	public void setPasswd_usuario(String passwd_usuario) {
		this.passwd_usuario = passwd_usuario;
	}
	public void setRol_usuario(String rol_usuario) {
		this.rol = rol_usuario;
	}
	//constructores
	public UsuarioDTO(Long id_usuario,String nombreCompleto_usuario, String email_usuario, String alias_usuario,
			String movil_usuario, String passwd_usuario, String rol_usuario) {
		super();
		this.id_usuario=id_usuario;
		this.nombreCompleto_usuario = nombreCompleto_usuario;
		this.email_usuario = email_usuario;
		this.alias_usuario = alias_usuario;
		this.movil_usuario = movil_usuario;
		this.passwd_usuario = passwd_usuario;
		this.rol = rol_usuario;
	}
	public UsuarioDTO() {
		super();
	}
	
	
}
