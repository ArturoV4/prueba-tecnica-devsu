package org.devsu.prueba.ms_cuenta_movimiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@ConfigurationProperties
public class MsCuentaMovimientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCuentaMovimientoApplication.class, args);
	}

}
