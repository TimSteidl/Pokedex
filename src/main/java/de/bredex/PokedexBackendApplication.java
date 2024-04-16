package de.bredex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {
	org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
	org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class})
public class PokedexBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexBackendApplication.class, args);
	}

}
