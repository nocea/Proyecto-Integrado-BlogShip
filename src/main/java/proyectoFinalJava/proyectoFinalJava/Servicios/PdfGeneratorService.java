package proyectoFinalJava.proyectoFinalJava.Servicios;

import java.awt.Color;
import java.io.IOException;


import org.springframework.stereotype.Service;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import proyectoFinalJava.proyectoFinalJava.DTO.UsuarioDTO;
@Service
public class PdfGeneratorService {
	public void export(UsuarioDTO usuario, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();


        // Add some space after the image
        document.add(new Paragraph(" "));

        // Set title font
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Paragraph title = new Paragraph("Datos de la cuenta: @"+usuario.getAlias_usuario(), fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // Add space after title
        document.add(new Paragraph(" "));

        // Create a table with 2 columns
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Set column widths
        float[] columnWidths = {1f, 2f};
        table.setWidths(columnWidths);

        // Set cell fonts
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Add header cells
        addColoredTableCell(table, "Campo", fontHeader, Color.GRAY);
        addColoredTableCell(table, "Información", fontHeader, Color.GRAY);

        // Add user information cells
        addColoredTableCell(table, "Nombre", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, usuario.getNombreCompleto_usuario(), fontBody, Color.WHITE);

        addColoredTableCell(table, "Email", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, usuario.getEmail_usuario(), fontBody, Color.WHITE);

        addColoredTableCell(table, "Teléfono", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, usuario.getMovil_usuario(), fontBody, Color.WHITE);

        addColoredTableCell(table, "Alias", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, usuario.getAlias_usuario(), fontBody, Color.WHITE);

        addColoredTableCell(table, "Conversaciones Iniciadas", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, String.valueOf(usuario.getNumeroConversacionesAbiertas()), fontBody, Color.WHITE);

        addColoredTableCell(table, "Mensajes Enviados", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, String.valueOf(usuario.getNumeroMensajesEnviados()), fontBody, Color.WHITE);

        addColoredTableCell(table, "Mensajes Recibidos", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, String.valueOf(usuario.getNumeroMensajesRecibidos()), fontBody, Color.WHITE);

        addColoredTableCell(table, "Posts Creados", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, String.valueOf(usuario.getNumeroPostsAsociados()), fontBody, Color.WHITE);

        addColoredTableCell(table, "Comentarios Enviados", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, String.valueOf(usuario.getNumeroTotalComentarios()), fontBody, Color.WHITE);

        addColoredTableCell(table, "Likes Registrados", fontBody, Color.LIGHT_GRAY);
        addColoredTableCell(table, String.valueOf(usuario.getNumeroTotalLikes()), fontBody, Color.WHITE);

        // Add table to document
        document.add(table);
        Font fontLink = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLUE);
        Anchor anchor = new Anchor("Vuelve a Blogship", fontLink);
        anchor.setReference("https://blogship.mnocea.eu/");
        document.add(anchor);
        document.close();
    }

    private void addColoredTableCell(PdfPTable table, String text, Font font, Color color) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setPadding(5);
        cell.setBackgroundColor(color);
        table.addCell(cell);
    }
    
}
