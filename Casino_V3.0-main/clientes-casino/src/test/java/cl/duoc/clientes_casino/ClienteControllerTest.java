package cl.duoc.clientes_casino;

import cl.duoc.clientes_casino.controller.ClienteController;
import cl.duoc.clientes_casino.model.Cliente;
import cl.duoc.clientes_casino.service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
@DisplayName("Prueba en la capa Controller de clientes")
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    private Cliente cliente;
    private Cliente clientesinID;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        cliente = new Cliente(20L, "hugo", "val", 19, "Segundo@gmail.com", 1000000);
        clientesinID = new Cliente(null, "hugo", "val", 19, "Segundo@gmail.com", 1000000);
    }


    @Test
    @DisplayName("GET /api/v1/clientes - Deberia retornar 200 OK y la lista de clientes")
    public void testEndpointListarTodos() throws Exception {
        when(clienteService.findAll()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/v1/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(20))
                .andExpect(jsonPath("$[0].nombre").value("hugo"))
                .andExpect(jsonPath("$[0].apellido").value("val"))
                .andExpect(jsonPath("$[0].edad").value(19))
                .andExpect(jsonPath("$[0].email").value("Segundo@gmail.com"))
                .andExpect(jsonPath("$[0].cantidad_fichas").value(1000000));
    }

    @Test
    @DisplayName("POST /api/v1/clientes - Deberia retornar 201 CREATED y el cliente creado")
    void testEndpointCrear() throws Exception {
        when(clienteService.save(any(Cliente.class)))
                .thenReturn(cliente);

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientesinID)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.nombre").value("Hugo"))
                .andExpect(jsonPath("$.apellido").value("val"))
                .andExpect(jsonPath("$.edad").value(19))
                .andExpect(jsonPath("$.email").value("Segundo@gmail.com"))
                .andExpect(jsonPath("$.cantidad_fichas").value(1000000));
   }

   @Test
   @DisplayName("GET /api/v1/clientes/{id} - Deberia retornar 200 OK y el cliente encontrado")
   void testEndpointBuscarPorId() throws Exception {
        when(clienteService.findById(20L)).thenReturn(cliente);

        mockMvc.perform(get("/api/v1/clientes/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.nombre").value("Hugo"))
                .andExpect(jsonPath("$.apellido").value("val"))
                .andExpect(jsonPath("$.edad").value(19))
                .andExpect(jsonPath("$.email").value("Segundo@gmail.com"))
                .andExpect(jsonPath("$.cantidad_fichas").value(1000000));
   }

   @Test
   @DisplayName("DELETE /api/v1/clientes/{id} - Deberia retornar 204 NO CONTENT")
   void testEndpointEliminar() throws Exception {
        when(clienteService.findById(20L)).thenReturn(cliente);

        mockMvc.perform(delete("/api/v1/clientes/20"))
                .andExpect(status().isNoContent());
   }


}
