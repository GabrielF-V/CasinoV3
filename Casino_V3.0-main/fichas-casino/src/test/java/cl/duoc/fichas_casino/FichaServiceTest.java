package cl.duoc.fichas_casino;


import cl.duoc.fichas_casino.exception.NotFoundException;
import cl.duoc.fichas_casino.model.Color;
import cl.duoc.fichas_casino.model.Ficha;
import cl.duoc.fichas_casino.repository.FichaRepository;
import cl.duoc.fichas_casino.service.FichaService;
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
@DisplayName("Pruebas unitarias para fichaService")
public class FichaServiceTest {

    @Mock
    private FichaRepository fichaRepository;

    @InjectMocks
    private FichaService fichaService;

    private Ficha ficha;

    private Color color;

    @BeforeEach
    public void setUp(){



        ficha = new Ficha(
                10L,
                324,
                color = new Color(
                        1L,
                        "blanco",
                        200,
                        "ficha blanca de 200 de valor"
                )


        );
    }

    @Test
    @DisplayName("Debe listar todos los fichas correctamente")
    public void ListarTodos_deberiadarlistarfichaes(){
        when(fichaRepository.findAll()).thenReturn(List.of(ficha));

        List<Ficha> resultado = fichaService.findAll();

        assertNotNull(resultado);
        assertEquals(1,resultado.size());
        assertEquals(324, resultado.get(0).getCantidad());
        assertEquals("blanco", resultado.get(0).getColor().getNombre());
        assertEquals(200, resultado.get(0).getColor().getValor());

        verify(fichaRepository).findAll();
    }

    @Test
    @DisplayName("Debe guardar un ficha correctamente")
    public void guardar_debeGuardaryMostrarFicha(){
        when(fichaRepository.save(ficha)).thenReturn(ficha);

        Ficha resultado = fichaService.save(ficha);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getNro_ficha());
        assertEquals(324, resultado.getCantidad());
        assertEquals("blanco", resultado.getColor().getNombre());
        assertEquals(200, resultado.getColor().getValor());


        verify(fichaRepository).save(ficha);
    }

    @Test
    @DisplayName("Debe buscar un ficha por ID cuando existe")
    public void buscarPorId_cuandoExiste_retornaFicha(){
        when(fichaRepository.findById(10L)).thenReturn(Optional.of(ficha));

        Ficha resultado = fichaService.findByNro(10L);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getNro_ficha());
        assertEquals(324, resultado.getCantidad());
        assertEquals("blanco", resultado.getColor().getNombre());




        verify(fichaRepository).findById(10L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundExeption cuando el ficha no existe")
    public void buscarPorId_cuandoNoExiste_LanzaNotFoundExeption(){
        when(fichaRepository.findById(100L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> fichaService.findByNro(100L)
        );

        assertEquals("Ficha no encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Debe eliminar un ficha por ID correctamente")
    public void eliminar_deberiaEliminarFichaPorID(){
        fichaService.delete(10L);

        verify(fichaRepository).deleteById(10L);
    }


}

