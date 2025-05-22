package com.avargas.devops.pruebas.app.microserviciotrazabilidad;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.PedidoModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi.ITrazaPersistencePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.usecase.TrazabilidadUseCase;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    @Order(2)
    void consultarTrazabilidadPedido_delegatesToPersistencePort() {
        Long idPedido = 1L;
        String idCliente = "123";

        trazabilidadUseCase.consultarTrazabilidadPedido(idPedido, idCliente);

        verify(trazaPersistencePort).consultarTrazabilidadPedido(idPedido, idCliente);
    }

    @Test
    @Order(3)
    void calcularTiempoPorPedido_deberiaCalcularTiempoCorrectamente() {
        PedidoModel pedido = new PedidoModel();
        pedido.setIdPedido(1L);
        pedido.setIdCliente(123L);

        List<TrazabilidadModel> trazas = new ArrayList<>();

        Date inicio = new Date();
        Date fin = new Date(inicio.getTime() + 60000); // +60 segundos

        TrazabilidadModel t1 = new TrazabilidadModel();
        t1.setFecha(inicio);
        t1.setIdEmpleado(10L);
        t1.setCorreoEmpleado("empleado@mail.com");

        TrazabilidadModel t2 = new TrazabilidadModel();
        t2.setFecha(fin);
        t2.setIdEmpleado(10L);
        t2.setCorreoEmpleado("empleado@mail.com");

        trazas.add(t1);
        trazas.add(t2);

        when(trazaPersistencePort.consultarTrazabilidadPedido(eq(1L), eq("123"))).thenReturn(trazas);


        List<PedidoModel> resultado = trazabilidadUseCase.calcularTiempoPorPedido(List.of(pedido));

        assertEquals(1, resultado.size());
        PedidoModel calculado = resultado.get(0);
        assertEquals(60, calculado.getTiempoEnSegundos());
        assertEquals(10L, calculado.getIdChef());
        assertEquals("empleado@mail.com", calculado.getCorreoEmpleado());
    }

}
