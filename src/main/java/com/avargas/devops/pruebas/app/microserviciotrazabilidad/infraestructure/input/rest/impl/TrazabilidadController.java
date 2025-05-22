package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.input.rest.impl;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request.TrazabilidadRequestDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.RankingEmpleadoDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.handler.IHandlerTrazabilidad;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.constants.EndPointApi;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.utils.ResponseUtil;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.input.rest.ITrazabilidadController;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.jwt.TokenJwtConfig;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.model.UsuarioAutenticado;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.SwaggerConstants;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.SwaggerResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.constants.EndPointApi.*;
import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.MessageUtils.MESAGE_EXITOSO;
import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.MessageUtils.RAKING_EMPLEADO;
import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.SwaggerConstants.DESC_ID_CLIENTE;
import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.SwaggerConstants.DESC_ID_PEDIDO;

@RestController
@RequestMapping(EndPointApi.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = SwaggerConstants.TAG_TRAZABILIDAD, description = SwaggerConstants.TAG_TRAZABILIDAD_DESC)
public class TrazabilidadController implements ITrazabilidadController {

    private final IHandlerTrazabilidad handlerTrazabilidad;

    @Override
    @PostMapping(CREATE_TRAZABILIDAD)
    @Operation(
            summary = SwaggerConstants.OP_CREAR_TRAZABILIDAD_SUMMARY,
            description = SwaggerConstants.OP_CREAR_TRAZABILIDAD_DESC
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerResponseCode.CREATED, description = SwaggerConstants.RESPONSE_201_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.BAD_REQUEST, description = SwaggerConstants.RESPONSE_400_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.INTERNAL_SERVER_ERROR, description = SwaggerConstants.RESPONSE_500_DESC)
    })
    public ResponseEntity<?> crearTrazaPedido(HttpServletRequest request, @RequestBody TrazabilidadRequestDTO trazabilidadRequestDTO, @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
        handlerTrazabilidad.guardarTrazabilidad(trazabilidadRequestDTO);
        return new ResponseEntity<>(ResponseUtil.response(MESAGE_EXITOSO.getMessage(), HttpStatus.CREATED.value()), HttpStatus.CREATED);

    }

    @Override
    @GetMapping(BUSCAR_TRAZABILIDAD_CLIENTE_PEDIDO)
    @PreAuthorize("hasRole('ROLE_CLI')")
    @Operation(
            summary = SwaggerConstants.OP_CONSULTAR_TRAZABILIDAD_SUMMARY,
            description = SwaggerConstants.OP_CONSULTAR_TRAZABILIDAD_DESC
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerResponseCode.OK, description = SwaggerConstants.RESPONSE_200_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.BAD_REQUEST, description = SwaggerConstants.RESPONSE_400_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.UNAUTHORIZED, description = SwaggerConstants.RESPONSE_401_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.FORBIDDEN, description = SwaggerConstants.RESPONSE_403_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.NOT_FOUND, description = SwaggerConstants.RESPONSE_404_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.INTERNAL_SERVER_ERROR, description = SwaggerConstants.RESPONSE_500_DESC)
    })
    public ResponseEntity<?> consultarTrazabilidadPedido(
            @Parameter(description = DESC_ID_PEDIDO, required = true)
            @PathVariable("idPedido") Long idPedido,
            @Parameter(description = DESC_ID_CLIENTE, required = true)
            @PathVariable("idCliente") String idCliente) {

        return new ResponseEntity<>(
                ResponseUtil.error(
                        MESAGE_EXITOSO.getMessage(),
                        handlerTrazabilidad.consultarTrazabilidadPedido(idPedido, idCliente),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }


    @Override
    @GetMapping(CONSULTAR_TIEMPO_PEDIDOS)
    @PreAuthorize("hasRole('ROLE_PROP')")
    @Operation(
            summary = SwaggerConstants.OP_CONSULTAR_TIEMPO_PEDIDO_SUMMARY,
            description = SwaggerConstants.OP_CONSULTAR_TIEMPO_PEDIDO_DESC
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerResponseCode.OK, description = SwaggerConstants.RESPONSE_200_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.BAD_REQUEST, description = SwaggerConstants.RESPONSE_400_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.UNAUTHORIZED, description = SwaggerConstants.RESPONSE_401_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.FORBIDDEN, description = SwaggerConstants.RESPONSE_403_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.NOT_FOUND, description = SwaggerConstants.RESPONSE_404_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.INTERNAL_SERVER_ERROR, description = SwaggerConstants.RESPONSE_500_DESC)
    })
    public ResponseEntity<?> calcularTiempoPorPedido(HttpServletRequest request,
                                                     @Parameter(description = DESC_ID_PEDIDO, required = true)
                                                     @PathVariable("idRestaurante") Long idRestaurante) {
        String token = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);
        return new ResponseEntity<>(
                ResponseUtil.error(
                        MESAGE_EXITOSO.getMessage(),
                        handlerTrazabilidad.calcularTiempoPorPedido(idRestaurante, token),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @Override
    @GetMapping(CONSULTAR_RANKN_EMPLEADOS)
    @PreAuthorize("hasRole('ROLE_PROP')")
    @Operation(
            summary = SwaggerConstants.OP_RANKING_EMPLEADOS_SUMMARY,
            description = SwaggerConstants.OP_RANKING_EMPLEADOS_DESC
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerResponseCode.OK, description = SwaggerConstants.RESPONSE_200_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.BAD_REQUEST, description = SwaggerConstants.RESPONSE_400_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.UNAUTHORIZED, description = SwaggerConstants.RESPONSE_401_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.FORBIDDEN, description = SwaggerConstants.RESPONSE_403_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.NOT_FOUND, description = SwaggerConstants.RESPONSE_404_DESC),
            @ApiResponse(responseCode = SwaggerResponseCode.INTERNAL_SERVER_ERROR, description = SwaggerConstants.RESPONSE_500_DESC)
    })
    public ResponseEntity<?> rankingPorEmpleado(HttpServletRequest request,
                                                @Parameter(description = DESC_ID_PEDIDO, required = true)
                                                @PathVariable("idRestaurante")
                                                Long idRestaurante) {
        String token = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);

        List<RankingEmpleadoDTO> ranking = handlerTrazabilidad.rankingPorEmpleado(idRestaurante, token);

        return new ResponseEntity<>(
                ResponseUtil.success(RAKING_EMPLEADO.getMessage(),
                        ranking
                ),
                HttpStatus.OK
        );
    }

}
