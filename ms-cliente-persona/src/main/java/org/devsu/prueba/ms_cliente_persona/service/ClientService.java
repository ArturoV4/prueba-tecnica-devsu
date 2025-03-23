package org.devsu.prueba.ms_cliente_persona.service;

import java.util.List;

import org.devsu.prueba.ms_cliente_persona.exception.ClientException;
import org.devsu.prueba.ms_cliente_persona.model.ClientRequest;
import org.devsu.prueba.ms_cliente_persona.model.ClientResponse;
import org.devsu.prueba.ms_cliente_persona.model.GenericResponse;

public interface ClientService {
	
	List<ClientResponse> getAllClients();
	
	ClientResponse findClientById(Long clientId) throws ClientException;
	
	ClientResponse createClient(ClientRequest clientRequest);
	
	ClientResponse updateClient(Long clientId, ClientRequest clientRequest) throws ClientException;
	
	GenericResponse deleteClient(Long clientId) throws ClientException;

}
