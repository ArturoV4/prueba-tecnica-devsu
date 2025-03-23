package org.devsu.prueba.ms_cliente_persona.repository;

import org.devsu.prueba.ms_cliente_persona.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
