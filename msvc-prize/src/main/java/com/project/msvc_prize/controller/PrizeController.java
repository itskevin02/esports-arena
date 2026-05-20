package com.project.msvc_prize.controller;

import com.project.msvc_prize.model.Prize;
import com.project.msvc_prize.service.PrizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prizes")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @GetMapping
    public List<Prize> listarPrizes() {
        return prizeService.listarPrizes();
    }

    @PostMapping
    public Prize guardarPrize(@Valid @RequestBody Prize prize) {
        return prizeService.guardarPrize(prize);
    }

    @GetMapping("/{id}")
    public Prize buscarPrize(@PathVariable Long id) {
        return prizeService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Prize actualizarPrize(@PathVariable Long id,
                                 @RequestBody Prize prize) {

        return prizeService.actualizarPrize(id, prize);
    }

    @DeleteMapping("/{id}")
    public void eliminarPrize(@PathVariable Long id) {
        prizeService.eliminarPrize(id);
    }
}