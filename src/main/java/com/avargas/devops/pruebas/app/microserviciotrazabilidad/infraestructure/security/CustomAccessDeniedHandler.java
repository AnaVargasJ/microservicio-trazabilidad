package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security;


import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.jwt.TokenJwtConfig.CONTENT_TYPE;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final String ERROR = "error";
    private final String RUTA = "error";
    private final String ERROR_MENSAJE = "Acceso denegado: No tiene permisos para realizar esta operación";
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(CONTENT_TYPE);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(ERROR,ERROR_MENSAJE);
        errorResponse.put(RUTA, request.getRequestURI());


        response.getWriter().write(new ObjectMapper().writeValueAsString(ResponseUtil.error("Acceso denegado: No tiene permisos para realizar esta operación",
                errorResponse,
                HttpStatus.FORBIDDEN.value())));
    }
}
