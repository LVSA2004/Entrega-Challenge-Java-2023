package br.com.fiap.apichatgpt.controllers;

import br.com.fiap.apichatgpt.models.Cliente;
import br.com.fiap.apichatgpt.repositories.ClienteRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("baymax/cliente")
public class ClienteController {
    Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteRepository repo;

    @Autowired
    PagedResourcesAssembler<Cliente> assembler;


    @GetMapping
    public PagedModel<EntityModel<Cliente>>  index(@PageableDefault(size = 5) Pageable pageable){
        return assembler.toModel(repo.findAll(pageable));
    }

    @GetMapping("/pesquisa")
    public Page<Cliente> search(@RequestParam String cpf,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return repo.findByCPF(cpf, pageable);
    }

    @GetMapping("{id}")
    public EntityModel<Cliente> show(@PathVariable Long id) {
        log.info("buscar cliente com id: " + id);
        Cliente cliente = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return cliente.toModel();
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody @Valid Cliente cliente) {
        log.info("cadastrar cliente: " + cliente);
        repo.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid Cliente clienteAtualizado) {
        log.info("atualizar o cliente com id: " + id);
        Cliente cliente = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        BeanUtils.copyProperties(clienteAtualizado, cliente, "id");
        repo.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cliente> destroy(@PathVariable Long id) {
        log.info("deletar cliente com o id: " + id);
        Cliente cliente = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));;
        repo.delete(cliente);
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
