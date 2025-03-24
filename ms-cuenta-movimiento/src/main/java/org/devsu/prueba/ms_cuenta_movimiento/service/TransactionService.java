package org.devsu.prueba.ms_cuenta_movimiento.service;

import java.time.LocalDate;
import java.util.List;

import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionBalance;
import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionReport;
import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionRequest;

import jakarta.annotation.Nullable;

public interface TransactionService {
	
	TransactionBalance createTransaction(TransactionRequest transaction);
	
	List<TransactionReport> generateReport(Long clientId, LocalDate startDate, @Nullable LocalDate endDate);

}
