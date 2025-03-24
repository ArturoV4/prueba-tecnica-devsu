package org.devsu.prueba.ms_cuenta_movimiento.rest;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Client;
import org.devsu.prueba.ms_cuenta_movimiento.exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientRest {
	
	private final RestClient restClient;
	
	@Value("${rest.api.ms-client.url}")
	private String clientUrl;
	
	public Client findClientById(Long clientId) {
		try {
			return this.restClient.get().uri(clientUrl + "/{clientId}", clientId)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.body(Client.class);			
		} catch (RestClientException e) {
			throw new GenericException("An error ocurred while consuming client API: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}

}
