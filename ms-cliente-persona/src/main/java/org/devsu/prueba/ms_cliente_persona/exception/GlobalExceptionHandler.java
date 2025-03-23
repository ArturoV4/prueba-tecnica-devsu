package org.devsu.prueba.ms_cliente_persona.exception;

import org.devsu.prueba.ms_cliente_persona.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ExceptionResponse> handleClientExpcetion(ClientException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), ex.getStatus());
	}

}
