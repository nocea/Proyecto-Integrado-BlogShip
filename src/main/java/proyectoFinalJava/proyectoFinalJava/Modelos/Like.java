package proyectoFinalJava.proyectoFinalJava.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="likes",schema="sch_pf")
public class Like {
	//Columnas y relaciones
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_like;
	@ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
	@ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;
	//Gets y Sets 
	public Long getId_like() {
		return id_like;
	}
	public void setId_like(Long id_like) {
		this.id_like = id_like;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	//Constructores
	public Like(Long id_like, Usuario usuario, Post post) {
		super();
		this.id_like = id_like;
		this.usuario = usuario;
		this.post = post;
	}
	public Like() {
		super();
	}
	
	}
