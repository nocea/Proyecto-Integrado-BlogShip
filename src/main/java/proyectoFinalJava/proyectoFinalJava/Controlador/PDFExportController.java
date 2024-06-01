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
import proyectoFinalJava.proyectoFinalJava.Util.Util;
import proyectoFinalJava.proyectoFinalJava.Repositorio.ComentarioRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.LikeRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.MensajeRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.PostRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.UsuarioRepositorio;
@Controller
public class PDFExportController {
	private final PdfGeneratorService pdfGeneratorService;
	//inyección  del pdfgeneratorservice
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
	/**
	 * Método para generar un pdf con los datos del usuario que ha iniciado sesión.
	 * @param response
	 * @throws DocumentException
	 * @throws IOException
	 */
	@GetMapping("/inicio/generate")
	public void generatePDF(HttpServletResponse response) {
	    try {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String username = authentication.getName();

	        Usuario usuario = usuarioRepositorio.findFirstByEmailUsuario(username); // Obtengo el usuario
	        if (usuario == null) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
	            return;
	        }

	        List<Usuario> conversacionesIniciadas = mensajeRepositorio.findUniqueUsersInConversationsWithUser(usuario);
	        UsuarioDTO usuarioDTO = usuarioServicio.convertirUsuarioADTO(usuario);
	        usuarioDTO.setNumeroConversacionesAbiertas(conversacionesIniciadas.size());
	        usuarioDTO.setNumeroMensajesEnviados(mensajeRepositorio.numeroMensajesEnviadosPorUsuario(usuario.getId_usuario()));
	        usuarioDTO.setNumeroMensajesRecibidos(mensajeRepositorio.numeroMensajesRecibidosPorUsuario(usuario.getId_usuario()));
	        usuarioDTO.setNumeroPostsAsociados(postRepositorio.findPostsByUsuario(usuario).size());
	        usuarioDTO.setNumeroTotalLikes(likeRepositorio.findLikesByUsuario(usuario).size());
	        usuarioDTO.setNumeroTotalComentarios(comentarioRepositorio.findComentariosByUsuario(usuario).size());

	        // Configuración del PDF
	        response.setContentType("application/pdf"); // Tipo de contenido
	        String headerKey = "Content-Disposition"; // Clave del encabezado
	        String headerValue = "attachment;filename=misDatos.pdf"; // Valor, nombre del encabezado y que se descargue el archivo
	        response.setHeader(headerKey, headerValue);

	        this.pdfGeneratorService.export(usuarioDTO, response);

	    } catch (DocumentException | IOException e) {
	        Util.log("Se ha producido un error al generar el PDF: " + e.getMessage()); // Registro del error
	        try {
	            response.sendRedirect("/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado."); // Redirección a la página de error
	        } catch (IOException ioException) {
	            Util.log("Se ha producido un error al redirigir a la página de error: " + ioException.getMessage());
	        }
	    } catch (Exception e) {
	        Util.log("Se ha producido un error inesperado: " + e.getMessage()); // Registro del error
	        try {
	            response.sendRedirect("/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado."); // Redirección a la página de error
	        } catch (IOException ioException) {
	            Util.log("Se ha producido un error al redirigir a la página de error: " + ioException.getMessage());
	        }
	    }
	}


}
