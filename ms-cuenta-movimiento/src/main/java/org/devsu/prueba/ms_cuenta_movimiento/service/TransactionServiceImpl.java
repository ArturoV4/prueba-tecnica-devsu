package org.devsu.prueba.ms_cuenta_movimiento.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Transaction;
import org.devsu.prueba.ms_cuenta_movimiento.exception.GenericException;
import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionBalance;
import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionReport;
import org.devsu.prueba.ms_cuenta_movimiento.model.TransactionRequest;
import org.devsu.prueba.ms_cuenta_movimiento.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
    private final AccountService accountService;

	@Override
	@Transactional
	public TransactionBalance createTransaction(TransactionRequest transaction) {
		var account = this.accountService.findByAccountNumber(transaction.getAccountNumber());
        var transactionValue = transaction.getValue();
        
        if (account.getBalance().add(transactionValue).compareTo(BigDecimal.ZERO) < 0) {
            throw new GenericException("Salido no disponible", HttpStatus.BAD_REQUEST);
        }
        
        var transactionEntity = Transaction.builder()
                .account(account)
                .initialBalance(account.getBalance())
                .value(transactionValue)
                .balance(account.getBalance().add(transactionValue))
                .build();
        
        this.transactionRepository.save(transactionEntity);
        
		return TransactionBalance.builder()
				.account(account.getAccountNumber())
				.transactionId(String.valueOf(transactionEntity.getTransactionId()))
				.avaibleBalance(transactionEntity.getBalance())
				.build();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TransactionReport> generateReport(Long clientId, LocalDate startDate, LocalDate endDate) {
		var currentDate = LocalDate.now(ZoneId.systemDefault());
        var endDateSearch =  Objects.requireNonNullElse(endDate, currentDate);
        if (startDate.isAfter(currentDate) || endDateSearch.isAfter(currentDate)) {
            throw new GenericException("Start Date can't be greater than current date", HttpStatus.BAD_REQUEST);
        }
        if (endDateSearch.isBefore(startDate)) {
            throw new GenericException("End Date can't be greater than start date", HttpStatus.BAD_REQUEST);
        }

        var dateStart = startDate.atStartOfDay();
        var dateEnd = endDateSearch.atTime(23,59,59);
        var transactions = this.transactionRepository.findAllByClientAndDate(clientId, dateStart, dateEnd);
        return transactions.stream()
        		.map(transaction -> TransactionReport.builder()
				        .date(transaction.getDate().toLocalDate())
				        .client(transaction.getAccount().getClient().getName())
				        .accountNumber(transaction.getAccount().getAccountNumber())
				        .accountType(transaction.getAccount().getAccountType().getDescription())
				        .initialBalance(transaction.getInitialBalance())
				        .status(transaction.getAccount().getStatus().getBooleanValue())
				        .transaction(transaction.getValue())
				        .avaibleBalance(transaction.getBalance())
				        .build())
        		.toList();
	}

}
