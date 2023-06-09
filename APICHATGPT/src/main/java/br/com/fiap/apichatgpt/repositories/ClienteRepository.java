package br.com.fiap.apichatgpt.repositories;

import br.com.fiap.apichatgpt.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> findByEmail(String email);
}
