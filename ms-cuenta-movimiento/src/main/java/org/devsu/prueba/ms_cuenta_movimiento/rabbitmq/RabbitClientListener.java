package org.devsu.prueba.ms_cuenta_movimiento.rabbitmq;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Client;
import org.devsu.prueba.ms_cuenta_movimiento.model.ClientEvent;
import org.devsu.prueba.ms_cuenta_movimiento.model.EnumStatus;
import org.devsu.prueba.ms_cuenta_movimiento.model.PublishClient;
import org.devsu.prueba.ms_cuenta_movimiento.repository.AccountRepository;
import org.devsu.prueba.ms_cuenta_movimiento.repository.ClientRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class RabbitClientListener {
	
	private final ClientRepository clientRepository;
	private final AccountRepository accountRepository;
	
	
	@Transactional
	@RabbitListener(queues = "clients.queue")
	public void processEvent(PublishClient event) {
		switch (event.getAction()) {
		case "CREATED_CLIENT":
			this.processClient((ClientEvent) event, false);
			break;
		case "UPDATED_CLIENT":
			this.processClient((ClientEvent) event, false);
			break;
		case "DELETED_CLIENT":
			this.processClient((ClientEvent) event, true);
			break;
		default:
			log.info("Event not allowed.");
			break;
		}
	}
	
	private void processClient(ClientEvent client, boolean isDeleted) {
		log.info("Client Data: {}", client);
		try {
			if(!isDeleted) {
				this.clientRepository.save(Client.builder()
						.clientId(client.getClientId())
						.name(client.getName())
						.build());
				log.info("Event successfully processed");
			} else {
				var clientEntity = this.clientRepository.findById(client.getClientId()).orElseThrow();
				var accountClient = this.accountRepository.findByClient(clientEntity)
						.stream()
						.map(account -> {
							account.setStatus(EnumStatus.FALSE);
							return account;
						}).toList();
				this.accountRepository.saveAll(accountClient);
			}			
		} catch (Exception e) {
			log.error("An error ocurred while proccessing event: {} - Error: {}", client, e.getMessage());
		}
	}

}
