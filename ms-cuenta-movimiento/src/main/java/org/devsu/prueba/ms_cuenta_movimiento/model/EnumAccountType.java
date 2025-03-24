package org.devsu.prueba.ms_cuenta_movimiento.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumAccountType {
	
	CORRIENTE("Corriente"),
	AHORRO("Ahorro");
	
	private final String description;

}
