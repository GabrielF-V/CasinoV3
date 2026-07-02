package cl.duoc.fichas_casino;

import cl.duoc.fichas_casino.controller.FichaController;
import cl.duoc.fichas_casino.model.Color;
import cl.duoc.fichas_casino.model.Ficha;
import cl.duoc.fichas_casino.service.FichaService;
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

@WebMvcTest(FichaController.class)
@DisplayName("Prueba en la capa Controller de fichas")
public class FichasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FichaService fichaService;

    private Ficha ficha;
    private Ficha fichasinID;

    private Color color;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        ficha = new Ficha(20L, 324,
                            color = new Color(1L, "blanco", 200, "ficha blanca de 200 de valor"
                            ));
        fichasinID =  new Ficha(null, 324,
                                color = new Color(1L, "blanco", 200, "ficha blanca de 200 de valor"
                                ));
    }


    @Test
    @DisplayName("GET /api/v1/fichas - Deberia retornar 200 OK y la lista de fichas")
    public void testEndpointListarTodos() throws Exception {
        when(fichaService.findAll()).thenReturn(List.of(ficha));

        mockMvc.perform(get("/api/v1/fichas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nro_ficha").value(20L))
                .andExpect(jsonPath("$[0].cantidad").value(324))
                .andExpect(jsonPath("$[0].color.nro_color").value(1L))
                .andExpect(jsonPath("$[0].color.nombre").value("blanco"))
                .andExpect(jsonPath("$[0].color.valor").value(200))
                .andExpect(jsonPath("$[0].color.descripcion").value("ficha blanca de 200 de valor"));
    }

    @Test
    @DisplayName("POST /api/v1/fichas - Deberia retornar 201 CREATED y el ficha creado")
    void testEndpointCrear() throws Exception {
        when(fichaService.save(any(Ficha.class)))
                .thenReturn(ficha);

        mockMvc.perform(post("/api/v1/fichas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fichasinID)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].nro_ficha").value(20L))
                .andExpect(jsonPath("$[0].cantidad").value(324))
                .andExpect(jsonPath("$[0].color.nro_color").value(1L))
                .andExpect(jsonPath("$[0].color.nombre").value("blanco"))
                .andExpect(jsonPath("$[0].color.valor").value(200))
                .andExpect(jsonPath("$[0].color.descripcion").value("ficha blanca de 200 de valor"));
   }

   @Test
   @DisplayName("GET /api/v1/fichas/{id} - Deberia retornar 200 OK y el ficha encontrado")
   void testEndpointBuscarPorId() throws Exception {
        when(fichaService.findByNro(20L)).thenReturn(ficha);

        mockMvc.perform(get("/api/v1/fichas/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nro_ficha").value(20L))
                .andExpect(jsonPath("$[0].cantidad").value(324))
                .andExpect(jsonPath("$[0].color.nro_color").value(1L))
                .andExpect(jsonPath("$[0].color.nombre").value("blanco"))
                .andExpect(jsonPath("$[0].color.valor").value(200))
                .andExpect(jsonPath("$[0].color.descripcion").value("ficha blanca de 200 de valor"));
   }

   @Test
   @DisplayName("DELETE /api/v1/fichas/{id} - Deberia retornar 204 NO CONTENT")
   void testEndpointEliminar() throws Exception {
        when(fichaService.findByNro(20L)).thenReturn(ficha);

        mockMvc.perform(delete("/api/v1/fichas/20"))
                .andExpect(status().isNoContent());
   }


}
