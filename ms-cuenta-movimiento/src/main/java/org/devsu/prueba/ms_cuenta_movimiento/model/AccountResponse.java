package org.devsu.prueba.ms_cuenta_movimiento.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
	
	private Long accountId;
	private String client;
	private String accountNumber;
	private String accountType;
	private BigDecimal initialBalance;
	private String status;

}
