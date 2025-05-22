package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "trazabilidad")
public class TrazabilidadDocument {

    @Id
    private String id;


    private Long idPedido;
    private String idCliente;
    private String correoCliente;
    private Date fecha;
    private String estadoAnterior;
    private String estadoNuevo;
    private Long idEmpleado;
    private String correoEmpleado;
}
