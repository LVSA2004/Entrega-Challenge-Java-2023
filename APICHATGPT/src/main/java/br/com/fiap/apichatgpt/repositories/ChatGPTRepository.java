package br.com.fiap.apichatgpt.repositories;

import br.com.fiap.apichatgpt.models.ChatGPT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatGPTRepository extends JpaRepository<ChatGPT, Long> {

}
