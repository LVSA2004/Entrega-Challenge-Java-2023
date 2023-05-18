package br.com.fiap.apichatgpt.controllers;

import br.com.fiap.apichatgpt.models.Cliente;
import br.com.fiap.apichatgpt.repositories.ClienteRepository;
import br.com.fiap.apichatgpt.repositories.MedicoRepository;
import br.com.fiap.apichatgpt.models.Medico;

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
@RequestMapping("baymax/medico")
public class MedicoController {
    Logger log = LoggerFactory.getLogger(MedicoController.class);

    @Autowired
    MedicoRepository repo;

    @Autowired
    PagedResourcesAssembler<Medico> assembler;


    @GetMapping
    public PagedModel<EntityModel<Medico>>  index(@PageableDefault(size = 5) Pageable pageable){
        return assembler.toModel(repo.findAll(pageable));
    }

    @GetMapping("/pesquisa")
    public Page<Medico> search(@RequestParam String crm,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return repo.findByCRM(crm, pageable);
    }

    @GetMapping("{id}")
    public EntityModel<Medico> show(@PathVariable Long id) {
        log.info("buscar médico com id: " + id);
        Medico medico = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado"));
        return medico.toModel();
    }

    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody @Valid Medico medico) {
        log.info("Cadastrar Médico: " + medico);
        repo.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Medico> update(@PathVariable Long id, @RequestBody @Valid Medico medicoAtualizado) {
        log.info("atualizar o médico com id: " + id);
        Medico medico = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado"));
        BeanUtils.copyProperties(medicoAtualizado, medico, "id");
        repo.save(medico);
        return ResponseEntity.ok(medico);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Medico> destroy(@PathVariable Long id) {
        log.info("deletar médico com o id: " + id);
        Medico medico = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado"));;
        repo.delete(medico);
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
