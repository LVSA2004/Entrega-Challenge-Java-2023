package br.com.fiap.apichatgpt.service;

import br.com.fiap.apichatgpt.repositories.MedicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public class AuthorizationServiceMed implements UserDetailsService {

    @Autowired
    MedicoRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Médico " + email + " não encontrado!"));
    }
}
