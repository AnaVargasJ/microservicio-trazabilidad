package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.configuration;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api.IClientAdapter;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api.ITrazaServicePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi.ITrazaPersistencePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.usecase.TrazabilidadUseCase;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.IGenericHttpClient;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.impl.GenericHttpClient;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.client.impl.PedidoAdapter;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.adapter.TrazabilidadMongoAdapter;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.mapper.ITrazabalidadDocumentMapper;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.out.mongo.repositories.TrazabilidadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {


    private final TrazabilidadRepository trazabilidadRepository;
    private final ITrazabalidadDocumentMapper trazabalidadDocumentMapper;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public IGenericHttpClient genericHttpClient(WebClient.Builder builder) {
        return new GenericHttpClient(builder);
    }

    @Bean
    public ITrazaPersistencePort trazaPersistencePort(){
      return   new TrazabilidadMongoAdapter(trazabilidadRepository, trazabalidadDocumentMapper);
    }

    @Bean
    public ITrazaServicePort trazaServicePort(){
        return new  TrazabilidadUseCase(trazaPersistencePort());
    }

    @Bean
    public IClientAdapter clientAdapter(IGenericHttpClient httpClient) {
        return new PedidoAdapter(httpClient);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}
