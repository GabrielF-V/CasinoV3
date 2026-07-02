package cl.duoc.deudores_casino;

import cl.duoc.deudores_casino.controller.DeudorController;
import cl.duoc.deudores_casino.model.Deudor;
import cl.duoc.deudores_casino.service.DeudorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeudorController.class)
@DisplayName("Prueba en la capa Controller de deudores")
public class DeudorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DeudorService deudorService;

    private Deudor deudor;
    private Deudor deudorsinID;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        deudor = new Deudor(20L, 150000, true, 1L);
        deudorsinID = new Deudor(20L, 150000, true, 1L);
    }


    @Test
    @DisplayName("GET /api/v1/deudores - Deberia retornar 200 OK y la lista de deudores")
    public void testEndpointListarTodos() throws Exception {
        when(deudorService.findAll()).thenReturn(List.of(deudor));

        mockMvc.perform(get("/api/v1/deudores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_deudor").value(20))
                .andExpect(jsonPath("$[0].cant_deuda").value(150000))
                .andExpect(jsonPath("$[0].ban").value(true))
                .andExpect(jsonPath("$[0].cliente").value(1L));
    }

    @Test
    @DisplayName("POST /api/v1/deudores - Deberia retornar 201 CREATED y el deudor creado")
    void testEndpointCrear() throws Exception {
        when(deudorService.save(any(Deudor.class)))
                .thenReturn(deudor);

        mockMvc.perform(post("/api/v1/deudores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deudorsinID)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id_deudor").value(20))
                .andExpect(jsonPath("$[0].cant_deuda").value(150000))
                .andExpect(jsonPath("$[0].ban").value(true))
                .andExpect(jsonPath("$[0].cliente").value(1L));
   }

   @Test
   @DisplayName("GET /api/v1/deudores/{id} - Deberia retornar 200 OK y el deudor encontrado")
   void testEndpointBuscarPorId() throws Exception {
        when(deudorService.findById(20L)).thenReturn(deudor);

        mockMvc.perform(get("/api/v1/deudores/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$[0].id_deudor").value(20))
                .andExpect(jsonPath("$[0].cant_deuda").value(150000))
                .andExpect(jsonPath("$[0].ban").value(true))
                .andExpect(jsonPath("$[0].cliente").value(1L));
   }

   @Test
   @DisplayName("DELETE /api/v1/deudores/{id} - Deberia retornar 204 NO CONTENT")
   void testEndpointEliminar() throws Exception {
        when(deudorService.findById(20L)).thenReturn(deudor);

        mockMvc.perform(delete("/api/v1/deudores/20"))
                .andExpect(status().isNoContent());
   }


}
