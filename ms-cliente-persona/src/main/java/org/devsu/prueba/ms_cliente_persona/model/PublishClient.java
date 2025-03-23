package org.devsu.prueba.ms_cliente_persona.model;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public abstract class PublishClient {
	
	private final String eventId;
    private final String action;
    private final Date eventDate;

    protected PublishClient(String action) {
        this.eventId = UUID.randomUUID().toString();
        this.action = action;
        this.eventDate = new Date();
    }

}
