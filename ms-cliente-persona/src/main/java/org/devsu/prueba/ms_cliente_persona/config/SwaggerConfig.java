package org.devsu.prueba.ms_cliente_persona.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;

@Configuration
public class SwaggerConfig {

	@Value("${swagger.info.version}")
	String appVersion;

	@Value("${swagger.info.name}")
	String apiName;

	@Value("${swagger.info.description}")
	String apiDescripcion;

	@Value("${swagger.info.contact.name}")
	String contactName;

	@Value("${swagger.info.contact.mail}")
	String contactEmail;

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info()
						.title(apiName)
						.version(appVersion)
						.description(apiDescripcion)
				.contact(new Contact()
						.name(contactName)
						.email(contactEmail)));
	}

	@Bean
	public ApiResponse apiResponse() {
		return new ApiResponse();
	}

}
