package org.devsu.prueba.ms_cuenta_movimiento.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

}
