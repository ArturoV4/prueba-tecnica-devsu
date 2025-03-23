package org.devsu.prueba.ms_cliente_persona.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientException extends RuntimeException {
	
	private HttpStatus status;
	
	public ClientException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
