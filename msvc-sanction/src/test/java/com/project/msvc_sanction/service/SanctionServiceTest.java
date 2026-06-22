package com.project.msvc_sanction.service;

import com.project.msvc_sanction.model.Sanction;
import com.project.msvc_sanction.repository.SanctionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SanctionServiceTest {

    @Mock
    private SanctionRepository sanctionRepository;

    @InjectMocks
    private SanctionService sanctionService;

    @Test
    void listarSanctionsDebeRetornarLista() {

        Sanction sanction = new Sanction();
        sanction.setMotivo("Conducta antideportiva");

        when(sanctionRepository.findAll()).thenReturn(List.of(sanction));

        List<Sanction> resultado = sanctionService.listarSanctions();

        assertEquals(1, resultado.size());
        assertEquals(
                "Conducta antideportiva",
                resultado.get(0).getMotivo()
        );

        verify(sanctionRepository, times(1)).findAll();
    }

    @Test
    void guardarSanctionDebeGuardarCorrectamente() {

        Sanction sanction = new Sanction();
        sanction.setMotivo("Conducta antideportiva");

        when(sanctionRepository.save(sanction)).thenReturn(sanction);

        Sanction resultado =
                sanctionService.guardarSanction(sanction);

        assertEquals(
                "Conducta antideportiva",
                resultado.getMotivo()
        );

        verify(sanctionRepository, times(1)).save(sanction);
    }
}