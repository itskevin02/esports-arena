package com.project.msvc_sanction.controller;

import com.project.msvc_sanction.model.Sanction;
import com.project.msvc_sanction.service.SanctionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sanctions")
public class SanctionController {

    @Autowired
    private SanctionService sanctionService;

    @GetMapping
    public List<Sanction> listarSanctions() {
        return sanctionService.listarSanctions();
    }

    @PostMapping
    public Sanction guardarSanction(@Valid @RequestBody Sanction sanction) {
        return sanctionService.guardarSanction(sanction);
    }

    @GetMapping("/{id}")
    public Sanction buscarSanction(@PathVariable Long id) {
        return sanctionService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Sanction actualizarSanction(@PathVariable Long id,
                                       @RequestBody Sanction sanction) {

        return sanctionService.actualizarSanction(id, sanction);
    }

    @DeleteMapping("/{id}")
    public void eliminarSanction(@PathVariable Long id) {
        sanctionService.eliminarSanction(id);
    }
}