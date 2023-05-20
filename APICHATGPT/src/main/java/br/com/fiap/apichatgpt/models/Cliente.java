package br.com.fiap.apichatgpt.models;

import br.com.fiap.apichatgpt.controllers.ClienteController;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="tb_gpt_cliente")
public class Cliente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_CLIENTE");
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public EntityModel<Cliente> toModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ClienteController.class).show(id)).withSelfRel(),
                linkTo(methodOn(ClienteController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(ClienteController.class).index(Pageable.unpaged())).withRel("all")

        );
    }
}
