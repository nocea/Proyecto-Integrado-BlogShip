package proyectoFinalJava.proyectoFinalJava.Controlador;
import java.util.ArrayList; 
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import proyectoFinalJava.proyectoFinalJava.DTO.ComentarioDTO;
import proyectoFinalJava.proyectoFinalJava.DTO.MensajeDTO;
import proyectoFinalJava.proyectoFinalJava.DTO.PostDTO;
import proyectoFinalJava.proyectoFinalJava.DTO.UsuarioDTO;
import proyectoFinalJava.proyectoFinalJava.Modelos.Comentario;
import proyectoFinalJava.proyectoFinalJava.Modelos.Like;
import proyectoFinalJava.proyectoFinalJava.Modelos.Mensajes;
import proyectoFinalJava.proyectoFinalJava.Modelos.Post;
import proyectoFinalJava.proyectoFinalJava.Modelos.Usuario;
import proyectoFinalJava.proyectoFinalJava.Repositorio.ComentarioRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.LikeRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.MensajeRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.PostRepositorio;
import proyectoFinalJava.proyectoFinalJava.Repositorio.UsuarioRepositorio;
import proyectoFinalJava.proyectoFinalJava.Servicios.LikeServicio;
import proyectoFinalJava.proyectoFinalJava.Servicios.PostServicio;
import proyectoFinalJava.proyectoFinalJava.Servicios.UsuarioServicio;
import proyectoFinalJava.proyectoFinalJava.Util.Util;

