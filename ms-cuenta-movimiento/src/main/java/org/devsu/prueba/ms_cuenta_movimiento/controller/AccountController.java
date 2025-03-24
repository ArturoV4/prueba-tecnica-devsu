package org.devsu.prueba.ms_cuenta_movimiento.controller;

import java.util.List;

import org.devsu.prueba.ms_cuenta_movimiento.model.AccountRequest;
import org.devsu.prueba.ms_cuenta_movimiento.model.AccountResponse;
import org.devsu.prueba.ms_cuenta_movimiento.model.GenericResponse;
import org.devsu.prueba.ms_cuenta_movimiento.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	@Operation(description = "Get All Accounts")
	@GetMapping
	public ResponseEntity<List<AccountResponse>> getAllAccounts() {
		return ResponseEntity.ok(this.accountService.getAccount());
	}
	
	@Operation(description = "Get Account Detail")
	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountResponse> findByAccountNumber(@PathVariable String accountNumber) {
		return ResponseEntity.ok(this.accountService.findAccount(accountNumber));
	}
	
	@Operation(description = "Create Account")
	@PostMapping
	public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest account) {
		return new ResponseEntity<>(this.accountService.createAccount(account), HttpStatus.CREATED);
	}
	
	@Operation(description = "Delete Account")
	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<GenericResponse> deleteAccount(@PathVariable String accountNumber) {
		return ResponseEntity.ok(this.accountService.deleteAccount(accountNumber));
	}
	

}
