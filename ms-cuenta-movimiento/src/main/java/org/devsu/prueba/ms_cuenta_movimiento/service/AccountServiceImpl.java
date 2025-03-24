package org.devsu.prueba.ms_cuenta_movimiento.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Account;
import org.devsu.prueba.ms_cuenta_movimiento.exception.GenericException;
import org.devsu.prueba.ms_cuenta_movimiento.model.AccountRequest;
import org.devsu.prueba.ms_cuenta_movimiento.model.AccountResponse;
import org.devsu.prueba.ms_cuenta_movimiento.model.EnumStatus;
import org.devsu.prueba.ms_cuenta_movimiento.model.GenericResponse;
import org.devsu.prueba.ms_cuenta_movimiento.repository.AccountRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository accountRepository;
	private final ConversionService conversionService;
	private final ClientService clientService;
	private final MapperService mapperService;
	
	@Override
	@Transactional(readOnly = true)
	public List<AccountResponse> getAccount() {
		return this.accountRepository.findAll()
				.stream()
				.map(account -> conversionService.convert(account, AccountResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public AccountResponse findAccount(String accountNumber) {
		var account = this.findByAccountNumber(accountNumber);
		return conversionService.convert(account, AccountResponse.class);
	}

	@Override
	public AccountResponse createAccount(AccountRequest account) {

		if (account.getInitialBalance().compareTo(BigDecimal.ZERO) <= 0) {
			throw new GenericException("Saldo Inicial debe ser mayor a 0", HttpStatus.BAD_REQUEST);
		}
		
		var client = clientService.findClient(account.getClient().getClientId());
		account.setClient(client);
		var accountEntity = this.mapToAccount(account);
		this.accountRepository.save(accountEntity);
		return this.conversionService.convert(accountEntity, AccountResponse.class);
	}

	@Override
	@Transactional
	public GenericResponse deleteAccount(String accountNumber) {
		var accountEntity = this.findByAccountNumber(accountNumber);
		accountEntity.setStatus(EnumStatus.FALSE);
		this.accountRepository.save(accountEntity);
		return new GenericResponse("Account successfully deleted - Logical Delete");
	}
	
	@Override
	@Transactional(readOnly = true)
	public Account findByAccountNumber(String accountNumber) {
		return this.accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new GenericException("Account not found.", HttpStatus.NO_CONTENT));
	}
	
	private Account mapToAccount(AccountRequest account) {
		return this.mapperService.convertToAccount(account);
	}
	
	

}
