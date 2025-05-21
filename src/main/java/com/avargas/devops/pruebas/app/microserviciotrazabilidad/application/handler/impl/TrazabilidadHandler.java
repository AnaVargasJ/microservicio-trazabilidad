package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.handler.impl;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request.TrazabilidadRequestDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.handler.IHandlerTrazabilidad;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper.ITrazabilidadRequestMapper;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api.ITrazaServicePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrazabilidadHandler implements IHandlerTrazabilidad {

    private final ITrazaServicePort trazaServicePort;
    private final ITrazabilidadRequestMapper requestMapper;
    @Override
    public void guardarTrazabilidad(TrazabilidadRequestDTO requestDTO) {
        TrazabilidadModel trazabilidadModel = requestMapper.toTrazabilidadDTOToModel(requestDTO);
        trazaServicePort.saveTrazabilidad(trazabilidadModel);
    }
}
