package br.com.fiap.apichatgpt.controllers;
import br.com.fiap.apichatgpt.models.Cliente;
import br.com.fiap.apichatgpt.models.Credencial;
import br.com.fiap.apichatgpt.repositories.ClienteRepository;
import br.com.fiap.apichatgpt.service.TokenService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContaCliente {
    @Autowired
    ClienteRepository repo;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/cliente/registrar")
    public ResponseEntity<Cliente> registrar(@RequestBody @Valid Cliente cliente){
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        repo.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PostMapping("/cliente/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }
}
