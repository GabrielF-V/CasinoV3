package cl.duoc.clientes_casino;

import cl.duoc.clientes_casino.dto.ClienteDTO;
import cl.duoc.clientes_casino.exception.NotFoundException;
import cl.duoc.clientes_casino.model.Cliente;
import cl.duoc.clientes_casino.repository.ClienteRepository;
import cl.duoc.clientes_casino.service.ClienteService;

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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para clienteService")
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente(
                10L,
                "Yoichi",
                "Isagi",
                21,
                "delantero@gmail.com",
                1200000
        );
    }

    @Test
    @DisplayName("Debe listar todos los clientes correctamente")
    public void ListarTodos_deberiadarlistarclientes(){
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.findAll();

        assertNotNull(resultado);
        assertEquals(1,resultado.size());
        assertEquals("Yoichi", resultado.get(0).getNombre());
        assertEquals("Isagi", resultado.get(0).getApellido());

        verify(clienteRepository).findAll();
    }

    @Test
    @DisplayName("Debe guardar un cliente correctamente")
    public void guardar_debeGuardaryMostrarCliente(){
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.save(cliente);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("Yoichi", resultado.getNombre());
        assertEquals("Isagi", resultado.getApellido());
        assertEquals(21, resultado.getEdad());
        assertEquals("delantero@gmail.com", resultado.getEmail());
        assertEquals(1200000, resultado.getCantidad_fichas());

        verify(clienteRepository).save(cliente);
    }

    @Test
    @DisplayName("Debe buscar un cliente por ID cuando existe")
    public void buscarPorId_cuandoExiste_retornaCliente(){
        when(clienteRepository.findById(10L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.findById(10L);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("Isagi", resultado.getNombre());
        assertEquals("Yoichi", resultado.getApellido());

        verify(clienteRepository).findById(10L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundExeption cuando el cliente no existe")
    public void buscarPorId_cuandoNoExiste_LanzaNotFoundExeption(){
        when(clienteRepository.findById(100L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> clienteService.findById(100L)
        );

        assertEquals("Cliente no encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Debe eliminar un cliente por ID correctamente")
    public void eliminar_deberiaEliminarClientePorID(){
        clienteService.delete(10L);

        verify(clienteRepository).deleteById(10L);
    }

    @Test
    @DisplayName("Debe buscar el correo del cliente correspondiente")
    public void buscarPorEmail_cuandoExiste_retornaCliente(){
        when(clienteRepository.findByEmail("delantero@gmail.com")).thenReturn(cliente);

        Cliente resultado = clienteService.findByEmail("delantero@gmail.com");

        assertNotNull(resultado);
        assertEquals("delantero@gmail.com", resultado.getEmail());

        verify(clienteRepository).findByEmail("delantero@gmail.com");
    }
}

