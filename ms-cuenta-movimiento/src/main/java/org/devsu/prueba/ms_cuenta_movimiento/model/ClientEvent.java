package org.devsu.prueba.ms_cuenta_movimiento.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientEvent extends PublishClient {
	private Long clientId;
	private String name;
	
	public ClientEvent(Long clientId, String name, EnumEvent action) {
		super(action.name());
		this.clientId = clientId;
		this.name = name;
	}
	
}
