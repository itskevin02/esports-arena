package com.project.msvc_result.controller;

import com.project.msvc_result.model.Result;
import com.project.msvc_result.service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<Result> listarResults() {
        return resultService.listarResults();
    }

    @PostMapping
    public Result guardarResult(@Valid @RequestBody Result result) {
        return resultService.guardarResult(result);
    }

    @GetMapping("/{id}")
    public Result buscarResult(@PathVariable Long id) {
        return resultService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Result actualizarResult(@PathVariable Long id,
                                   @RequestBody Result result) {

        return resultService.actualizarResult(id, result);
    }

    @DeleteMapping("/{id}")
    public void eliminarResult(@PathVariable Long id) {
        resultService.eliminarResult(id);
    }
}