package com.portfolio.my_portfolio_backend.config;

import com.portfolio.my_portfolio_backend.service.IUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final IUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilitamos CSRF SOLAMENTE para las rutas de la API REST.
                // Mantenemos CSRF activado para tu panel de administración web por seguridad.
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))

                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/education", "/experience", "/projects", "/skills", "/personal-info").authenticated()
                                .requestMatchers("/education/new", "/education/save", "/education/edit/**", "/education/delete/**").authenticated()
                                .requestMatchers("/experience/new", "/experience/save", "/experience/edit/**", "/experience/delete/**").authenticated()
                                .requestMatchers("/projects/new-project", "/projects/save", "/projects/edit/**", "/projects/delete/**").authenticated()
                                .requestMatchers("/skills/new", "/skills/save", "/skills/edit/**", "/skills/delete/**").authenticated()
                                .requestMatchers("/personal-info/create", "/personal-info/save", "/personal-info/edit/**").authenticated()

                                //  Protegemos las rutas de la API, lectura pública y escritura privada.
                                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/**").authenticated()

                                // Permite el acceso a la documentación de Swagger sin autenticación
                                .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()

                                // Las rutas de búsqueda por personalInfoId
                                .requestMatchers("/education/personal/**", "/experience/personal/**", "/projects/personal/**", "/skills/personal/**").authenticated()
                                .anyRequest().permitAll()
                )
                // 2. Mantenemos tu formulario de login visual intacto
                .formLogin(form ->
                        form.loginPage("/login").permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                // 3. Añadimos HTTP Basic para que Postman y otros clientes puedan
                // autenticarse en la API enviando las credenciales en la cabecera
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
