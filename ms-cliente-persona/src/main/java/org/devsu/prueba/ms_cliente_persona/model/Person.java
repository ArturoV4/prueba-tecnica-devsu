package org.devsu.prueba.ms_cliente_persona.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Person {
	
	private String name;
	@Enumerated(EnumType.STRING)
	private EnumGender gender;
	private Integer age;
	private String identification;
	private String address;
	private String phone;

}
