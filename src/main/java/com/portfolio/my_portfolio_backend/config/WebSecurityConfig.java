package com.portfolio.my_portfolio_backend.config;

import com.portfolio.my_portfolio_backend.service.IUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final IUserDetailsService userDetailsService;

    // Un @Bean le dice a Spring: "Crea este objeto y guárdalo en el contenedor"
    // Spring usará este objeto para aplicar la configuración de seguridad
    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/education", "/experience", "/projects", "/skills", "/personal-info").authenticated()
                                .requestMatchers("/education/new", "/education/save", "/education/edit/**", "/education/delete/**").authenticated()
                                .requestMatchers("/experience/new", "/experience/save", "/experience/edit/**", "/experience/delete/**").authenticated()
                                .requestMatchers("/projects/new-project", "/projects/save", "/projects/edit/**", "/projects/delete/**").authenticated()
                                .requestMatchers("/skills/new", "/skills/save", "/skills/edit/**", "/skills/delete/**").authenticated()
                                .requestMatchers("/personal-info/create", "/personal-info/save", "/personal-info/edit/**").authenticated()

                                //Las rutas de búsqueda por personalInfoId tambien deben ser protegidas si son para administración
                                .requestMatchers("/education/personal/**", "/experience/personal/**", "/projects/personal/**", "/skills/personal/**").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(form ->
                        form.loginPage("/login").permitAll()

                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Esta ruta no la hemos creado, la proporciona Spring Security para hacer logout
                        .logoutRequestMatcher(request ->
                                "GET".equalsIgnoreCase(request.getMethod()) && "/logout".equals(request.getRequestURI())
                        ) // Permite cerrar sesion con un simple GET a /logout en vez de un POST
                        .logoutSuccessUrl("/login?logout") // Indica donde redirigir después de cerrar sesion
                        .invalidateHttpSession(true) // Destruye los datos de la sesion en memoria
                        .deleteCookies("JSESSIONID")
                        .permitAll() //Asegura que cualquiera pueda ejecutar esta ruta sin bloqueos
                );

        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

















//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("admin")  // withDefaultPasswordEncoder está deprecado porque no es útil en producción, solo para pruebas. Lo cambiamos por withUsername.
//                .username("admin")
//                .password(passwordEncoder.encode("1234"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }


}
