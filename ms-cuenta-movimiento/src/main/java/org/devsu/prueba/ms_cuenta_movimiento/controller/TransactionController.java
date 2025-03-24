package org.devsu.prueba.ms_cuenta_movimiento.controller;

import java.time.LocalDate;
import java.util.List;

import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionBalance;
import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionReport;
import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionRequest;
import org.devsu.prueba.ms_cuenta_movimiento.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController {
	
	private final TransactionService transactionService;
	
	@Operation(description = "Register Transaction")
	@PostMapping
	public ResponseEntity<TransactionBalance> registerTransaction(@RequestBody TransactionRequest transaction) {
		return new ResponseEntity<>(this.transactionService.createTransaction(transaction), HttpStatus.CREATED);
	}
	
	@Operation(description = "Transaction Report By Client")
	@GetMapping("/{clientId}")
	public ResponseEntity<List<TransactionReport>> report(@PathVariable Long clientId,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) {
		return ResponseEntity.ok(this.transactionService.generateReport(clientId, startDate, endDate));
	}

}
