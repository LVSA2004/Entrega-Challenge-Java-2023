package br.com.fiap.apichatgpt.repositories;

import br.com.fiap.apichatgpt.models.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByEmail(String crm);
    @Query("SELECT m FROM Medico m WHERE LOWER(m.crm) LIKE LOWER(CONCAT('%', :crm, '%')) ORDER BY m.crm DESC")
    Page<Medico> findByCRM(@Param("crm") String crm, Pageable pageable);

}
