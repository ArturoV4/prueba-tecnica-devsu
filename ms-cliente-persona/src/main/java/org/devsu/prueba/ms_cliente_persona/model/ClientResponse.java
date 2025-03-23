package org.devsu.prueba.ms_cliente_persona.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
	
	private Long clientId;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String status;

}
