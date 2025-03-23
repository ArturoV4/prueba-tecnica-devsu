package org.devsu.prueba.ms_cliente_persona.entity;

import org.devsu.prueba.ms_cliente_persona.model.EnumStatus;
import org.devsu.prueba.ms_cliente_persona.model.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "clientes")
public class Client extends Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String password;
    @Enumerated(EnumType.STRING)
    private EnumStatus status;
}
