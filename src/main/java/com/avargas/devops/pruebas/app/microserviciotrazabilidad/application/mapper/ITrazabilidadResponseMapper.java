package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.TrazabilidadRespnseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrazabilidadResponseMapper {

    @Mapping(source = "id", target = "idTrazabilidad")
    TrazabilidadRespnseDTO toTrazabilidadResponse(TrazabilidadModel trazabilidadModel);
    List<TrazabilidadRespnseDTO> toTrazabilidadResponse(List<TrazabilidadModel> trazabilidadModel);


}
