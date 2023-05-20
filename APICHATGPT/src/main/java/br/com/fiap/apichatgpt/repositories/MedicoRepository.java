package br.com.fiap.apichatgpt.repositories;

import br.com.fiap.apichatgpt.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByEmail(String email);

}
