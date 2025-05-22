package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.input.rest;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request.TrazabilidadRequestDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.model.UsuarioAutenticado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface ITrazabilidadController {

    ResponseEntity<?> crearTrazaPedido(HttpServletRequest request, TrazabilidadRequestDTO trazabilidadRequestDTO, UsuarioAutenticado usuarioAutenticado);

    ResponseEntity<?> consultarTrazabilidadPedido(Long idPedido, String idCliente);
    ResponseEntity<?> calcularTiempoPorPedido(HttpServletRequest request, Long idRestaurante);

    ResponseEntity<?> rankingPorEmpleado(HttpServletRequest request, Long idRestaurante);
}
