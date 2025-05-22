package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.controllerAdvisor;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.ResponseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.exception.ClientException;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.utils.ResponseUtil;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.JsonParseClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.controllerAdvisor.MensajeError.ERROR_PARSEO;

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

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ResponseDTO> handleClientException(ClientException ex) {
        log.error( MensajeError.ERROR_VALIDACION.getMessage() + "{}", ex.getMessage());
        ResponseDTO response = ResponseUtil.error(
                MensajeError.ERROR_DATA.getMessage(),
                Map.of(ERROR, ex.getMessage()),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.ClientException.class)
    public ResponseEntity<ResponseDTO> handleClientInfraException(com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.ClientException ex) {
        log.error( MensajeError.ERROR_VALIDACION.getMessage() + "{}", ex.getMessage());
        ResponseDTO response = ResponseUtil.error(
                MensajeError.ERROR_DATA.getMessage(),
                Map.of(ERROR, ex.getMessage()),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseClientException.class)
    public ResponseEntity<ResponseDTO> handleJsonParseClientException(JsonParseClientException ex) {
        log.error("Error al parsear respuesta JSON: {}", ex.getMessage());
        ResponseDTO response = ResponseUtil.error(
                ERROR_PARSEO.getMessage(),
                Map.of(ERROR, ex.getMessage()),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
