package com.avargas.devops.pruebas.app.microserviciotrazabilidad;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi.ITrazaPersistencePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.usecase.TrazabilidadUseCase;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TrazabilidadUseCaseTest {

    @Mock
    private ITrazaPersistencePort trazaPersistencePort;

    @InjectMocks
    private TrazabilidadUseCase trazabilidadUseCase;

    @Test
    @Order(1)
    void saveTrazabilidad_delegatesToPersistencePort() {
        TrazabilidadModel model = new TrazabilidadModel();
        model.setIdPedido(1L);
        model.setIdCliente("123");
        model.setCorreoCliente("cliente@mail.com");
        model.setEstadoAnterior("PENDIENTE");
        model.setEstadoNuevo("EN_PREPARACION");
        model.setIdEmpleado(10L);
        model.setCorreoEmpleado("empleado@mail.com");

        trazabilidadUseCase.saveTrazabilidad(model);

        verify(trazaPersistencePort).guardarTrazabilidad(model);
    }
}