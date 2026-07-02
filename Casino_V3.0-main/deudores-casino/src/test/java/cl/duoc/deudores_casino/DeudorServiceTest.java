package cl.duoc.deudores_casino;

import cl.duoc.deudores_casino.dto.ClienteDTO;
import cl.duoc.deudores_casino.model.Deudor;
import cl.duoc.deudores_casino.repository.DeudorRepository;
import cl.duoc.deudores_casino.service.DeudorService;
import cl.duoc.deudores_casino.exception.NotFoundException;
import cl.duoc.deudores_casino.model.Deudor;
import cl.duoc.deudores_casino.repository.DeudorRepository;
import cl.duoc.deudores_casino.service.DeudorService;
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
@DisplayName("Pruebas unitarias para deudorService")
public class DeudorServiceTest {

    @Mock
    private DeudorRepository deudorRepository;

    @InjectMocks
    private DeudorService deudorService;

    private Deudor deudor;

    private ClienteDTO clienteDTO;

    @BeforeEach
    public void setUp(){



        deudor = new Deudor(
                10L,
                150000,
                true,
                1L
        );
    }

    @Test
    @DisplayName("Debe listar todos los deudores correctamente")
    public void ListarTodos_deberiadarlistardeudores(){
        when(deudorRepository.findAll()).thenReturn(List.of(deudor));

        List<Deudor> resultado = deudorService.findAll();

        assertNotNull(resultado);
        assertEquals(1,resultado.size());
        assertEquals(150000, resultado.get(0).getCant_deuda());

        verify(deudorRepository).findAll();
    }

    @Test
    @DisplayName("Debe guardar un deudor correctamente")
    public void guardar_debeGuardaryMostrarDeudor(){
        when(deudorRepository.save(deudor)).thenReturn(deudor);

        Deudor resultado = deudorService.save(deudor);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId_deudor());
        assertEquals(150000, resultado.getCant_deuda());
        assertEquals(true, resultado.isBan());
        assertEquals(1L, resultado.getCliente());

        verify(deudorRepository).save(deudor);
    }

    @Test
    @DisplayName("Debe buscar un deudor por ID cuando existe")
    public void buscarPorId_cuandoExiste_retornaDeudor(){
        when(deudorRepository.findById(10L)).thenReturn(Optional.of(deudor));

        Deudor resultado = deudorService.findById(10L);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId_deudor());
        assertEquals(150000, resultado.getCant_deuda());
        assertEquals(1L, resultado.getCliente());

        verify(deudorRepository).findById(10L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundExeption cuando el deudor no existe")
    public void buscarPorId_cuandoNoExiste_LanzaNotFoundExeption(){
        when(deudorRepository.findById(100L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> deudorService.findById(100L)
        );

        assertEquals("Deudor no encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Debe eliminar un deudor por ID correctamente")
    public void eliminar_deberiaEliminarDeudorPorID(){
        deudorService.delete(10L);

        verify(deudorRepository).deleteById(10L);
    }


}

