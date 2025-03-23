package org.devsu.prueba.ms_cliente_persona.model;

import org.apache.commons.lang.StringUtils;

public enum EnumStatus {
	TRUE,
	FALSE;
	
	public static String getStatusValue(EnumStatus status) {
		return StringUtils.capitalize(status.name());
	}
}
