package br.com.fiap.apichatgpt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class SecurityConfig {

    @Autowired
    AuthorizationFilter authorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/cliente/registrar").permitAll()
                .requestMatchers(HttpMethod.POST, "/cliente/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/medico/registrar").permitAll()
                .requestMatchers(HttpMethod.POST, "/medico/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/baymax/cliente").permitAll()
                .requestMatchers(HttpMethod.GET, "/baymax/cliente/pesquisa/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/baymax/cliente/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/baymax/cliente/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/baymax/medico").permitAll()
                .requestMatchers(HttpMethod.GET, "/baymax/medico/pesquisa/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/baymax/medico/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/baymax/medico/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/baymax/chatbot").permitAll()
                .requestMatchers(HttpMethod.POST, "/baymax/chatbot/api").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/baymax/chatbot/{id}").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
