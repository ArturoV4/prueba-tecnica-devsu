package org.devsu.prueba.ms_cuenta_movimiento.repository;

import java.util.List;
import java.util.Optional;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Account;
import org.devsu.prueba.ms_cuenta_movimiento.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByAccountNumber(String accountNumber);
	
	List<Account> findByClient(Client client);
	
}
