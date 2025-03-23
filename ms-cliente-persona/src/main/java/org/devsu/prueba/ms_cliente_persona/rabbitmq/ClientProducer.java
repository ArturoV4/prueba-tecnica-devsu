package org.devsu.prueba.ms_cliente_persona.rabbitmq;

import org.devsu.prueba.ms_cliente_persona.model.PublishClient;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClientProducer {
	
	private final RabbitProperties rabbitProp;
	private final RabbitTemplate rabbitTemplate;
	
	public void publish(PublishClient event) {
		try {
			log.info("Publishing client: {}", event);
			this.rabbitTemplate.convertAndSend(rabbitProp.getExchange(), rabbitProp.getRouting().getKey(), event);
		} catch (AmqpException e) {
			log.error("An error ocurred while publishing the client: {}", e.getMessage());
		}
	}

}
