package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.controllerAdvisor;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.ResponseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    private static final String ERROR = "error";
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ResponseDTO> handleValidacionNegocioException(TokenExpiredException ex) {
        log.error( MensajeError.ERROR_VALIDACION.getMessage() + "{}", ex.getMessage());
        ResponseDTO response = ResponseUtil.error(
                MensajeError.ERROR_VALIDACION.getMessage(),
                Map.of(ERROR, ex.getMessage()),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
