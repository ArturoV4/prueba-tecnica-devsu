package org.devsu.prueba.ms_cliente_persona.controller;

import java.util.List;

import org.devsu.prueba.ms_cliente_persona.model.ClientRequest;
import org.devsu.prueba.ms_cliente_persona.model.ClientResponse;
import org.devsu.prueba.ms_cliente_persona.model.GenericResponse;
import org.devsu.prueba.ms_cliente_persona.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "ClientController")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	
	private final ClientService clientService;
	
	@Operation(description = "Get All Clients", tags = {"ClientController"})
	@GetMapping
	public ResponseEntity<List<ClientResponse>> getClients() {
		return ResponseEntity.ok(this.clientService.getAllClients());
	}
	
	@Operation(description = "Find Client By ID", tags = {"ClientController"})
	@GetMapping("/{clientId}")
	public ResponseEntity<ClientResponse> findById(@PathVariable Long clientId) {
		return ResponseEntity.ok(this.clientService.findClientById(clientId));
	}
	
	@Operation(description = "Create Client", tags = {"ClientController"})
	@PostMapping
	public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest client) {
		return new ResponseEntity<>(this.clientService.createClient(client), HttpStatus.CREATED);
	}
	
	@Operation(description = "Update Client", tags = {"ClientController"})
	@PutMapping("/{clientId}")
	public ResponseEntity<ClientResponse> updateClient(@PathVariable Long clientId, @RequestBody ClientRequest client) {
		return ResponseEntity.ok(this.clientService.updateClient(clientId, client));
	}
	
	@Operation(description = "Delete Client", tags = {"ClientController"})
	@DeleteMapping("/{clientId}")
	public ResponseEntity<GenericResponse> deleteClient(@PathVariable Long clientId) {
		return ResponseEntity.ok(this.clientService.deleteClient(clientId));
	}
		
}
