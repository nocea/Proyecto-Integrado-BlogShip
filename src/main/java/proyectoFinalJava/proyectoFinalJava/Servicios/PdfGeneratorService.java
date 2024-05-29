package proyectoFinalJava.proyectoFinalJava.Servicios;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import proyectoFinalJava.proyectoFinalJava.DTO.UsuarioDTO;
@Service
public class PdfGeneratorService {
public void export(UsuarioDTO usuario,HttpServletResponse response) throws DocumentException, IOException {
	Document document=new Document(PageSize.A4);
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();
	Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    fontTitle.setSize(18);
    Paragraph paragraph = new Paragraph("TUS DATOS", fontTitle);
    paragraph.setAlignment(Paragraph.ALIGN_CENTER);
    document.add(paragraph);

    Font fontBody = FontFactory.getFont(FontFactory.HELVETICA);
    fontBody.setSize(12);

    Paragraph name = new Paragraph("Nombre: " + usuario.getNombreCompleto_usuario(), fontBody);
    name.setAlignment(Paragraph.ALIGN_LEFT);
    document.add(name);

    Paragraph email = new Paragraph("Email: " + usuario.getEmail_usuario(), fontBody);
    email.setAlignment(Paragraph.ALIGN_LEFT);
    document.add(email);

    Paragraph address = new Paragraph("Teléfono: " + usuario.getMovil_usuario(), fontBody);
    address.setAlignment(Paragraph.ALIGN_LEFT);
    document.add(address);
	document.close();
}
    
}
