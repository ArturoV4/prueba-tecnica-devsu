package org.devsu.prueba.ms_cliente_persona.service;

import java.util.Objects;

import org.devsu.prueba.ms_cliente_persona.entity.Client;
import org.devsu.prueba.ms_cliente_persona.model.ClientRequest;
import org.devsu.prueba.ms_cliente_persona.model.ClientResponse;
import org.devsu.prueba.ms_cliente_persona.model.EnumStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements Converter<Client, ClientResponse> {

	@Override
	public ClientResponse convert(Client source) {
		return ClientResponse.builder()
				.clientId(source.getClientId())
				.name(source.getName())
				.gender(source.getGender().name())
				.age(source.getAge())
				.identification(source.getIdentification())
				.address(source.getAddress())
				.phone(source.getPhone())
				.status(EnumStatus.getStatusValue(source.getStatus()))
				.build();
	}
	
	public Client convertToClient(ClientRequest client) {
		return Client.builder()
				.name(client.getName())
				.gender(client.getGender())
				.age(client.getAge())
				.identification(client.getIdentification())
				.address(client.getAddress())
				.phone(client.getPhone())
				.password(client.getPassword())
				.status(client.getStatus())
				.build();
	}
	
	public Client mapNewData(Client client, ClientRequest clientRequest) {
		return Client.builder()
				.clientId(client.getClientId())
				.name(Objects.requireNonNullElse(clientRequest.getName(), client.getName()))
				.gender(Objects.requireNonNullElse(clientRequest.getGender(), client.getGender()))
				.age(Objects.requireNonNullElse(clientRequest.getAge(), client.getAge()))
				.identification(Objects.requireNonNullElse(clientRequest.getIdentification(), client.getIdentification()))
				.address(Objects.requireNonNullElse(clientRequest.getAddress(), client.getAddress()))
				.phone(Objects.requireNonNullElse(clientRequest.getPhone(), client.getPhone()))
				.password(Objects.requireNonNullElse(clientRequest.getPassword(), client.getPassword()))
				.status(Objects.requireNonNullElse(clientRequest.getStatus(), client.getStatus()))
				.build();
	}

}
