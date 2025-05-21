package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.docs.SwaggerTrazabilidadDescriptions;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = SwaggerTrazabilidadDescriptions.TRAZABILIDAD_REQUEST)
public class TrazabilidadRequestDTO {

    @Schema(description = SwaggerTrazabilidadDescriptions.ID_PEDIDO, example = "1001", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idPedido;

    @Schema(description = SwaggerTrazabilidadDescriptions.ID_CLIENTE, example = "C123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String idCliente;

    @Schema(description = SwaggerTrazabilidadDescriptions.CORREO_CLIENTE, example = "cliente@correo.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String correoCliente;

    @Schema(description = SwaggerTrazabilidadDescriptions.FECHA, example = "2025-05-21T15:30:00Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date fecha;

    @Schema(description = SwaggerTrazabilidadDescriptions.ESTADO_ANTERIOR, example = "EN_PREPARACION", requiredMode = Schema.RequiredMode.REQUIRED)
    private String estadoAnterior;

    @Schema(description = SwaggerTrazabilidadDescriptions.ESTADO_NUEVO, example = "EN_REPARTO", requiredMode = Schema.RequiredMode.REQUIRED)
    private String estadoNuevo;

    @Schema(description = SwaggerTrazabilidadDescriptions.ID_EMPLEADO, example = "200", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long idEmpleado;

    @Schema(description = SwaggerTrazabilidadDescriptions.CORREO_EMPLEADO, example = "empleado@restaurante.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String correoEmpleado;
}