package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.PedidoResponseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.PedidoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoResponseMapper {

    @Mapping(source = "id", target = "idPedido")
    @Mapping(source = "idChef", target = "idEmpleado")
    @Mapping(target = "correoEmpleado", source = "correoEmpleado")
    @Mapping(target = "tiempoEnSegundos", source = "tiempoEnSegundos")
    PedidoResponseDTO toResponseDto(PedidoModel pedidoModel);
}
