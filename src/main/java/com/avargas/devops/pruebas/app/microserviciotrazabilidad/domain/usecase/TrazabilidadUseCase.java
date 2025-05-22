package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.usecase;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api.ITrazaServicePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.PedidoModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.RankingEmpleadoModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi.ITrazaPersistencePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
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

    @Override
    public List<PedidoModel> calcularTiempoPorPedido(List<PedidoModel> pedidos) {
        return pedidos.stream()
                .map(pedido -> {
                    try {
                        List<TrazabilidadModel> trazas = trazaPersistencePort.consultarTrazabilidadPedido(
                                pedido.getIdPedido(), String.valueOf(pedido.getIdCliente()));

                        if (trazas.size() < 2) return null;

                        trazas.sort(Comparator.comparing(TrazabilidadModel::getFecha));
                        Date inicio = trazas.get(0).getFecha();
                        Date fin = trazas.get(trazas.size() - 1).getFecha();
                        long segundos = (fin.getTime() - inicio.getTime()) / 1000;

                        Long idEmpleado = trazas.get(trazas.size() - 1).getIdEmpleado();
                        String correo = trazas.get(trazas.size() - 1).getCorreoEmpleado();

                        PedidoModel pedidoModel = new PedidoModel();
                        pedidoModel.setIdPedido(pedido.getIdPedido());
                        pedidoModel.setIdChef(idEmpleado);
                        pedidoModel.setCorreoEmpleado(correo);
                        pedidoModel.setTiempoEnSegundos(segundos);

                        return pedidoModel;

                    } catch (NoDataFoundException e) {
                        log.warn("No hay trazabilidad para el pedido {}: {}", pedido.getIdPedido(), e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public List<RankingEmpleadoModel> calcularRankingPorEmpleado(List<PedidoModel> pedidos) {
        return pedidos.stream()
                .filter(p -> p.getIdChef() != null && p.getTiempoEnSegundos() != null)
                .collect(Collectors.groupingBy(
                        PedidoModel::getIdChef,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                listaPedidos -> {
                                    String correo = listaPedidos.get(0).getCorreoEmpleado();
                                    double promedio = listaPedidos.stream()
                                            .mapToLong(PedidoModel::getTiempoEnSegundos)
                                            .average()
                                            .orElse(0);
                                    return new AbstractMap.SimpleEntry<>(correo, promedio);
                                }
                        )
                ))
                .entrySet().stream()
                .map(entry -> RankingEmpleadoModel.builder()
                        .idEmpleado(entry.getKey())
                        .correoEmpleado(entry.getValue().getKey())
                        .promedioSegundos(entry.getValue().getValue())
                        .build())
                .sorted(Comparator.comparingDouble(RankingEmpleadoModel::getPromedioSegundos))
                .toList();
    }


}