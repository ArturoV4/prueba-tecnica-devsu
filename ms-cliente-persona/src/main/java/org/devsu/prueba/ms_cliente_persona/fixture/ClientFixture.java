package org.devsu.prueba.ms_cliente_persona.fixture;

import java.util.List;

import org.devsu.prueba.ms_cliente_persona.entity.Client;
import org.devsu.prueba.ms_cliente_persona.model.ClientRequest;
import org.devsu.prueba.ms_cliente_persona.model.ClientResponse;
import org.devsu.prueba.ms_cliente_persona.model.EnumGender;
import org.devsu.prueba.ms_cliente_persona.model.EnumStatus;

public class ClientFixture {

    public static List<Client> getClientList() {
        return List.of(Client.builder()
                .clientId(12345L)
                .name("Arturo")
                .gender(EnumGender.MASCULINO)
                .status(EnumStatus.TRUE)
                .address("Address")
                .age(23)
                .password("Test123")
                .identification("0950579847")
                .phone("0992145076")
            .build());
    }

    public static List<Client> getEmptyClientList() {
        return List.of();
    }

    public static Client getClient() {
        return Client.builder()
        		.clientId(12345L)
                .name("Arturo")
                .gender(EnumGender.MASCULINO)
                .status(EnumStatus.TRUE)
                .address("Address")
                .age(23)
                .password("Test123")
                .identification("0950579847")
                .phone("0992145076")
            .build();
    }

    public static ClientRequest getClientRequest() {
        return ClientRequest.builder()
                .name("Arturo")
                .gender(EnumGender.MASCULINO)
                .status(EnumStatus.TRUE)
                .address("Address")
                .age(23)
                .password("Test123")
                .identification("0950579847")
                .phone("0992145076")
            .build();
    }

    public static List<ClientResponse> getClientResponseList() {
        return List.of(getClientResponse());
    }

    public static ClientResponse getClientResponse() {
        return ClientResponse.builder()
        		.name("Arturo")
                .gender(EnumGender.MASCULINO.name())
                .status(EnumStatus.getStatusValue(EnumStatus.TRUE))
                .address("Address")
                .age(23)
                .identification("0950579847")
                .phone("0992145076")
            .build();
    }
}
