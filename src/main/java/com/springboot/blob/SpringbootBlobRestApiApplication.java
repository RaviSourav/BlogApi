package com.springboot.blob;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App REST Apis",
				description = "Spring Boot Blog App REST Apis documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Ravi Sourav",
						email = "rdxhero@gmail.com",
						url = "https://github.com/RaviSourav/Spring-Crud1"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/RaviSourav/Spring-Crud1"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog App REST Apis documentations",
				url = "https://github.com/RaviSourav/Spring-Crud1"
		)
)
public class SpringbootBlobRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlobRestApiApplication.class, args);
	}

}
