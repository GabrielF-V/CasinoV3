package cl.duoc.empleado_casino;

import cl.duoc.empleado_casino.controller.EmpleadoController;
import cl.duoc.empleado_casino.model.Empleado;
import cl.duoc.empleado_casino.model.Puesto;
import cl.duoc.empleado_casino.service.EmpleadoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpleadoController.class)
@DisplayName("Prueba en la capa Controller de empleadoes")
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpleadoService empleadoService;

    private Empleado empleado;
    private Empleado empleadosinID;

    private Puesto puesto;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        empleado = new Empleado(20L, "barou", "shoei", "leon@gmail.com",
                        puesto = new Puesto("3d",
                        "bartender"));
        empleadosinID = new Empleado(null, "barou", "shoei", "leon@gmail.com",
                        puesto = new Puesto("3d",
                        "bartender"));
    }


    @Test
    @DisplayName("GET /api/v1/empleadoes - Deberia retornar 200 OK y la lista de empleadoes")
    public void testEndpointListarTodos() throws Exception {
        when(empleadoService.findAll()).thenReturn(List.of(empleado));

        mockMvc.perform(get("/api/v1/empleadoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(20L))
                .andExpect(jsonPath("$[0].nombre").value("barou"))
                .andExpect(jsonPath("$[0].apellido").value("shoei"))
                .andExpect(jsonPath("$[0].gmail").value("leon@gmail.com"))
                .andExpect(jsonPath("$[0].puesto.puesto_codigo").value("3d"))
                .andExpect(jsonPath("$[0].puesto.nombre").value("bartender"));
    }

    @Test
    @DisplayName("POST /api/v1/empleados - Deberia retornar 201 CREATED y el empleado creado")
    void testEndpointCrear() throws Exception {
        when(empleadoService.save(any(Empleado.class)))
                .thenReturn(empleado);

        mockMvc.perform(post("/api/v1/empleadoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleadosinID)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id").value(20L))
                .andExpect(jsonPath("$[0].nombre").value("barou"))
                .andExpect(jsonPath("$[0].apellido").value("shoei"))
                .andExpect(jsonPath("$[0].gmail").value("leon@gmail.com"))
                .andExpect(jsonPath("$[0].puesto.puesto_codigo").value("3d"))
                .andExpect(jsonPath("$[0].puesto.nombre").value("bartender"));
   }

   @Test
   @DisplayName("GET /api/v1/empleadoes/{id} - Deberia retornar 200 OK y el empleado encontrado")
   void testEndpointBuscarPorId() throws Exception {
        when(empleadoService.findById(20L)).thenReturn(empleado);

        mockMvc.perform(get("/api/v1/empleadoes/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(20L))
                .andExpect(jsonPath("$[0].nombre").value("barou"))
                .andExpect(jsonPath("$[0].apellido").value("shoei"))
                .andExpect(jsonPath("$[0].gmail").value("leon@gmail.com"))
                .andExpect(jsonPath("$[0].puesto.puesto_codigo").value("3d"))
                .andExpect(jsonPath("$[0].puesto.nombre").value("bartender"));
   }

   @Test
   @DisplayName("DELETE /api/v1/empleadoes/{id} - Deberia retornar 204 NO CONTENT")
   void testEndpointEliminar() throws Exception {
        when(empleadoService.findById(20L)).thenReturn(empleado);

        mockMvc.perform(delete("/api/v1/empleadoes/20"))
                .andExpect(status().isNoContent());
   }


}
