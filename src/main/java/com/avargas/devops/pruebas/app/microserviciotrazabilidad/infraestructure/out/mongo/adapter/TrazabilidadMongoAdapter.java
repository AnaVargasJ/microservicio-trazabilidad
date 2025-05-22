package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.adapter;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi.ITrazaPersistencePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.Exception;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.NoDataFoundException;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.document.TrazabilidadDocument;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.mapper.ITrazabalidadDocumentMapper;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.repositories.TrazabilidadRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @Override
    public List<TrazabilidadModel> consultarTrazabilidadPedido(Long idPedido, String idCliente) {
        List<TrazabilidadDocument> documentList = trazabilidadRepository.findAllByIdPedidoAndIdCliente(idPedido, idCliente);
        if (documentList.isEmpty())
            throw new NoDataFoundException(Exception.NO_EXISTE_DATOS.getMessage());
        return trazabalidadDocumentMapper.toTrazabilidadModelList(documentList);
    }
}
