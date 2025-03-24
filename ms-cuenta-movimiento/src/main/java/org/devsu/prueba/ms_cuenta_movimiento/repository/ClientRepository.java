package org.devsu.prueba.ms_cuenta_movimiento.repository;

import org.devsu.prueba.ms_cuenta_movimiento.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
