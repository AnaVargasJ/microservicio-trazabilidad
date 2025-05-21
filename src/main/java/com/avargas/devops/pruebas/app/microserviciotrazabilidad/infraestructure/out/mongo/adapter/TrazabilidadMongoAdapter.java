package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.adapter;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi.ITrazaPersistencePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.document.TrazabilidadDocument;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.mapper.ITrazabalidadDocumentMapper;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.repositories.TrazabilidadRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrazabilidadMongoAdapter implements ITrazaPersistencePort {

    private final TrazabilidadRepository trazabilidadRepository;
    private final ITrazabalidadDocumentMapper trazabalidadDocumentMapper;
    @Override
    public TrazabilidadModel guardarTrazabilidad(TrazabilidadModel trazabilidadModel) {
        TrazabilidadDocument document = trazabalidadDocumentMapper.toTrazabilidadDocument(trazabilidadModel);
        TrazabilidadDocument saved = trazabilidadRepository.save(document);
        return trazabalidadDocumentMapper.toTrazabilidadModel(saved);
    }
}
