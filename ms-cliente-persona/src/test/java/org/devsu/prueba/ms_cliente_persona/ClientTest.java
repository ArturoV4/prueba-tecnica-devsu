package org.devsu.prueba.ms_cliente_persona;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.devsu.prueba.ms_cliente_persona.controller.ClientController;
import org.devsu.prueba.ms_cliente_persona.fixture.ClientFixture;
import org.devsu.prueba.ms_cliente_persona.model.ClientRequest;
import org.devsu.prueba.ms_cliente_persona.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@WebMvcTest(controllers = {ClientController.class})
@ContextConfiguration(classes = ClientController.class)
@RequiredArgsConstructor
public class ClientTest {
	
	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;
	
	@MockBean
    private ClientService clientService;
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
    void getClients() throws Exception {
        Mockito.when(clientService.getAllClients())
            .thenReturn(ClientFixture.getClientResponseList());
        mockMvc.perform(get("/clientes"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
	
	@Test
    void findById() throws Exception {
        Mockito.when(clientService.findClientById(any(Long.class)))
            .thenReturn(ClientFixture.getClientResponse());
        mockMvc.perform(get("/clients/{clienteId}", 12345))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createClient() throws Exception {
        var request = ClientFixture.getClientRequest();
        Mockito.when(clientService.createClient(any(ClientRequest.class)))
            .thenReturn(ClientFixture.getClientResponse());
        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateClient() throws Exception {
        var request = ClientFixture.getClientRequest();
        Mockito.when(clientService.updateClient(any(Long.class), any(ClientRequest.class)))
            .thenReturn(ClientFixture.getClientResponse());
        mockMvc.perform(put("/clients/{clienteId}", 12345)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deleteClient() throws Exception {
        Mockito.doNothing().when(clientService).deleteClient(any(Long.class));
        mockMvc.perform(delete("/clients/{clienteId}", 12345))
            .andDo(print())
            .andExpect(status().isOk());
    }

}