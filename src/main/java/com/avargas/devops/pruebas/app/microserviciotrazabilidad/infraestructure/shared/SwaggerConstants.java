package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared;

public class SwaggerConstants
{
    // === Operaciones - Trazabilidad ===
    public static final String TAG_TRAZABILIDAD = "Trazabilidad";
    public static final String TAG_TRAZABILIDAD_DESC = "Registro y consulta del historial de cambios de estado de los pedidos.";

    public static final String OP_CREAR_TRAZABILIDAD_SUMMARY = "Registrar cambio de estado del pedido";
    public static final String OP_CREAR_TRAZABILIDAD_DESC = """
    Se crea una entrada de trazabilidad cada vez que un pedido cambia de estado. 
    Este registro contiene información del cliente, empleado, estados anterior y nuevo, y la fecha del cambio.
    """;

    public static final String RESPONSE_201_DESC = "Recurso creado correctamente.";
    public static final String RESPONSE_400_DESC = "Solicitud inválida o datos incorrectos.";
    public static final String RESPONSE_500_DESC = "Error interno del servidor.";

}
