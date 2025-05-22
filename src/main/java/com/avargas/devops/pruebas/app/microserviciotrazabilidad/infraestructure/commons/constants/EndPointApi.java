package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.commons.constants;

public interface EndPointApi {

    String BASE_PATH = "/api/v1/trazabilidad";
    String URL_BUSCAR_POR_CORREO = "/buscarPorCorreo/{correo}";

    String CREATE_TRAZABILIDAD = "/crearTrazabilidad";
    String BUSCAR_TRAZABILIDAD_CLIENTE_PEDIDO = "/{idCliente}/pedido/{idPedido}";
    String FILTRAR_PEDIDOS_ID_RESTAURANTE = "/pedidos/{idRestaurante}";
}
