package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.RankingEmpleadoDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.RankingEmpleadoModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRankingEmpleadoMapper {

    RankingEmpleadoDTO toDto(RankingEmpleadoModel model);

    List<RankingEmpleadoDTO> toDtoList(List<RankingEmpleadoModel> modelList);
}
