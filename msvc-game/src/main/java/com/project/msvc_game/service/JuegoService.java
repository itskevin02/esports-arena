package com.project.msvc_game.service;

import com.project.msvc_game.model.Juego;
import com.project.msvc_game.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> listarJuegos() {
        return juegoRepository.findAll();
    }

    public Juego guardarJuego(Juego juego) {
        return juegoRepository.save(juego);
    }
}