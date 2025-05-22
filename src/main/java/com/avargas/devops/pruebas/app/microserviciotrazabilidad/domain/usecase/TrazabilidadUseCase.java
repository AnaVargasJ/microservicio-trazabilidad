package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.usecase;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api.ITrazaServicePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi.ITrazaPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TrazabilidadUseCase implements ITrazaServicePort {

    private final ITrazaPersistencePort trazaPersistencePort;
    @Override
    public void saveTrazabilidad(TrazabilidadModel trazabilidadModel) {
        trazaPersistencePort.guardarTrazabilidad(trazabilidadModel);
    }

    @Override
    public List<TrazabilidadModel> consultarTrazabilidadPedido(Long idPedido, String idCliente) {
        return trazaPersistencePort.consultarTrazabilidadPedido(idPedido, idCliente);
    }
}
