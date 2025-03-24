package org.devsu.prueba.ms_cuenta_movimiento.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GenericException extends RuntimeException {
	
	private HttpStatus status;
	
	public GenericException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
