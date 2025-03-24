package org.devsu.prueba.ms_cuenta_movimiento.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "rabbitmq.queue")
public class RabbitProperties {
	
	private String name;
    private String exchange;
    private Routing routing;

    @Data
    public static class Routing {
        private String key;
    }

}
