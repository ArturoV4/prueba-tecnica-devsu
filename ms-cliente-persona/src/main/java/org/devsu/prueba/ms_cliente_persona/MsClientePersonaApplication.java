package org.devsu.prueba.ms_cliente_persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication
public class MsClientePersonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientePersonaApplication.class, args);
	}

}
