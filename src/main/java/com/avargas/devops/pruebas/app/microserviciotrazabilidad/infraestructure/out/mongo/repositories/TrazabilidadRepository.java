package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.repositories;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.document.TrazabilidadDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrazabilidadRepository extends MongoRepository<TrazabilidadDocument, Long> {
}
