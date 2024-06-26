package proyectoFinalJava.proyectoFinalJava.Servicios;

import java.io.ByteArrayInputStream;

import proyectoFinalJava.proyectoFinalJava.DTO.UsuarioDTO; 
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;

public interface UsuarioServicio {
public Boolean registrar(UsuarioDTO usuarioDTO);

public UsuarioDTO convertirUsuarioADTO(Usuario usuario);

public Usuario usuarioDTOaUsuario(UsuarioDTO usuarioDTO);
public String generarToken(Usuario Usuario);
public void EnviarEmailRecuperar(String email,String token);
void EnviarEmailRegistro(String email);
}
