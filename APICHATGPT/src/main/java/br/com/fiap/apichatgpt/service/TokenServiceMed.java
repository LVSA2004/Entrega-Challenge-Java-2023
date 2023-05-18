package br.com.fiap.apichatgpt.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import br.com.fiap.apichatgpt.repositories.MedicoRepository;
import br.com.fiap.apichatgpt.models.TokenJwt;
import br.com.fiap.apichatgpt.models.Credencial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
@Service
public class TokenServiceMed {
    @Autowired
    MedicoRepository repo;

    public TokenJwt generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withSubject(credencial.email())
                .withIssuer("Baymax")
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .sign(alg);
        return new TokenJwt(token);
    }

    public String validate(String token) {
        Algorithm alg = Algorithm.HMAC256("secret");
        return JWT.require(alg)
                .withIssuer("Baymax")
                .build()
                .verify(token)
                .getSubject();
    }
}
