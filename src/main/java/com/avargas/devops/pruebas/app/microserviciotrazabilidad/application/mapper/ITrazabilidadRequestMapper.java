package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request.TrazabilidadRequestDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrazabilidadRequestMapper {

    TrazabilidadModel toTrazabilidadDTOToModel(TrazabilidadRequestDTO requestDTO);
}
