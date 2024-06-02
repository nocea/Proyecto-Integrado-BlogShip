## Documentación técnico-funcional del Proyecto-Integrado-BlogShip
---------------------------------------------------------------
- Título:BlogShip
- Fecha:2 de Junio de 2024
- Versión:1.0
- Autor:Mario Nocea Cabrera
- Contacto:mnoceacabrera@gmail.com
---------------------------------------------------------------
### ALCANCE DEL PROYECTO
El proyecto de Blogship es una pequeña aplicación web que actúa como RRSS sobre el espacio y todo lo relacionado con el mismo,los usuarios pueden intercambiar likes y comentarios en los posts que ellos mismos suben y también intercambiar mensajes entre ellos.
---------------------------------------------------------------
### DESCRIPCIÓN GENERAL DEL SISTEMA
El proyecto interactua con una base de externa PostgreSQL 16.2 para almacenar todos los datos de la web,utiliza Java 17, Spring Boot 3.2.2 ,HTML5 y Thymeleaf para la parte del frontend.
---------------------------------------------------------------
### FUNCIONALIDADES DE LA WEB
- Registro y Login de Usuarios
- Crear Posts
- Comentar Posts
- Dar Likes a Posts
- Intercambiar Mensajes entre usuarios.
- Visualizar los Datos de su cuenta y poder descargar en pdf.
- El administrador podra gestionar tanto los usuarios de la web como los posts.
- ---------------------------------------------------------------
### DISEÑO DEL SISTEMA
1. Los datos son enviados desde la vista al controlador o viceversa(POST/GET).
2. 2. Estos datos se gestionan en los servicios y se hacen las diferentes operaciones CRUD en la base de datos.
3. El controlador devuelve al usuario una respuesta con los datos necesarios.
