package org.devsu.prueba.ms_cuenta_movimiento.exception;


import org.devsu.prueba.ms_cuenta_movimiento.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ExceptionResponse> handleClientExpcetion(GenericException ex) {
		return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), ex.getStatus());
	}

}
