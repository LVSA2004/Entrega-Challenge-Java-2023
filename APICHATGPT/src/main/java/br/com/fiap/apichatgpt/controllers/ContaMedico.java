package br.com.fiap.apichatgpt.controllers;

import br.com.fiap.apichatgpt.models.Medico;
import br.com.fiap.apichatgpt.models.Credencial;
import br.com.fiap.apichatgpt.repositories.MedicoRepository;
import br.com.fiap.apichatgpt.service.TokenService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContaMedico {
    @Autowired
    MedicoRepository repo;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/medico/registrar")
    public ResponseEntity<Medico> registrar(@RequestBody @Valid Medico medico){
        medico.setSenha(encoder.encode(medico.getSenha()));
        repo.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico);
    }

    @PostMapping("/medico/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }
}
