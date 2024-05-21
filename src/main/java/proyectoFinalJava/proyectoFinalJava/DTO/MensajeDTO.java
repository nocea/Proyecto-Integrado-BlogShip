package proyectoFinalJava.proyectoFinalJava.DTO;

import java.util.Calendar;

public class MensajeDTO {
  private  String mensaje;
  private Long idReceptor;
  private Long idEmisor;
  private Calendar fechaEnvio;
public String getMensaje() {
	return mensaje;
}
public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
}
public Long getIdReceptor() {
	return idReceptor;
}
public void setIdReceptor(Long idReceptor) {
	this.idReceptor = idReceptor;
}
public Long getIdEmisor() {
	return idEmisor;
}
public void setIdEmisor(Long idEmisor) {
	this.idEmisor = idEmisor;
}
public Calendar getFechaEnvio() {
	return fechaEnvio;
}
public void setFechaEnvio(Calendar fechaEnvio) {
	this.fechaEnvio = fechaEnvio;
}
public MensajeDTO(String mensaje, Long idReceptor, Long idEmisor, Calendar fechaEnvio) {
	super();
	this.mensaje = mensaje;
	this.idReceptor = idReceptor;
	this.idEmisor = idEmisor;
	this.fechaEnvio = fechaEnvio;
}
public MensajeDTO() {
	super();
}
}
