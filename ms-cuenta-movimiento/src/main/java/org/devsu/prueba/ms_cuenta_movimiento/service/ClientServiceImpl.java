package org.devsu.prueba.ms_cuenta_movimiento.service;

import java.util.Optional;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Client;
import org.devsu.prueba.ms_cuenta_movimiento.exception.GenericException;
import org.devsu.prueba.ms_cuenta_movimiento.repository.ClientRepository;
import org.devsu.prueba.ms_cuenta_movimiento.rest.ClientRest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final ClientRest clientRest;
	private final ClientRepository clientRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Client findClient(Long clientId) {
		return this.clientRepository.findById(clientId)
				.or(() -> this.consumeMS(clientId))
				.orElseThrow(() -> new GenericException("Client not found.", HttpStatus.NO_CONTENT));
	}
	
	@Transactional
	public Optional<Client> consumeMS(Long clientId) {
		var client = this.clientRest.findClientById(clientId);
		var clientEntity = Client.builder()
				.clientId(clientId)
				.name(client.getName())
				.build();
		this.clientRepository.save(clientEntity);
		return Optional.of(clientEntity);
	}
	

}
