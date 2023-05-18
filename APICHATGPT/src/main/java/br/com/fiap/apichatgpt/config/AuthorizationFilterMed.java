package br.com.fiap.apichatgpt.config;

import br.com.fiap.apichatgpt.models.Medico;
import br.com.fiap.apichatgpt.repositories.MedicoRepository;
import br.com.fiap.apichatgpt.service.TokenServiceMed;

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
public class AuthorizationFilterMed extends OncePerRequestFilter{

    @Autowired
    TokenServiceMed tokenService;

    @Autowired
    MedicoRepository repo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // pegar o token do header
        String token = getToken(request);

        // se tiver um token
        if (token != null){
            String email = tokenService.validate(token);
            Medico medico = repo.findByEmail(email).get();
            Authentication auth = new UsernamePasswordAuthenticationToken(medico, null, medico.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

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
