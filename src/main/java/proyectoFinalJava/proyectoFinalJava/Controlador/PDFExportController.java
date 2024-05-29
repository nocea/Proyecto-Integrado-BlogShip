package proyectoFinalJava.proyectoFinalJava.Controlador;

import java.io.IOException;
import java.net.http.HttpResponse;

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
	@GetMapping("/inicio/generate")
	public void generatePDF(HttpServletResponse response) throws DocumentException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Usuario usuario = usuarioRepositorio.findFirstByEmailUsuario(username);
		UsuarioDTO usuarioDTO = usuarioServicio.convertirUsuarioADTO(usuario);
		response.setContentType("application/pdf");
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=misDatos.pdf";
		response.setHeader(headerKey, headerValue);
		this.pdfGeneratorService.export(usuarioDTO,response);
		
	}

}
