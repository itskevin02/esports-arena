package com.project.msvc_registration.controller;

import com.project.msvc_registration.model.Registration;
import com.project.msvc_registration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<Registration> listarRegistrations() {
        return registrationService.listarRegistrations();
    }

    @PostMapping
    public Registration guardarRegistration(@Valid @RequestBody Registration registration) {
        return registrationService.guardarRegistration(registration);
    }

    @GetMapping("/{id}")
    public Registration buscarRegistration(@PathVariable Long id) {
        return registrationService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Registration actualizarRegistration(@PathVariable Long id,
                                               @RequestBody Registration registration) {

        return registrationService.actualizarRegistration(id, registration);
    }

    @DeleteMapping("/{id}")
    public void eliminarRegistration(@PathVariable Long id) {
        registrationService.eliminarRegistration(id);
    }
}