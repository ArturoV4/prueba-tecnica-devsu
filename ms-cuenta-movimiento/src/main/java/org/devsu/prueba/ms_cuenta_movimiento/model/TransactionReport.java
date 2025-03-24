package org.devsu.prueba.ms_cuenta_movimiento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReport {
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private String client;
	private String accountNumber;
	private String accountType;
	private BigDecimal initialBalance;
	private boolean status;
	private BigDecimal transaction;
	private BigDecimal avaibleBalance;

}