@Controller
public class UsuarioNormalControlador {
	//IMPLEMENTO SERVICIOS Y REPOSITORIOS PARA PODER USARLOS
	@Autowired
	UsuarioServicio usuarioServicio;
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	@Autowired
	LikeRepositorio likeRepositorio;
	@Autowired
	PostRepositorio postRepositorio;
	@Autowired
	PostServicio postServicio;
	@Autowired
	ComentarioRepositorio comentarioRepositorio;
	@Autowired
	MensajeRepositorio mensajeRepositorio;
	@Autowired
	LikeServicio likeServiocio;
	/**
	 * metodo para mostrar los datos del usuario que ha iniciado sesión
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/inicio/miCuenta")
	public String miCuenta(Model model) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();//obtengo el nombre de la autentificación
		
		Usuario usuario = usuarioRepositorio.findFirstByEmailUsuario(username);
		
		UsuarioDTO usuarioDTO = usuarioServicio.convertirUsuarioADTO(usuario);
		
		byte[] imagen_usuario=usuarioDTO.getImagen_usuario();
		String imagenBase64 = Base64.getEncoder().encodeToString(imagen_usuario);
		usuarioDTO.setString_imagen_usuario(imagenBase64);
		model.addAttribute("usuarioDTO", usuarioDTO);//añado usuario
		return "miCuenta";
		}catch (Exception e) {
			Util.log("Se ha producido un error al mostrar los datos d el usuario");
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	/**
	 * Método para mostrar la vista de post
	 * @param model
	 * @return
	 */
	@GetMapping("/inicio/paraTi")
	public String paraTi(Model model) {
		try {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    System.out.println(username);
		List<Post> posts = postRepositorio.findAll();
		List<PostDTO> listaPostDTO = posts.stream()
                .map(postServicio::convertirPostADTO)
                .collect(Collectors.toList());
		for (PostDTO postDTO : listaPostDTO) {
            byte[] imagen_post = postDTO.getImagen_post();
            String imagenBase64 = Base64.getEncoder().encodeToString(imagen_post);
            postDTO.setString_imagen_post(imagenBase64);
            postDTO.setUsuario_alias_post(postDTO.getUsuario().getAlias_usuario());
            boolean userHasLiked = likeServiocio.usuarioHaDadoLike(username, postDTO.getId_post());
            System.out.println(userHasLiked);//para verificar si el usuario ya le ha dado like
            postDTO.setUsuarioHaDadoLike(userHasLiked);
        }
        model.addAttribute("posts", listaPostDTO);
		return "paraTi";
		}catch (Exception e) {
			Util.log("Se ha producido un error al mostrar los post de paraTi");
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	/**
	 * Método para mostrar la vista de crear post
	 * @param model
	 * @return
	 */
	@GetMapping("/inicio/crearPost")
	public String crearPost(Model model) {
		try {
		return "crearPost";
		}catch (Exception e) {
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	/**
	 * Método para guardar un post en la base de datos
	 * @param titulo
	 * @param imagen
	 * @param pieDeFoto
	 * @param model
	 * @return
	 */
	@PostMapping("/inicio/guardarPost")
    public String guardarPost(@RequestParam("titulo") String titulo,
                              @RequestParam("imagen") MultipartFile imagen,
                              @RequestParam("pieDeFoto") String pieDeFoto,@RequestParam("ubicacion") String ubicacion,Model model) {
		try {
			System.out.println("entra en guardar post");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("obtiene el usuario:"+username);
        Usuario usuario=usuarioRepositorio.findFirstByEmailUsuario(username);
        byte[] imagenBytes = imagen.getBytes();
        System.out.println("obtiene la imagen");
        Post post = new Post();
        post.setTitulo_post(titulo);
        post.setImagen_post(imagenBytes);
        post.setPieDeFoto_post(pieDeFoto);
        post.setUsuario(usuario);
        post.setUbicacion(ubicacion);
        postRepositorio.save(post);
        Util.log("Se ha guardado un post nuevo");
		return "redirect:/inicio/paraTi";
		}catch (Exception e) {
			Util.log("Se ha producido un error al guardar un post nuevo");
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
    }
	/**
	 * método para cambiar la imagen del usuario desde la vista de miCuenta
	 * @param imagen
	 * @return
	 */
	@PostMapping("/inicio/cambiarImagen")
    public String guardarPost(@RequestParam("imagen") MultipartFile imagen) {
		try {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario=usuarioRepositorio.findFirstByEmailUsuario(username);
        byte[] imagenBytes = imagen.getBytes();
        usuario.setImagen_usuario(imagenBytes);
        usuarioRepositorio.save(usuario);
        Util.log("Se ha cambiado la imagen del usuario:"+username);
        return "redirect:/inicio/miCuenta";
		}catch (Exception e) {
			Util.log("Se ha producido un error al cambiar la imagen de un usuario");
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	/**
	 * método para mostrar la vista de comentarios de un post con todos los comentarios de ese post
	 * @param idPost
	 * @param model
	 * @return
	 */
	@GetMapping("/inicio/comentar/{id_post}")
    public String mostrarFormularioComentario(@PathVariable("id_post") Long idPost, Model model) {
		try {
        model.addAttribute("id_post", idPost);
        List<Comentario> listaComentarios = comentarioRepositorio.findByPostId(idPost);//obtengo todos los comentarios de ese post
        List<ComentarioDTO> listaComentariosDTO = new ArrayList();
        //paso los comentarios a dto
        for (Comentario comentario : listaComentarios) {
            ComentarioDTO comentarioDTO = new ComentarioDTO(
                    comentario.getContenido(),
                    comentario.getUsuario(),
                    comentario.getPost()
            );
            comentarioDTO.setId_comentario(comentario.getId_comentario());
            listaComentariosDTO.add(comentarioDTO);
        }
        model.addAttribute("comentarios",listaComentariosDTO);
        return "comentarPost";
		}catch (Exception e) {
			Util.log("Se ha producido un error al mostrar los comentarios de un post");
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
    }
	/**
	 * método para guardar un comentario en la base de datos
	 * @param postid
	 * @param textoComentario
	 * @param model
	 * @return
	 */
	@PostMapping("/inicio/guardarComentario")
	public String guardarComentario(@RequestParam("id_post") Long postid,
			@RequestParam("contenido") String textoComentario,Model model) {
		try {
		Post post = postRepositorio.findById(postid).orElse(null);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuarioComentario=usuarioRepositorio.findFirstByEmailUsuario(username);
		Comentario nuevoComentario=new Comentario();
		nuevoComentario.setContenido(textoComentario);
		nuevoComentario.setUsuario(usuarioComentario);
		nuevoComentario.setPost(post);
		comentarioRepositorio.save(nuevoComentario);
		Util.log("Se ha añadido un nuevo  comentario al post:"+postid);
		return "redirect:/inicio/paraTi";
		}catch (Exception e) {
			Util.log("Se ha producido un error al comentar un post");
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	/**
	 * método para guardar un like en la base de datos
	 * @param idPost
	 * @param model
	 * @return
	 */
	@PostMapping("/inicio/registrarLike/{id_post}")
	public String registrarLike(@PathVariable("id_post")Long idPost,Model model) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//obtengo el usuario que ha iniciado sesion
	        String username = authentication.getName();
	        Usuario usuario = usuarioRepositorio.findFirstByEmailUsuario(username);
	        Post post = postRepositorio.findById(idPost).orElse(null);//busco el post al que se le quiere dar like
	        // Verificar si el usuario ya ha dado like al post
	        Like likeExistente = likeRepositorio.findByUsuarioAndPost(usuario, post);
	        if (likeExistente != null) {
	            // Si el like ya existe, eliminarlo
	            likeRepositorio.delete(likeExistente);
	        } else {
	            // Si el like no existe, crear uno nuevo
	            Like nuevoLike = new Like();
	            nuevoLike.setUsuario(usuario);
	            nuevoLike.setPost(post);
	            likeRepositorio.save(nuevoLike);
	        }
	        
	        return "redirect:/inicio/paraTi";
		}catch (Exception e) {
	        Util.log("Se ha producido un error al dar like a un post");
	        return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	/**
	 * Muestra las conversaciones del usuario que tiene iniciada la sesión.
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/inicio/conversaciones")
	public String conversaciones(@RequestParam(name = "error", required = false) String error,Model model,Authentication authentication) {
		try {
			if (error != null) {
		        model.addAttribute("error", error);
		        System.out.println(error);
		    }
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findFirstByEmailUsuario(username);
			List<Usuario> usuarioConversaciones=mensajeRepositorio.findUniqueUsersInConversationsWithUser(usuario);//obtengo todas las conversaciones salientes y entrantes
			List<UsuarioDTO> usuarioConversacionesDTO=new ArrayList<>();
			for (Usuario usuarioConversacion : usuarioConversaciones) {//convierto a dto
				
	            UsuarioDTO usuarioDTO = usuarioServicio.convertirUsuarioADTO(usuarioConversacion);
	            
	            byte[] imagen_usuario = usuarioDTO.getImagen_usuario();
	            
	            String imagenBase64 = Base64.getEncoder().encodeToString(imagen_usuario);
	            
	            usuarioDTO.setString_imagen_usuario(imagenBase64);
	            usuarioConversacionesDTO.add(usuarioDTO);
	            
	        }
			model.addAttribute("usuariosConversaciones",usuarioConversacionesDTO);//añado la lista dto a la vista
			return "conversaciones";
		}catch (Exception e) {
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	@GetMapping("/inicio/verPost/{idPost}")
	public String verPost(@PathVariable("idPost") Long idPost, Model model,Authentication authentication) {
		try {
		Post post=new Post();
		post=postRepositorio.buscarPorId(idPost);
		PostDTO postDTO=new PostDTO();
		postDTO.setCantidad_likes(post.getLikes().size());
		postDTO.setCantidad_comentarios(post.getComentarios().size());
		postDTO.setPieDeFoto_post(post.getPieDeFoto_post());
		postDTO.setTitulo_post(post.getTitulo_post());
		postDTO.setImagen_post(post.getImagen_post());
		String imagenBase64 = Base64.getEncoder().encodeToString(postDTO.getImagen_post());
		postDTO.setString_imagen_post(imagenBase64);
		postDTO.setUbicacion(post.getUbicacion());
		model.addAttribute("post",postDTO);
		return "verPost";
		}catch (Exception e) {
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	@GetMapping("/inicio/conversaciones/{idReceptor}")
	public String chat(@PathVariable("idReceptor") Long idReceptor, Model model,Authentication authentication) {
		try {
		String username = authentication.getName();
		Usuario usuario1 = usuarioRepositorio.findFirstByEmailUsuario(username);
		Usuario usuarioReceptor=usuarioRepositorio.buscarPorId(idReceptor);
		UsuarioDTO usuarioReceptorDTO=usuarioServicio.convertirUsuarioADTO(usuarioReceptor);
		List<Mensajes> mensajes = mensajeRepositorio.findMensajesEntreUsuarios(usuario1.getId_usuario(), idReceptor);
		List<MensajeDTO> mensajesDTO = mensajes.stream()
                .map(mensaje -> {
                    MensajeDTO mensajeDTO = new MensajeDTO();
                    mensajeDTO.setMensaje(mensaje.getContenido());
                    mensajeDTO.setFechaEnvio(mensaje.getFechaEnvio());
                    mensajeDTO.setIdEmisor(mensaje.getidEmisor().getId_usuario());
                    mensajeDTO.setIdReceptor(mensaje.getidReceptor().getId_usuario());
                    return mensajeDTO;
                })
                .collect(Collectors.toList());
		byte[] imagen_usuario = usuarioReceptorDTO.getImagen_usuario();
        
        String imagenBase64 = Base64.getEncoder().encodeToString(imagen_usuario);
        
        usuarioReceptorDTO.setString_imagen_usuario(imagenBase64);
		model.addAttribute("usuarioReceptor",usuarioReceptorDTO);
        model.addAttribute("idUsuario", idReceptor);
        model.addAttribute("mensajes", mensajesDTO);
        return "chat";
		}catch (Exception e) {
			return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
    }
	@GetMapping("/inicio/conversaciones/buscar")
    public String buscarUsuarioPorTelefono(@RequestParam("telefono") String telefono, Model model, Authentication authentication) {
		try {
            Usuario usuarioEncontrado = usuarioRepositorio.buscarporTelefono(telefono);
            if (usuarioEncontrado != null) {
                UsuarioDTO usuarioDTO = usuarioServicio.convertirUsuarioADTO(usuarioEncontrado);
                System.out.println(usuarioDTO.getId_usuario());
                return "redirect:/inicio/conversaciones/"+usuarioDTO.getId_usuario();
            } else {
            	return "redirect:/inicio/conversaciones?error=No+se+ha+encontrado+el+usuario.";
            }           
		} catch (Exception e) {
            return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
        }
	}
	@PostMapping("/inicio/mandarMensaje")
	public String mandarMensaje(@RequestParam("idReceptor") Long idReceptor,
			@RequestParam("mensaje") String mensaje,Model model,Authentication authentication) {
		try {
			Mensajes mensajeNuevo=new Mensajes();
			String username = authentication.getName();
			Usuario usuario = usuarioRepositorio.findFirstByEmailUsuario(username);
			Usuario usuarioReceptor=usuarioRepositorio.buscarPorId(idReceptor);
			Calendar fechaEnvioMensaje = Calendar.getInstance();
			fechaEnvioMensaje.setTimeInMillis(System.currentTimeMillis());
			mensajeNuevo.setContenido(mensaje);
			mensajeNuevo.setFechaEnvio(fechaEnvioMensaje);
			mensajeNuevo.setidEmisor(usuario);
			mensajeNuevo.setidReceptor(usuarioReceptor);
			mensajeRepositorio.save(mensajeNuevo);
	        return "redirect:/inicio/conversaciones/"+idReceptor;
		}catch (Exception e) {
	        Util.log("Se ha producido un error al dar like a un post");
	        return "redirect:/controller/ERRORPAGE?error=Se+ha+producido+un+error+inesperado.";
	    }
	}
	@GetMapping("/proyectoFinalJava/inicio/descargar-pdf")
    public ResponseEntity<byte[]> descargarPdf() {
        // Generar PDF usando iText
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Agregar contenido al PDF
        String datos="prueba mario";
        Paragraph paragraph = new Paragraph(datos);
        document.add(paragraph);

        document.close();

        // Configurar respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        headers.add("Content-Disposition", "attachment; filename=misDatos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(baos.toByteArray());
    }
}
