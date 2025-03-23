package org.devsu.prueba.ms_cliente_persona.service;

import java.util.List;

import org.devsu.prueba.ms_cliente_persona.entity.Client;
import org.devsu.prueba.ms_cliente_persona.exception.ClientException;
import org.devsu.prueba.ms_cliente_persona.model.ClientEvent;
import org.devsu.prueba.ms_cliente_persona.model.ClientRequest;
import org.devsu.prueba.ms_cliente_persona.model.ClientResponse;
import org.devsu.prueba.ms_cliente_persona.model.EnumEvent;
import org.devsu.prueba.ms_cliente_persona.model.EnumStatus;
import org.devsu.prueba.ms_cliente_persona.model.GenericResponse;
import org.devsu.prueba.ms_cliente_persona.rabbitmq.ClientProducer;
import org.devsu.prueba.ms_cliente_persona.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	private final MapperServiceImpl mapperService;
	private final ClientProducer clientProducer;

	@Override
	public List<ClientResponse> getAllClients() {
		return this.clientRepository.findAll().stream().map(this::mapToClientResponse).toList();
	}

	@Override
	public ClientResponse findClientById(Long clientId) throws ClientException {
		log.info("Looking for client information: {}", clientId);
		var client = this.findClient(clientId);
		return this.mapToClientResponse(client);
	}

	@Override
	@Transactional
	public ClientResponse createClient(ClientRequest clientRequest) {
		log.info("Creating client: {}", clientRequest);
		var client = this.mapToClient(clientRequest);
		this.clientRepository.save(client);
		this.clientProducer.publish(new ClientEvent(client.getClientId(), client.getName(), EnumEvent.CREATED_CLIENT));
		return this.mapToClientResponse(client);
	}

	@Override
	@Transactional
	public ClientResponse updateClient(Long clientId, ClientRequest clientRequest) throws ClientException {
		log.info("Updating client information: {} - New information: {}", clientId, clientRequest);
		var client = this.findClient(clientId);
		client = this.mapNewData(client, clientRequest);
		this.clientRepository.save(client);
		this.clientProducer.publish(new ClientEvent(client.getClientId(), client.getName(), EnumEvent.UPDATED_CLIENT));
		return this.mapToClientResponse(client);
	}

	@Override
	@Transactional
	public GenericResponse deleteClient(Long clientId) throws ClientException {
		log.info("Deleting client: {}", clientId);
		var client = this.findClient(clientId);
		client.setStatus(EnumStatus.FALSE);
		this.clientRepository.save(client);
		this.clientProducer.publish(new ClientEvent(client.getClientId(), client.getName(), EnumEvent.DELETED_CLIENT));
		return new GenericResponse("Client successfully deleted");
	}

	private ClientResponse mapToClientResponse(Client client) {
		return this.mapperService.convert(client);
	}

	private Client mapToClient(ClientRequest client) {
		return this.mapperService.convertToClient(client);
	}
	
	private Client mapNewData(Client client, ClientRequest clientRequest) {
		return this.mapNewData(client, clientRequest);
	}
	
	private Client findClient(Long clientId) {
		return this.clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientException("Client not found.", HttpStatus.NO_CONTENT));
	}

}
