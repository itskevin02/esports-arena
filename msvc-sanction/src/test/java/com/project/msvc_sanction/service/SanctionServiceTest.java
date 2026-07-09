package com.project.msvc_sanction.service;

import com.project.msvc_sanction.model.Sanction;
import com.project.msvc_sanction.repository.SanctionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SanctionServiceTest {

    @Mock
    private SanctionRepository sanctionRepository;

    @InjectMocks
    private SanctionService sanctionService;

    @Test
    void listarSanctionsTest() {

        Sanction sanction = new Sanction();
        sanction.setMotivo("Motivo 1");

        when(sanctionRepository.findAll()).thenReturn(Arrays.asList(sanction));

        assertEquals(1, sanctionService.listarSanctions().size());

        verify(sanctionRepository, times(1)).findAll();
    }

    @Test
    void buscarSanctionPorIdTest() {

        Sanction sanction = new Sanction();
        sanction.setId(1L);
        sanction.setMotivo("Motivo 1");

        when(sanctionRepository.findById(1L)).thenReturn(Optional.of(sanction));

        Sanction resultado = sanctionService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Motivo 1", resultado.getMotivo());

        verify(sanctionRepository, times(1)).findById(1L);
    }

    @Test
    void guardarSanctionTest() {

        Sanction sanction = new Sanction();
        sanction.setMotivo("Nuevo Motivo");

        when(sanctionRepository.save(sanction)).thenReturn(sanction);

        Sanction resultado = sanctionService.guardarSanction(sanction);

        assertNotNull(resultado);
        assertEquals("Nuevo Motivo", resultado.getMotivo());

        verify(sanctionRepository, times(1)).save(sanction);
    }

    @Test
    void actualizarSanctionTest() {

        Sanction sanctionExistente = new Sanction();
        sanctionExistente.setId(1L);
        sanctionExistente.setUsuarioId(1L);
        sanctionExistente.setMotivo("Motivo 1");
        sanctionExistente.setSeveridad("Media");
        sanctionExistente.setEstado("Activo");

        Sanction nuevaSanction = new Sanction();
        nuevaSanction.setUsuarioId(2L);
        nuevaSanction.setMotivo("Motivo Actualizado");
        nuevaSanction.setSeveridad("Alta");
        nuevaSanction.setEstado("Inactivo");

        when(sanctionRepository.findById(1L)).thenReturn(Optional.of(sanctionExistente));
        when(sanctionRepository.save(any(Sanction.class))).thenReturn(sanctionExistente);

        Sanction resultado = sanctionService.actualizarSanction(1L, nuevaSanction);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getUsuarioId());
        assertEquals("Motivo Actualizado", resultado.getMotivo());
        assertEquals("Alta", resultado.getSeveridad());
        assertEquals("Inactivo", resultado.getEstado());

        verify(sanctionRepository, times(1)).findById(1L);
        verify(sanctionRepository, times(1)).save(any(Sanction.class));
    }

    @Test
    void eliminarSanctionTest() {

        sanctionService.eliminarSanction(1L);

        verify(sanctionRepository, times(1)).deleteById(1L);
    }

}