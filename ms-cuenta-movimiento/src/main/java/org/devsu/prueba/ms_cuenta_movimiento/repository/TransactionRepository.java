package org.devsu.prueba.ms_cuenta_movimiento.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	@Query("SELECT t, a FROM Transaction t LEFT JOIN m.account a LEFT JOIN a.client c "
			+ "WHERE c.clientId = ?1 AND t.date BETWEEN ?2 AND ?3")
	List<Transaction> findAllByClientAndDate(Long clientId, LocalDateTime startDate, LocalDateTime endDate);
}
