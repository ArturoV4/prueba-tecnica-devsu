package org.devsu.prueba.ms_cliente_persona.config;

import org.devsu.prueba.ms_cliente_persona.rabbitmq.RabbitProperties;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

	private final RabbitProperties rabbitProp;

	@Bean
	public Queue queue() {
		return new Queue(rabbitProp.getName());
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(rabbitProp.getExchange());
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(rabbitProp.getRouting().getKey());
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		var rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

}
