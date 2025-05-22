package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.mapper;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.document.TrazabilidadDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ITrazabalidadDocumentMapper {

    TrazabilidadDocument toTrazabilidadDocument(TrazabilidadModel trazabilidadModel);
    TrazabilidadModel toTrazabilidadModel(TrazabilidadDocument trazabilidadDocument);
    List<TrazabilidadModel> toTrazabilidadModelList(List<TrazabilidadDocument> trazabilidadDocument);



}
