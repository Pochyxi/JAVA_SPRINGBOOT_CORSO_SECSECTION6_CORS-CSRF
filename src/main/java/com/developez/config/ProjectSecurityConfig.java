package com.developez.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/* @Configuration, indica che è una classe di configurazione per l'applicazione Spring.
 */
@Configuration
public class ProjectSecurityConfig {

    /*
     * Il metodo defaultSecurityFilterChain è annotato con @Bean e restituisce un oggetto SecurityFilterChain.
     * Questo metodo configura le regole di autorizzazione per le richieste HTTP.
     * In particolare, specifica che le richieste agli endpoint ("/myAccount", "myLoans", "myBalance", "myCards") devono
     * essere autenticate, mentre le richieste agli endpoint ("notices", "contact") devono essere accessibili a tutti
     * senza autenticazione. Viene utilizzato il metodo http.authorizeHttpRequests() per definire queste regole.
     *
     * Il metodo defaultSecurityFilterChain configura anche la gestione del login tramite form (http.formLogin()) e
     * la gestione delle richieste HTTP di base (http.httpBasic()).
     * Infine, viene restituito l'oggetto http costruito tramite il metodo build().
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http ) throws Exception {
        http.csrf( AbstractHttpConfigurer::disable )
                .authorizeHttpRequests( ( requests ) -> requests
                        .requestMatchers( "/myAccount", "/myLoans", "/myBalance", "/myCards", "/user" ).authenticated()
                        .requestMatchers( "/notices", "/contact", "/register" ).permitAll() )
                .formLogin( Customizer.withDefaults() )
                .httpBasic( Customizer.withDefaults() );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
