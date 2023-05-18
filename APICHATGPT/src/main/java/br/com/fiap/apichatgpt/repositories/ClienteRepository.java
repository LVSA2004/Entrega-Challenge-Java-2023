package br.com.fiap.apichatgpt.repositories;

import br.com.fiap.apichatgpt.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> findByEmail(String email);
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.cpf) LIKE LOWER(CONCAT('%', :cpf, '%')) ORDER BY c.cpf DESC")
    Page<Cliente> findByCPF(@Param("cpf") String cpf, Pageable pageable);
}
