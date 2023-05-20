package br.com.fiap.apichatgpt.service;

import br.com.fiap.apichatgpt.repositories.ClienteRepository;

import br.com.fiap.apichatgpt.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService{

    @Autowired
    ClienteRepository Repository;

    @Autowired
    MedicoRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " não encontrado!"));
    }


}
