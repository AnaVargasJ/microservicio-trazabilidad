package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared;

public class SwaggerConstants {
    public static final String TAG_TRAZABILIDAD = "Trazabilidad";
    public static final String TAG_TRAZABILIDAD_DESC = "Operaciones relacionadas con la trazabilidad de los pedidos";
    public static final String OP_CREAR_TRAZABILIDAD_SUMMARY = "Guardar trazabilidad del pedido";
    public static final String OP_CREAR_TRAZABILIDAD_DESC = "Guarda un evento de trazabilidad sobre el cambio de estado de un pedido";
    public static final String OP_CONSULTAR_TRAZABILIDAD_SUMMARY = "Consultar trazabilidad de un pedido";
    public static final String OP_CONSULTAR_TIEMPO_PEDIDO_SUMMARY = "Consultar el tiempo en que cada pedido inicia y termina";
    public static final String OP_CONSULTAR_TRAZABILIDAD_DESC = "Consulta la trazabilidad histórica de un pedido filtrada por el cliente";
    public static final String OP_CONSULTAR_TIEMPO_PEDIDO_DESC = "mostrar de cada pedido, cual es el tiempo entre que un pedido inicia y un pedido termina";
    public static final String RESPONSE_200_DESC = "Operación exitosa";
    public static final String RESPONSE_201_DESC = "Recurso creado exitosamente";
    public static final String RESPONSE_400_DESC = "Petición inválida";
    public static final String RESPONSE_401_DESC = "No autorizado";
    public static final String RESPONSE_403_DESC = "Acceso prohibido";
    public static final String RESPONSE_404_DESC = "Recurso no encontrado";
    public static final String RESPONSE_500_DESC = "Error interno del servidor";

    public static final String DESC_ID_PEDIDO = "ID del pedido a asignar";
    public static final String DESC_ID_CLIENTE = "ID del cliente a asignar";
}
