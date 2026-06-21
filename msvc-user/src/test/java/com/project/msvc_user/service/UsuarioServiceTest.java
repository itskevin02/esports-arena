package com.project.msvc_user.service;

import com.project.msvc_user.model.Usuario;
import com.project.msvc_user.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void listarUsuariosDebeRetornarLista() {

        Usuario usuario = new Usuario();
        usuario.setNombre("Kevin");

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioService.listarUsuarios();

        assertEquals(1, resultado.size());
        assertEquals("Kevin", resultado.get(0).getNombre());

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void guardarUsuarioDebeGuardarCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setNombre("Kevin");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardarUsuario(usuario);

        assertEquals("Kevin", resultado.getNombre());

        verify(usuarioRepository, times(1)).save(usuario);
    }
}