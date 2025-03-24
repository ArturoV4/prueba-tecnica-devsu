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
public class TransactionBalance {
	
	private String transactionId;
	private String account;
	private BigDecimal avaibleBalance;

}
