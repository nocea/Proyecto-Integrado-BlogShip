package proyectoFinalJava.proyectoFinalJava.Modelos;

import java.util.Calendar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="mensajes",schema="sch_pf")
public class Mensajes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_emisor")
    private Usuario idEmisor;

    @ManyToOne
    @JoinColumn(name = "id_receptor")
    private Usuario idReceptor;

    private String contenido;
    private Calendar fechaEnvio;
    //constructores
	public Mensajes(Long id, Usuario idEmisor, Usuario idReceptor, String contenido, Calendar fechaEnvio) {
		super();
		this.id = id;
		this.idEmisor = idEmisor;
		this.idReceptor = idReceptor;
		this.contenido = contenido;
		this.fechaEnvio = fechaEnvio;
	}
	public Mensajes() {
		super();
	}
	// getters y setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getidEmisor() {
		return idEmisor;
	}
	public void setidEmisor(Usuario idEmisor) {
		this.idEmisor = idEmisor;
	}
	public Usuario getidReceptor() {
		return idReceptor;
	}
	public void setidReceptor(Usuario idReceptor) {
		this.idReceptor = idReceptor;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Calendar getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Calendar fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}  
}
