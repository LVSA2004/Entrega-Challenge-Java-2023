package br.com.fiap.apichatgpt.controllers;

import br.com.fiap.apichatgpt.models.ChatGPT;
import br.com.fiap.apichatgpt.models.Medico;
import br.com.fiap.apichatgpt.repositories.ChatGPTRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;

@RestController
@RequestMapping("/baymax/chatbot")
public class ChatGPTController {
    @Autowired
    ChatGPTRepository repo;

    @Autowired
    PagedResourcesAssembler<ChatGPT> assembler;

    Logger log = LoggerFactory.getLogger(ChatGPTController.class);
    private static final String API_KEY = "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";

    @GetMapping
    public PagedModel<EntityModel<ChatGPT>> index(@PageableDefault(size = 5) Pageable pageable) {
        return assembler.toModel(repo.findAll(pageable));
    }

    @GetMapping("/{id}")
    public EntityModel<ChatGPT> show(@PathVariable Long id) {
        log.info("buscar chat com id: " + id);
        ChatGPT chatGPT = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return chatGPT.toModel();
    }

    @PostMapping("/api")
    public ResponseEntity<ChatGPT> create(@RequestBody @Valid ChatGPT input) {
        OpenAiService service = new OpenAiService(API_KEY);
        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(input.getPergunta())
                .maxTokens(100)
                .build();

        String resposta = service.createCompletion(request).getChoices().get(0).getText();
        ChatGPT chatGPT = new ChatGPT(input.getPergunta(), resposta);

        log.info("Saída do chatbot: " + chatGPT);
        repo.save(chatGPT);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatGPT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ChatGPT>destroy(@PathVariable Long id) {
        log.info("deletar chat com o id: " + id);
        ChatGPT chatgpt = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat não encontrado"));;
        repo.delete(chatgpt);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationExceptions(ConstraintViolationException ex) {
        log.error("Erro de validação: ", ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        log.error("Erro não esperado: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado. Tente novamente mais tarde.");
    }


}
