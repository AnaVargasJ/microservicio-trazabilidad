package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security;


import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.ResponseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.utils.ResponseUtil;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.jwt.TokenJwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.MessageUtils.MESSAGE_TOKEN;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String ERROR = "error";

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        ResponseDTO responseDTO = ResponseUtil.error(
                MESSAGE_TOKEN.getMessage(),
                Map.of(ERROR, authException.getMessage()),
                HttpStatus.FORBIDDEN.value()
        );

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(TokenJwtConfig.CONTENT_TYPE);
        response.getWriter().write(objectMapper.writeValueAsString(responseDTO));
    }
}