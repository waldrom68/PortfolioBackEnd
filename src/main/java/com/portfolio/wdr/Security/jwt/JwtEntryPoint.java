/*
 * Chequeo si hay un Token válido
*/

package com.portfolio.wdr.Security.jwt;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger; // Ojo aqui, hay varias clases llamadas igual, debe tener el mismo origen que LoggerFactory
import org.slf4j.LoggerFactory;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
          
            logger.error("Fallo el metodo commence");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        
    }
    
}
