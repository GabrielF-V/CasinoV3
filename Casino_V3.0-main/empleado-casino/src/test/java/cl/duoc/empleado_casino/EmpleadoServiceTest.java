package cl.duoc.empleado_casino;


import cl.duoc.empleado_casino.exception.NotFoundException;
import cl.duoc.empleado_casino.model.Empleado;
import cl.duoc.empleado_casino.model.Puesto;
import cl.duoc.empleado_casino.repository.EmpleadoRepository;
import cl.duoc.empleado_casino.service.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para empleadoService")
public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    private Empleado empleado;

    private Puesto puesto;

    @BeforeEach
    public void setUp(){



        empleado = new Empleado(
                10L,
                "barou",
                "shoei",
                "leon@gmail.com",
                puesto = new Puesto("3d",
                                    "bartender")


        );
    }

    @Test
    @DisplayName("Debe listar todos los empleados correctamente")
    public void ListarTodos_deberiadarlistarempleadoes(){
        when(empleadoRepository.findAll()).thenReturn(List.of(empleado));

        List<Empleado> resultado = empleadoService.findAll();

        assertNotNull(resultado);
        assertEquals(1,resultado.size());
        assertEquals("barou", resultado.get(0).getNombre());
        assertEquals("shoie", resultado.get(0).getApellido());
        assertEquals("bartender", resultado.get(0).getPuesto().getNombre());

        verify(empleadoRepository).findAll();
    }

    @Test
    @DisplayName("Debe guardar un empleado correctamente")
    public void guardar_debeGuardaryMostrarEmpleado(){
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado resultado = empleadoService.save(empleado);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("barou", resultado.getNombre());
        assertEquals("shoie", resultado.getApellido());
        assertEquals("leon@gmail.com", resultado.getGmail());
        assertEquals("3d", resultado.getPuesto().getPuesto_codigo());
        assertEquals("bartender", resultado.getPuesto().getNombre());

        verify(empleadoRepository).save(empleado);
    }

    @Test
    @DisplayName("Debe buscar un empleado por ID cuando existe")
    public void buscarPorId_cuandoExiste_retornaEmpleado(){
        when(empleadoRepository.findById(10L)).thenReturn(Optional.of(empleado));

        Empleado resultado = empleadoService.findById(10L);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("barou", resultado.getNombre());
        assertEquals("shoie", resultado.getApellido());
        assertEquals("bartender", resultado.getPuesto().getNombre());


        verify(empleadoRepository).findById(10L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundExeption cuando el empleado no existe")
    public void buscarPorId_cuandoNoExiste_LanzaNotFoundExeption(){
        when(empleadoRepository.findById(100L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> empleadoService.findById(100L)
        );

        assertEquals("Empleado no encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Debe eliminar un empleado por ID correctamente")
    public void eliminar_deberiaEliminarEmpleadoPorID(){
        empleadoService.delete(10L);

        verify(empleadoRepository).deleteById(10L);
    }


}

