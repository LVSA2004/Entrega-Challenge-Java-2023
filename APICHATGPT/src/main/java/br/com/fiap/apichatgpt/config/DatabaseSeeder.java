package br.com.fiap.apichatgpt.config;

import br.com.fiap.apichatgpt.models.Cliente;
import br.com.fiap.apichatgpt.models.Medico;
import br.com.fiap.apichatgpt.repositories.ClienteRepository;
import br.com.fiap.apichatgpt.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{
    @Autowired
    ClienteRepository Repository;

    @Autowired
    MedicoRepository MedRepository;

    @Autowired
    PasswordEncoder encoder;
    @Override
    public void run(String... args) throws Exception {

        Cliente cl1 = Cliente.builder()
                .nome("Luan Sá")
                .telefone("(11) 95954-0882")
                .email("lvssfiap@gmail.com")
                .cpf("515.730.748-96")
                .senha(encoder.encode("rm93057"))
                .build();
        Cliente cl2 = Cliente.builder()
                .nome("André Santi")
                .telefone("(11) 93897-8675")
                .email("santificado@gmail.com")
                .cpf("355.754.374-96")
                .senha(encoder.encode("|(:oUuC<UZ"))
                .build();
        Cliente cl3 = Cliente.builder()
                .nome("Gabriel Henrique")
                .telefone("(19) 92483-9220")
                .email("ainzedamanga@gmail.com")
                .cpf("168.384.445-93")
                .senha(encoder.encode("()faG(Ix40"))
                .build();
        Cliente cl4 = Cliente.builder()
                .nome("Henrique Alves")
                .telefone("(12) 92851-39012")
                .email("SORIYEEEE@gmail.com")
                .cpf("236.862.556-93")
                .senha(encoder.encode(".kTU]l](|!n"))
                .build();

        Repository.saveAll(List.of(
                cl1, cl2, cl3, cl4
        ));

        Medico med1 = Medico.builder()
                .nome("Luiza Ferreira")
                .telefone("(13) 93189-40862")
                .email("lvsi2571@gmail.com")
                .crm("515730-6")
                .senha(encoder.encode("o2ecohTEXl"))
                .afiliacao("Associação Paulista de Medicina")
                .build();
        Medico med2 = Medico.builder()
                .nome("Fernando de Sá")
                .telefone("(16) 93735-5312")
                .email("fds@gmail.com")
                .crm("027730-6")
                .senha(encoder.encode("JsXCaDule6"))
                .afiliacao("Associação Paulista de Medicina")
                .build();
        Medico med3 = Medico.builder()
                .nome("Thayna Isabelle")
                .telefone("(16) 3910-7367")
                .email("ainzedamanga@gmail.com")
                .crm("678544-6")
                .senha(encoder.encode("cogqIw8oX4"))
                .afiliacao("Associação Paulista de Medicina")
                .build();
        Medico med4 = Medico.builder()
                .nome("Luiz Fernando")
                .telefone("(12) 92851-39012")
                .email("SORIYEEEE@gmail.com")
                .crm("955428-6")
                .senha(encoder.encode("fUWCXE4bP6"))
                .afiliacao("Associação Paulista de Medicina")
                .build();

        MedRepository.saveAll(List.of(
                med1, med2, med3, med4
        ));

    }
}
