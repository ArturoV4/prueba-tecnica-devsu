package org.devsu.prueba.ms_cuenta_movimiento.model;

import java.math.BigDecimal;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

	private String accountNumber;
	private EnumAccountType accountType;
	private BigDecimal initialBalance;
	private EnumStatus status;
	private Client client;
	
}
