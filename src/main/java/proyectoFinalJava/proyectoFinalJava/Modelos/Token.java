package proyectoFinalJava.proyectoFinalJava.Modelos;

import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tokens",schema="sch_pf")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_token;
	@Column(name = "cadena_token") // Cambia el nombre de la columna si es necesario
    private String cadenaToken;
	private Calendar fechafinToken;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	public Token() {
		super();
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Token(String cadena_token, Calendar fechafinToken,Usuario usuario) {
		super();
		this.cadenaToken = cadena_token;
		this.fechafinToken = fechafinToken;
		this.usuario=usuario;
	}
	public Long getId_token() {
		return id_token;
	}
	public String getCadena_token() {
		return cadenaToken;
	}
	public Calendar getFechafinToken() {
		return fechafinToken;
	}
	public void setId_token(Long id_token) {
		this.id_token = id_token;
	}
	public void setCadena_token(String cadena_token) {
		this.cadenaToken = cadena_token;
	}
	public void setFechafinToken(Calendar fechafinToken) {
		this.fechafinToken = fechafinToken;
	}
}
