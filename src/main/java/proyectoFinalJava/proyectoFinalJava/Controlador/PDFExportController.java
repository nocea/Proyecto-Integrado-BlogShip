package proyectoFinalJava.proyectoFinalJava.Controlador;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import proyectoFinalJava.proyectoFinalJava.DTO.UsuarioDTO;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;
import proyectoFinalJava.proyectoFinalJava.Servicios.PdfGeneratorService;
import proyectoFinalJava.proyectoFinalJava.Servicios.UsuarioServicio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.ComentarioRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.LikeRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.MensajeRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.PostRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.UsuarioRepositorio;
@Controller
public class PDFExportController {
	private final PdfGeneratorService pdfGeneratorService;
	public PDFExportController(PdfGeneratorService pdfGeneratorService) {
		this.pdfGeneratorService=pdfGeneratorService;
	}
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	@Autowired
	UsuarioServicio usuarioServicio;
	@Autowired
	MensajeRepositorio mensajeRepositorio;
	@Autowired
	PostRepositorio postRepositorio;
	@Autowired
	LikeRepositorio likeRepositorio;
	@Autowired
	ComentarioRepositorio comentarioRepositorio;
	@GetMapping("/inicio/generate")
	public void generatePDF(HttpServletResponse response) throws DocumentException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Usuario usuario = usuarioRepositorio.findFirstByEmailUsuario(username);
		List<Usuario>conversacionesIniciadas=mensajeRepositorio.findUniqueUsersInConversationsWithUser(usuario);
		UsuarioDTO usuarioDTO = usuarioServicio.convertirUsuarioADTO(usuario);
		usuarioDTO.setNumeroConversacionesAbiertas(conversacionesIniciadas.size());
		usuarioDTO.setNumeroMensajesEnviados(mensajeRepositorio.numeroMensajesEnviadosPorUsuario(usuario.getId_usuario()));
		usuarioDTO.setNumeroMensajesRecibidos(mensajeRepositorio.numeroMensajesRecibidosPorUsuario(usuario.getId_usuario()));
		usuarioDTO.setNumeroPostsAsociados(postRepositorio.findPostsByUsuario(usuario).size());
		usuarioDTO.setNumeroTotalLikes(likeRepositorio.findLikesByUsuario(usuario).size());
		usuarioDTO.setNumeroTotalComentarios(comentarioRepositorio.findComentariosByUsuario(usuario).size());
		response.setContentType("application/pdf");
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=misDatos.pdf";
		response.setHeader(headerKey, headerValue);
		this.pdfGeneratorService.export(usuarioDTO,response);
		
	}

}
