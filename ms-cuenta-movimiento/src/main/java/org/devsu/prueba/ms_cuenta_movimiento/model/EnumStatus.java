package org.devsu.prueba.ms_cuenta_movimiento.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumStatus {
	
	TRUE("True"),
	FALSE("False");
	
	private final String description;
	
	public boolean getBooleanValue() {
		return TRUE.description.equals(this.getDescription());
	}

}
