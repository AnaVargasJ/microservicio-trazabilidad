package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.input.rest.impl;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request.TrazabilidadRequestDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.handler.IHandlerTrazabilidad;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.constants.EndPointApi;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.utils.ResponseUtil;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.input.rest.ITrazabilidadController;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.security.model.UsuarioAutenticado;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.SwaggerConstants;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.SwaggerResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.constants.EndPointApi.CREATE_TRAZABILIDAD;
import static com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared.MessageUtils.MESAGE_EXITOSO;

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
}
