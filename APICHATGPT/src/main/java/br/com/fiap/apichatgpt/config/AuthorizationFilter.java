package br.com.fiap.apichatgpt.config;

import br.com.fiap.apichatgpt.models.Cliente;
import br.com.fiap.apichatgpt.models.Medico;
import br.com.fiap.apichatgpt.repositories.ClienteRepository;
import br.com.fiap.apichatgpt.repositories.MedicoRepository;
import br.com.fiap.apichatgpt.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    ClienteRepository repository;

    @Autowired
    MedicoRepository repo;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // pegar o token do header
        var token = getToken(request);

        var token2 = getToken(request);

        // se for valido, autenticar o usuario
        if (token != null){
            String email = tokenService.valide(token);
            Cliente cliente = repository.findByEmail(email).get();
            Authentication auth = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        if (token2 != null){
            String email = tokenService.valide(token);
            Medico medico = repo.findByEmail(email).get();
            Authentication autho = new UsernamePasswordAuthenticationToken(medico, null, medico.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autho);
        }
        // chama o próximo
        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
            return null;
        }


        return header.replace("Bearer ", "");
    }
}
