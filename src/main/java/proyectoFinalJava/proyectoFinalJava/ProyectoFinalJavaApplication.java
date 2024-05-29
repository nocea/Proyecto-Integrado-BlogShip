package proyectoFinalJava.proyectoFinalJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

public class ProyectoFinalJavaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalJavaApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(ProyectoFinalJavaApplication.class);
	}

}
