package com.developez.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Definisce una classe che estende il filtro OncePerRequestFilter di Spring,  il quale garantisce che il filtro venga
// applicato esattamente una volta per ogni richiesta. Il suo scopo è recuperare il token CSRF dalla richiesta corrente
// e impostarlo come un header di risposta. Questo può essere utile quando si lavora con client JavaScript che
// leggono il token CSRF da un header di risposta invece che da un cookie.
public class CsrfCookieFilter extends OncePerRequestFilter {
    // Questo metodo viene chiamato una volta per ogni richiesta HTTP
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {
        // Cerca un attributo della richiesta con il nome della classe CsrfToken
        // Questo attributo viene solitamente impostato dal framework Spring Security quando si abilita la protezione CSRF
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        // Controlla se l'attributo esiste (cioè, se la protezione CSRF è abilitata)
        if(null != csrfToken.getHeaderName()){
            // Se l'attributo esiste, imposta un header di risposta con il nome del token CSRF e il suo valore
            // Questo permette ai client di leggere il token CSRF da un header di risposta
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }

        // Passa la richiesta e la risposta al prossimo filtro nella catena
        // Se questo è l'ultimo filtro, la richiesta viene effettivamente gestita dal server
        filterChain.doFilter(request, response);
    }
}
