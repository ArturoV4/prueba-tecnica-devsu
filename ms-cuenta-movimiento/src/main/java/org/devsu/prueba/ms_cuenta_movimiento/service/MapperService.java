package org.devsu.prueba.ms_cuenta_movimiento.service;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Account;
import org.devsu.prueba.ms_cuenta_movimiento.model.AccountRequest;
import org.devsu.prueba.ms_cuenta_movimiento.model.AccountResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class MapperService implements Converter<Account, AccountResponse> {

	@Override
	public AccountResponse convert(Account source) {
		return AccountResponse.builder()
				.accountId(source.getAccountId())
				.client(source.getClient().getName())
				.accountNumber(source.getAccountNumber())
				.accountType(source.getAccountType().getDescription())
				.initialBalance(source.getBalance())
				.status(source.getStatus().getDescription())
				.build();
	}
	
	public Account convertToAccount(AccountRequest account) {
		return Account.builder()
				.client(account.getClient())
				.accountNumber(account.getAccountNumber())
				.accountType(account.getAccountType())
				.balance(account.getInitialBalance())
				.status(account.getStatus())
				.build();
	}
	
	

}
