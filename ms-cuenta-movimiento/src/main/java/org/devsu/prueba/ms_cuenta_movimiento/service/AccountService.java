package org.devsu.prueba.ms_cuenta_movimiento.service;

import java.util.List;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Account;
import org.devsu.prueba.ms_cuenta_movimiento.model.AccountRequest;
import org.devsu.prueba.ms_cuenta_movimiento.model.AccountResponse;
import org.devsu.prueba.ms_cuenta_movimiento.model.GenericResponse;

public interface AccountService {

	List<AccountResponse> getAccount();
	
	AccountResponse findAccount(String accountNumber);
	
	AccountResponse createAccount(AccountRequest account);
	
	Account findByAccountNumber(String accountNumber);
	
	GenericResponse deleteAccount(String accountNumber);
	
}
